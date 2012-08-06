/**
 * 
 * @author kyle
 *
 */

package skrull.game.model.rockpaperscissors;

import skrull.SkrullGameException;
import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.AbstractGameModel;
import skrull.game.model.IMove;
import skrull.game.model.IPlayer;
import skrull.game.view.IClientAction;
import skrull.rmi.server.IClientUpdater;


public class RockPaperScissors extends AbstractGameModel{

	private static final long serialVersionUID = 1870980829045671398L;
	private IPlayer myPlayers[];
	private int myRPSmove[];
	private boolean gameStop;
	
	
	public RockPaperScissors(IPlayer startingPlayer, int gameId, IClientUpdater updater) {
		super(startingPlayer, gameId, GameType.ROCK_PAPER_SCISSORS, updater, 3, 2);
		gameStop = true;   // block moves until second player joins.
		setMoveCount(0);
		// TODO: handle this in the abstract superclass
		myPlayers = new IPlayer[2];
		myRPSmove = new int[2];
		myPlayers[0] = startingPlayer;
		startingPlayer.setPlayerToken('1');
		
	}

	@Override
	public void joinGame(IClientAction action) throws SkrullGameException {
		if (!needsPlayers()){
			throw new SkrullGameException("game full");
		}
		super.addPlayer(action.getPlayer());
		
		myPlayers[1] = action.getPlayer();
		action.getPlayer().setPlayerToken('2');
		gameStop = false;
		
		super.updateListener();

		
	}

	@Override
	public void doProcessMove(IClientAction action) {
	
		// TODO Auto-generated method stub
		if (myPlayers[getCurrentPlayer()].equals(action.getPlayer()) && !gameStop){
			
			myRPSmove[0] = action.getMove().getMoveIndex();
			
			setMoveCount(getMoveCount() + 1);
			if (getMoveCount() == getMaxMoves())
				finished = true;
			
			// WINNER Check
			if(haveWinner()){
				if((myRPSmove[0] == 0 && myRPSmove[1] == 2) || (myRPSmove[0] == 1 && myRPSmove[1] == 0) || (myRPSmove[0] == 2 && myRPSmove[1] == 1))
					winner = myPlayers[0];
				else 
					winner = myPlayers[1];
			}
			
			// DRAW Check
			if(!haveWinner() && isGameOver()){
				// TODO make active player impossible.
				System.out.println("RPS Game is over.  Its a draw.");
			}
							
			// TODO change activePlayer
			setCurrentPlayer((getCurrentPlayer() + 1) % 2);
			setActiveplayer(myPlayers[getCurrentPlayer()]);
			
			// TODO announce what the move was.
			
		}
		// Not valid player
		// TODO send to chat window instead
		else System.out.println("Please wait your turn"); 
	
	// TODO notify other player of move
	updateListener();
	}
	
	private boolean haveWinner() {
		
		boolean winnerDetected = false;
		if (myRPSmove[0] != myRPSmove[1] && getMoveCount()==getMaxMoves())
			winnerDetected =true;
		return winnerDetected;
	}
	
	// KH - Adapted from Java How to Program, 3rd Edition, Deitel & Deitel chapter 21
	public boolean isOccupied(int m){
		
		if (board.getBoardLoc(m).equals(null))
			return false;
		else
			return true;
	}

}
