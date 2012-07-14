package skrull.base.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ViewInterface extends Remote {

	  /**
	   * Remotely invocable method.
	   * @return the message of the remote object, such as "Hello, world!".
	   * @exception RemoteException if the remote invocation fails.
	   */
	  public static final String SERVICE_NAME = "View";

	  public void update() throws RemoteException;
}

