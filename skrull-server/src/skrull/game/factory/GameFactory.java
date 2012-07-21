package skrull.game.factory;

import skrull.game.controller.IGameController;
import skrull.game.controller.IServerController;
import skrull.game.model.IPlayer;
import skrull.game.model.tictactoe.TicTacToe;

public class GameFactory implements IGameFactory {
	private String[] availableGames;
	public IServerController _unnamed_ServerConttroller_;
	public TicTacToe _unnamed_TicTacToe_;

	/* (non-Javadoc)
	 * @see skrull.game.factory.IGameFactory#setupGame(java.lang.String, skrull.game.model.IPlayer)
	 */
	@Override
	public IGameController setupGame(GameType type, IPlayer startingPlayer) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see skrull.game.factory.IGameFactory#listGames()
	 */
	@Override
	public String[] listAvailableGames() {
		throw new UnsupportedOperationException();
	}
}