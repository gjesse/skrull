package skrull.rmi.client;

import java.rmi.Remote;
import java.rmi.RemoteException;

import skrull.game.model.IGameModel;


public interface IClientListener extends Remote {

	public final String SERVICE_NAME = "Skrull.Client.Listener";

	public void modelChanged(IGameModel model) throws RemoteException;

}