package skrull.game.view;

import java.util.UUID;

import skrull.game.model.IGameModel;
import skrull.rmi.client.IClientListener;

public class GameClientView {
	private Object _clientController;
	public IClientListener _unnamed_ClientListener_;
	public ClientInputHandler cih;
	private UUID playerId;

	public GameClientView(ClientInputHandler cih, UUID playerId) {
		this.cih = cih;
		this.playerId = playerId;
	}

	public void modelChanged(IGameModel model) {
		System.out.println("model changed.. " + model);
	}

	public void DrawBoard() {
		throw new UnsupportedOperationException();
	}

	public void UpdateBoard() {
		throw new UnsupportedOperationException();
	}
}