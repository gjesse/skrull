package skrull.game.view;

import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.IGameModel;

/**
 * The primary view interface
 *
 */
public interface IGameClientView {

	public abstract void modelChanged(IGameModel model);

	public abstract String getChatText();

	public abstract int getGameId();

	public abstract GameType getGameType();

	public abstract String getJoinGameString();

	public abstract void setBroadcastMessage(String message);

	
}