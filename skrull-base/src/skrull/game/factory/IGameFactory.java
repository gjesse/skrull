package skrull.game.factory;

import skrull.game.controller.IGameController;
import skrull.game.model.IGameModel;
import skrull.game.model.IPlayer;
import skrull.rmi.server.IClientUpdater;

/**
 * This class is responsible for creating the data structures necessary
 * to represent a particular game. These should include both the {@link IGameController}
 * and the {@lin IGameModel}
 * 
 * @author jesse
 *
 */
public interface IGameFactory {

	/**
	 * All the game types available to the system
	 * @author jesse
	 */
	public enum GameType {
		DEFAULT(true),TIC_TAC_TOE(false),ROCK_PAPER_SCISSORS(false);
		
		private boolean isDefault;

		
		GameType(boolean isDefault){
			this.isDefault = isDefault;
		}

		/**
		 * Denotes whether this game type is default,
		 * as the default game needs special handling at times
		 * 
		 * @return
		 */
		public boolean isDefault() {
			return isDefault;
		}

	}

	public static final int DEFAULT_GAME_ID = 0;

	/**
	 * Should return a single GameController,
	 *  with attendant gameModel and all leaf objects instantiated (except players)
	 *  
	 *  Should handle every type of GameType
	 * @param updater TODO
	 */
	public IGameController setupGame(GameType type, IPlayer startingPlayer, int gameId);

}