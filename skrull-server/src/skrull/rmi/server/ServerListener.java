package skrull.rmi.server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
	private boolean multiThreaded;
	private static ExecutorService executor = Executors.newCachedThreadPool();

	public ServerListener(IServerController controller, IActionWorkerFactory factory){
		this(controller, factory, true);
	}
	
	public ServerListener(IServerController controller, IActionWorkerFactory factory, boolean multiThreaded){
		this.serverController = controller;
		this.workerFactory = factory;
		this.multiThreaded = multiThreaded;
	}
	
	// don't instantiate w/out a controller
	@SuppressWarnings("unused")
	private ServerListener(){};
	
	@Override
	public void ProcessClientAction(IClientAction action) {
		System.out.println("received an action");
		if (multiThreaded){
			executor.submit(workerFactory.newWorker(action, serverController));
		}else{
			serverController.processClientAction(action);
		}
	}

}
