package skrull.util;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import skrull.rmi.client.ClientListener;
import skrull.rmi.client.IClientListener;

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
            final Registry registry = LocateRegistry.getRegistry();
           // IClientListener listener = (IClientListener)registry.lookup(IClientListener.SERVICE_NAME);
            
            // this needs to do some work to hook up
            // the server methods as well
            
            IClientListener listener = new ClientListener();
            Remote engineStub = UnicastRemoteObject.exportObject(listener, 0);

            registry.rebind(IClientListener.SERVICE_NAME, engineStub);
            
           
            
		}catch(Exception e){
			e.printStackTrace();
		}		
		
	}
}