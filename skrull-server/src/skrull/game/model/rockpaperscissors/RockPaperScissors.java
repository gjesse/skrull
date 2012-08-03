/**
 * 
 * @author kyle
 *
 */

package skrull.game.model.rockpaperscissors;

import skrull.SkrullGameException;
import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.AbstractGameModel;
import skrull.game.model.IPlayer;
import skrull.game.view.IClientAction;
import skrull.rmi.server.IClientUpdater;


public class RockPaperScissors extends AbstractGameModel{

	private static final long serialVersionUID = 1870980829045671398L;

	public RockPaperScissors(IPlayer startingPlayer, int gameId, IClientUpdater updater) {
		super(startingPlayer, gameId, GameType.ROCK_PAPER_SCISSORS, updater, 3);
	}

	@Override
	public void joinGame(IClientAction action) throws SkrullGameException {
		if (getPlayers().size() >= 2){
			throw new SkrullGameException("game full");
		}
		super.addPlayer(action.getPlayer());
		super.updateListener();

		
	}

	@Override
	public void doProcessMove(IClientAction action) {
	
		// TODO Auto-generated method stub
		
	}

}
