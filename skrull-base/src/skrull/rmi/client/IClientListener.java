package skrull.rmi.client;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface IClientListener extends Remote {

	public final String SERVICE_NAME = "Skrull.Client.Listener";

	public void modelChanged() throws RemoteException;

	public void updateListener() throws RemoteException;
}