package skrull.util;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

import org.apache.log4j.Logger;

import skrull.game.view.ClientInputHandler;
import skrull.game.view.GameClientView;
import skrull.game.view.IClientAction;
import skrull.game.view.IGameClientView;
import skrull.rmi.client.ClientListener;
import skrull.rmi.client.IClientListener;
import skrull.rmi.client.ServerUpdater;
import skrull.util.logging.SkrullLogger;

public class SkrullClientStarter extends RmiStarter {

	private static final Logger logger = SkrullLogger.getLogger(SkrullClientStarter.class);

	public SkrullClientStarter() throws Exception {
		super();
	}

	public static void main(String[] args) throws Exception {	
		SkrullClientStarter starter = new SkrullClientStarter();
		
	}

	@Override
	public void doCustomRmiHandling() {
		try {
			final UUID playerId = UUID.randomUUID();
			
			final ServerUpdater serverUpdater = new ServerUpdater();
			final ClientInputHandler cih = new ClientInputHandler(serverUpdater, playerId);
			final IGameClientView view = new GameClientView(cih, playerId);
			cih.setView(view);

			final IClientListener listener = new ClientListener(view);

            final String host =System.getProperty("skrull.server.host", "localhost");
            final String port = System.getProperty("skrull.server.port", "1099");
            
            final Registry registry = LocateRegistry.getRegistry(host, Integer.valueOf(port));

            final Remote engineStub = UnicastRemoteObject.exportObject(listener, 0);

            registry.rebind(IClientListener.SERVICE_NAME + "." + playerId, engineStub);
            
            
            IClientAction action = cih.getStartupAction();
            serverUpdater.processClientAction(action);
            
            logger.info("initialization completed");
            
            
		}catch(Exception e){
        	logger.fatal("error encountered initializing", e);
		}		
		
	}

}