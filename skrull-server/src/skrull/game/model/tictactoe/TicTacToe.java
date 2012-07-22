package skrull.game.model.tictactoe;

import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.AbstractGameModel;
import skrull.game.model.IPlayer;
import skrull.game.view.IClientAction;
import skrull.rmi.server.IClientUpdater;


public class TicTacToe extends AbstractGameModel{

	public TicTacToe(IPlayer startingPlayer, int gameId, IClientUpdater updater) {
		super(startingPlayer, gameId, GameType.TIC_TAC_TOE, updater);
	}

	@Override
	public void joinGame(IClientAction action) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void processMove(IClientAction action) {
		// TODO Auto-generated method stub
		
	}

}