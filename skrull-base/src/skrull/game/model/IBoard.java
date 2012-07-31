package skrull.game.model;

public interface IBoard {
	public abstract boolean setBoard(int loc, byte xo);
	public abstract byte getBoardset();
	public abstract IMove[] getBoard();
}