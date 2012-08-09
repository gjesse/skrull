package skrull.game.view;

import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.IGameModel;

/**
 * The primary view interface
 *
 */
public interface IGameClientView {

	/**
	 * method called when the model is updated by a client action
	 * 
	 * @param model
	 */
	public abstract void modelChanged(IGameModel model);

	/**
	 * @return the chat text input by this client. 
	 */
	public abstract String getChatText();

	public abstract int getGameId();

	public abstract GameType getGameType();

	/**
	 * 
	 * @return a string can be parsed to a particular game id and game type
	 */
	public abstract String getJoinGameString();

	/**
	 * method to allow updating of the status message
	 * @param message
	 */
	public abstract void setBroadcastMessage(String message);

	
}