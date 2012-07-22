package skrull.game.model.tictactoe;

import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.AbstractGameModel;
import skrull.game.model.IPlayer;


public class TicTacToe extends AbstractGameModel{

	public TicTacToe(IPlayer startingPlayer, int gameId) {
		super(startingPlayer, gameId, GameType.TIC_TAC_TOE);
	}

}