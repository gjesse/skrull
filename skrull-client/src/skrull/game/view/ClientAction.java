package skrull.game.view;

import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.IMove;
import skrull.game.model.IPlayer;

public class ClientAction implements IClientAction {
	private int gameId;
	private IPlayer player;
	private ActionType actionType;
	private String actionMessage;
	private IMove move;
	// public ClientInputHandler unnamed_ClientInputHandler_;
	@Override
	public ActionType getActionType() {
		return actionType;
	}
	@Override
	public String getActionMessage() {
		return actionMessage;
	}
	@Override
	public IPlayer getPlayer() {
		return player;
	}
	@Override
	public GameType getGameType() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int getGameId() {
		// TODO Auto-generated method stub
		return 0;
	}

}