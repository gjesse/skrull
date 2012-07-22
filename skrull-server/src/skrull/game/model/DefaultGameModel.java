package skrull.game.model;

import skrull.game.factory.IGameFactory.GameType;
import skrull.rmi.server.IClientUpdater;

public class DefaultGameModel extends AbstractGameModel {

	public DefaultGameModel(IPlayer startingPlayer, int gameId, IClientUpdater updater) {
		super(startingPlayer, gameId, GameType.DEFAULT, updater);
	}

}
