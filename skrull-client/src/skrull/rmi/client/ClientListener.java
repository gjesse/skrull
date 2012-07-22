package skrull.rmi.client;

import skrull.game.model.IGameModel;


public class ClientListener implements IClientListener {

	@Override
	public void modelChanged(IGameModel model) {
		// TODO Auto-generated method stub 
		System.out.println("modelChanged...");
		throw new UnsupportedOperationException();
	}



}
