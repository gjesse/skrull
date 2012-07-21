package skrull.rmi.server;


import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import skrull.game.controller.IActionWorker;
import skrull.game.controller.IActionWorkerFactory;
import skrull.game.controller.IServerController;
import skrull.game.view.IClientAction;

public class ServerListenerTest {

	IServerController mockController;
	private IServerListener listener;
	private IActionWorkerFactory workerFactory;
	private IActionWorker worker;
	
	@Before
	public void setUp(){
		mockController = EasyMock.createNiceMock(IServerController.class);
		workerFactory = EasyMock.createNiceMock(IActionWorkerFactory.class);
		worker = EasyMock.createNiceMock(IActionWorker.class);
		listener = new ServerListener(mockController, workerFactory);
	}
	
	
	@Test
	public void testProcessClientAction() throws Exception {
		IClientAction action = EasyMock.createNiceMock(IClientAction.class);

		EasyMock.expect(workerFactory.newWorker(action, mockController)).andReturn(worker);
		
		worker.run();
		EasyMock.expectLastCall();
	
		EasyMock.replay(workerFactory);
		EasyMock.replay(worker);
		listener.ProcessClientAction(action);
		EasyMock.verify(workerFactory);
		EasyMock.verify(worker);


		
	}
	
	

}
