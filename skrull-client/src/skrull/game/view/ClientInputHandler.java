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
	private IGameClientView view;

	public ClientInputHandler(ServerUpdater serverUpdater, UUID playerId ) {
		this.serverUpdater = serverUpdater;
		this.player = new Player(playerId);
		this.gameId = -1;
	}

	public void handleInput(ActionEvent e) {
		ActionType type = ActionType.valueOf(e.getActionCommand());
		
		switch(type){
		
		case CHAT:
			// TODO: need to determine the game type from the view. this will break once we have actual games going
			// TODO: a builder or factory seems to be in order for the ClientActions
			serverUpdater.ProcessClientAction(new ClientAction(gameId, player, type, GameType.DEFAULT, view.getChatText(), null));
			
			break;
		default:
			throw new UnsupportedOperationException(e + e.getActionCommand());
		}
	}

	public IClientAction getStartupAction() {
		return new ClientAction(gameId, player, ActionType.JOIN_SERVER, GameType.DEFAULT, null, null);
	}

	public void setView(IGameClientView view) {
		this.view = view;
	}
}