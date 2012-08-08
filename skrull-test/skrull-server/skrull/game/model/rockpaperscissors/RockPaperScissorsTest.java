package skrull.game.model.rockpaperscissors;

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
import skrull.game.model.tictactoe.TicTacToe;
import skrull.game.view.ClientAction;
import skrull.game.view.IClientAction;
import skrull.game.view.IClientAction.ActionType;
import skrull.game.view.Player;
import skrull.rmi.server.IClientUpdater;

public class RockPaperScissorsTest {
	
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
	private RockPaperScissors mygame;

	@Before
	public void setUp() throws Exception {
		
		/**  The Setup also tests the Game Constructor and Join Methods */
		updater = EasyMock.createMock(IClientUpdater.class);

		player1 = new Player(p1Id);
		player2 = new Player(p2Id);
		player3 = new Player(p3Id);
		if ( p1Id == p2Id || p1Id == p3Id || p2Id == p3Id )
			fail("UUID not unique");
		
		move1 = new Move();
		move2 = new Move();
		mygame = new RockPaperScissors(player1, gameId, updater);
		
		actionJoin = new ClientAction(gameId, player2, ActionType.JOIN_GAME, GameType.ROCK_PAPER_SCISSORS, "", null);
		mygame.joinGame(actionJoin);
	}

	@Test
	public void testJoinGame() throws Exception {
		actionJoin = new ClientAction(gameId, player3, ActionType.JOIN_GAME, GameType.TIC_TAC_TOE, "", null);
		mygame.joinGame(actionJoin);
	}

	@Test
	public void testDoProcessMoveMoveCount() throws SkrullGameException {
		
		assertEquals(0,mygame.getMoveCount());
		
		move1.setMoveIndex(1);
		move1.setPlayer(player1);		
		actionMove1 = new ClientAction(gameId, player1, ActionType.MOVE, GameType.ROCK_PAPER_SCISSORS, "", move1);
		mygame.doProcessMove(actionMove1);
		
		assertEquals(1,mygame.getMoveCount());
		
		move2.setMoveIndex(2);
		move2.setPlayer(player2);
		actionMove2 = new ClientAction(gameId, player1, ActionType.MOVE, GameType.ROCK_PAPER_SCISSORS, "", move2);
		mygame.doProcessMove(actionMove2);
		
		assertEquals(2,mygame.getMoveCount());
	}
	
	@Test
	public void testDoProcessMoveWinner() throws SkrullGameException {
		move1.setMoveIndex(0);
		move1.setPlayer(player1);		
		actionMove1 = new ClientAction(gameId, player1, ActionType.MOVE, GameType.ROCK_PAPER_SCISSORS, "", move1);
		mygame.doProcessMove(actionMove1); // TODO
		
		move2.setMoveIndex(2);
		move2.setPlayer(player2);		
		actionMove1 = new ClientAction(gameId, player1, ActionType.MOVE, GameType.ROCK_PAPER_SCISSORS, "", move1);
		mygame.doProcessMove(actionMove1);
		
		assertEquals(player2,mygame.getWinner());
	}

	@Test
	public void testDoProcessMoveDraw() throws SkrullGameException {
		move1.setMoveIndex(0);
		move1.setPlayer(player1);		
		actionMove1 = new ClientAction(gameId, player1, ActionType.MOVE, GameType.ROCK_PAPER_SCISSORS, "", move1);
		mygame.doProcessMove(actionMove1); // TODO
		
		move2.setMoveIndex(0);
		move2.setPlayer(player2);		
		actionMove1 = new ClientAction(gameId, player1, ActionType.MOVE, GameType.ROCK_PAPER_SCISSORS, "", move1);
		mygame.doProcessMove(actionMove1);
		
		assertEquals(player2,mygame.getWinner());
	}
	
	@Test
	public void testRockPaperScissors() {
		fail("Not yet implemented"); // TODO
	}

}
