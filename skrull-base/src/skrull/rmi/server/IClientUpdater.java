package skrull.rmi.server;


import java.io.Serializable;

import skrull.game.model.IGameModel;
import skrull.game.model.IPlayer;
import skrull.rmi.SkrullRMIException;

/**
 * Provides a proxy for communicating with the clients. Outgoing game model updates are routed from here to 
 * interested clients
 * 
 * All the details about contacting and communicating to the view/client are isolated here
 * 
 * @author jesse
 *
 */
public interface IClientUpdater extends Serializable {

	/**
	 * Pass an update to all interested clients
	 * @param model
	 * @throws SkrullRMIException 
	 */
	public void modelChanged(IGameModel model) throws SkrullRMIException;

	/**
	 * Verify that the player client is still connected
	 * @param p
	 * @throws SkrullGameException 
	 */
	public void checkPlayerConnected(IPlayer p) throws SkrullRMIException;
}