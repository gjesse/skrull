package skrull.rmi.client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import skrull.SkrullException;
import skrull.game.view.IClientAction;
import skrull.rmi.server.IServerListener;


public class ServerUpdater implements IServerUpdater {

	private Registry registry;
	//private static final Logger logger = SkrullLogger.getLogger(ServerUpdater.class);

	public ServerUpdater() throws RemoteException{
        final String host =System.getProperty("skrull.server.host", "localhost");
        final String port = System.getProperty("skrull.server.port", "1099");
		registry = LocateRegistry.getRegistry(host, Integer.valueOf(port));		

	}
	@Override
	public void processClientAction(IClientAction action) throws SkrullException, RemoteException{
					IServerListener listener;
					try {
						listener = (IServerListener)registry.lookup(IServerListener.SERVICE_NAME);
						listener.processClientAction(action);

					} catch (NotBoundException e) {
						throw new SkrullException(e);
					}
				
			

	}

}
