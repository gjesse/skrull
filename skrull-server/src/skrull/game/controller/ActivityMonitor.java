package skrull.game.controller;

import java.util.concurrent.TimeUnit;

public class ActivityMonitor implements IActivityMonitor {
	
	private static final long serialVersionUID = 3015280422260700785L;
	final private int CHECK_INTERVAL_SECONDS = 20;
	final private IServerController serverController;

	
	public ActivityMonitor (IServerController serverController){
		this.serverController = serverController;
		this.run();
	}
	
	/* (non-Javadoc)
	 * @see skrull.game.controller.IActivityMonitor#checkActivity(java.lang.Object)
	 */
	@Override
	public void checkActivity(IServerController serverController) {
	
		
		try {
			TimeUnit.SECONDS.sleep(CHECK_INTERVAL_SECONDS);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		for (IGameController c : serverController.getControllers()){
			c.checkActivity();
		}
			
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}