package skrull.util;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import skrull.game.controller.ActivityMonitor;
import skrull.game.controller.IActivityMonitor;
import skrull.game.controller.IServerController;
import skrull.game.controller.ServerController;
import skrull.game.factory.GameFactory;
import skrull.game.factory.IGameFactory;
import skrull.rmi.server.IClientUpdater;
import skrull.rmi.server.IServerListener;
import skrull.rmi.server.ServerListener;
import skrull.rmi.server.SkrullClientUpdater;
import skrull.util.logging.SkrullLogger;

public class SkrullServerStarter extends RmiStarter {


	private static final Logger logger = SkrullLogger.getLogger(SkrullServerStarter.class);

	public SkrullServerStarter() throws Exception {
		super();
	}
	public static void main(String[] args) throws Exception {

		@SuppressWarnings("unused")
		SkrullServerStarter starter = new SkrullServerStarter();
	}

	/**
	 * Just a note that this is doing all the application initialization and
	 * dependency injection. It would be nice to have this organized a little better
	 */
	@Override
	public void doCustomRmiHandling() {
       try {
    	    IClientUpdater updater = new SkrullClientUpdater();
    	    IGameFactory factory = new GameFactory(updater);
    	    IServerController controller = new ServerController(factory);
    	    IActivityMonitor monitor = new ActivityMonitor(controller);
    	    spawnThread(monitor);
            IServerListener listener = new ServerListener(controller);
            Remote engineStub = UnicastRemoteObject.exportObject(listener, 0);

            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind(IServerListener.SERVICE_NAME, engineStub);
            logger.info("initialization completed");

        }
        catch(Exception e) {
        	logger.fatal("error encountered initializing", e);
        }

		
	}

	private void spawnThread(Runnable monitor) {

		ScheduledExecutorService svc = Executors.newSingleThreadScheduledExecutor();
		// svc.execute(monitor);
		long interval = Long.parseLong(
				System.getProperty("activity.monitor.interval", String.valueOf(IActivityMonitor.CHECK_INTERVAL_MS))
				);
		svc.scheduleAtFixedRate(monitor, 1000, interval, TimeUnit.MILLISECONDS);
	}
}