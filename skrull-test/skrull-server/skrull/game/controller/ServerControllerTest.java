package skrull.game.controller;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import skrull.game.factory.IGameFactory;
import skrull.game.model.IPlayer;
import skrull.game.view.IClientAction;
import skrull.game.view.IClientAction.ActionType;

public class ServerControllerTest {

	private IServerController controller;
	private IGameFactory gameFactory;
	private IClientAction action;
	private IGameController gameController;

	@Before
	public void setUp() throws Exception {
		gameFactory = EasyMock.createNiceMock(IGameFactory.class);
		controller = new ServerController(gameFactory);
		action = EasyMock.createNiceMock(IClientAction.class);
		gameController = EasyMock.createNiceMock(IGameController.class);
	}

	// need to test all the action types: 		JOIN_SERVER, CREATE_GAME, JOIN_GAME, MOVE, CHAT, QUIT;
	
	
		/** 
		 * this can probably 
		 * serve as a rough template for the other tyes of tests
		 * @throws Exception
		 */
	
	// broken until a real game comes back from 
		@Test
		public void testProcessClientActionCreateGame() throws Exception {

			EasyMock.expect(action.getActionType()).andReturn(ActionType.CREATE_GAME);
			EasyMock.expect(gameFactory.setupGame(EasyMock.anyObject(String.class), EasyMock.anyObject(IPlayer.class))).andReturn(gameController);
			
			EasyMock.replay(action);
			controller.ProcessClientAction(action);
			EasyMock.verify(action);
		}

}
