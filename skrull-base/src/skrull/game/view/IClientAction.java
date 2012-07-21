package skrull.game.view;

import skrull.game.model.IPlayer;

public interface IClientAction {

	public enum ActionType {
		JOIN_SERVER, CREATE_GAME, JOIN_GAME, MOVE, CHAT, QUIT;
	}

	public ActionType getActionType();
	
	/**
	 * other details depending on the actionType
	 * @return
	 */
	public String getActionMessage();
	
	public IPlayer getPlayer();
	
	public String getGameType();
	
	public int getGameId();
}