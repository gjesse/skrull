package skrull.game.model;

import skrull.game.factory.IGameFactory.GameType;
import skrull.game.view.IClientAction;
import skrull.rmi.server.IClientUpdater;

public class DefaultGameModel extends AbstractGameModel {

	public DefaultGameModel(IPlayer startingPlayer, int gameId, IClientUpdater updater) {
		super(startingPlayer, gameId, GameType.DEFAULT, updater);
	}

	@Override
	public void joinGame(IClientAction action) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processMove(IClientAction action) {
		// TODO Auto-generated method stub
		
	}

}
