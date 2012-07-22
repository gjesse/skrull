package skrull.util;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import skrull.game.controller.ActionWorkerFactory;
import skrull.game.controller.IServerController;
import skrull.game.controller.ServerController;
import skrull.game.factory.GameFactory;
import skrull.game.factory.IGameFactory;
import skrull.rmi.server.IClientUpdater;
import skrull.rmi.server.IServerListener;
import skrull.rmi.server.ServerListener;
import skrull.rmi.server.SkrullClientUpdater;

public class SkrullServerStarter extends RmiStarter {

	public SkrullServerStarter(Class<IServerListener> clazzToAddToServerCodebase) {
		super(clazzToAddToServerCodebase);
	}

	public static void main(String[] args) {
		SkrullServerStarter starter = new SkrullServerStarter(IServerListener.class);
	}

	@Override
	public void doCustomRmiHandling() {
       try {
    	    IClientUpdater updater = new SkrullClientUpdater();
    	    IGameFactory factory = new GameFactory(updater);
    	    IServerController controller = new ServerController(factory);
            IServerListener listener = new ServerListener(controller, new ActionWorkerFactory());
            Remote engineStub = UnicastRemoteObject.exportObject(listener, 0);

            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(IServerListener.SERVICE_NAME, engineStub);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

		
	}
}