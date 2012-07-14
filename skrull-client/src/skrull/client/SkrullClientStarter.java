package skrull.client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import skrull.base.rmi.HelloInterface;
import skrull.base.rmi.RmiStarter;

public class SkrullClientStarter extends RmiStarter {


	public SkrullClientStarter(Class clazzToAddToServerCodebase) {
		super(clazzToAddToServerCodebase);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args){
		SkrullClientStarter starter = new SkrullClientStarter(HelloClient.class);
	}

	@Override
	public void doCustomRmiHandling() {
		try {
            Registry registry = LocateRegistry.getRegistry();
            HelloInterface hello = (HelloInterface)registry.lookup(HelloInterface.SERVICE_NAME);
            System.out.println(hello.say());
		}catch(Exception e){
			e.printStackTrace();
		}		
	}
	




}
