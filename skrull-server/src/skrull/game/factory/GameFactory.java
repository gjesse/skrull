package skrull.game.factory;

import skrull.game.controller.DefaultGameController;
import skrull.game.controller.IGameController;
import skrull.game.controller.IServerController;
import skrull.game.model.DefaultGameModel;
import skrull.game.model.IGameModel;
import skrull.game.model.IPlayer;
import skrull.game.model.tictactoe.TicTacToe;

public class GameFactory implements IGameFactory {

	/* (non-Javadoc)
	 * @see skrull.game.factory.IGameFactory#setupGame(java.lang.String, skrull.game.model.IPlayer)
	 */
	@Override
	public IGameController setupGame(GameType type, IPlayer startingPlayer, int gameId) {
		
		switch(type)
		{
		case DEFAULT:
			return setupDefaultGame(startingPlayer, gameId);
		
		case TIC_TAC_TOE:
		case ROCK_PAPER_SCISSORS:
			throw new UnsupportedOperationException("Game type " + type + " not defined yet.");

		default:
			throw new UnsupportedOperationException("unexpected game type received");
	
		}
		
	}

	private IGameController setupDefaultGame(IPlayer startingPlayer, int gameId) {
		IGameModel model = new DefaultGameModel(startingPlayer, gameId);
		IGameController defaultController = new DefaultGameController(model);
		return defaultController;
	}

	/* (non-Javadoc)
	 * @see skrull.game.factory.IGameFactory#listGames()
	 */
	@Deprecated /* this probably isn't needed now that game types are an enum */
	@Override
	public String[] listAvailableGames() {
	
		throw new UnsupportedOperationException();
	}
}