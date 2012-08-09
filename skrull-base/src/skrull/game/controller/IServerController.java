package skrull.game.controller;

import java.util.Collection;

import skrull.SkrullException;
import skrull.game.view.IClientAction;

/**
 * IServerController is the first and most important decision point on the server side for dealing 
 * with incoming client actions. Responsibilities include handling incoming input, instantiating
 * new games, and routing input to existing game controllers as appropriate.
 * 
 * Generally the implementation should be a singleton.
 * 
 * @author jesse
 *
 */
public interface IServerController {

	/**
	 * Responsible for dealing with the specifics of an
	 * incoming client action. 
	 * Possible results correspond to @IClientAction.ActionType
	 *   
	 * @param action
	 * @throws SkrullException 
	 */
	public abstract void processClientAction(IClientAction action) throws SkrullException;

	/**
	 * a collection of all active gameControllers
	 * @return
	 */
	public abstract Collection<IGameController> getControllers();



}