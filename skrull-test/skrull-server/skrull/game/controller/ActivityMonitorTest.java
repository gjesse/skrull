package skrull.game.controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class ActivityMonitorTest {

	private ActivityMonitor monitor;
	private IServerController serverController;
	private Collection<IGameController> gameControllers;
	private IGameController game1;
	private IGameController game2;
	private IGameController game3;

	@Before
	public void setUp() throws Exception {
		serverController = EasyMock.createMock(IServerController.class);
		
		game1 = EasyMock.createMock(IGameController.class);
		game2 = EasyMock.createMock(IGameController.class);
		game3 = EasyMock.createMock(IGameController.class);

		gameControllers = new ArrayList<IGameController>();
		
		gameControllers.add(game1);
		gameControllers.add(game2);
		gameControllers.add(game3);

		
		
		monitor = new ActivityMonitor(serverController);
		monitor.setCheckInterval(5);
	}

	@Test
	public void testCheckActivity() {
		
		for (IGameController mockController: gameControllers){
			mockController.checkActivity();
			EasyMock.expectLastCall();
		}
		
		EasyMock.expect(serverController.getControllers()).andReturn(gameControllers);
		
		EasyMock.replay(serverController, game1, game2, game3);
		// this runs forever so we need to kill somehow?
		monitor.checkActivity();
		EasyMock.verify(serverController, game1, game2, game3);

		
	}

}
