package skrull.game.model;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import skrull.game.controller.IGameController;
import skrull.game.factory.IGameFactory.GameType;
import skrull.game.view.IClientAction;
import skrull.rmi.server.IClientUpdater;

/**
 * General logic common to all games.
 * @author jhodges
 *
 */
public class AbstractGameModel implements IGameModel {
	private List<IPlayer> players = new CopyOnWriteArrayList<IPlayer>();
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

	public AbstractGameModel(IPlayer startingPlayer, int gameId, GameType type, IClientUpdater updater) {
		this.gameType = type;
		this.gameId = gameId;
		this.players.add(startingPlayer);
		this.clientUpdater = updater;
	}

	/* (non-Javadoc)
	 * @see skrull.game.model.IGameModel#chatUpdate(skrull.game.view.ClientAction)
	 */
	@Override
	public void chatUpdate(IClientAction aAction) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see skrull.game.model.IGameModel#joinGame(skrull.game.view.ClientAction)
	 */
	@Override
	public void joinGame(IClientAction aAction) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see skrull.game.model.IGameModel#processMove(skrull.game.view.ClientAction)
	 */
	@Override
	public void processMove(IClientAction aAction) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see skrull.game.model.IGameModel#isGameOver()
	 */
	@Override
	public boolean isGameOver() {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see skrull.game.model.IGameModel#getWinner()
	 */
	@Override
	public IPlayer getWinner() {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see skrull.game.model.IGameModel#updateListener(skrull.game.model.IGameModel)
	 */
	@Override
	public void updateListener(IGameModel aGameModel) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see skrull.game.model.IGameModel#checkActivity()
	 */
	@Override
	public void checkActivity() {
		throw new UnsupportedOperationException();
	}

	private void validateMove(IMove aMove) {
		throw new UnsupportedOperationException();
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
}