package skrull.rmi.server;


import java.io.Serializable;

import skrull.game.model.IGameModel;
import skrull.game.model.IPlayer;
import skrull.rmi.SkrullRMIException;

public interface IClientUpdater extends Serializable {

	/**
	 * Pass an update to all interested clients
	 * @param model
	 * @throws SkrullRMIException 
	 */
	public void modelChanged(IGameModel model) throws SkrullRMIException;

	/**
	 * Verify that the player client is connected still
	 * @param p
	 * @throws SkrullGameException 
	 */
	public void checkPlayerConnected(IPlayer p) throws SkrullRMIException;
}