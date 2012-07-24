package skrull.game.view;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.*;

import skrull.game.factory.IGameFactory;
import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.IGameModel;
import skrull.rmi.client.IClientListener;

public class GameClientView extends JFrame {
	private Object _clientController;
	public IClientListener _unnamed_ClientListener_;
	public ClientInputHandler cih;
	private UUID playerId;
	private JTextField textField;

	public GameClientView(ClientInputHandler cih, UUID playerId) {
		this.cih = cih;
		this.playerId = playerId;
		
		initBoard();
		
		
		
	}

	public void modelChanged(IGameModel model) {
		System.out.println("model changed.. " + model);
		updateBoard(model);
	}

	private void initBoard() {
			Handler handler = new Handler(); 
			textField = new JTextField();
			textField.setText("0123456789");
	        getContentPane().add(textField, BorderLayout.NORTH);
	        JPanel buttonPanel = new JPanel(); 
	        List<JButton> gameButtons = new ArrayList<JButton>();
	        for (GameType t: IGameFactory.GameType.values()){
	        	JButton b = new JButton(t.toString());
	    		b.addActionListener(handler); 
	    		buttonPanel.add(b);
	    		gameButtons.add(b);
	        }
	        
	    	buttonPanel.setLayout(new GridLayout(1, buttonPanel.getComponentCount(), 5, 5));
	    	getContentPane().add(buttonPanel);
	        pack();
	        setVisible(true);

	}

	private void updateBoard(IGameModel model) {
		textField.setText("got a message from the model - player id " + playerId + " " + model.getGameType());
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