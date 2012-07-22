package skrull.game.view;

import skrull.rmi.client.IServerUpdater;
import skrull.rmi.client.ServerUpdater;

public class ClientInputHandler {
	private Object _playerID;
	private Object _gameID;
	public GameClientView _unnamed_GameCientView_;
	public IServerUpdater serverUpdater;
	public IClientAction _unnamed_Client_Actions_;

	public ClientInputHandler(ServerUpdater serverUpdater) {
		this.serverUpdater = serverUpdater;
	}

	public void handleClick() {
		throw new UnsupportedOperationException();
	}
}