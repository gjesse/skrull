package skrull.rmi.server;

import skrull.game.model.IGameModel;
import skrull.game.model.IPlayer;

public class SkrullClientUpdater implements IClientUpdater {

	@Override
	public void modelChanged(IGameModel model) {
		for (IPlayer player: model.getPlayers()){
			// here we need to get the rmi exposed interface for each player, and call it
		}

	}
}
