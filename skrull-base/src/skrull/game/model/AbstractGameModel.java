package skrull.game.model;

import java.util.Date;

import skrull.game.controller.IGameController;
import skrull.game.view.IClientAction;
import skrull.rmi.server.IClientUpdater;

/**
 * General logic common to all games.
 * @author jhodges
 *
 */
public class AbstractGameModel implements IGameModel {
	private IPlayer[] players;
	private IBoard board;
	private IPlayer activeplayer;
	private IPlayer winner;
	private boolean finished;
	private Date lastActivity;
	private IClientAction lastAction;
	private IGameController gameController;
	private IClientUpdater clientUpdater;

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
}