package skrull.game.model.tictactoe;

import skrull.SkrullGameException;
import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.AbstractGameModel;
import skrull.game.model.IPlayer;
import skrull.game.view.IClientAction;
import skrull.rmi.server.IClientUpdater;

/**
 * 
 * @author Kyle.Higgins
 *
 * Tic Tax Toe class extending the abstract game model with TTT specific methods.
 *
 */
public class TicTacToe extends AbstractGameModel{


	private static final long serialVersionUID = -8648567625229924677L;

	private boolean gameStop;

	
	
	public TicTacToe(IPlayer startingPlayer, int gameId, IClientUpdater updater) {
		// Instantiate Game
		super(startingPlayer, gameId, GameType.TIC_TAC_TOE, updater, 9, 9, 2);
		
		// Initialize Model Specific Parameters
		gameStop = true;   // block moves until second player joins.
		setDraw(false);
		setMoveCount(0);
		setActiveplayer(startingPlayer);
		startingPlayer.setPlayerToken("X");
		
		super.updateListener();

	}

	@Override

public void joinGame(IClientAction action) throws SkrullGameException {
		if (!needsPlayers()){
			throw new SkrullGameException("Game full");
		}
		
		super.addPlayer(action.getPlayer());
		action.getPlayer().setPlayerToken("O");
		this.setLastAction(action);
		setBroadcastMessage("Player " + action.getPlayer().getPlayerToken() + " joined");

		gameStop = false;	// Allow starting player to start game
		super.updateListener();
		
	}

	@Override
	// KH - Game Logic adapted from Java How to Program, 3rd Edition, Deitel & Deitel chapter 21
	public void doProcessMove(IClientAction action) throws SkrullGameException {
		
		int boardLoc = action.getMove().getMoveIndex();

		
		// match player making move to current player 
		if (!finished){
			if (getActiveplayer().equals(action.getPlayer()) && !gameStop){
				
				// verify move is legal
				if (!isOccupied(boardLoc)){
					
					board.setBoardMove(action.getMove(), boardLoc);
					setMoveCount(getMoveCount() + 1);
					if(getMoveCount() == getMaxMoves())
						finished = true;
				
					// WINNER Check
					if(haveWinner()){
						finished = true;
						winner = getActiveplayer();
						setBroadcastMessage("We have a winner:  " + winner);
					}
					
					// DRAW Check
					if(!haveWinner() && isGameOver()){
						setDraw(true);						
					}
	
					// Announce Move
					setBroadcastMessage("Player " + action.getPlayer().getPlayerToken() + " played on location " + boardLoc);
					
					// Change activePlayer
					setActiveplayer(getLastAction().getPlayer());
					this.setLastAction(action);

				}
				// Invalid move by correct player.
				else throw new SkrullGameException("Invalid Move...");

			}
			// Not valid player
			else throw new SkrullGameException("Please wait your turn."); 
		}
		// Game Over
		else throw new SkrullGameException("Game is Over, please return to main menu.");
		
		// Duh - Update the Listener
		updateListener();
	}
		
	private boolean haveWinner() {
		// TODO:  take this line out -- System.out.println("in the have winner function!");
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
			
			IPlayer p1 = board.getBoardLoc(w[0]).getPlayer();
			IPlayer p2 = board.getBoardLoc(w[1]).getPlayer();

					IPlayer p3 = board.getBoardLoc(w[2]).getPlayer();

            // no null reference chance, check for matches indicating a winner
			if (p1.equals(p2) && p2.equals(p3)){
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
