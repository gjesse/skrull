package skrull.rmi.server;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.UUID;

import skrull.game.model.IGameModel;
import skrull.game.model.IPlayer;
import skrull.rmi.client.IClientListener;

public class SkrullClientUpdater implements IClientUpdater {

	Registry registry;
	
	public SkrullClientUpdater() throws RemoteException{
		registry = LocateRegistry.getRegistry();		
	}

	@Override
	public void modelChanged(IGameModel model) {
		for (IPlayer player: model.getPlayers()){
			// here we need to get the rmi exposed interface for each player, and call it
			notifyListener(model, player);
		}

	}

	private void notifyListener(IGameModel model, IPlayer player) {
		 // TODO: might be good to put this in a map for later
		// TODO: we should be able to deal with failure here somehow
		 try {
			UUID playerId = player.getPlayerId();
			IClientListener listener = (IClientListener)registry.lookup(IClientListener.SERVICE_NAME + "." + playerId);
			listener.modelChanged(model);
		} catch (AccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}
