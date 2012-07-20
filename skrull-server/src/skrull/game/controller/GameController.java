package skrull.game.controller;

import skrull.game.model.IGameModel;
import skrull.game.view.IClientAction;

public abstract class GameController implements IGameController {
	private IGameModel model;
	public IServerController serverController;

	@Override
	public void RunGame() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void EndGame() {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void checkActivity() {
		throw new UnsupportedOperationException();
	}



	@Override
	public void processGameAction(IClientAction action) {
		// TODO Auto-generated method stub
		
	}
}