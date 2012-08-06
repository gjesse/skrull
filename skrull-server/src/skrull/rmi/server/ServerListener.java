package skrull.rmi.server;


import java.rmi.RemoteException;

import org.apache.log4j.Logger;

import skrull.SkrullException;
import skrull.game.controller.IServerController;
import skrull.game.view.IClientAction;
import skrull.util.logging.SkrullLogger;

/**
 * @author jesse
 *
 */
public class ServerListener implements IServerListener {

	private IServerController serverController;
	private static Logger logger = SkrullLogger.getLogger(ServerListener.class);
	public ServerListener(IServerController controller){
		this.serverController = controller;
	}
	
	// don't instantiate w/out a controller
	@SuppressWarnings("unused")
	private ServerListener(){};
	
	@Override
	public void processClientAction(IClientAction action) throws RemoteException, SkrullException {
				serverController.processClientAction(action);
	}

}
