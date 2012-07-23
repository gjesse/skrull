package skrull.game.model;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import skrull.game.controller.IGameController;
import skrull.game.factory.IGameFactory.GameType;
import skrull.game.view.IClientAction;
import skrull.rmi.server.IClientUpdater;

/**
 * General logic common to all games.
 * @author jhodges
 *
 */
public abstract class AbstractGameModel implements IGameModel {
	private Set<IPlayer> players = new CopyOnWriteArraySet<IPlayer>();
	private IBoard board;
	private IPlayer activeplayer;
	private IPlayer winner;
	private boolean finished;
	private Date lastActivity;
	private IClientAction lastAction;
	private IGameController gameController;
	private IClientUpdater clientUpdater;
	int gameId;
	private GameType gameType;
	private StringBuffer chatBuffer = new StringBuffer(1024);

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
		this.chatBuffer.append(action.getActionMessage());
		updateListener();
	}

	/* (non-Javadoc)
	 * @see skrull.game.model.IGameModel#joinGame(skrull.game.view.ClientAction)
	 */
	@Override
	public abstract void joinGame(IClientAction action);

	/* (non-Javadoc)
	 * @see skrull.game.model.IGameModel#processMove(skrull.game.view.ClientAction)
	 */
	@Override
	public abstract void processMove(IClientAction action);
	
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
		throw new UnsupportedOperationException();
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
}