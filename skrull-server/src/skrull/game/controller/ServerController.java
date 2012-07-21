package skrull.game.controller;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import skrull.game.factory.IGameFactory;
import skrull.game.view.IClientAction;

public class ServerController implements IServerController {
	private List<IGameController> activeGameControllers = new CopyOnWriteArrayList<IGameController>();
	private IGameFactory gameFactory;
	private IGameController defaultGameController;

	// TODO: implement this
	private ActivityMonitor activityMontor;
	

	public ServerController(IGameFactory gameFactory){
		this.gameFactory = gameFactory;
		this.defaultGameController = gameFactory.setupGame(IGameFactory.GameType.DEFAULT, null);
	}
	/* (non-Javadoc)
	 * @see skrull.game.controller.IServerController#ProcessClientAction(skrull.game.view.ClientAction)
	 */
	@Override
	public void processClientAction(IClientAction action) {
		switch (action.getActionType())
		{
			case CHAT:
			case MOVE:
			case JOIN_GAME:
			{
				final IGameController game = getActiveGameController(action);
				// for now if the game isn't found this will throw  
				// NullPointerException. 
				game.processGameAction(action);
			}	
			break;
			
			// setting up a new game
			case CREATE_GAME:
			{
				// first we should remove the player from the default game...
				defaultGameController.processGameAction(action);

				// then setup a new game and assign this player
				final IGameController gameController = gameFactory.setupGame(action.getGameType(), action.getPlayer());
				activeGameControllers.add(gameController);
				
				// finally process the action via the game controller
				gameController.processGameAction(action);
			}	
			break;
			
			// first-time connection, come on!
			case JOIN_SERVER:
				// what to do here?
				// assign player into the default game, give them a player id
				// 1. hook up the client listener for later notifications
				defaultGameController.processGameAction(action);
			break;
			

			case QUIT:
				// something bad happened or the client 
				// was closed. Hey, it happens
				// what to do here?
				// 1. remove the player from any game (handled by game)
				// 2. maybe remove the game if the player was the only member(handled by game)
				// 3. notify other clients (handled by game)
				{
					final IGameController gameController = getActiveGameController(action);
					// for now if the game isn't found this will throw  
					// NullPointerException. 
					gameController.processGameAction(action);
				}	
			break;
			
			default:
				throw new RuntimeException("Action type " + action.getActionType() + " is not handled - it must be handled!");
		
		} // end switch
	
	}

	/**
	 * Get the controller matched to the current game action
	 * @param action
	 * @return the active game, or null if no match
	 */
	private IGameController getActiveGameController(IClientAction action) {
		
		for (IGameController game : activeGameControllers)
		{
			if (game.getGameType().equals(action.getGameType())
					&& game.getGameId() == action.getGameId()){
				return game;
			}
		}
		
		return null;
	}

	protected void addGameController(IGameController controller){
		activeGameControllers.add(controller);
	}
	
	/* (non-Javadoc)
	 * @see skrull.game.controller.IServerController#getControllers()
	 */
	@Override
	public Collection<IGameController> getControllers() {
		throw new UnsupportedOperationException();
	}


}