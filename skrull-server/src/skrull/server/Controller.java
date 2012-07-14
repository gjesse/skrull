package skrull.server;

import java.rmi.RemoteException;
import skrull.base.rmi.ControllerInterface;
import skrull.base.rmi.ViewInterface;


public class Controller  implements ControllerInterface{
	// public static void main(String[] args){
	//	Hello hi = new Hello();
	//	System.out.println(hi.getMessage());
	// }
	
	  /**
	 * 
	 */
	private static final long serialVersionUID = 4714567181625599653L;



	private String msg;



	private ViewInterface view;
	
	Controller(String msg) throws RemoteException{
		this.msg = msg;
	}
	private String getMessage(){
		return msg;
	}

	@Override
	public String say() throws RemoteException {
		return getMessage();
	}
	@Override
	public void registerView(ViewInterface view) throws RemoteException {

			this.view = view;
			view.update();
		
	}
	
	
}
