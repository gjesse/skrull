package skrull.rmi.client;

import skrull.game.view.IClientAction;
import skrull.rmi.SkrullRMIException;


public interface IServerUpdater {

	public void ProcessClientAction(IClientAction action) throws SkrullRMIException;
}