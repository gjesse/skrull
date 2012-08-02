package skrull.game.controller;

import skrull.SkrullException;
import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.IGameModel;
import skrull.game.view.IClientAction;

public interface IGameController {
	public void RunGame();

	public void EndGame();

	public void checkActivity();

	public void processGameAction(IClientAction action) throws SkrullException;
	
	public GameType getGameType();
	
	public int getGameId();
	
	public void setServerController(IServerController serverController);

	public IGameModel getGameModel();

}