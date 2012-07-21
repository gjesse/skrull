package skrull.game.factory;

import skrull.game.controller.IGameController;
import skrull.game.model.IPlayer;

public interface IGameFactory {

	/**
	 * Should return a single GameController, with attendant gameModel and all leaf objects instantiated (except for additional players)
	 */
	public IGameController setupGame(String aGameType,
			IPlayer startingPlayer);

	public String[] listAvailableGames();

}