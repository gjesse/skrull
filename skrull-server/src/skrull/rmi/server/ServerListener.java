package skrull.rmi.server;

import skrull.game.controller.IServerController;
import skrull.game.view.IClientAction;


public class ServerListener implements IServerListener {

	private IServerController serverController;

	public ServerListener(IServerController controller){
		this.serverController = controller;
	}
	
	// don't instantiate w/out a controller
	@SuppressWarnings("unused")
	private ServerListener(){};
	
	@Override
	public void ProcessClientAction(IClientAction action) {
		// TODO Auto-generated method stub

	}

}
