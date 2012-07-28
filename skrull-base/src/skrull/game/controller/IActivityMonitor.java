package skrull.game.controller;

import java.io.Serializable;

public interface IActivityMonitor extends Runnable, Serializable{

	public abstract void checkActivity(IServerController serverController);

}