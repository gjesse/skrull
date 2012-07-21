package skrull.game.controller;

import skrull.game.view.IClientAction;

public class ActionWorkerFactory implements IActionWorkerFactory {

	/* (non-Javadoc)
	 * @see skrull.game.controller.IActionWorkerFactory#newWorker(skrull.game.view.IClientAction, skrull.game.controller.IServerController)
	 */
	@Override
	public IActionWorker newWorker(IClientAction action, IServerController serverController) {
		return new ActionWorker(action, serverController);
	}

}
