package skrull.game.controller;

import skrull.SkrullException;
import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.IGameModel;
import skrull.game.view.IClientAction;


/**
 * GameController should be implemented by multiple classes, specific to each 
 * game type. Client input actions flow from the server controller through 
 * this interface on to the model. In most cases the implementation will
 * be a thin proxy to the game model.
 * 
 * @author jesse
 *
 */
public interface IGameController {
	
	/**
	 * check activity of clients related to this game
	 */
	public void checkActivity();

	/**
	 * Process the incoming client action for this specific game model. 
	 * It's expected that the action is already determined to be for this game.
	 * 
	 * @param action
	 * @throws SkrullException
	 */
	public void processGameAction(IClientAction action) throws SkrullException;
	
	/**
	 * Get the {@link GameType} for this particular game
	 * @return
	 */
	public GameType getGameType();
	
	/**
	 * Get the system-unique game id
	 * @return
	 */
	public int getGameId();
	
	/**
	 * set the reference to the upstream serverController
	 * @param serverController
	 */
	public void setServerController(IServerController serverController);

	/**
	 * Return the model implementation for this game
	 * @return
	 */
	public IGameModel getGameModel();

}
