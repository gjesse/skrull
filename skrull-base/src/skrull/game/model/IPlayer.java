package skrull.game.model;

import java.util.UUID;

public interface IPlayer {

	/** 
	 * unique identifier for this player / client
	 * @return
	 */
	public UUID getPlayerId();

}