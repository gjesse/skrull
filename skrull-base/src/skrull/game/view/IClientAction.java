package skrull.game.view;

public interface IClientAction {

	public enum ActionType {
		JOIN_SERVER, CREATE_GAME, JOIN_GAME, MOVE, CHAT, QUIT;
	}
}