package skrull.game.view;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

import javax.swing.*;
import javax.swing.border.BevelBorder;

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
	JButton exitButton;
	JButton sendButton;
	JButton startButton;
	JButton joinButton;
	
	String sampleGameList[] = {"Tic-Tac-Toe", "Rock-Paper-Scissors","No Game"};
	String sampleGamesToJoin[] = {"sampleGame1","sampleGame2","sampleGame3","sampleGame4"};
	

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
	public void buildClientGameView() {
		
		/*		chatTextInputField = new JTextField();
				chatTextInputField.setText("Enter Message...");
				
				chatTextInputField.setForeground(Color.gray);
		        chatWindow = new JTextArea(5,20);
		        chatWindow.setText("Welcome to Skrull Gaming...");
				chatWindow.setEditable(false);
				
		        sendButton = new JButton("SEND");*/
				
		/*		JPanel chatPanel = new JPanel(new GridBagLayout());					 
				chatPanel.setPreferredSize(new Dimension(400,0));
				GridBagConstraints constraints = new GridBagConstraints();
				
				Handler handler = new Handler();

		        sendButton.setActionCommand(IClientAction.ActionType.CHAT.toString());
		        sendButton.addActionListener(handler);
		        exitButton.addActionListener(handler);
		        
		        constraints.fill = GridBagConstraints.BOTH;
		        constraints.gridx = 1;
		        constraints.gridy = 0;
		        constraints.ipady = 100;
		        chatPanel.add(chatWindow, constraints);
		        
		        constraints.ipady = 10;
		        constraints.gridx = 1;
		        constraints.gridy = 1;
		        chatTextInputField.setBorder(BorderFactory.createLoweredBevelBorder());
		        
		        chatPanel.add(chatTextInputField,constraints);
		        
		        constraints.gridx = 1;
		        constraints.gridy = 2;
		        constraints.ipady = 0;
		        chatPanel.add(sendButton, constraints);
		 		*/
				exitButton = new JButton("Exit");
				JFrame gameWindowFrame = new JFrame("Skrull");						//overall window frame title
				
				JPanel userPanel = new JPanel();									

		        
				userPanel.add(exitButton, null);
				gameWindowFrame.setBackground(Color.orange);
				
				//chatPanel.setBackground(Color.black);
		/*******************************************************************************************		
				//hoping to set up UI in a way that we have the left side of the window as the game 
				//and the right side as the housing for the chat client
		*********************************************************************************************/		
				JPanel myChattingPanel = new JPanel();
				myChattingPanel.add(buildChatClient());
				JPanel gameBoardPanel = new JPanel();
				gameBoardPanel.setPreferredSize(new Dimension(550,0));
				gameBoardPanel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, Color.BLACK, Color.DARK_GRAY));	//trying to create a border that will surround the game panel; not sure what the 3 does
				gameBoardPanel.setBackground(Color.WHITE);
				
				
				gameWindowFrame.getContentPane().add(gameBoardPanel,BorderLayout.WEST);		
				gameWindowFrame.getContentPane().add(myChattingPanel, BorderLayout.EAST);	
				gameWindowFrame.getContentPane().add(userPanel,BorderLayout.SOUTH);
				
				gameWindowFrame.setSize(800, 600);
				gameWindowFrame.setResizable(false);
			//	gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				gameWindowFrame.setVisible(true);
				
			}
		/************************************************************************************************************/
	public void buildClientMainView(){
				
				JFrame mainFrame = new JFrame("Main View");
				
				JPanel left = new JPanel();
				JPanel middle = new JPanel();
				JPanel right = new JPanel();
				
				left.setPreferredSize(new Dimension(300,0));
				middle.setPreferredSize(new Dimension(300,0));
				right.setPreferredSize(new Dimension(200,0));
				
				startButton = new JButton("Start Game");
				joinButton = new JButton("Join Game");
				
				/*******************LEFT PROPERTIES************************/
				left.setLayout(new GridLayout(3,0));
				
				JList newGameList = new JList(sampleGameList);
				newGameList.setBorder(BorderFactory.createTitledBorder("Create A New Game"));
				
				left.add(newGameList);
				left.add(startButton);
				left.setBorder(BorderFactory.createEtchedBorder());
				
				/*******************MIDDLE PROPERTIES************************/
				middle.setLayout(new GridLayout(3,0));
				JList activeGamesToJoin = new JList(sampleGamesToJoin);
				activeGamesToJoin.setBorder(BorderFactory.createTitledBorder("Join A Game"));
				middle.add(activeGamesToJoin);
				middle.add(joinButton);
				middle.setBorder(BorderFactory.createEtchedBorder());
				
				/*******************RIGHT PROPERTIES************************/
				
				right.add(buildChatClient());
				right.setBorder(BorderFactory.createEtchedBorder());
				
				GridLayout mainGrid = new GridLayout(0,3);
				mainFrame.setLayout(mainGrid);
				mainFrame.getContentPane().add(left);
				mainFrame.getContentPane().add(middle);
				mainFrame.getContentPane().add(right);
				
				mainFrame.setSize(800,600);
				mainFrame.setVisible(true);
				mainFrame.setResizable(false);
				
			}
			
	private Component buildChatClient(){
				
				//chat panel will have textArea, textField, SendButton, 
				
				chatTextInputField = new JTextField();
				chatTextInputField.setText("Enter Message...");
				
				chatTextInputField.setForeground(Color.gray);
		        chatWindow = new JTextArea(5,20);
		        chatWindow.setText("Welcome to Skrull Gaming...");
				chatWindow.setEditable(false);
				
				sendButton = new JButton("SEND");
				
				JPanel chatPanel = new JPanel(new GridBagLayout());					
				GridBagConstraints constraints = new GridBagConstraints();
				
				Handler handler = new Handler();

		        sendButton.setActionCommand(IClientAction.ActionType.CHAT.toString());
		        sendButton.addActionListener(handler);
		        exitButton.addActionListener(handler);
		        
		        constraints.fill = GridBagConstraints.BOTH;
		        constraints.gridx = 1;
		        constraints.gridy = 0;
		        constraints.ipady = 100;
		        chatPanel.add(chatWindow, constraints);
		        
		        constraints.ipady = 10;
		        constraints.gridx = 1;
		        constraints.gridy = 1;
		        chatTextInputField.setBorder(BorderFactory.createLoweredBevelBorder());
		        
		        chatPanel.add(chatTextInputField,constraints);
		        
		        constraints.gridx = 1;
		        constraints.gridy = 2;
		        constraints.ipady = 0;
		        chatPanel.add(sendButton, constraints);
		        
				return chatPanel;
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