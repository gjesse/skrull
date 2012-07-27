package skrull.util;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import skrull.game.controller.IServerController;
import skrull.game.controller.ServerController;
import skrull.game.factory.GameFactory;
import skrull.game.factory.IGameFactory;
import skrull.rmi.server.IClientUpdater;
import skrull.rmi.server.IServerListener;
import skrull.rmi.server.ServerListener;
import skrull.rmi.server.SkrullClientUpdater;

public class SkrullServerStarter extends RmiStarter {


	public SkrullServerStarter() throws Exception {
		this(true);
	}

	public SkrullServerStarter(boolean multiThreaded) throws Exception {
		super();
	}
	public static void main(String[] args) throws Exception {

		SkrullServerStarter starter = new SkrullServerStarter();
	}

	@Override
	public void doCustomRmiHandling() {
       try {
    	    IClientUpdater updater = new SkrullClientUpdater();
    	    IGameFactory factory = new GameFactory(updater);
    	    IServerController controller = new ServerController(factory);
            IServerListener listener = new ServerListener(controller);
            Remote engineStub = UnicastRemoteObject.exportObject(listener, 0);

            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(IServerListener.SERVICE_NAME, engineStub);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

		
	}
}