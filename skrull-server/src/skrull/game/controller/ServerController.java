package skrull.game.controller;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import skrull.game.factory.IGameFactory;
import skrull.game.view.IClientAction;

public class ServerController implements IServerController {
	private List<IGameController> activeGames = new CopyOnWriteArrayList<IGameController>();
	private IGameFactory gameFactory;
	
	// TODO: implement this
	private ActivityMonitor activityMontor;
	

	public ServerController(IGameFactory gameFactory){
		this.gameFactory = gameFactory;
	}
	/* (non-Javadoc)
	 * @see skrull.game.controller.IServerController#ProcessClientAction(skrull.game.view.ClientAction)
	 */
	@Override
	public void ProcessClientAction(IClientAction action) {
		switch (action.getActionType())
		{
			case CHAT:
			case MOVE:
			case JOIN_GAME:
			{
				final IGameController game = getActiveGame(action);
				// for now if the game isn't found this will throw  
				// NullPointerException. 
				game.processGameAction(action);
			}	
			break;
			
			// setting up a new game
			case CREATE_GAME:
			{
				final IGameController game = gameFactory.setupGame(action.getActionMessage(), action.getPlayer());
				activeGames.add(game);
				game.processGameAction(action);
			}	
			break;
			
			// first-time connection, come on!
			case JOIN_SERVER:
				
			break;
			
			// something bad happened or the client 
			// was closed. Hey, it happens
			case QUIT:
				
				
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
	private IGameController getActiveGame(IClientAction action) {
		
		for (IGameController game : activeGames)
		{
			if (game.getGameType().equals(action.getGameType())
					&& game.getGameId() == action.getGameId()){
				return game;
			}
		}
		
		return null;
	}

	protected void addGameController(IGameController controller){
		activeGames.add(controller);
	}
	
	/* (non-Javadoc)
	 * @see skrull.game.controller.IServerController#getControllers()
	 */
	@Override
	public Collection<IGameController> getControllers() {
		throw new UnsupportedOperationException();
	}


}