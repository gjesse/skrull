package skrull.rmi.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import skrull.game.view.IClientAction;
import skrull.rmi.server.IServerListener;


public class ServerUpdater implements IServerUpdater {

	private Registry registry;
	
	public ServerUpdater() throws RemoteException{
		registry = LocateRegistry.getRegistry();		

	}
	@Override
	public void ProcessClientAction(IClientAction action) {
		
				try {
					IServerListener listener = (IServerListener)registry.lookup(IServerListener.SERVICE_NAME);
					listener.ProcessClientAction(action);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			

	}

}
