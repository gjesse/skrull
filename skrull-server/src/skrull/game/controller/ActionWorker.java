package skrull.game.controller;

import skrull.game.view.IClientAction;

public class ActionWorker implements IActionWorker {

	private IClientAction action;
	private IServerController serverController;

	public ActionWorker(IClientAction action, IServerController serverController) {
		this.action = action;
		this.serverController = serverController;
	}

	@Override
	public void run() {
		serverController.ProcessClientAction(action);
	}

}
