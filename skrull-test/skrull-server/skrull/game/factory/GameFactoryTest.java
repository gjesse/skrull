package skrull.game.factory;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import skrull.game.controller.DefaultGameController;
import skrull.game.controller.IGameController;
import skrull.game.controller.TicTacToeController;
import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.IGameModel;
import skrull.game.model.IPlayer;
import skrull.game.model.tictactoe.TicTacToe;
import skrull.rmi.server.IClientUpdater;

public class GameFactoryTest {

	private GameFactory factory;

	@Before
	public void setUp() throws Exception {
		factory = new GameFactory(EasyMock.createNiceMock(IClientUpdater.class));
	}

	@Test
	public void testSetupDefaultGame() {
		IPlayer player = EasyMock.createMock(IPlayer.class);
		IGameController controller = factory.setupGame(GameType.DEFAULT, player, 44);
		assertNotNull(controller);
		assertTrue(controller instanceof DefaultGameController);
		
		assertEquals(44, controller.getGameId());
		assertEquals(GameType.DEFAULT, controller.getGameType());
		
	}

	// failing now but should start passing when
	// tic tac toe is implemented
	@Test
	public void testSetupTicTacToe() {
		IPlayer player = EasyMock.createMock(IPlayer.class);
		IGameController controller = factory.setupGame(GameType.TIC_TAC_TOE, player, 44);
		assertNotNull(controller);
		assertTrue(controller instanceof TicTacToeController);
		
		assertEquals(44, controller.getGameId());
		assertEquals(GameType.TIC_TAC_TOE, controller.getGameType());
		
	}
}
