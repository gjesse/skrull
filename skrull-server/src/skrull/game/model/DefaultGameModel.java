package skrull.game.model;

import skrull.game.factory.IGameFactory.GameType;

public class DefaultGameModel extends AbstractGameModel {

	public DefaultGameModel(IPlayer startingPlayer, int gameId) {
		super(startingPlayer, gameId, GameType.DEFAULT);
	}

}
