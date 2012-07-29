package skrull.rmi.client;

import org.apache.log4j.Logger;

import skrull.game.model.IGameModel;
import skrull.game.view.IGameClientView;
import skrull.util.logging.SkrullLogger;


public class ClientListener implements IClientListener {

	private IGameClientView gameClientView;
	private static final Logger logger = SkrullLogger.getLogger(ClientListener.class);

	public ClientListener(IGameClientView view) {
		this.gameClientView = view;
	}

	@Override
	public void modelChanged(IGameModel model) {
		logger.info("Model changed: " + model);
		gameClientView.modelChanged(model);
	}

	@Override
	public boolean isConnected() {
		return true;
	}



}
