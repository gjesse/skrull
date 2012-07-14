package skrull.server;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import skrull.base.rmi.HelloInterface;
import skrull.base.rmi.RmiStarter;


public class Hello extends UnicastRemoteObject implements HelloInterface{
	// public static void main(String[] args){
	//	Hello hi = new Hello();
	//	System.out.println(hi.getMessage());
	// }
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 4714567181625599653L;



	private String msg;
	
	Hello(String msg) throws RemoteException{
		this.msg = msg;
	}
	private String getMessage(){
		return msg;
	}

	@Override
	public String say() throws RemoteException {
		return getMessage();
	}
	
	
}
