/**
 * 
 * @author kyle
 *
 */

package skrull.game.model.rockpaperscissors;

import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.AbstractGameModel;
import skrull.game.model.IPlayer;
import skrull.game.view.IClientAction;
import skrull.rmi.server.IClientUpdater;


public class RockPaperScissors extends AbstractGameModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1870980829045671398L;

	public RockPaperScissors(IPlayer startingPlayer, int gameId, IClientUpdater updater) {
		super(startingPlayer, gameId, GameType.ROCK_PAPER_SCISSORS, updater);
	}

	@Override
	public void joinGame(IClientAction action) {
		super.addPlayer(action.getPlayer());
		super.updateListener();
		
	}

	@Override
	public void processMove(IClientAction action) {
		// TODO Auto-generated method stub
		
	}

}