package skrull.game.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
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
		switch (action.getActionType()){
		case CHAT:
		case MOVE:
			// move and  chat stuff go to the game controller
		break;
		
		// setting up a new game
		case CREATE_GAME:
			IGameController game = gameFactory.setupGame(action.getActionMessage(), action.getPlayer());
			activeGames.add(game);
			game.processGameAction(action);
			
		break;
		
		// trying to join an existing game
		case JOIN_GAME:
			
		break;
		
		// first-time connection, come on!
		case JOIN_SERVER:
			
		break;
		
		// something bad happened or the client 
		// was close. Hey, it happens
		case QUIT:
			
			
		break;
			
		}
	
	}

	/* (non-Javadoc)
	 * @see skrull.game.controller.IServerController#getControllers()
	 */
	@Override
	public Collection<IGameController> getControllers() {
		throw new UnsupportedOperationException();
	}


}