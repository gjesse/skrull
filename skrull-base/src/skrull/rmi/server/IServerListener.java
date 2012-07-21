package skrull.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;

import skrull.game.view.IClientAction;


public interface IServerListener extends Remote{

	String SERVICE_NAME = "Skrull.Server.Listener";

	/**
	 * primary entry point to the server from remote client.
	 * passes the action to the server controller
	 * @throws RemoteException
	 */
	public void ProcessClientAction(IClientAction action) throws RemoteException;
	
}