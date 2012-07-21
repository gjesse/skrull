package skrull.game.controller;

import skrull.game.view.IClientAction;

public interface IActionWorkerFactory {

	public abstract IActionWorker newWorker(IClientAction action,
			IServerController serverController);

}