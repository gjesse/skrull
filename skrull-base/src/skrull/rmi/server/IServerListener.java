package skrull.rmi.server;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface IServerListener extends Remote{

	String SERVICE_NAME = "Skrull.Server.Listener";

	public void ProcessClientAction() throws RemoteException;
}