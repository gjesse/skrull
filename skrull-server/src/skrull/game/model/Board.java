package skrull.game.model;

public class Board implements IBoard{
	/**
	 * 
	 */
	public static final long serialVersionUID = 1517902292563760990L;	
	
	// THE Board
	private IMove[] boardLocations;
	
	
	// The Board Methods
	
	public Board(int i) {
		// TODO Verify this with Jesse
		this.boardLocations = new IMove[i];
	}

	@Override
	public boolean setBoard(IMove m, int loc){
		if( this.boardLocations[loc] == null){
			this.boardLocations[loc] = m;
			return true;
		}
		else return false;
	}
	
	@Override
	public IMove getBoardLoc(int m){
		return boardLocations[m];		
	}
	
	@Override
	public IMove[] getBoard(){
		return boardLocations;		
	}
	
}