package skrull.client;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import skrull.base.rmi.ControllerInterface;
import skrull.base.rmi.RmiStarter;
import skrull.base.rmi.ViewInterface;

public class SkrullClientStarter extends RmiStarter {


	public SkrullClientStarter(Class clazzToAddToServerCodebase) {
		super(clazzToAddToServerCodebase);
	}

	public static void main(String[] args){
		SkrullClientStarter starter = new SkrullClientStarter(ViewInterface.class);
	}

	@Override
	public void doCustomRmiHandling() {
		try {
            final Registry registry = LocateRegistry.getRegistry();
            ControllerInterface hello = (ControllerInterface)registry.lookup(ControllerInterface.SERVICE_NAME);
            System.out.println(hello.say());
            
            ViewInterface view = new HelloClient();
           // UnicastRemoteObject.unexportObject(hello, true);
           Remote engineStub = UnicastRemoteObject.exportObject(view, 0);

            registry.rebind(ControllerInterface.SERVICE_NAME, engineStub);
            
            hello.registerView(view);
            
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
	




}
