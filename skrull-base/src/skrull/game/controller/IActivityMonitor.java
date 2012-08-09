package skrull.game.controller;

import java.io.Serializable;

/**
 * Activity Monitor is responsible for checking all connected players periodically,
 * both for connectivity and against a defined timeout interval (to deal with walkaways)
 * 
 * Runs on an interval defined in system property activity.monitor.interval
 * Default timeout is defined in system property inactivity.timeout
 * 
 * @author jesse
 *
 */
public interface IActivityMonitor extends Runnable, Serializable{

	final static public int CHECK_INTERVAL_MS = 5000;

	/**
	 * Check activity of all connected clients
	 */
	public abstract void checkActivity();

}