package skrull.game.view;

import skrull.game.model.IMove;

public class ClientAction implements IClientAction {
	private int gameId;
	private String playerId;
	private ActionType actionType;
	private String chatMsg;
	private IMove move;
	// public ClientInputHandler unnamed_ClientInputHandler_;
	public enum ActionType {
		JOIN_SERVER, CREATE_GAME, JOIN_GAME, MOVE, CHAT, QUIT;
	}
}