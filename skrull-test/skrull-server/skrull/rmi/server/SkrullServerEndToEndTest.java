package skrull.rmi.server;

import static org.junit.Assert.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import skrull.game.view.ClientAction;
import skrull.game.view.IClientAction;
import skrull.rmi.client.ClientListener;
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
        final Registry registry = LocateRegistry.getRegistry();
        Remote engineStub = UnicastRemoteObject.exportObject(clientListener, 0);
        registry.rebind(IClientListener.SERVICE_NAME + "." + playerId, engineStub);
	}

	@Ignore
	@Test
	public void testEndToEnd() throws Exception {

		SkrullServerStarter starter = new SkrullServerStarter(IServerListener.class);
		IServerUpdater serverUpdater = new ServerUpdater();
		
		
		// IClientAction action = new ClientAction();
		
	
		
		}

}
