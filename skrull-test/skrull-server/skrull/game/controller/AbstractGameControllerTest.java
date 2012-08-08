package skrull.game.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.Before;
import org.junit.Test;

import skrull.SkrullException;
import skrull.game.model.IGameModel;
import skrull.game.view.IClientAction;
import skrull.game.view.IClientAction.ActionType;

public class AbstractGameControllerTest {

	private IGameModel model;
	private AbstractGameController controller;
	private IClientAction action;
	private IMocksControl ctrl;
	private IServerController serverController;

	@Before
	public void setUp() throws Exception {
		
	    ctrl = EasyMock.createControl();
	    model = ctrl.createMock(IGameModel.class);
		action  = ctrl.createMock(IClientAction.class);
		serverController = ctrl.createMock(IServerController.class);
		controller = new DefaultGameController(model);
		controller.setServerController(serverController);

	}

	@Test
	public void testCheckActivity() {

		model.checkActivity();
		
		ctrl.replay();
		controller.checkActivity();
		ctrl.verify();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testProcessGameActionChat() throws SkrullException {
		ActionType a = ActionType.CHAT;
		
		EasyMock.expect(action.getActionType()).andReturn(a).atLeastOnce();
		
		ArrayList<IGameController> active = new ArrayList<IGameController>();
		EasyMock.expect(serverController.getControllers()).andReturn(active);
		model.setActiveGames((Collection<IGameModel>) EasyMock.anyObject());
		EasyMock.expectLastCall();
		
		
		model.chatUpdate(action);

		ctrl.replay();
		controller.processGameAction(action);
		ctrl.verify();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testProcessGameActionMove() throws SkrullException {
		ActionType a = ActionType.MOVE;
		
		EasyMock.expect(action.getActionType()).andReturn(a).atLeastOnce();
		
		ArrayList<IGameController> active = new ArrayList<IGameController>();
		EasyMock.expect(serverController.getControllers()).andReturn(active);
		model.setActiveGames((Collection<IGameModel>) EasyMock.anyObject());
		EasyMock.expectLastCall();
		model.processMove(action);

		ctrl.replay();
		controller.processGameAction(action);
		ctrl.verify();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testProcessGameActionJoinGame() throws SkrullException {
		ActionType a = ActionType.JOIN_GAME;
		
		
		EasyMock.expect(action.getActionType()).andReturn(a).atLeastOnce();
		
		ArrayList<IGameController> active = new ArrayList<IGameController>();
		EasyMock.expect(serverController.getControllers()).andReturn(active);
		model.setActiveGames((Collection<IGameModel>) EasyMock.anyObject());
		EasyMock.expectLastCall();
		
		model.joinGame(action);

		ctrl.replay();
		controller.processGameAction(action);
		ctrl.verify();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testProcessGameActionJoinServer() throws SkrullException {
		ActionType a = ActionType.JOIN_SERVER;
		
		EasyMock.expect(action.getActionType()).andReturn(a).atLeastOnce();
		
		ArrayList<IGameController> active = new ArrayList<IGameController>();
		EasyMock.expect(serverController.getControllers()).andReturn(active);
		model.setActiveGames((Collection<IGameModel>) EasyMock.anyObject());
		EasyMock.expectLastCall();
		model.joinGame(action);

		ctrl.replay();
		controller.processGameAction(action);
		ctrl.verify();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testProcessGameActionCreateGame() throws SkrullException {
		ActionType a = ActionType.CREATE_GAME;
		
		EasyMock.expect(action.getActionType()).andReturn(a).atLeastOnce();
		
		ArrayList<IGameController> active = new ArrayList<IGameController>();
		EasyMock.expect(serverController.getControllers()).andReturn(active);
		model.setActiveGames((Collection<IGameModel>) EasyMock.anyObject());
		EasyMock.expectLastCall();
		model.createGame(action);

		ctrl.replay();
		controller.processGameAction(action);
		ctrl.verify();
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testProcessGameActionQuit() throws SkrullException {
		ActionType a = ActionType.QUIT;
		
		EasyMock.expect(action.getActionType()).andReturn(a).atLeastOnce();
		
		ArrayList<IGameController> active = new ArrayList<IGameController>();
		EasyMock.expect(serverController.getControllers()).andReturn(active);
		model.setActiveGames((Collection<IGameModel>) EasyMock.anyObject());
		EasyMock.expectLastCall();
		model.quit(action);

		ctrl.replay();
		controller.processGameAction(action);
		ctrl.verify();
	}
	
}
