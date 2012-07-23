package skrull.game.view;

import java.util.UUID;

import skrull.game.model.IPlayer;

public class Player implements IPlayer {


	private static final long serialVersionUID = 2041179704423897548L;
	private UUID playerId;
	
	public Player(UUID playerId){
		this.playerId = playerId;
	}
	@Override
	public UUID getPlayerId() {
		return playerId;
	}

}
