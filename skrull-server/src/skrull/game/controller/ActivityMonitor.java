package skrull.game.controller;

import org.apache.log4j.Logger;
import skrull.util.logging.SkrullLogger;

public class ActivityMonitor implements IActivityMonitor {
	
	private static final long serialVersionUID = 3015280422260700785L;
	final private IServerController serverController;
	private static final Logger logger = SkrullLogger.getLogger(ActivityMonitor.class);

	
	public ActivityMonitor (IServerController serverController){
		this.serverController = serverController;
	}
	
	/* (non-Javadoc)
	 * @see skrull.game.controller.IActivityMonitor#checkActivity(java.lang.Object)
	 */
	@Override
	public void checkActivity() {
		
		// logger.debug("activity monitor running");
		for (IGameController c : serverController.getControllers()){
			c.checkActivity();
		}
			
	}

	@Override
	public void run(){
		try {
			checkActivity();
		}
		catch (Throwable t){
			// catch anything and log it
			logger.error("Activity monitor encountered a problem:", t);
		}
	}
}