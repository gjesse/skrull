package skrull.rmi.client;


import org.easymock.EasyMock;
import org.junit.Test;

import skrull.game.model.IGameModel;
import skrull.game.view.IGameClientView;

public class ClientListenerTest {

	@Test
	public void testModelChanged() {

		
		IGameModel model = EasyMock.createMock(IGameModel.class);
		IGameClientView mockView =  EasyMock.createMock(IGameClientView.class);
		ClientListener listener = new ClientListener(mockView);
		
		mockView.modelChanged(model);
		EasyMock.expectLastCall();
		
		EasyMock.replay(mockView);
		listener.modelChanged(model);
		EasyMock.verify(mockView);
		
	}

}
