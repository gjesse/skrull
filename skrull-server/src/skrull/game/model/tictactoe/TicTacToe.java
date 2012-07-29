package skrull.game.model.tictactoe;

import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.AbstractGameModel;
import skrull.game.model.IPlayer;
import skrull.game.view.IClientAction;
import skrull.rmi.server.IClientUpdater;


public class TicTacToe extends AbstractGameModel{

	private static final long serialVersionUID = -6502566082662680883L;

	public TicTacToe(IPlayer startingPlayer, int gameId, IClientUpdater updater) {
		super(startingPlayer, gameId, GameType.TIC_TAC_TOE, updater);
	}

	@Override
	public void joinGame(IClientAction action) {
		super.addPlayer(action.getPlayer());
		super.updateListener();
		
	}

	@Override
	public void doProcessMove(IClientAction action) {
		// TODO Auto-generated method stub
		
	}

}