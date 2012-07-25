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

	private static final long serialVersionUID = 733356106858477245L;
	private ClientInputHandler cih;
	private UUID playerId;
	private JTextField chatTextInputField;
	JTextArea chatWindow;

	public GameClientView(ClientInputHandler cih, UUID playerId) {
		this.cih = cih;
		this.playerId = playerId;
		
		initBoard();
		
		/*		String codebase="file:///C:/Users/Jeremiah/Code/skrull/skrull-base/bin/ ";
		codebase+="file:///C:/Users/Jeremiah/Code/skrull/skrull-client/bin/";*/
		
		
		
	}

	public void modelChanged(IGameModel model) {
		System.out.println("model changed.. " + model);
		updateBoard(model);
	}

	private void initBoard() {
		// TODO: this is pretty messy. most of this logic needs to be abstracted 
			Handler handler = new Handler(); 
			chatTextInputField = new JTextField();
			chatTextInputField.setText("0123456789");
			chatTextInputField.addActionListener(handler);
	       // getContentPane().add(chatTextInputField, BorderLayout.NORTH);
	        
	        Panel p = new Panel();
	        
	        chatWindow = new JTextArea(44,4);
	        chatWindow.setText("...");
	        
	        p.add(chatWindow);
	        
	        JButton chatButton = new JButton("send chat");
	        chatButton.setActionCommand(IClientAction.ActionType.CHAT.toString());
	        chatButton.addActionListener(handler);
	        p.add(chatButton);
	        p.add(chatTextInputField);
	        getContentPane().add(p);
	        
	        
	        JPanel buttonPanel = new JPanel(); 
	        List<JButton> gameButtons = new ArrayList<JButton>();
	        for (GameType t: IGameFactory.GameType.values()){
	        	JButton b = new JButton(t.toString());
	        	b.setActionCommand(IClientAction.ActionType.CREATE_GAME.toString());
	    		b.addActionListener(handler); 
	    		buttonPanel.add(b);
	    		gameButtons.add(b);
	        }
	        
	    	buttonPanel.setLayout(new GridLayout(1, buttonPanel.getComponentCount(), 5, 5));
	    	
	        Panel p2 = new Panel();
	        p2.add(buttonPanel);
	    	
	    	getContentPane().add(p2, BorderLayout.NORTH);
	        pack();
	        setVisible(true);

	}

	private void updateBoard(IGameModel model) {
		//chatTextInputField.setText("got a message from the model - player id " + playerId + " " + model.getGameType());
		chatWindow.setText(model.getChatContents());
	}
	
	public String getChatText(){
		return chatTextInputField.getText();
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