package skrull.game.model;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import org.apache.log4j.Logger;

import skrull.SkrullException;
import skrull.SkrullGameException;
import skrull.game.factory.IGameFactory.GameType;
import skrull.game.view.IClientAction;
import skrull.rmi.SkrullRMIException;
import skrull.rmi.server.IClientUpdater;
import skrull.util.logging.SkrullLogger;

/**
 * General logic common to all games.
 * @author jhodges
 *
 */
public abstract class AbstractGameModel implements IGameModel {

	private static final long serialVersionUID = -5970164504419304864L;
	private Set<IPlayer> players = new CopyOnWriteArraySet<IPlayer>();
	
	private IPlayer activePlayer;	// The player attempting to update the board
	protected IPlayer winner;		//	
	
	private int moveCount;			// move
	private int maxMoves;			// max moves, used with moveCount to determine draws

	
	protected IBoard board;
	protected boolean finished;
	protected boolean draw;
	
	private long lastMoveTime;
	private IClientAction lastAction;
	
	//private IGameController gameController;
	private IClientUpdater clientUpdater;
	int gameId;
	private GameType gameType;
	private StringBuffer chatBuffer = new StringBuffer(1024);
	private String broadcastMsg;
	private Collection<IGameModel> activeGames;
	private int playersRequired;

	private static final Logger  logger = SkrullLogger.getLogger(AbstractGameModel.class);
	
	public AbstractGameModel(IPlayer startingPlayer, int gameId, GameType type, IClientUpdater updater, int boardSize, int maxMoves, int playersRequired) {
		this(gameId, type, updater);
		this.players.add(startingPlayer);
		this.board = new Board(boardSize);
		this.maxMoves = maxMoves;
		this.playersRequired = playersRequired;
	}
	
	public AbstractGameModel(int gameId, GameType type, IClientUpdater updater) {
		this.gameType = type;
		this.gameId = gameId;
		this.lastMoveTime = System.currentTimeMillis();
		this.clientUpdater = updater;
	}
	
	@Override
	public boolean needsPlayers() {
		return getPlayers().size() < getPlayersRequired();
	}

	/* (non-Javadoc)
	 * @see skrull.game.model.IGameModel#chatUpdate(skrull.game.view.ClientAction)
	 */
	@Override
	public void chatUpdate(IClientAction action) {
		this.chatBuffer.append(action.getActionMessage() + "\n");
		updateListener();
	}

	@Override
	public String getChatContents() {
		return chatBuffer.toString();
	};
	
	/* (non-Javadoc)
	 * @see skrull.game.model.IGameModel#joinGame(skrull.game.view.ClientAction)
	 */
	@Override
	public abstract void joinGame(IClientAction action) throws SkrullGameException;

	/* (non-Javadoc)
	 * @see skrull.game.model.IGameModel#processMove(skrull.game.view.ClientAction)
	 */
	@Override
	public final void processMove(IClientAction action) throws SkrullGameException{
		// let the subclass do the actual move logic
		doProcessMove(action);
		
		// then do some common move housekeeping
		this.lastMoveTime = System.currentTimeMillis();
		// this.activeplayer = action.getPlayer();   // TDO kh - may need to remove this
		// this.lastAction = action;
	}
	
	/**
	 * implemented by subclasses
	 * to implement the actual move logic
	 * @param action
	 */
	public abstract void doProcessMove(IClientAction action) throws SkrullGameException;
	
	/* (non-Javadoc)
	 * @see skrull.game.model.IGameModel#isGameOver()
	 */
	@Override
	public boolean isGameOver() {
		return finished;
	}

	/* (non-Javadoc)
	 * @see skrull.game.model.IGameModel#getWinner()
	 */
	@Override
	public IPlayer getWinner() {
		return winner;
	}
	
	@Override
	public IPlayer getActivePlayer() {
		return activePlayer;
	}

	@Override
	public Collection<IPlayer> getPlayers(){
		return players;
	}

	/* (non-Javadoc)
	 * @see skrull.game.model.IGameModel#checkActivity()
	 */
	@Override
	public void checkActivity() {

		
		IPlayer playerBeingChecked = null;
		
		final long now = System.currentTimeMillis();

		try {

			for (IPlayer p : this.players){
					playerBeingChecked = p;
					clientUpdater.checkPlayerConnected(playerBeingChecked);
					
			}
						
			if ((lastMoveTime + getInactivityTimeout()) < now && activePlayer != null){
				playerBeingChecked = activePlayer;
				activePlayer = null;
				throw new SkrullGameException("Player " + playerBeingChecked.getPlayerId() + " timed out. game over");
			}

		} catch (SkrullException e) {
			
			logger.error(e.getMessage(), e);
			
			if (!this.getGameType().equals(GameType.DEFAULT)){
				this.finished = true;
			}
			
			this.broadcastMsg = e.getMessage();
			if (!this.players.remove(playerBeingChecked)){
				logger.error("cant't remove " + playerBeingChecked + ". Is player in collection? " + players.contains(playerBeingChecked));
				
			}

			try {
				clientUpdater.modelChanged(this);
			} catch (SkrullRMIException e1) {
				logger.error("rmi issue encountered", e1);
			}
		}

		
		
	}


	/**
	 * Must return the value, in milliseconds, in which the active player
	 * has to make a move. If no move is made within this time, the game is forfeit
	 * 
	 * Subclasses should override this if they need something besides
	 * the default.
	 * 
	 * @return number of milliseconds to timeout on
	 */
	protected long getInactivityTimeout(){
		final Long t = Long.parseLong(System.getProperty("inactivity.timeout", String.valueOf( 25 * 60 * 1000 )));
		return t;
	}
	
	public void updateListener() {
		
		try {
			clientUpdater.modelChanged(this);
		} catch (SkrullRMIException e) {
			logger.error("Problem encountered notifying listener, re-checking activity", e);
			checkActivity();
		}
	}

	@Override
	public GameType getGameType() {
		return gameType;
	}

	@Override
	public int getGameId() {
		return gameId;
	}

	@Override
	public void createGame(IClientAction action) {
		// this should have been setup already
		// as in this player should already be in the game
		// but since we are using a set it shouldn't matter
		players.add(action.getPlayer());
		setLastAction(action);
		setBroadcastMessage("Waiting for more players...");
		try {
			clientUpdater.modelChanged(this);
		} catch (SkrullRMIException e1) {
			logger.error("rmi issue encountered", e1);
		}
	}
	
	/**
	 * Somebody quit, so the 
	 * game is O-Ver
	 */
	@Override
	public void quit(IClientAction action) {
		this.finished = true;
		setBroadcastMessage("Player " + action.getPlayer() + " quit. Game over");
		players.remove(action.getPlayer());
		updateListener();

		
	}
	
	protected void addPlayer(IPlayer player){
		this.players.add(player);
	}

	public IBoard getBoard() {
		return board;
	}

	protected void setBoard(IBoard board) {
		this.board = board;
	}
	protected IPlayer getActiveplayer() {
		return activePlayer;
	}

	protected void setActiveplayer(IPlayer activeplayer) {
		this.activePlayer = activeplayer;
	}

	public IClientAction getLastAction() {
		return lastAction;
	}

	protected void setLastAction(IClientAction lastAction) {
		this.lastAction = lastAction;
	}

	@Override
	public String getBroadcastMessage() {
		return this.broadcastMsg;
	}
	
	@Override
	public void setActiveGames(Collection<IGameModel> activeGames) {
		this.activeGames = activeGames;
	}

	@Override
	public Collection<IGameModel> getActiveGames() {
		return activeGames;
	}

	@Override
	public int getMoveCount() {
		return moveCount;
	}

	protected void setMoveCount(int moveCount) {
		this.moveCount = moveCount;
	}

	@Override
	public int getMaxMoves() {
		return maxMoves;
	}

	protected void setMaxMoves(int maxMoves) {
		this.maxMoves = maxMoves;
	}

	protected int getPlayersRequired() {
		return playersRequired;
	}

	protected void setBroadcastMessage(String msg){
		this.broadcastMsg = msg;
	}
	
	@Override
	public boolean isDraw() {
		return draw;
	}
	
	protected void setDraw(boolean draw) {
		this.draw = draw;
	}
}
