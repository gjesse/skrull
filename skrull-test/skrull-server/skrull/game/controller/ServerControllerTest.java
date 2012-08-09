package skrull.game.controller;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import skrull.game.factory.IGameFactory;
import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.IPlayer;
import skrull.game.view.IClientAction;
import skrull.game.view.IClientAction.ActionType;

public class ServerControllerTest {

	private static final Integer GAME_ID =  IGameFactory.DEFAULT_GAME_ID;
	private ServerController controller;
	private IGameFactory gameFactory;
	private IClientAction action;
	private IGameController gameController;
	private IGameController defaultGameController;

	@Before
	public void setUp() throws Exception {
		gameFactory = EasyMock.createMock(IGameFactory.class);

		action = EasyMock.createNiceMock(IClientAction.class);
		defaultGameController = EasyMock.createNiceMock(IGameController.class);
		gameController = EasyMock.createNiceMock(IGameController.class);
		EasyMock.expect(gameFactory.setupGame(GameType.DEFAULT,null, 0)).andReturn(defaultGameController);

	}

	// need to test all the action types: 		JOIN_SERVER, CREATE_GAME, JOIN_GAME, MOVE, CHAT, QUIT;
	
	
		/** 
		 * this can probably 
		 * serve as a rough template for the other tyes of tests
		 * @throws Exception
		 */
	
	@Test(expected=UnsupportedOperationException.class)
	public void testProcessClientActionCreateGameDefault() throws Exception {
		IPlayer mockPlayer = EasyMock.createNiceMock(IPlayer.class);
		EasyMock.expect(action.getActionType()).andReturn(ActionType.CREATE_GAME).atLeastOnce();
		EasyMock.expect(action.getPlayer()).andReturn(mockPlayer);
		EasyMock.expect(action.getGameType()).andReturn(GameType.DEFAULT).atLeastOnce();

		
		EasyMock.replay(action, gameFactory, defaultGameController);
		controller = new ServerController(gameFactory);
		//controller.addGameController(gameController);
		controller.processClientAction(action);
		EasyMock.verify(action, gameFactory, defaultGameController);

	}
	
		@Test
		public void testProcessClientActionCreateGameSpecific() throws Exception {
			IPlayer mockPlayer = EasyMock.createNiceMock(IPlayer.class);
			EasyMock.expect(action.getActionType()).andReturn(ActionType.CREATE_GAME).atLeastOnce();
			EasyMock.expect(action.getPlayer()).andReturn(mockPlayer);
			EasyMock.expect(action.getGameType()).andReturn(GameType.TIC_TAC_TOE).atLeastOnce();

			EasyMock.expect(gameFactory.setupGame(GameType.TIC_TAC_TOE, mockPlayer, 1)).andReturn(gameController);
			
			gameController.processGameAction(action);
			EasyMock.expectLastCall();
			
			EasyMock.replay(action, gameFactory, gameController);
			controller = new ServerController(gameFactory);
			controller.addGameController(gameController);
			controller.processClientAction(action);
			EasyMock.verify(action, gameFactory, gameController);

		}
		
		@Test
		public void testProcessClientActionJoinDefault() throws Exception {


			ActionType t = ActionType.JOIN_GAME;
			EasyMock.expect(action.getActionType()).andReturn(t);
			
			EasyMock.expect(action.getGameType()).andReturn(GameType.DEFAULT);
			EasyMock.expect(action.getGameId()).andReturn(GAME_ID);
			
			EasyMock.expect(defaultGameController.getGameType()).andReturn(GameType.DEFAULT).atLeastOnce();
			EasyMock.expect(defaultGameController.getGameId()).andReturn(GAME_ID);


			
			defaultGameController.processGameAction(action);
			EasyMock.expectLastCall();
			
			EasyMock.replay(gameFactory, action, defaultGameController);
			controller = new ServerController(gameFactory);
			//controller.addGameController(gameController);
			controller.processClientAction(action);
			EasyMock.verify(gameFactory, action, defaultGameController);
			

		}

		
		@Test
		public void testProcessClientActionJoinSpecific() throws Exception {


			final ActionType t = ActionType.JOIN_GAME;
			
			EasyMock.expect(action.getActionType()).andReturn(t);
			
			EasyMock.expect(action.getGameType()).andReturn(GameType.TIC_TAC_TOE).atLeastOnce();
			EasyMock.expect(action.getGameId()).andReturn(GAME_ID + 1).atLeastOnce();
			
			EasyMock.expect(defaultGameController.getGameType()).andReturn(GameType.DEFAULT).anyTimes();
			EasyMock.expect(defaultGameController.getGameId()).andReturn(GAME_ID).anyTimes();
			
			EasyMock.expect(gameController.getGameType()).andReturn(GameType.TIC_TAC_TOE);
			EasyMock.expect(gameController.getGameId()).andReturn(GAME_ID + 1);
			
			
			


			
			gameController.processGameAction(action);
			EasyMock.expectLastCall();
			
			EasyMock.replay(gameController, gameFactory, action, defaultGameController);
			controller = new ServerController(gameFactory);
			controller.addGameController(gameController);
			controller.processClientAction(action);
			EasyMock.verify(gameController, gameFactory, action, defaultGameController);
			

		}
		
		@Test
		public void testProcessClientActionMoveDefault() throws Exception {


			ActionType t = ActionType.MOVE;
			EasyMock.expect(action.getActionType()).andReturn(t);
			
			EasyMock.expect(action.getGameType()).andReturn(GameType.DEFAULT);
			EasyMock.expect(action.getGameId()).andReturn(GAME_ID);
			
			EasyMock.expect(defaultGameController.getGameType()).andReturn(GameType.DEFAULT);
			EasyMock.expect(defaultGameController.getGameId()).andReturn(GAME_ID);


			
			defaultGameController.processGameAction(action);
			EasyMock.expectLastCall();
			
			EasyMock.replay(gameFactory,action, defaultGameController);
			controller = new ServerController(gameFactory);
			//controller.addGameController(gameController);
			controller.processClientAction(action);
			EasyMock.verify(gameFactory,action, defaultGameController);
			

		}
		
		
		@Test
		public void testProcessClientActionMoveSpecific() throws Exception {


			final ActionType t = ActionType.MOVE;
			
			EasyMock.expect(action.getActionType()).andReturn(t);
			
			EasyMock.expect(action.getGameType()).andReturn(GameType.TIC_TAC_TOE).atLeastOnce();
			EasyMock.expect(action.getGameId()).andReturn(GAME_ID + 1).atLeastOnce();
			
			EasyMock.expect(defaultGameController.getGameType()).andReturn(GameType.DEFAULT).anyTimes();
			EasyMock.expect(defaultGameController.getGameId()).andReturn(GAME_ID).anyTimes();
			
			EasyMock.expect(gameController.getGameType()).andReturn(GameType.TIC_TAC_TOE);
			EasyMock.expect(gameController.getGameId()).andReturn(GAME_ID + 1);
			
			
			


			
			gameController.processGameAction(action);
			EasyMock.expectLastCall();
			
			EasyMock.replay(gameController, gameFactory, action, defaultGameController);
			controller = new ServerController(gameFactory);
			controller.addGameController(gameController);
			controller.processClientAction(action);
			EasyMock.verify(gameController, gameFactory, action, defaultGameController);
			

		}
		
		
		@Test
		public void testProcessClientActionChatDefault() throws Exception {


			ActionType t = ActionType.CHAT;
			EasyMock.expect(action.getActionType()).andReturn(t);
			
			EasyMock.expect(action.getGameType()).andReturn(GameType.DEFAULT);
			EasyMock.expect(action.getGameId()).andReturn(GAME_ID);
			
			EasyMock.expect(defaultGameController.getGameType()).andReturn(GameType.DEFAULT);
			EasyMock.expect(defaultGameController.getGameId()).andReturn(GAME_ID);


			
			defaultGameController.processGameAction(action);
			EasyMock.expectLastCall();
			
			EasyMock.replay(gameFactory, action, defaultGameController);
			controller = new ServerController(gameFactory);
			//controller.addGameController(gameController);
			controller.processClientAction(action);
			EasyMock.verify(gameFactory, action, defaultGameController);
			

		}
		
		@Test
		public void testProcessClientActionChatSpecific() throws Exception {


			final ActionType t = ActionType.CHAT;
			
			EasyMock.expect(action.getActionType()).andReturn(t);
			
			EasyMock.expect(action.getGameType()).andReturn(GameType.TIC_TAC_TOE).atLeastOnce();
			EasyMock.expect(action.getGameId()).andReturn(GAME_ID + 1).atLeastOnce();
			
			EasyMock.expect(defaultGameController.getGameType()).andReturn(GameType.DEFAULT).anyTimes();
			EasyMock.expect(defaultGameController.getGameId()).andReturn(GAME_ID).anyTimes();
			
			EasyMock.expect(gameController.getGameType()).andReturn(GameType.TIC_TAC_TOE);
			EasyMock.expect(gameController.getGameId()).andReturn(GAME_ID + 1);
			
			
			


			
			gameController.processGameAction(action);
			EasyMock.expectLastCall();
			
			EasyMock.replay(gameController, gameFactory, action, defaultGameController);
			controller = new ServerController(gameFactory);
			controller.addGameController(gameController);
			controller.processClientAction(action);
			EasyMock.verify(gameController, gameFactory, action, defaultGameController);
			

		}
		
		
		@Test
		public void testProcessClientActionQuitDefault() throws Exception {


			ActionType t = ActionType.QUIT;
			EasyMock.expect(action.getActionType()).andReturn(t);
			
			EasyMock.expect(action.getGameType()).andReturn(GameType.DEFAULT);
			EasyMock.expect(action.getGameId()).andReturn(GAME_ID);
			
			EasyMock.expect(defaultGameController.getGameType()).andReturn(GameType.DEFAULT);
			EasyMock.expect(defaultGameController.getGameId()).andReturn(GAME_ID);


			
			defaultGameController.processGameAction(action);
			EasyMock.expectLastCall();
			
			EasyMock.replay(gameFactory, action, defaultGameController);
			controller = new ServerController(gameFactory);
			controller.addGameController(gameController);
			controller.processClientAction(action);
			EasyMock.verify(gameFactory, action, defaultGameController);
			

		}
		
		@Test
		public void testProcessClientActionQuitSpecific() throws Exception {


			final ActionType t = ActionType.QUIT;
			
			EasyMock.expect(action.getActionType()).andReturn(t);
			
			EasyMock.expect(action.getGameType()).andReturn(GameType.TIC_TAC_TOE).atLeastOnce();
			EasyMock.expect(action.getGameId()).andReturn(GAME_ID + 1).atLeastOnce();
			
			EasyMock.expect(defaultGameController.getGameType()).andReturn(GameType.DEFAULT).anyTimes();
			EasyMock.expect(defaultGameController.getGameId()).andReturn(GAME_ID).anyTimes();
			
			EasyMock.expect(gameController.getGameType()).andReturn(GameType.TIC_TAC_TOE);
			EasyMock.expect(gameController.getGameId()).andReturn(GAME_ID + 1);
			
			
			


			
			gameController.processGameAction(action);
			EasyMock.expectLastCall();
			
			EasyMock.replay(gameController, gameFactory, action, defaultGameController);
			controller = new ServerController(gameFactory);
			controller.addGameController(gameController);
			controller.processClientAction(action);
			EasyMock.verify(gameController, gameFactory, action, defaultGameController);
			

		}
		
		@Test
		public void testProcessClientActionJoinServer() throws Exception {
			final ActionType t = ActionType.JOIN_SERVER;
			EasyMock.expect(action.getActionType()).andReturn(t);
			//EasyMock.expect(gameFactory.listAvailableGames()).andReturn(new String[]{"game1","game2","game3"});

			defaultGameController.processGameAction(action);
			EasyMock.expectLastCall();
			
			EasyMock.expect(action.getGameType()).andReturn(GameType.DEFAULT);
			EasyMock.expect(action.getGameId()).andReturn(GAME_ID);
			
			EasyMock.expect(defaultGameController.getGameType()).andReturn(GameType.DEFAULT);
			EasyMock.expect(defaultGameController.getGameId()).andReturn(GAME_ID);
			
			EasyMock.replay(defaultGameController, action, gameFactory);
			controller = new ServerController(gameFactory);
			//controller.addGameController(gameController);
			controller.processClientAction(action);
			EasyMock.verify(defaultGameController, action, gameFactory);
		}

}
