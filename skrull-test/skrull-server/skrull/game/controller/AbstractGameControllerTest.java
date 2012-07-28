package skrull.game.controller;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import skrull.game.model.IGameModel;

public class AbstractGameControllerTest {

	private IGameModel model;
	private AbstractGameController controller;

	@Before
	public void setUp() throws Exception {
		
		model = EasyMock.createMock(IGameModel.class);
		controller = new DefaultGameController(model);
	}

	@Test
	public void testCheckActivity() {

		model.checkActivity();
		EasyMock.expectLastCall();
		
		EasyMock.replay(model);
		controller.checkActivity();
		EasyMock.verify(model);
	}

	@Test
	public void testProcessGameAction() {
		// TODO: this should be tested
		fail("Not yet implemented");
	}

}
