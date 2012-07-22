package skrull.rmi.client;

import skrull.game.model.IGameModel;
import skrull.game.view.GameClientView;


public class ClientListener implements IClientListener {

	private GameClientView gameClientView;

	public ClientListener(GameClientView view) {
		this.gameClientView = view;
	}

	@Override
	public void modelChanged(IGameModel model) {
		// TODO Auto-generated method stub 
		System.out.println("modelChanged...");
		gameClientView.modelChanged(model);
	}



}
