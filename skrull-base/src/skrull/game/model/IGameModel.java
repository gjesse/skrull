package skrull.game.model;

import skrull.game.view.IClientAction;

public interface IGameModel {

	public abstract void chatUpdate(IClientAction aAction);

	public abstract void joinGame(IClientAction aAction);

	public abstract void processMove(IClientAction aAction);

	public abstract boolean isGameOver();

	public abstract IPlayer getWinner();

	public abstract void updateListener(IGameModel aGameModel);

	public abstract void checkActivity();

}