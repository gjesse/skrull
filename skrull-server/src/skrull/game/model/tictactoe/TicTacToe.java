package skrull.game.model.tictactoe;

import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.AbstractGameModel;
import skrull.game.model.IPlayer;
import skrull.rmi.server.IClientUpdater;


public class TicTacToe extends AbstractGameModel{

	public TicTacToe(IPlayer startingPlayer, int gameId, IClientUpdater updater) {
		super(startingPlayer, gameId, GameType.TIC_TAC_TOE, updater);
	}

}