package skrull.game.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

import skrull.game.factory.IGameFactory;
import skrull.game.factory.IGameFactory.GameType;
import skrull.game.view.IClientAction;
import skrull.util.logging.SkrullLogger;

public class ServerController implements IServerController {
	private List<IGameController> activeGameControllers = new CopyOnWriteArrayList<IGameController>();
	private IGameFactory gameFactory;
	private IGameController defaultGameController;
	private static final Logger logger = SkrullLogger.getLogger(ServerController.class);


	// maintains a sequence of id numbers for each game
	private AtomicInteger gameIdSequence = new AtomicInteger( IGameFactory.DEFAULT_GAME_ID );	

	public ServerController(IGameFactory gameFactory){
		this.gameFactory = gameFactory;
		int next =  nextGameId();
		this.defaultGameController = gameFactory.setupGame(IGameFactory.GameType.DEFAULT, null, next);
		defaultGameController.setServerController(this);
		activeGameControllers.add(defaultGameController);
	}
	/* (non-Javadoc)
	 * @see skrull.game.controller.IServerController#ProcessClientAction(skrull.game.view.ClientAction)
	 */
	@Override
	public void processClientAction(IClientAction action) {
		logger.debug("processing action: " + action);
		switch (action.getActionType())
		{
			case CHAT:
			case MOVE:
			case JOIN_GAME:
			case JOIN_SERVER: // TODO: join server is not needed - we can just use JOIN_GAME for the default game in this case
			{
				final IGameController gameController = getActiveGameController(action);
				// for now if the game isn't found this will throw  
				// NullPointerException. 
				gameController.processGameAction(action);
			}	
			break;
	
			// first-time connection, come on!
			//case JOIN_SERVER:
				// what to do here?
				// assign player into the default game, give them a player id
				// 1. hook up the client listener for later notifications
				//defaultGameController.processGameAction(action);
			//break;
			

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
			
			// setting up a new game
			case CREATE_GAME:
			{
				logger.debug("create game of type " + action.getGameType());
				if (action.getGameType().equals(GameType.DEFAULT)){
					throw new UnsupportedOperationException("Cannot create additional default games");
				}

				// then setup a new game and assign this player
				final IGameController gameController = gameFactory.setupGame(action.getGameType(),action.getPlayer(), nextGameId());
				gameController.setServerController(this);
				activeGameControllers.add(gameController);
				
				// finally process the action via the game controller
				gameController.processGameAction(action);
			}	
			break;
			
			default:
				throw new RuntimeException("Action type " + action.getActionType() + " is not handled - it must be handled!");
		
		} // end switch
	
	}

	private int nextGameId() {
		return gameIdSequence.getAndIncrement();
	}
	
	/**
	 * Get the controller matched to the current game action
	 * @param action
	 * @return the active game, or default game if no match
	 */
	private IGameController getActiveGameController(IClientAction action) {
		
		for (IGameController gameController : activeGameControllers)
		{
			if (gameController.getGameType().equals(action.getGameType())
					&& gameController.getGameId() == action.getGameId()){
				return gameController;
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
		return Collections.unmodifiableList(activeGameControllers);
	}


}