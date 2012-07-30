package skrull.game.controller;

import java.io.Serializable;

public interface IActivityMonitor extends Runnable, Serializable{

	final static public int CHECK_INTERVAL_MS = 5000;

	public abstract void checkActivity();

}