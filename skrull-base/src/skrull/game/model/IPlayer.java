package skrull.game.model;

import java.io.Serializable;
import java.util.UUID;

public interface IPlayer extends Serializable {

	/** 
	 * unique identifier for this player / client
	 * @return
	 */
	public UUID getPlayerId();

}