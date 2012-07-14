package skrull.server;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import skrull.base.rmi.ControllerInterface;
import skrull.base.rmi.RmiStarter;

public class SkrullServerStarter extends RmiStarter {
	/**
	   * Server program for the "Hello, world!" example.
	   * @param argv The command line arguments which are ignored.
	   */
	  public static void main (String[] argv) {
	    try {
		      System.out.println ("setting up.. ");
		      SkrullServerStarter st = new SkrullServerStarter();
		      
	      System.out.println ("Hello Server is ready.");
	    } catch (Exception e) {
	      System.out.println ("Hello Server failed: " + e);
	    }
	  }
	  
			
			public SkrullServerStarter() {
					super(ControllerInterface.class);
			}
			



			@Override
			    public void doCustomRmiHandling() {
			        try {
			            Controller hello = new Controller("hi there");
			           // UnicastRemoteObject.unexportObject(hello, true);
			           Remote engineStub = UnicastRemoteObject.exportObject(hello, 0);

			            Registry registry = LocateRegistry.getRegistry();
			            registry.rebind(ControllerInterface.SERVICE_NAME, engineStub);
			        }
			        catch(Exception e) {
			            e.printStackTrace();
			        }

			    }


}
