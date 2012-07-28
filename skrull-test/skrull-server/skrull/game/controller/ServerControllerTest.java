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

	private static final GameType GAME_TYPE = GameType.DEFAULT;
	private static final Integer GAME_ID = 0;
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
		EasyMock.expect(gameFactory.setupGame(GameType.DEFAULT, null,0)).andReturn(defaultGameController);

	}

	// need to test all the action types: 		JOIN_SERVER, CREATE_GAME, JOIN_GAME, MOVE, CHAT, QUIT;
	
	
		/** 
		 * this can probably 
		 * serve as a rough template for the other tyes of tests
		 * @throws Exception
		 */
	
		@Test
		public void testProcessClientActionCreateGame() throws Exception {
			IPlayer mockPlayer = EasyMock.createNiceMock(IPlayer.class);
			EasyMock.expect(action.getActionType()).andReturn(ActionType.CREATE_GAME).atLeastOnce();
			EasyMock.expect(action.getPlayer()).andReturn(mockPlayer);
			EasyMock.expect(action.getGameType()).andReturn(GameType.TIC_TAC_TOE);

			EasyMock.expect(gameFactory.setupGame(GameType.TIC_TAC_TOE, mockPlayer, 1)).andReturn(gameController);
			
			gameController.processGameAction(action);
			EasyMock.expectLastCall();
			
			EasyMock.replay(action, gameFactory, gameController);
			controller = new ServerController(gameFactory);
			//controller.addGameController(gameController);
			controller.processClientAction(action);
			EasyMock.verify(action, gameFactory, gameController);

		}
		
		@Test
		public void testProcessClientActionJoin() throws Exception {


			ActionType t = ActionType.JOIN_GAME;
			EasyMock.expect(action.getActionType()).andReturn(t);
			
			EasyMock.expect(action.getGameType()).andReturn(GAME_TYPE);
			EasyMock.expect(action.getGameId()).andReturn(GAME_ID);
			
			EasyMock.expect(gameController.getGameType()).andReturn(GAME_TYPE);
			EasyMock.expect(gameController.getGameId()).andReturn(GAME_ID);


			
			gameController.processGameAction(action);
			EasyMock.expectLastCall();
			
			EasyMock.replay(action, gameController);
			controller = new ServerController(gameFactory);
			controller.addGameController(gameController);
			controller.processClientAction(action);
			EasyMock.verify(action, gameController);
			

		}

		
		@Test
		public void testProcessClientActionMove() throws Exception {


			ActionType t = ActionType.MOVE;
			EasyMock.expect(action.getActionType()).andReturn(t);
			
			EasyMock.expect(action.getGameType()).andReturn(GAME_TYPE);
			EasyMock.expect(action.getGameId()).andReturn(GAME_ID);
			
			EasyMock.expect(gameController.getGameType()).andReturn(GAME_TYPE);
			EasyMock.expect(gameController.getGameId()).andReturn(GAME_ID);


			
			gameController.processGameAction(action);
			EasyMock.expectLastCall();
			
			EasyMock.replay(action, gameController);
			controller = new ServerController(gameFactory);
			controller.addGameController(gameController);
			controller.processClientAction(action);
			EasyMock.verify(action, gameController);
			

		}
		
		@Test
		public void testProcessClientActionChat() throws Exception {


			ActionType t = ActionType.CHAT;
			EasyMock.expect(action.getActionType()).andReturn(t);
			
			EasyMock.expect(action.getGameType()).andReturn(GAME_TYPE);
			EasyMock.expect(action.getGameId()).andReturn(GAME_ID);
			
			EasyMock.expect(gameController.getGameType()).andReturn(GAME_TYPE);
			EasyMock.expect(gameController.getGameId()).andReturn(GAME_ID);


			
			gameController.processGameAction(action);
			EasyMock.expectLastCall();
			
			EasyMock.replay(action, gameController);
			controller = new ServerController(gameFactory);
			controller.addGameController(gameController);
			controller.processClientAction(action);
			EasyMock.verify(action, gameController);
			

		}
		
		@Test
		public void testProcessClientActionQuit() throws Exception {


			ActionType t = ActionType.QUIT;
			EasyMock.expect(action.getActionType()).andReturn(t);
			
			EasyMock.expect(action.getGameType()).andReturn(GAME_TYPE);
			EasyMock.expect(action.getGameId()).andReturn(GAME_ID);
			
			EasyMock.expect(gameController.getGameType()).andReturn(GAME_TYPE);
			EasyMock.expect(gameController.getGameId()).andReturn(GAME_ID);


			
			gameController.processGameAction(action);
			EasyMock.expectLastCall();
			
			EasyMock.replay(action, gameController);
			controller = new ServerController(gameFactory);
			controller.addGameController(gameController);
			controller.processClientAction(action);
			EasyMock.verify(action, gameController);
			

		}
		
		@Test
		public void testProcessClientActionJoinServer() throws Exception {
			final ActionType t = ActionType.JOIN_SERVER;
			EasyMock.expect(action.getActionType()).andReturn(t);
			//EasyMock.expect(gameFactory.listAvailableGames()).andReturn(new String[]{"game1","game2","game3"});

			defaultGameController.processGameAction(action);
			EasyMock.expectLastCall();
			
			EasyMock.replay(action, gameFactory);
			controller = new ServerController(gameFactory);
			controller.addGameController(gameController);
			controller.processClientAction(action);
			EasyMock.verify(action, gameFactory);
		}

}
