package skrull.game.model;

import java.io.Serializable;
import java.util.UUID;
/** 
 * Player Interface
 * 
 * Player Interface stores the players unique ID and Token for a particular game
 * Interface is extended by Player in skrull.client 
 * 
 * */
public interface IPlayer extends Serializable {

	/** 
	 * @return unique identifier for this player / client
	 * 
	 */
	public UUID getPlayerId();
	
	/** 
	 * @return token for this player / client
	 * 
	 */
	public String getPlayerToken();
	
	/** 
	 * @param String token for this player as set by game model
	 * 
	 */
	public void setPlayerToken(String c);

}