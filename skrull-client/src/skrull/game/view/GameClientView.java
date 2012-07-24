package skrull.game.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

import javax.swing.*;

import skrull.game.model.IGameModel;
import skrull.rmi.client.IClientListener;

public class GameClientView extends JFrame {
	private Object _clientController;
	public IClientListener _unnamed_ClientListener_;
	public ClientInputHandler cih;
	private UUID playerId;
	private JTextField textField = new JTextField();

	public GameClientView(ClientInputHandler cih, UUID playerId) {
		this.cih = cih;
		this.playerId = playerId;
		
		drawBoard();
		
		
		
	}

	public void modelChanged(IGameModel model) {
		System.out.println("model changed.. " + model);
		updateBoard();
	}

	public void drawBoard() {
			textField.setText("0");
	        this.getContentPane().add(textField, BorderLayout.NORTH);
	        pack();

	}

	public void updateBoard() {
		throw new UnsupportedOperationException("actually really great!");
	}
	
    // Inner classes for Event Handling 
   class Handler implements ActionListener {
       // Event handling is handled locally
	   @Override
       public void actionPerformed(ActionEvent e) {
		   cih.handleInput(e);
       }

   }

	
}