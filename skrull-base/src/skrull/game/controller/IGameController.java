package skrull.game.controller;

import skrull.game.view.IClientAction;

public interface IGameController {
	public void RunGame();

	public void EndGame();

	public void checkActivity();

	public void processGameAction(IClientAction action);
}