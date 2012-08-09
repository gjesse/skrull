package skrull.game.view;

import java.io.Serializable;

import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.IMove;
import skrull.game.model.IPlayer;

/**
 * A single action from a single player.
 * All fields should be final and immutable
 * @author jesse
 *
 */
public interface IClientAction extends Serializable {

	/**
	 * 
	 * Each {@link IClientAction} must declare one of these.
	 * 
	 * @author jesse
	 *
	 */
	public enum ActionType {
		JOIN_SERVER, CREATE_GAME, JOIN_GAME, MOVE, CHAT, QUIT;
	}

	/**
	 * The  {@link IClientAction.ActionType} of this object
	 * @return
	 */
	public ActionType getActionType();
	
	/**
	 * other details depending on the actionType
	 * @return
	 */
	public String getActionMessage();
	
	public IPlayer getPlayer();
	
	public GameType getGameType();
	
	public int getGameId();
	
	public IMove getMove();
}