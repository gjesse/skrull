/**
 * 
 * @author kyle
 *
 */
package skrull.game.model.tictactoe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.UUID;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import skrull.SkrullGameException;
import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.IPlayer;
import skrull.game.model.Move;
import skrull.game.view.ClientAction;
import skrull.game.view.IClientAction;
import skrull.game.view.IClientAction.ActionType;
import skrull.game.view.Player;
import skrull.rmi.server.IClientUpdater;

public class TicTacToeTest {
	
	private int gameId = 1;
	private IClientAction actionIsOccupied;
	private IClientAction actionJoin;
	private IClientAction actionMove1;
	private IClientAction actionMove2;
	private IClientUpdater updater;
	private IPlayer player1;
	private IPlayer player2;
	private IPlayer player3;
	private Move move1;
	private Move move2;
	private final static UUID p1Id = UUID.randomUUID();
	private final static UUID p2Id = UUID.randomUUID();
	private final static UUID p3Id = UUID.randomUUID();
	private TicTacToe mygame;
		
	@Before
	public void setUp() throws Exception {

		updater = EasyMock.createMock(IClientUpdater.class);
		player1 = new Player(p1Id);
		player2 = new Player(p2Id);
		player3 = new Player(p3Id);
		if ( p1Id == p2Id || p1Id == p3Id || p2Id == p3Id )
			fail("UUID not unique");
		
		move1 = new Move();
		move2 = new Move();
		mygame = new TicTacToe(player1, gameId, updater);
		
		actionJoin = new ClientAction(gameId, player2, ActionType.JOIN_GAME, GameType.TIC_TAC_TOE, "", null);
		mygame.joinGame(actionJoin);
		
	}


	@Test(expected = SkrullGameException.class)
	public void testJoinGame() throws Exception {
		/** Confirm that 3rd person cannot join game */
		actionJoin = new ClientAction(gameId, player3, ActionType.JOIN_GAME, GameType.TIC_TAC_TOE, "", null);
		mygame.joinGame(actionJoin);

	}

	@Test
	public void testDoProcessMoveMoveCount() throws SkrullGameException {
		
		assertEquals(0,mygame.getMoveCount());
		
		move1.setMoveIndex(1);
		move1.setPlayer(player1);		
		actionMove1 = new ClientAction(gameId, player1, ActionType.MOVE, GameType.TIC_TAC_TOE, "", move1);
		mygame.doProcessMove(actionMove1);
		
		assertEquals(1,mygame.getMoveCount());
		
		move2.setMoveIndex(2);
		move2.setPlayer(player2);
		actionMove2 = new ClientAction(gameId, player2, ActionType.MOVE, GameType.TIC_TAC_TOE, "", move2);
		mygame.doProcessMove(actionMove2);
		
		assertEquals(2,mygame.getMoveCount());
		
		
	}

	@Test
	public void testDoProcessNineMoveDrawGame() throws SkrullGameException {
		
		/*
		 *                   MOVES           MOVE ORDER 
		 *                   x   o   o       7   2   6  
		 *                   o   x   x       4   1   3
		 *                   x   x   o       5   9   8
		 *   
		 */
		
		
		assertEquals(0,mygame.getMoveCount());
		assertEquals(false,mygame.isDraw());
		assertEquals(false,mygame.isGameOver());
		
		move1.setMoveIndex(4);
		move1.setPlayer(player1);		
		actionMove1 = new ClientAction(gameId, player1, ActionType.MOVE, GameType.TIC_TAC_TOE, "", move1);
		mygame.doProcessMove(actionMove1);
		assertEquals(1,mygame.getMoveCount());
		assertEquals(false,mygame.isDraw());
		assertEquals(false,mygame.isGameOver());
		assertEquals("X",actionMove1.getPlayer().getPlayerToken());
		
		move2.setMoveIndex(1);
		move2.setPlayer(player2);
		actionMove2 = new ClientAction(gameId, player2, ActionType.MOVE, GameType.TIC_TAC_TOE, "", move2);
		mygame.doProcessMove(actionMove2);
		assertEquals(2,mygame.getMoveCount());
		assertEquals(false,mygame.isDraw());
		assertEquals(false,mygame.isGameOver());
		
		move1.setMoveIndex(5);
		move1.setPlayer(player1);		
		actionMove1 = new ClientAction(gameId, player1, ActionType.MOVE, GameType.TIC_TAC_TOE, "", move1);
		mygame.doProcessMove(actionMove1);
		assertEquals(3,mygame.getMoveCount());
		assertEquals(false,mygame.isDraw());
		assertEquals(false,mygame.isGameOver());
		
		move2.setMoveIndex(3);
		move2.setPlayer(player2);
		actionMove2 = new ClientAction(gameId, player2, ActionType.MOVE, GameType.TIC_TAC_TOE, "", move2);
		mygame.doProcessMove(actionMove2);
		assertEquals(4,mygame.getMoveCount());
		assertEquals(false,mygame.isDraw());
		assertEquals(false,mygame.isGameOver());
		
		move1.setMoveIndex(6);
		move1.setPlayer(player1);		
		actionMove1 = new ClientAction(gameId, player1, ActionType.MOVE, GameType.TIC_TAC_TOE, "", move1);
		mygame.doProcessMove(actionMove1);
		assertEquals(5,mygame.getMoveCount());
		assertEquals(false,mygame.isDraw());
		assertEquals(false,mygame.isGameOver());
		
		move2.setMoveIndex(2);
		move2.setPlayer(player2);
		actionMove2 = new ClientAction(gameId, player2, ActionType.MOVE, GameType.TIC_TAC_TOE, "", move2);
		mygame.doProcessMove(actionMove2);
		assertEquals(6,mygame.getMoveCount());
		assertEquals(false,mygame.isDraw());
		assertEquals(false,mygame.isGameOver());
		
		move1.setMoveIndex(0);
		move1.setPlayer(player1);		
		actionMove1 = new ClientAction(gameId, player1, ActionType.MOVE, GameType.TIC_TAC_TOE, "", move1);
		mygame.doProcessMove(actionMove1);
		assertEquals(7,mygame.getMoveCount());
		assertEquals(false,mygame.isDraw());
		assertEquals(false,mygame.isGameOver());
		
		move2.setMoveIndex(8);
		move2.setPlayer(player2);
		actionMove2 = new ClientAction(gameId, player2, ActionType.MOVE, GameType.TIC_TAC_TOE, "", move2);
		mygame.doProcessMove(actionMove2);
		assertEquals(8,mygame.getMoveCount());
		assertEquals(false,mygame.isDraw());
		assertEquals(false,mygame.isGameOver());
		
		move1.setMoveIndex(7);
		move1.setPlayer(player1);		
		actionMove1 = new ClientAction(gameId, player1, ActionType.MOVE, GameType.TIC_TAC_TOE, "", move1);
		mygame.doProcessMove(actionMove1);
		assertEquals(9,mygame.getMoveCount());
		assertEquals(true,mygame.isDraw());
		assertEquals(true,mygame.isGameOver());
	}
	
	
	@Test
	public void testDoProcessEightMoveWinGame() throws SkrullGameException {
		/*
		 *      MOVES         MOVE ORDER 
		 *    o   o   o       8   2   6  
		 *    o   x   x       4   1   3
		 *    x   x   -       5   7   -  
		 */
		
		assertEquals(0,mygame.getMoveCount());
		assertEquals(false,mygame.isDraw());
		assertEquals(false,mygame.isGameOver());
		
		move1.setMoveIndex(4);
		move1.setPlayer(player1);		
		actionMove1 = new ClientAction(gameId, player1, ActionType.MOVE, GameType.TIC_TAC_TOE, "", move1);
		mygame.doProcessMove(actionMove1);
		assertEquals(1,mygame.getMoveCount());
		assertEquals(false,mygame.isDraw());
		assertEquals(false,mygame.isGameOver());
		assertEquals("X",actionMove1.getPlayer().getPlayerToken());
		
		move2.setMoveIndex(1);
		move2.setPlayer(player2);
		actionMove2 = new ClientAction(gameId, player2, ActionType.MOVE, GameType.TIC_TAC_TOE, "", move2);
		mygame.doProcessMove(actionMove2);
		assertEquals(2,mygame.getMoveCount());
		assertEquals(false,mygame.isDraw());
		assertEquals(false,mygame.isGameOver());
		assertEquals("O",actionMove2.getPlayer().getPlayerToken());
		
		move1.setMoveIndex(5);
		move1.setPlayer(player1);		
		actionMove1 = new ClientAction(gameId, player1, ActionType.MOVE, GameType.TIC_TAC_TOE, "", move1);
		mygame.doProcessMove(actionMove1);
		assertEquals(3,mygame.getMoveCount());
		assertEquals(false,mygame.isDraw());
		assertEquals(false,mygame.isGameOver());
		assertEquals("X",actionMove1.getPlayer().getPlayerToken());
		
		move2.setMoveIndex(3);
		move2.setPlayer(player2);
		actionMove2 = new ClientAction(gameId, player2, ActionType.MOVE, GameType.TIC_TAC_TOE, "", move2);
		mygame.doProcessMove(actionMove2);
		assertEquals(4,mygame.getMoveCount());
		assertEquals(false,mygame.isDraw());
		assertEquals(false,mygame.isGameOver());
		assertEquals("O",actionMove2.getPlayer().getPlayerToken());
		
		move1.setMoveIndex(6);
		move1.setPlayer(player1);		
		actionMove1 = new ClientAction(gameId, player1, ActionType.MOVE, GameType.TIC_TAC_TOE, "", move1);
		mygame.doProcessMove(actionMove1);
		assertEquals(5,mygame.getMoveCount());
		assertEquals(false,mygame.isDraw());
		assertEquals(false,mygame.isGameOver());
		assertEquals("X",actionMove1.getPlayer().getPlayerToken());
		
		move2.setMoveIndex(2);
		move2.setPlayer(player2);
		actionMove2 = new ClientAction(gameId, player2, ActionType.MOVE, GameType.TIC_TAC_TOE, "", move2);
		mygame.doProcessMove(actionMove2);
		assertEquals(6,mygame.getMoveCount());
		assertEquals(false,mygame.isDraw());
		assertEquals(false,mygame.isGameOver());
		assertEquals("O",actionMove2.getPlayer().getPlayerToken());
		
		move1.setMoveIndex(7);
		move1.setPlayer(player1);		
		actionMove1 = new ClientAction(gameId, player1, ActionType.MOVE, GameType.TIC_TAC_TOE, "", move1);
		mygame.doProcessMove(actionMove1);
		assertEquals(7,mygame.getMoveCount());
		assertEquals(false,mygame.isDraw());
		assertEquals(false,mygame.isGameOver());
		assertEquals("X",actionMove1.getPlayer().getPlayerToken());
		
		move2.setMoveIndex(0);
		move2.setPlayer(player2);
		actionMove2 = new ClientAction(gameId, player2, ActionType.MOVE, GameType.TIC_TAC_TOE, "", move2);
		mygame.doProcessMove(actionMove2);
		assertEquals(8,mygame.getMoveCount());
		assertEquals(false,mygame.isDraw());
		assertEquals(true,mygame.isGameOver());
		assertEquals(player2, mygame.getWinner());
		assertEquals("O",actionMove2.getPlayer().getPlayerToken());
		
	}

	@Test
	public void testIsOccupied() throws SkrullGameException {
				
		move1.setMoveIndex(1);
		move1.setPlayer(player1);		
		
		actionIsOccupied = new ClientAction(gameId, player1, ActionType.MOVE, GameType.TIC_TAC_TOE, "", move1);
		assertEquals(false, mygame.isOccupied(1));
		
		mygame.doProcessMove(actionIsOccupied);
		assertEquals(true, mygame.isOccupied(1));		
	}

}
