package skrull.game.controller;

import java.util.concurrent.TimeUnit;

public class ActivityMonitor implements IActivityMonitor {
	
	private static final long serialVersionUID = 3015280422260700785L;
	final private IServerController serverController;
	private int checkInterval = CHECK_INTERVAL_MS;

	
	public ActivityMonitor (IServerController serverController){
		this.serverController = serverController;
	}
	
	/**
	 * override the default check interval
	 * time unit is in milliseconds
	 * @param checkInterval
	 */
	public void setCheckInterval(int checkInterval){
		this.checkInterval  = checkInterval;
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
		checkActivity();
	}
}