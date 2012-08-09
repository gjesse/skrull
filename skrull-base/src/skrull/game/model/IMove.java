package skrull.game.model;

import java.io.Serializable;

/**
 * Represents a single move
 * 
 * @author jesse
 *
 */
public interface IMove extends Serializable{


	/** 
	 * @return index to the board location chosen by player for their move.
	 * 
	 */
	int getMoveIndex();

	/**
	 * the {@link IPlayer} that made the move
	 * @return
	 */
	IPlayer getPlayer();

	/**
	 * Stores the location on the board for the move
	 * @param m
	 */
	void setMoveIndex(int m);

	/**
	 * Stores the player UUID that made the move.
	 * @param player
	 */
	void setPlayer(IPlayer player);

}
