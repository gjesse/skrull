package skrull.game.controller;

import skrull.rmi.server.IServerListener;
import skrull.game.factory.Game_Factory;
import skrull.game.view.IClientAction;

public class ServerController implements IServerController {
	private IGameController[] _activeGamesArray;
	private ActivityMonitor _activityMonitor;
	private IActionWorker[] _actionWorkers;
	public IServerListener _unnamed_ServerListener_;
	public IGameController _unnamed_GameController_;
	public Game_Factory _unnamed_Game_Factory_;
	public ActivityMonitor _unnamed_ActivityMonitor_;
	public IActionWorker _unnamed_ActionWorker_;

	/* (non-Javadoc)
	 * @see skrull.game.controller.IServerController#ProcessClientAction(skrull.game.view.ClientAction)
	 */
	@Override
	public void ProcessClientAction(IClientAction action) {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see skrull.game.controller.IServerController#getControllers()
	 */
	@Override
	public IGameController[] getControllers() {
		throw new UnsupportedOperationException();
	}

	/* (non-Javadoc)
	 * @see skrull.game.controller.IServerController#listGames()
	 */
	@Override
	public String[] listGames() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addGameController(IGameController controller) {
		// TODO Auto-generated method stub
		
	}
}