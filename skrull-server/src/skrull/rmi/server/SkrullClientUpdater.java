package skrull.rmi.server;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.UUID;

import org.apache.log4j.Logger;

import skrull.SkrullGameException;
import skrull.game.model.IGameModel;
import skrull.game.model.IPlayer;
import skrull.rmi.SkrullRMIException;
import skrull.rmi.client.IClientListener;
import skrull.util.SkrullServerStarter;
import skrull.util.logging.SkrullLogger;

public class SkrullClientUpdater implements IClientUpdater {


	private static final long serialVersionUID = 1308396593090902859L;
	Registry registry;
	private static final Logger logger = SkrullLogger.getLogger(SkrullClientUpdater.class);

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
		 try {
			 // TODO: this should throw a skrullRMIException up the stack
			//final UUID playerId = player.getPlayerId();
			getListener(player.getPlayerId()).modelChanged(model);
//			listener.modelChanged(model);
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

	private IClientListener getListener(UUID playerId) throws AccessException, RemoteException, NotBoundException {
		// TODO Cache this!
		// TODO: figure out how to deal with failure

		final IClientListener listener = (IClientListener)registry.lookup(IClientListener.SERVICE_NAME + "." + playerId);
		return listener;
		
	}

	@Override
	public void checkPlayerConnected(IPlayer p) throws SkrullRMIException {
			try {
				// can we call a method on the client w/out exception?
				getListener(p.getPlayerId()).isConnected();
			} catch (Exception e) {
				//logger.error("Player " + p.getPlayerId() + " is unreachable", e);
				throw new SkrullRMIException("Player " + p.getPlayerId() + " is unreachable", e);
			}
		
	}
}
