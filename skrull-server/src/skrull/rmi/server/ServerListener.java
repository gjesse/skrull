package skrull.rmi.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import skrull.game.controller.ActionWorker;
import skrull.game.controller.IActionWorker;
import skrull.game.controller.IActionWorkerFactory;
import skrull.game.controller.IServerController;
import skrull.game.view.IClientAction;

/**
 * @author jesse
 *
 */
public class ServerListener implements IServerListener {

	private IServerController serverController;
	private IActionWorkerFactory workerFactory;
	private static ExecutorService executor = Executors.newCachedThreadPool();

	public ServerListener(IServerController controller, IActionWorkerFactory factory){
		this.serverController = controller;
		this.workerFactory = factory;
	}
	
	// don't instantiate w/out a controller
	@SuppressWarnings("unused")
	private ServerListener(){};
	
	@Override
	public void ProcessClientAction(IClientAction action) {
		
		executor.submit(workerFactory.newWorker(action, serverController));
	}

}
