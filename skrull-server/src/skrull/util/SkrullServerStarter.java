package skrull.util;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import skrull.rmi.server.IServerListener;
import skrull.rmi.server.ServerListener;

public class SkrullServerStarter extends RmiStarter {

	public SkrullServerStarter(Class clazzToAddToServerCodebase) {
		super(clazzToAddToServerCodebase);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		SkrullServerStarter starter = new SkrullServerStarter(IServerListener.class);
	}

	@Override
	public void doCustomRmiHandling() {
       try {
            IServerListener listener = new ServerListener();
           Remote engineStub = UnicastRemoteObject.exportObject(listener, 0);

            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(IServerListener.SERVICE_NAME, engineStub);
        }
        catch(Exception e) {
            e.printStackTrace();
        }

		
	}
}