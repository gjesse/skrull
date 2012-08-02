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
	public IGameController setupGame(GameType type, int gameId) {
		
		switch(type)
		{
		case DEFAULT:
			return setupDefaultGame(gameId);
		
		case TIC_TAC_TOE:
			return setupTicTacToeGame(gameId);
			
		case ROCK_PAPER_SCISSORS:
			return setupRockPaperScissorsGame(gameId);

		default:
			throw new UnsupportedOperationException("unexpected game type received");
	
		}
		
	}

	// default game is setup before any players connect
	private IGameController setupDefaultGame(int gameId) {
		IGameModel model = new DefaultGameModel(gameId, updater);
		IGameController defaultController = new DefaultGameController(model);
		System.out.println("return Default Gamecontroller...");
		return defaultController;
	}
	
	// TIC_TAC_TOE
	private IGameController setupTicTacToeGame(int gameId) {
		IGameModel model = new TicTacToe(gameId, updater);
		IGameController GameController = new TicTacToeController(model);
		System.out.println("returning TTT GameController...");
		return GameController;
	}
	
	// ROCK_PAPER_SCISSORS
	private IGameController setupRockPaperScissorsGame(int gameId) {
		IGameModel model = new RockPaperScissors(gameId, updater);
		IGameController GameController = new RockPaperScissorsController(model);
		System.out.println("returning RPS GameController...");
		return GameController;
	}
	
	public IClientUpdater getUpdater() {
		return updater;
	}

}