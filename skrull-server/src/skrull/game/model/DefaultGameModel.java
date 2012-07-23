package skrull.game.model;

import skrull.game.factory.IGameFactory.GameType;
import skrull.game.view.IClientAction;
import skrull.rmi.server.IClientUpdater;

public class DefaultGameModel extends AbstractGameModel {

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
	public void processMove(IClientAction action) {
		// TODO Auto-generated method stub
		
	}

}
