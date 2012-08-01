package skrull.game.model;

import java.io.Serializable;
import java.util.Collection;

import skrull.game.factory.IGameFactory.GameType;
import skrull.game.view.IClientAction;

public interface IGameModel extends Serializable {

	public abstract void chatUpdate(IClientAction aAction);

	/**
	 * Validation must be done here by the game before
	 * adding the new player to the game
	 * @param aAction
	 */
	public abstract void joinGame(IClientAction aAction);

	public abstract void processMove(IClientAction aAction);

	public abstract boolean isGameOver();

	public abstract IPlayer getWinner();
	
	public abstract IPlayer getActivePlayer();
	
	/**
	 * 2 types of checks should be performed here: 
	 * 	1. make sure the active player hasn't exceeded some reasonable threshold (ie walked away from the computer)
	 *  2. make sure we still have network connectivity to the client
	 */
	public abstract void checkActivity();

	public abstract GameType getGameType();

	public abstract int getGameId();

	public abstract void quit(IClientAction action);

	public abstract Collection<IPlayer> getPlayers();
	
	public abstract String getChatContents();

	public abstract String getBroadcastMessage();
	
}