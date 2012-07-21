package skrull.game.controller;

import java.util.Collection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import skrull.rmi.server.IServerListener;
import skrull.game.factory.GameFactory;
import skrull.game.view.IClientAction;

public class ServerController implements IServerController {
	private IGameController[] _activeGamesArray;
	private ActivityMonitor _activityMonitor;
	private IActionWorker[] _actionWorkers;
	private IServerListener _unnamed_ServerListener_;
	private IGameController _unnamed_GameController_;
	private GameFactory _unnamed_Game_Factory_;
	private ActivityMonitor _unnamed_ActivityMonitor_;
	private IActionWorker _unnamed_ActionWorker_;
	

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
	public Collection<IGameController> getControllers() {
		throw new UnsupportedOperationException();
	}


}