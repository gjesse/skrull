package skrull.game.controller;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import skrull.game.view.IClientAction;

public class ActionWorkerTest {

	private IClientAction action;
	private IServerController serverController;

	@Before
	public void setUp() throws Exception {
		action = EasyMock.createNiceMock(IClientAction.class);
		serverController = EasyMock.createNiceMock(IServerController.class);
	}

	@Test
	public void testWorker() {
		IActionWorker worker = new ActionWorker(action, serverController);
		
		serverController.ProcessClientAction(action);
		EasyMock.expectLastCall();
		
		EasyMock.replay(serverController);
		worker.run();
		EasyMock.verify(serverController);

	}

}
