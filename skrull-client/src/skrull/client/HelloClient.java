package skrull.client;

import java.rmi.Naming;
import java.rmi.RemoteException;

import skrull.base.rmi.ControllerInterface;
import skrull.base.rmi.ViewInterface;

public class HelloClient implements ViewInterface {

	
	  /**
	   * Client program for the "Hello, world!" example.
	   * @param argv The command line arguments which are ignored.
	   */
	  public static void main (String[] argv) {
	    try {
	      ControllerInterface hello = 
	        (ControllerInterface) Naming.lookup ("//localhost/Hello");
	      System.out.println (hello.say());
	    } catch (Exception e) {
	      System.out.println ("HelloClient exception: " + e);
	    }
	  }
	
	public String getMessage(){
		return "my msg";
	}

	@Override
	public void update() throws RemoteException {
		System.out.println("update received from server");
	}
}