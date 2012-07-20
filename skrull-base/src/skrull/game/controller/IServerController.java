package skrull.game.controller;

import skrull.game.view.IClientAction;

public interface IServerController {

	public abstract void ProcessClientAction(IClientAction action);

	public abstract IGameController[] getControllers();

	public abstract void addGameController(IGameController controller);

	public abstract String[] listGames();

}