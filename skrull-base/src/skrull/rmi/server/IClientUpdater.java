package skrull.rmi.server;


import java.io.Serializable;

import skrull.game.model.IGameModel;
import skrull.game.model.IPlayer;

public interface IClientUpdater extends Serializable {

	/**
	 * Pass an update to all interested clients
	 * @param model
	 */
	public void modelChanged(IGameModel model);

	/**
	 * Verify that the player client is connected still
	 * @param p
	 */
	public boolean isPlayerConnected(IPlayer p);
}