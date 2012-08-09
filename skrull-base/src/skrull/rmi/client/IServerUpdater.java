package skrull.rmi.client;

import java.rmi.RemoteException;

import skrull.SkrullException;
import skrull.game.view.IClientAction;

/**
 * Provides a proxy for communicating with the Game server. Outgoing client actions
 * are routed through here to the server.
 * 
 * All the details about contacting and communicating to the server are isolated here
 * 
 * @author jesse
 *
 */
public interface IServerUpdater {

	/**
	 * Send the provided action to the server.
	 * 
	 * @param action
	 * @throws SkrullException
	 * @throws RemoteException
	 */
	public void processClientAction(IClientAction action) throws SkrullException, RemoteException;
}