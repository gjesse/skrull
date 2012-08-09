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
	 * the index location on the board where the move is mad
	 * @return
	 */
	int getMoveIndex();

	/**
	 * the {@link IPlayer} that made the move
	 * @return
	 */
	IPlayer getPlayer();

}
