package skrull.rmi.client;

import skrull.game.view.IClientAction;


public interface IServerUpdater {

	public void ProcessClientAction(IClientAction action);
}