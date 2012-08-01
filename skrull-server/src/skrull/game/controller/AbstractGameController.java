package skrull.game.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.IGameModel;
import skrull.game.view.IClientAction;
import skrull.util.logging.SkrullLogger;

/**
 * Common operations for all game controllers
 * @author jesse
 *
 */
public abstract class AbstractGameController implements IGameController {


	private IGameModel gameModel;
	private IServerController serverController;
	private static final Logger logger = SkrullLogger.getLogger(AbstractGameController.class);


	AbstractGameController(IGameModel model){
		this.gameModel = model;
	}
	
	// TODO: remove these next 2 if they are really not needed
	@Override
	public void RunGame() {
		// TODO Auto-generated method stub

	}

	@Override
	public void EndGame() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setServerController(IServerController serverController) {
		this.serverController = serverController;
	}	
	
	@Override
	public void checkActivity() {
		gameModel.checkActivity();
	}

	@Override
	public void processGameAction(IClientAction action) {
		logger.debug("processing game action " + action.getActionType());
		
		gameModel.setActiveGames(getActiveGames());
		switch (action.getActionType())
		{
			case CHAT:
				gameModel.chatUpdate(action);
			break;
				
			case MOVE:
				gameModel.processMove(action);
			break;
			
			case JOIN_GAME:
			case JOIN_SERVER:
			case CREATE_GAME:
				gameModel.joinGame(action);
			break;
			

			case QUIT:
				gameModel.quit(action);
			break;
			
			default:
				throw new RuntimeException("Action type " + action.getActionType() + " was unexpected here");
		
		} // end switch
		
		// gameModel.processMove(action);

	}

	private Collection<IGameModel> getActiveGames() {
		
		Set<IGameModel> games = new HashSet<IGameModel>();
		for (IGameController c : serverController.getControllers()){
			games.add(c.getGameModel());
		}
		
		return Collections.unmodifiableSet(games);
		
	}

	@Override
	public GameType getGameType() {
		return gameModel.getGameType();
	}

	@Override
	public int getGameId() {
		return gameModel.getGameId();
	}
	
	@Override
	public IGameModel getGameModel() {
		
		return gameModel;
	}


}
