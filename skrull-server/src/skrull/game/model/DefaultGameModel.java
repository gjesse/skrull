package skrull.game.model;

import skrull.game.factory.IGameFactory.GameType;
import skrull.game.view.IClientAction;
import skrull.rmi.server.IClientUpdater;

public class DefaultGameModel extends AbstractGameModel {


	private static final long serialVersionUID = 4248612423826766248L;

	public DefaultGameModel(int gameId, IClientUpdater updater) {
		super(gameId, GameType.DEFAULT, updater);
	}

	@Override
	public void joinGame(IClientAction action) {
		// for the default game, no validation is needed. 
		// anyone can join
		super.addPlayer(action.getPlayer());
		super.updateListener();
	}

	@Override
	public void doProcessMove(IClientAction action) {
		throw new UnsupportedOperationException("I'm not expecting a move here");
	}
	
	/**
	 * default game should never timeout
	 */
	@Override
	protected long getInactivityTimeout() {
		return Long.MAX_VALUE;
	}


}
