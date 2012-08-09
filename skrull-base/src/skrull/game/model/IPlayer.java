package skrull.game.model;

import java.io.Serializable;
import java.util.UUID;

/**
 * Represents a single player. Players are identified by a randomly generated
 * UUID.
 * 
 * @author jesse
 *
 */
public interface IPlayer extends Serializable {

	/** 
	 * unique identifier for this player / client. This is different for each
	 * player and never changes for that player.
	 * 
	 * @return
	 */
	public UUID getPlayerId();
	
	/**
	 * Token identifier (eg "X","O") for the player in the current game. This is set by the model and may change
	 * as the player plays different games.
	 * @return
	 */
	public String getPlayerToken();

	/** 
	 * @param String token for this player as set by game model
	 * 
	 */
	public void setPlayerToken(String c);

}
