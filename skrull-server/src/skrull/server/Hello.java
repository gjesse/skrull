package skrull.server;

import java.rmi.RemoteException;

import skrull.base.HelloInterface;


public class Hello implements HelloInterface{
	// public static void main(String[] args){
	//	Hello hi = new Hello();
	//	System.out.println(hi.getMessage());
	// }

	private String msg;
	
	Hello(String msg){
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
