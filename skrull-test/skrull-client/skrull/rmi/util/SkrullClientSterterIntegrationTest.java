package skrull.rmi.util;

import static org.junit.Assert.*;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import skrull.game.factory.IGameFactory.GameType;
import skrull.game.view.IClientAction;
import skrull.game.view.IClientAction.ActionType;
import skrull.game.view.IGameClientView;
import skrull.rmi.PolicyFileLocater;
import skrull.rmi.SystemPropertyReader;
import skrull.rmi.client.ClientListener;
import skrull.rmi.client.IClientListener;
import skrull.rmi.server.IServerListener;
import skrull.rmi.server.ServerListener;
import skrull.util.SkrullClientStarter;

/**
 * Tests the startup config
 * and client-side initialization
 * 
 * @author jesse
 *
 */
public class SkrullClientSterterIntegrationTest {



	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	private IGameClientView view;
	private MockServerListener listener;

	@Before
	public void setUp() throws Exception {
		
		//playerId = UUID.randomUUID();
		listener = new MockServerListener();
        
		
		System.setProperty("skrull.properties", "skrull.client.properties");
		SystemPropertyReader.readProperties();
        System.setProperty("java.rmi.server.codebase", IServerListener.class
            .getProtectionDomain().getCodeSource().getLocation().toString());

        System.setProperty("java.security.policy", PolicyFileLocater.getLocationOfPolicyFile());

        if(System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
		
        final Registry registry = LocateRegistry.getRegistry();
        Remote engineStub = UnicastRemoteObject.exportObject(listener, 0);
        registry.rebind(IServerListener.SERVICE_NAME, engineStub);
        
	}

	@Test
	public void viewToServer() throws Exception{

	
			SkrullClientStarter starter = new SkrullClientStarter();
			assertTrue(listener.action.getActionType().equals(ActionType.JOIN_SERVER));
			assertTrue(listener.action.getGameType().equals(GameType.DEFAULT));
		
	}
	
	
	public class MockServerListener implements IServerListener {

		private IClientAction action;

		@Override
		public void ProcessClientAction(IClientAction action)
				throws RemoteException {
			this.action = action;
		}

	}

}
