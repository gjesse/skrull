package skrull.game.controller;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import skrull.game.view.IClientAction;
import skrull.game.view.IClientAction.ActionType;

public class ServerControllerTest {

	private IServerController controller;

	@Before
	public void setUp() throws Exception {
		controller = new ServerController();
	}

	// need to test all the action types: 		JOIN_SERVER, CREATE_GAME, JOIN_GAME, MOVE, CHAT, QUIT;
	
		@Test
		public void testProcessClientActionCreateGame() throws Exception {
			IClientAction action = EasyMock.createNiceMock(IClientAction.class);

			EasyMock.expect(action.getActionType()).andReturn(ActionType.CREATE_GAME);
			// TODO: we should additionally expect a call to the game factory for the game
			// then the controller should be assigned
			// then we process some action on the controller
			EasyMock.replay(action);
			controller.ProcessClientAction(action);
			EasyMock.verify(action);
		}

}
