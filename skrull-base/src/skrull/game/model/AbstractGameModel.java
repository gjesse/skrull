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
	private IPlayer activeplayer;
	protected IPlayer winner;
	
	private IBoard board;
	private boolean finished;
	
	private long lastMoveTime;
	private IClientAction lastAction;
	
	//private IGameController gameController;
	private IClientUpdater clientUpdater;
	int gameId;
	private GameType gameType;
	private StringBuffer chatBuffer = new StringBuffer(1024);
	private String broadcastMsg;
	private Collection<IGameModel> activeGames;

	private static final Logger  logger = SkrullLogger.getLogger(AbstractGameModel.class);
	
	
	public AbstractGameModel(int gameId, GameType type, IClientUpdater updater) {
		this.gameType = type;
		this.gameId = gameId;
		this.lastMoveTime = System.currentTimeMillis();
		this.clientUpdater = updater;
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
	public final void processMove(IClientAction action){
		// let the subclass do the actual move logic
		doProcessMove(action);
		
		// then do some common move housekeeping
		this.lastMoveTime = System.currentTimeMillis();
		this.activeplayer = action.getPlayer();   // TDO kh - may need to remove this
		this.lastAction = action;
	}
	
	/**
	 * implemented by subclasses
	 * to implement the actual move logic
	 * @param action
	 */
	public abstract void doProcessMove(IClientAction action);
	
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
		return activeplayer;
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

			for (IPlayer p : getPlayers()){
					playerBeingChecked = p;
					clientUpdater.checkPlayerConnected(playerBeingChecked);
					
			}
			
			if ((lastMoveTime + getInactivityTimeout()) < now){
				playerBeingChecked = activeplayer;
				throw new SkrullGameException("Player " + playerBeingChecked.getPlayerId() + " timed out. game over");
			}

		} catch (SkrullException e) {
			
			logger.error(e.getMessage(), e);
			
			if (!this.getGameType().equals(GameType.DEFAULT)){
				this.finished = true;
			}
			
			this.broadcastMsg = e.getMessage();
			this.players.remove(playerBeingChecked);
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
		return Long.parseLong(System.getProperty("inactivity.timeout", String.valueOf( 5 * 60 * 1000 )));
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
	public void quit(IClientAction action) {
		// TODO Auto-generated method stub
	}
	
	protected void addPlayer(IPlayer player){
		this.players.add(player);
	}

	protected IBoard getBoard() {
		return board;
	}

	protected void setBoard(IBoard board) {
		this.board = board;
	}

	protected IPlayer getActiveplayer() {
		return activeplayer;
	}

	protected void setActiveplayer(IPlayer activeplayer) {
		this.activeplayer = activeplayer;
	}

	protected IClientAction getLastAction() {
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
	
}
