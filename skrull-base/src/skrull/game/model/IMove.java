package skrull.game.model;

import java.io.Serializable;

/** 
 * Move Interface
 * 
 * Each Move contains players unique ID and the index of their chose move on the board.
 * Interface is extended by Move in skrull.game.factory 
 * 
 * */
public interface IMove extends Serializable{

	/** 
	 * @return index to the board location chosen by player for their move.
	 * 
	 */
	int getMoveIndex();

	/** 
	 * @return unique identifier for the player assocatiated with this move.
	 * 
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
