package skrull.rmi.server;

import skrull.game.model.IGameModel;

public interface IClientUpdater {

	public void modelChanged(IGameModel aModel);
}