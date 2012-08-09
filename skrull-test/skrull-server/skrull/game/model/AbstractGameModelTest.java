package skrull.game.model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import skrull.SkrullException;
import skrull.SkrullGameException;
import skrull.game.view.IClientAction;
import skrull.rmi.SkrullRMIException;
import skrull.rmi.server.IClientUpdater;

public class AbstractGameModelTest {

	private IClientUpdater updater;
	private AbstractGameModel game;
	private List<IPlayer> players;
	private IPlayer player1;
	private IPlayer player2;
	private final static UUID p1Id = UUID.randomUUID();;
	private final static UUID p2Id = UUID.randomUUID();;

	@Before
	public void setUp() throws Exception {
		
	
		updater = EasyMock.createMock(IClientUpdater.class);
		game = new DefaultGameModel(1, updater);

		players = new ArrayList<IPlayer>();

		players.add( player1 = EasyMock.createMock(IPlayer.class));
		players.add( player2 = EasyMock.createMock(IPlayer.class));
		
		for (IPlayer p: players){
			game.addPlayer(p);
		}
		
		EasyMock.expect(player1.getPlayerId()).andReturn(p1Id).anyTimes();
		EasyMock.expect(player2.getPlayerId()).andReturn(p2Id).anyTimes();

		
	}


	@Test
	public void testCheckActivity() throws Exception {

		updater.checkPlayerConnected(player1);
		EasyMock.expectLastCall();
		updater.checkPlayerConnected(player2);
		EasyMock.expectLastCall();

			
		EasyMock.replay(updater,player1, player2);
		game.checkActivity();
		EasyMock.verify(updater, player1, player2);

		
	
	}
	
	@Test
	public void testCheckActivityDisconnected() throws SkrullRMIException {
		final String msg = "abcdefg";
		updater.checkPlayerConnected(player1);
		EasyMock.expectLastCall();
		updater.checkPlayerConnected(player2);
		EasyMock.expectLastCall().andThrow(new SkrullRMIException(msg));
		
		
		updater.modelChanged(game);
		EasyMock.expectLastCall();


			
		EasyMock.replay(updater,player1, player2);
			game.checkActivity();
			assertEquals(msg, game.getBroadcastMessage());
			assertFalse(game.getPlayers().contains(player2));
		EasyMock.verify(updater, player1, player2);
	
	}
	
	
	
	@Test
	public void testChatUpdate() throws SkrullRMIException {
		
		IClientAction action = EasyMock.createMock(IClientAction.class);
		updater.modelChanged(game);
		EasyMock.expectLastCall();
		
		EasyMock.expect(action.getActionMessage()).andReturn("chat");
		
		EasyMock.replay(updater, action);
		game.chatUpdate(action);
		EasyMock.verify(updater, action);

		assertEquals("chat\n", game.getChatContents());

	}


	@Test
	public void testJoinGame() throws SkrullException {
		IClientAction action = EasyMock.createMock(IClientAction.class);
		updater.modelChanged(game);
		EasyMock.expectLastCall();
		
		EasyMock.expect(action.getPlayer()).andReturn(player1).atLeastOnce();
	
		EasyMock.replay(updater, action);
		game.joinGame(action);
		EasyMock.verify(updater, action);
		
		assertTrue(game.getPlayers().contains(player1));
	}



}
