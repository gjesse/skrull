package skrull.rmi.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

import skrull.game.model.IGameModel;

/**
 * entry point for updates to the view/client/player side.
 * @author jesse
 *
 */
public interface IClientListener extends Remote {

	public final String SERVICE_NAME = "Skrull.Client.Listener";

	/**
	 * Any action successfully processed by the game model should call this
	 * after processing.
	 * 
	 * @param model
	 * @throws RemoteException
	 */
	public void modelChanged(IGameModel model) throws RemoteException;

	/**
	 * This is called as a no-op test from the server side. 
	 * it should always return true, as it won't get called if
	 * the connection isn't there
	 * @return TRUE
	 */
	public boolean isConnected() throws RemoteException;

}