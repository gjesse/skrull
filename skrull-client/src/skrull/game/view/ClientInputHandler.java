package skrull.game.view;

import java.awt.event.ActionEvent;
import java.util.UUID;

import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.IPlayer;
import skrull.game.view.IClientAction.ActionType;
import skrull.rmi.client.IServerUpdater;
import skrull.rmi.client.ServerUpdater;

public class ClientInputHandler {
	private IPlayer player;
	private int gameId; // is this necessary?
	public IServerUpdater serverUpdater;

	public ClientInputHandler(ServerUpdater serverUpdater, UUID playerId ) {
		this.serverUpdater = serverUpdater;
		this.player = new Player(playerId);
		this.gameId = -1;
	}

	public void handleInput(ActionEvent e) {
		throw new UnsupportedOperationException();
	}

	public IClientAction getStartupAction() {
		return new ClientAction(gameId, player, ActionType.JOIN_SERVER, GameType.DEFAULT, null, null);
	}
}