package skrull.rmi.server;


import java.io.Serializable;

import skrull.game.model.IGameModel;

public interface IClientUpdater extends Serializable {

	public void modelChanged(IGameModel aModel);
}