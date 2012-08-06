/**
 * 
 * @author kyle
 *
 */
package skrull.game.model.tictactoe;

import java.util.Iterator;

import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.AbstractGameModel;
import skrull.game.model.IBoard;
import skrull.game.model.IMove;
import skrull.game.model.IPlayer;
import skrull.game.view.IClientAction;
import skrull.rmi.server.IClientUpdater;


public class TicTacToe extends AbstractGameModel{


	private static final long serialVersionUID = -8648567625229924677L;

	private boolean gameStop;
	private boolean gameFull;

	
	
	public TicTacToe(IPlayer startingPlayer, int gameId, IClientUpdater updater) {
		// Instantiate Game
		super(startingPlayer, gameId, GameType.TIC_TAC_TOE, updater, 9);
		
		// Initialize Model Specific Parameters
		gameStop = true;   // block moves until second player joins.
		gameFull = false;
		setMoveCount(0);
		setActiveplayer(startingPlayer);
		startingPlayer.setPlayerToken('X');
		
		super.updateListener();

	}

	@Override
	public void joinGame(IClientAction action) {
		
		// TODO modify to use needsPLayers instead of gameFull
		if(!gameFull){
			super.addPlayer(action.getPlayer());
			action.getPlayer().setPlayerToken('O');
			setLastAction(action);
			gameFull = true;
			gameStop = false;	// Allow starting player to start game
		}
		else System.out.println("Game is Full...");
				
		super.updateListener();
		
	}

	@Override
	// KH - Game Logic adapted from Java How to Program, 3rd Edition, Deitel & Deitel chapter 21
	public void doProcessMove(IClientAction action) {
		
		int boardLoc = action.getMove().getMoveIndex();

		
		// match player making move to current player 
		
		if (getActiveplayer().equals(action.getPlayer()) && !gameStop){
			
			// verify move is legal
			if (!isOccupied(boardLoc)){
				
				board.setBoard(action.getMove(), boardLoc);
				setMoveCount(getMoveCount() + 1);
				if(getMoveCount() == getMaxMoves())
					finished = true;
			
				// WINNER Check
				if(haveWinner()){
					winner = getActiveplayer();
					// TODO set isFinished flag
				}
				
				// DRAW Check
				if(!haveWinner() && isGameOver()){
					// TODO make active player impossible.
					// TODO set draw flag
				}
								
				// TODO change activePlayer

				setActiveplayer(getLastAction().getPlayer());
				
				// TODO announce what the move was.
				System.out.println("location: ");
				System.out.println(boardLoc);
				System.out.println(" player: ");
				System.out.println(getCurrentPlayer());
				System.out.println("/n");
			}
			// Invalid move by correct player.
			// TODO send to chat window.
			else System.out.println("Invalid Move...");
		
		}
		// Not valid player
		// TODO send to chat window instead
		else System.out.println("Please wait your turn"); 
		
		// TODO notify other player of move
		updateListener();
	}
		
	private boolean haveWinner() {
		
		boolean winnerDetected = false;
		
		// winning combinations  rows, columns, diagonals
		int[][] winnerCombinations = new int[][]{
                {0,1,2},
                {3,4,5},
                {6,7,8},
                {0,3,6},
                {1,4,7},
                {2,5,8},
                {0,4,8},
                {2,4,6}};

		// loop thru arrays and test for null (blank move), then test to see if they have the same UUID
		for (int[] w : winnerCombinations){
               
			// check for nulls
			if (board.getBoardLoc(w[0]) == null || board.getBoardLoc(w[1]) == null || board.getBoardLoc(w[2]) == null)
				continue;
			
            // no null reference chance, check for matches indicating a winner
			if (board.getBoardLoc(w[0]).equals(board.getBoardLoc(w[1])) && board.getBoardLoc(w[1]).equals(board.getBoardLoc(w[2]))){
                winnerDetected = true;
                break;
			}                      
		}
		return winnerDetected;
	}
	

	public boolean isOccupied(int m){
		
		if (board.getBoardLoc(m) == null)
			return false;
		else
			return true;
	}
}
