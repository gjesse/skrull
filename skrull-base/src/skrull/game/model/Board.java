package skrull.game.model;

public class Board implements IBoard{
	/**
	 * 
	 */
	public static final long serialVersionUID = 1517902292563760990L;	
	
	// THE Board
	private IMove[] board;
	
	
	// The Board Methods
	public Board(int i) {
		// TODO Verify this with Jesse
		this.board = new IMove[i];
	}

	@Override
	public boolean setBoard(IMove m, int loc){
		if( this.board[loc] == null){
			this.board[loc] = m;
			return true;
		}
		else return false;
	}
	
	@Override
	public IMove getBoardLoc(int m){
		return board[m];		
	}
	
	@Override
	public IMove[] getBoard(){
		return board;		
	}
	
}