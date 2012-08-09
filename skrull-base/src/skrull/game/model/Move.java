package skrull.game.model;

/**
 * 
 * @author Kyle.Higgins (JAVADOC author)
 *
 * Move class implementing IMove
 * Allows the specific game models to set/get Move variables.
 * 
 */
public class Move implements IMove {

	private static final long serialVersionUID = 3692646851275065925L;
	private int moveIndex;    // Location of move on board
	private IPlayer player;   // UUID of player who made the move.
	
	@Override
	public void setMoveIndex(int m){
		this.moveIndex = m;
	}
	
	
	@Override
	public int getMoveIndex(){
		return moveIndex;
	}
	
	@Override
	public IPlayer getPlayer() {
		return player;
	}

	@Override
	public void setPlayer(IPlayer player) {
		this.player = player;
	}
	

	
}
