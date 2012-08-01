package skrull.game.model;

public abstract class Move implements IMove {

	private static final long serialVersionUID = 3692646851275065925L;
	private int moveIndex;
	private int moveCount;
	private IPlayer player;   // UUID
	
	// moves are all ints for now, representing the location of the move
	// 0 thru 8 for tic tac toe, 0 thru 2 for rock paper scissors.
	// the game model will know who made the move and what to put in the location.
	public void setMove(int m){
		moveIndex = m;
	}
	
	@Override
	public int getMoveIndex(){
		return moveIndex;
	}
	
	public void incMoveCount(){
		moveCount++;
	}
	
	public void setMoveCount(int mc){
		moveCount = mc;
	}
	
	@Override
	public int getMoveCount(){
		return moveCount;
	}

	@Override
	public IPlayer getPlayer() {
		return player;
	}

	public void setPlayer(IPlayer player) {
		this.player = player;
	}
	

	
}
