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
import skrull.rmi.server.IServerListener;
import skrull.rmi.server.ServerListener;

public class SkrullServerStarter extends RmiStarter {

	public SkrullServerStarter(Class<IServerListener> clazzToAddToServerCodebase) {
		super(clazzToAddToServerCodebase);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		SkrullServerStarter starter = new SkrullServerStarter(IServerListener.class);
	}

	@Override
	public void doCustomRmiHandling() {
       try {
    	    IGameFactory factory = new GameFactory();
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