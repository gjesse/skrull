package skrull.game.view;

import skrull.game.model.IPlayer;

public interface IClientAction {

	public enum ActionType {
		JOIN_SERVER, CREATE_GAME, JOIN_GAME, MOVE, CHAT, QUIT;
	}

	ActionType getActionType();
	
	/**
	 * can represent a particular game or
	 * other details depending on the actionType
	 * @return
	 */
	String getActionMessage();
	
	IPlayer getPlayer();
}