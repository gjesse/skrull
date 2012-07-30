package skrull.game.factory;

import skrull.game.controller.*;
import skrull.game.model.DefaultGameModel;
import skrull.game.model.IGameModel;
import skrull.game.model.IPlayer;
import skrull.game.model.rockpaperscissors.RockPaperScissors;
import skrull.game.model.tictactoe.TicTacToe;
import skrull.rmi.server.IClientUpdater;

public class GameFactory implements IGameFactory {

	private IClientUpdater updater;
	
	public GameFactory(IClientUpdater updater) {
		this.updater = updater;
	}

	/* (non-Javadoc)
	 * @see skrull.game.factory.IGameFactory#setupGame(java.lang.String, skrull.game.model.IPlayer)
	 */
	@Override
	public IGameController setupGame(GameType type, IPlayer startingPlayer, int gameId) {
		
		switch(type)
		{
		case DEFAULT:
			return setupDefaultGame(gameId);
		
		case TIC_TAC_TOE:
			return setupTicTacToeGame(startingPlayer, gameId);
			
		case ROCK_PAPER_SCISSORS:
			return setupRockPaperScissorsGame(startingPlayer, gameId);

		default:
			throw new UnsupportedOperationException("unexpected game type received");
	
		}
		
	}

	// default game is setup before any players connect
	private IGameController setupDefaultGame(int gameId) {
		IGameModel model = new DefaultGameModel(gameId, updater);
		IGameController defaultController = new DefaultGameController(model);
		return defaultController;
	}
	
	// TIC_TAC_TOE
	private IGameController setupTicTacToeGame(IPlayer startingPlayer, int gameId) {
		IGameModel model = new TicTacToe(startingPlayer, gameId, updater);
		IGameController GameController = new TicTacToeController(model);
		return GameController;
	}
	
	// ROCK_PAPER_SCISSORS
	private IGameController setupRockPaperScissorsGame(IPlayer startingPlayer, int gameId) {
		IGameModel model = new RockPaperScissors(startingPlayer, gameId, updater);
		IGameController GameController = new RockPaperScissorsController(model);
		return GameController;
	}
	

	/* (non-Javadoc)
	 * @see skrull.game.factory.IGameFactory#listGames()
	 */
	@Deprecated /* this probably isn't needed now that game types are an enum */
	@Override
	public String[] listAvailableGames() {
	
		throw new UnsupportedOperationException();
	}

	public IClientUpdater getUpdater() {
		return updater;
	}

}