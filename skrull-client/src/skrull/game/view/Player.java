package skrull.game.view;

import java.util.UUID;

import skrull.game.model.IPlayer;

public class Player implements IPlayer {


	private static final long serialVersionUID = 2041179704423897548L;
	private UUID playerId;
	private String playerToken;
	
	public Player(UUID playerId){
		this.playerId = playerId;
	}
	@Override
	public UUID getPlayerId() {
		return playerId;
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((playerId == null) ? 0 : playerId.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Player other = (Player) obj;
		if (playerId == null) {
			if (other.playerId != null) {
				return false;
			}
		} else if (!playerId.equals(other.playerId)) {
			return false;
		}
		return true;
	}
	@Override
	public String getPlayerToken() {
		return playerToken;
	}
	
	@Override	
	public void setPlayerToken(String token){
		this.playerToken = token;
	}

	@Override
	public String toString(){
		return this.playerId.toString();
	}
}
