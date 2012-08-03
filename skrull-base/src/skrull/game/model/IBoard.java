package skrull.game.model;

import java.io.Serializable;

public interface IBoard extends Serializable{
	
	public abstract boolean setBoard(IMove m, int loc);
	public abstract IMove[] getBoard();
	public abstract IMove getBoardLoc(int m);
	
}