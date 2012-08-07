package skrull.util;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

import org.apache.log4j.Logger;

import skrull.game.model.IPlayer;
import skrull.game.view.ClientInputHandler;
import skrull.game.view.GameClientView;
import skrull.game.view.IClientAction;
import skrull.game.view.IGameClientView;
import skrull.game.view.Player;
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
			
			final ServerUpdater serverUpdater = new ServerUpdater();
			final IPlayer player = new Player( UUID.randomUUID());
			final ClientInputHandler cih = new ClientInputHandler(serverUpdater, player);
			final IGameClientView view = new GameClientView(cih, player);
			cih.setView(view);

			final IClientListener listener = new ClientListener(view);
            
            final Registry registry = LocateRegistry.getRegistry();

            final Remote engineStub = UnicastRemoteObject.exportObject(listener, 0);

            registry.rebind(IClientListener.SERVICE_NAME + "." + player.getPlayerId(), engineStub);
            
            
            IClientAction action = cih.getStartupAction();
            serverUpdater.processClientAction(action);
            
            logger.info("initialization completed");
            
            
		}catch(Exception e){
        	logger.fatal("error encountered initializing", e);
		}		
		
	}

}