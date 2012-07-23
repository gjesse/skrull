package skrull.rmi.server;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.IGameModel;
import skrull.game.model.IMove;
import skrull.game.model.IPlayer;
import skrull.game.view.ClientAction;
import skrull.game.view.IClientAction;
import skrull.game.view.IClientAction.ActionType;
import skrull.rmi.PolicyFileLocater;
import skrull.rmi.client.IClientListener;
import skrull.rmi.client.IServerUpdater;
import skrull.rmi.client.ServerUpdater;
import skrull.util.SkrullServerStarter;

public class SkrullServerEndToEndTest {

	private UUID playerId;
	private IClientListener clientListener;
	
	@Before
	public void setUp() throws Exception {
		
		playerId = UUID.randomUUID();
		clientListener = EasyMock.createMock(IClientListener.class);
		//clientListener = new ClientListener(null);
		

        System.setProperty("java.rmi.server.codebase", IClientListener.class
            .getProtectionDomain().getCodeSource().getLocation().toString());

        System.setProperty("java.security.policy", PolicyFileLocater.getLocationOfPolicyFile());

        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
		
        final Registry registry = LocateRegistry.getRegistry();
        Remote engineStub = UnicastRemoteObject.exportObject(clientListener, 0);
        registry.rebind(IClientListener.SERVICE_NAME + "." + playerId, engineStub);
	}

	@Test
	public void testEndToEndJoinServer() throws Exception {

		// starts the server
		SkrullServerStarter starter = new SkrullServerStarter(IServerListener.class, false);

		
		IServerUpdater serverUpdater = new ServerUpdater();
		
		
		/*
		 * 		this.gameId =gameId;
		this.player = player;
		this.actionType = type;
		this.actionMessage = actionMessage;
		this.move = move;
		 */
		int gameId = -1;
		IPlayer player = EasyMock.createMock(IPlayer.class);
		IMove move = EasyMock.createMock(IMove.class);
		IClientAction action = new ClientAction(gameId, player, ActionType.JOIN_SERVER, GameType.DEFAULT, null, move );
		
		

		
		
		// should be the result
		clientListener.modelChanged(EasyMock.anyObject(IGameModel.class));
		EasyMock.expectLastCall();

		EasyMock.replay(clientListener);
		// this would be called by the client
		serverUpdater.ProcessClientAction(action);
		EasyMock.verify(clientListener);
		
		
		}

}
