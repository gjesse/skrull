package skrull.game.model;

import java.util.Collection;

import skrull.game.factory.IGameFactory.GameType;
import skrull.game.view.IClientAction;

public interface IGameModel {

	public abstract void chatUpdate(IClientAction aAction);

	public abstract void joinGame(IClientAction aAction);

	public abstract void processMove(IClientAction aAction);

	public abstract boolean isGameOver();

	public abstract IPlayer getWinner();

	public abstract void checkActivity();

	public abstract GameType getGameType();

	public abstract int getGameId();

	public abstract void quit(IClientAction action);

	public abstract Collection<IPlayer> getPlayers();
	
}