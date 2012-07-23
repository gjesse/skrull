package skrull.game.view;

import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.IMove;
import skrull.game.model.IPlayer;

public final class ClientAction implements IClientAction {
	private final int gameId;
	private final IPlayer player;
	private final ActionType actionType;
	private final String actionMessage;
	private final IMove move;
	private final GameType gameType;

	public ClientAction(int gameId, IPlayer player, ActionType actionType, GameType gameType, String actionMessage, IMove move){
		this.gameId =gameId;
		this.player = player;
		this.actionType = actionType;
		this.gameType = gameType;
		this.actionMessage = actionMessage;
		this.move = move;
	}

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
		return gameType;
	}
	@Override
	public int getGameId() {
		return gameId;
	}
	@Override
	public IMove getMove() {
		return move;
	}

}