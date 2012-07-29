package skrull.rmi.client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import skrull.game.view.IClientAction;
import skrull.rmi.SkrullRMIException;
import skrull.rmi.server.IServerListener;


public class ServerUpdater implements IServerUpdater {

	private Registry registry;
	//private static final Logger logger = SkrullLogger.getLogger(ServerUpdater.class);

	public ServerUpdater() throws RemoteException{
		registry = LocateRegistry.getRegistry();		

	}
	@Override
	public void ProcessClientAction(IClientAction action) throws SkrullRMIException{
		
				try {
					IServerListener listener = (IServerListener)registry.lookup(IServerListener.SERVICE_NAME);
					listener.ProcessClientAction(action);
				} catch (Exception e) {
					throw new SkrullRMIException(e);
				}
			

	}

}
