package skrull.game.controller;

import java.util.concurrent.TimeUnit;

public class ActivityMonitor implements IActivityMonitor {
	
	private static final long serialVersionUID = 3015280422260700785L;
	final private IServerController serverController;

	
	public ActivityMonitor (IServerController serverController){
		this.serverController = serverController;
	}
	
	/* (non-Javadoc)
	 * @see skrull.game.controller.IActivityMonitor#checkActivity(java.lang.Object)
	 */
	@Override
	public void checkActivity() {
		
		// TODO: log this instead
		System.out.println("activity monitor running");
		for (IGameController c : serverController.getControllers()){
			c.checkActivity();
		}
			
	}

	@Override
	public void run() {
		try {
			checkActivity();
		}
		// catch anything and log it
		catch (Throwable t){
			// TODO: log this
			t.printStackTrace();
		}
	}
}