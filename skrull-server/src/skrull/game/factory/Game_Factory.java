package skrull.game.factory;

import skrull.game.controller.IGameController;
import skrull.game.controller.IServerController;
import skrull.game.model.IPlayer;
import skrull.game.model.tictactoe.TicTacToe;

public class Game_Factory {
	private String[] availableGames;
	public IServerController _unnamed_ServerConttroller_;
	public TicTacToe _unnamed_TicTacToe_;

	/**
	 * Should return a single GameController, with attendant gameModel and all leaf objects instantiated (except for additional players)
	 */
	public IGameController SetupGame(String aGameType, IPlayer startingPlayer) {
		throw new UnsupportedOperationException();
	}

	public String ListGames() {
		throw new UnsupportedOperationException();
	}
}