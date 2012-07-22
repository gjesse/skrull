package skrull.util;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

import skrull.game.view.ClientInputHandler;
import skrull.game.view.GameClientView;
import skrull.rmi.client.ClientListener;
import skrull.rmi.client.IClientListener;
import skrull.rmi.client.ServerUpdater;

public class SkrullClientStarter extends RmiStarter {

	public SkrullClientStarter(Class clazzToAddToServerCodebase) {
		super(clazzToAddToServerCodebase);
	}

	public static void main(String[] args) {
		SkrullClientStarter starter = new SkrullClientStarter(IClientListener.class);
	}

	@Override
	public void doCustomRmiHandling() {
		try {
			UUID playerId = UUID.randomUUID();
			
			ServerUpdater serverUpdater = new ServerUpdater();
			ClientInputHandler cih = new ClientInputHandler(serverUpdater);
			GameClientView view = new GameClientView(cih, playerId);

            IClientListener listener = new ClientListener(view);

            final Registry registry = LocateRegistry.getRegistry();

            Remote engineStub = UnicastRemoteObject.exportObject(listener, 0);

            registry.rebind(IClientListener.SERVICE_NAME + "." + playerId, engineStub);
            
            
            
		}catch(Exception e){
			e.printStackTrace();
		}		
		
	}
}