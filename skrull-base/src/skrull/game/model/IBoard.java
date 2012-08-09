package skrull.game.model;

import java.io.Serializable;

/**
 * General Board model.
 * 
 * The board is represented as an array of {@link IMove}
 * @author jesse
 *
 */
public interface IBoard extends Serializable{
	
	/**
	 * Set the board location at the specified location to the provided move
	 * 
	 * TODO: (jh) this should maybe throw an exception if the board loc is already occupied
	 * 
	 * @param m
	 * @param loc
	 * @return true if successful, false if the location was already occupied
	 */
	public abstract boolean setBoardMove(IMove m, int loc);
	
	/**
	 * Provides the entire array of moves indexed by location. Some or all locations
	 * may be null.
	 * @return 
	 */
	public abstract IMove[] getBoardMoves();
	
	/**
	 * Return the {@link IMove} at the specified index.
	 * Value may be null.
	 * 
	 * @param m
	 * @return
	 */
	public abstract IMove getBoardLoc(int m);
	
}