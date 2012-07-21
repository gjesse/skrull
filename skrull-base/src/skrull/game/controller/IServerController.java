package skrull.game.controller;

import java.util.Collection;

import skrull.game.view.IClientAction;

public interface IServerController {

	/**
	 * Responsible for dealing with the specifics of an
	 * incoming client action. 
	 * Possible results correspond to @IClientAction.ActionType
	 *   
	 * @param action
	 */
	public abstract void ProcessClientAction(IClientAction action);

	/**
	 * a thread-safe array of all active gameControllers
	 * @return
	 */
	public abstract Collection<IGameController> getControllers();


}