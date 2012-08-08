package skrull.rmi.client;

import java.rmi.RemoteException;

import skrull.SkrullException;
import skrull.game.view.IClientAction;


public interface IServerUpdater {

	public void processClientAction(IClientAction action) throws SkrullException, RemoteException;
}