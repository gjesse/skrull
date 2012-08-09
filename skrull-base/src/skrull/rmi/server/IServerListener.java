package skrull.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import skrull.SkrullException;
import skrull.game.view.IClientAction;


/**
 * The implementation of this should really be a singleton and enforce that.
 * 
 * responsible for receiving input from clients and dispatching worker threads
 * to handle that input
 * 
 * @author jesse
 *
 */
public interface IServerListener extends Remote{

	String SERVICE_NAME = "Skrull.Server.Listener";

	/**
	 * primary entry point to the server from remote client.
	 * passes the action to the server controller
	 * @throws RemoteException
	 */
	public void processClientAction(IClientAction action) throws RemoteException, SkrullException;
	
}