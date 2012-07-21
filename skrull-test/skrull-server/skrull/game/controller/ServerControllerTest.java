package skrull.game.controller;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import skrull.game.factory.IGameFactory;
import skrull.game.model.IPlayer;
import skrull.game.view.IClientAction;
import skrull.game.view.IClientAction.ActionType;

public class ServerControllerTest {

	private static final String GAME_TYPE = "THEMOSTAWESOMEGAMEEVER";
	private static final Integer GAME_ID = 42;
	private ServerController controller;
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
	
		@Test
		public void testProcessClientActionCreateGame() throws Exception {

			EasyMock.expect(action.getActionType()).andReturn(ActionType.CREATE_GAME);
			EasyMock.expect(gameFactory.setupGame(EasyMock.anyObject(String.class), EasyMock.anyObject(IPlayer.class))).andReturn(gameController);
			
			gameController.processGameAction(action);
			EasyMock.expectLastCall();
			
			EasyMock.replay(action, gameFactory, gameController);
			controller.ProcessClientAction(action);
			EasyMock.verify(action, gameFactory, gameController);

		}
		
		@Test
		public void testProcessClientActionJoin() throws Exception {


			ActionType t = ActionType.JOIN_GAME;
			controller.addGameController(gameController);
			EasyMock.expect(action.getActionType()).andReturn(t);
			
			EasyMock.expect(action.getGameType()).andReturn(GAME_TYPE);
			EasyMock.expect(action.getGameId()).andReturn(GAME_ID);
			
			EasyMock.expect(gameController.getGameType()).andReturn(GAME_TYPE);
			EasyMock.expect(gameController.getGameId()).andReturn(GAME_ID);


			
			gameController.processGameAction(action);
			EasyMock.expectLastCall();
			
			EasyMock.replay(action, gameController);
			controller.ProcessClientAction(action);
			EasyMock.verify(action, gameController);
			

		}

		
		@Test
		public void testProcessClientActionMove() throws Exception {


			ActionType t = ActionType.MOVE;
			controller.addGameController(gameController);
			EasyMock.expect(action.getActionType()).andReturn(t);
			
			EasyMock.expect(action.getGameType()).andReturn(GAME_TYPE);
			EasyMock.expect(action.getGameId()).andReturn(GAME_ID);
			
			EasyMock.expect(gameController.getGameType()).andReturn(GAME_TYPE);
			EasyMock.expect(gameController.getGameId()).andReturn(GAME_ID);


			
			gameController.processGameAction(action);
			EasyMock.expectLastCall();
			
			EasyMock.replay(action, gameController);
			controller.ProcessClientAction(action);
			EasyMock.verify(action, gameController);
			

		}
		
		@Test
		public void testProcessClientActionChat() throws Exception {


			ActionType t = ActionType.CHAT;
			controller.addGameController(gameController);
			EasyMock.expect(action.getActionType()).andReturn(t);
			
			EasyMock.expect(action.getGameType()).andReturn(GAME_TYPE);
			EasyMock.expect(action.getGameId()).andReturn(GAME_ID);
			
			EasyMock.expect(gameController.getGameType()).andReturn(GAME_TYPE);
			EasyMock.expect(gameController.getGameId()).andReturn(GAME_ID);


			
			gameController.processGameAction(action);
			EasyMock.expectLastCall();
			
			EasyMock.replay(action, gameController);
			controller.ProcessClientAction(action);
			EasyMock.verify(action, gameController);
			

		}
}
