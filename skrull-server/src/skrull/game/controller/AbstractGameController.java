package skrull.game.controller;

import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.IGameModel;
import skrull.game.view.IClientAction;

/**
 * Common operations for all game controllers
 * @author jesse
 *
 */
public abstract class AbstractGameController implements IGameController {


	private IGameModel gameModel;

	AbstractGameController(IGameModel model){
		this.gameModel = model;
	}
	@Override
	public void RunGame() {
		// TODO Auto-generated method stub

	}

	@Override
	public void EndGame() {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkActivity() {
		// TODO Auto-generated method stub

	}

	@Override
	public void processGameAction(IClientAction action) {
		
		switch (action.getActionType())
		{
			case CHAT:
				gameModel.chatUpdate(action);
			break;
				
			case MOVE:
				gameModel.processMove(action);
			break;
			
			case JOIN_GAME:
				gameModel.joinGame(action);
			break;
	
			case JOIN_SERVER:
				gameModel.joinGame(action);
			break;
			

			case QUIT:
				gameModel.quit(action);
			break;
			
			case CREATE_GAME:
			default:
				throw new RuntimeException("Action type " + action.getActionType() + " was unexpected here");
		
		} // end switch
		
		gameModel.processMove(action);

	}

	@Override
	public GameType getGameType() {
		return gameModel.getGameType();
	}

	@Override
	public int getGameId() {
		return gameModel.getGameId();
	}

}
