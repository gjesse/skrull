package skrull.rmi.server;

import skrull.game.model.IGameModel;

public class SkrullClientUpdater implements IClientUpdater {

	@Override
	public void modelChanged(IGameModel aModel) {
		throw new UnsupportedOperationException();

	}

	@Override
	public void updateListener(IGameModel aModel) {
		throw new UnsupportedOperationException();

	}

}
