package skrull.game.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import skrull.SkrullException;
import skrull.SkrullGameException;
import skrull.game.controller.IGameController;
import skrull.game.factory.IGameFactory.GameType;
import skrull.game.view.IClientAction;
import skrull.rmi.SkrullRMIException;
import skrull.rmi.server.IClientUpdater;

/**
 * General logic common to all games.
 * @author jhodges
 *
 */
public abstract class AbstractGameModel implements IGameModel {

	private static final long serialVersionUID = -5970164504419304864L;
	private Set<IPlayer> players = new CopyOnWriteArraySet<IPlayer>();
	private IBoard board;
	private IPlayer activeplayer;
	private IPlayer winner;
	private boolean finished;
	private long lastMoveTime;
	private IClientAction lastAction;
	//private IGameController gameController;
	private IClientUpdater clientUpdater;
	int gameId;
	private GameType gameType;
	private StringBuffer chatBuffer = new StringBuffer(1024);
	private String broadcastMsg;

	public AbstractGameModel(IPlayer startingPlayer, int gameId, GameType type, IClientUpdater updater) {
		this(gameId, type, updater);
		this.players.add(startingPlayer);

	}
	
	public AbstractGameModel(int gameId, GameType type, IClientUpdater updater) {
		this.gameType = type;
		this.gameId = gameId;
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
	public abstract void joinGame(IClientAction action);

	/* (non-Javadoc)
	 * @see skrull.game.model.IGameModel#processMove(skrull.game.view.ClientAction)
	 */
	@Override
	public final void processMove(IClientAction action){
		// let the subclass do the actual move logic
		doProcessMove(action);
		
		// then do some common move housekeeping
		this.lastMoveTime = System.currentTimeMillis();
		this.activeplayer = action.getPlayer();
		this.lastAction = action;
	}
	
	/**
	 * implemented by subclasses
	 * to implement the actual move logic
	 * @param action
	 */
	protected abstract void doProcessMove(IClientAction action);
	
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
	public Collection<IPlayer> getPlayers(){
		// return Collections.unmodifiableCollection(players);
		return players;
	}

	/* (non-Javadoc)
	 * @see skrull.game.model.IGameModel#checkActivity()
	 */
	@Override
	public void checkActivity() {

		
		final long now = System.currentTimeMillis();

		try {

			for (IPlayer p : getPlayers()){
					clientUpdater.checkPlayerConnected(p);
					
			}
			
			if ((lastMoveTime + getInactivityTimeout()) < now){
				throw new SkrullGameException("Player " + activeplayer.getPlayerId() + " timed out. game over");
			}

		} catch (SkrullException e) {
			// TODO: log this
			e.printStackTrace();
			this.finished = true;
			this.broadcastMsg = e.getMessage();
			clientUpdater.modelChanged(this);
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
		clientUpdater.modelChanged(this);
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
	
}