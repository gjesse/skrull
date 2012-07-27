package skrull.util;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

import skrull.game.view.ClientInputHandler;
import skrull.game.view.GameClientView;
import skrull.game.view.IClientAction;
import skrull.game.view.IGameClientView;
import skrull.rmi.client.ClientListener;
import skrull.rmi.client.IClientListener;
import skrull.rmi.client.ServerUpdater;

public class SkrullClientStarter extends RmiStarter {

	public SkrullClientStarter() throws Exception {
		super();
	}

	public static void main(String[] args) throws Exception {	
		SkrullClientStarter starter = new SkrullClientStarter();
		
	}

	@Override
	public void doCustomRmiHandling() {
		try {
			UUID playerId = UUID.randomUUID();
			
			ServerUpdater serverUpdater = new ServerUpdater();
			ClientInputHandler cih = new ClientInputHandler(serverUpdater, playerId);
			IGameClientView view = new GameClientView(cih, playerId);
			cih.setView(view);

            IClientListener listener = new ClientListener(view);

            final Registry registry = LocateRegistry.getRegistry();

            Remote engineStub = UnicastRemoteObject.exportObject(listener, 0);

            registry.rebind(IClientListener.SERVICE_NAME + "." + playerId, engineStub);
            
            
            IClientAction action = cih.getStartupAction();
            serverUpdater.ProcessClientAction(action);
            
            
            
		}catch(Exception e){
			e.printStackTrace();
		}		
		
	}

}