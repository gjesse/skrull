package skrull.game.view;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.BevelBorder;

import skrull.game.factory.IGameFactory;
import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.IGameModel;
import skrull.rmi.client.IClientListener;

public class GameClientView extends JFrame {

	private static final long serialVersionUID = 733356106858477245L;
	private static final String IMAGE_DIR = System.getProperty("image.dir");
	private ClientInputHandler cih;
	private UUID playerId;
	private JTextField chatTextInputField;
	JTextArea chatWindow;
	JButton exitButton;
	JButton sendButton;
	JButton startButton;
	JButton joinButton;
	JPanel cards;
	
	String sampleGameList[] = {"Tic-Tac-Toe", "Rock-Paper-Scissors","No Game"};
	String sampleGamesToJoin[] = {"sampleGame1","sampleGame2","sampleGame3","sampleGame4",
			"sampleGame5","sampleGame6","sampleGame7","sampleGame8","sampleGame9","sampleGame1",
			"sampleGame2","sampleGame3","sampleGame4","sampleGame5","sampleGame6","sampleGame7",
			"sampleGame8","sampleGame9","sampleGame8","sampleGame7","sampleGame6","sampleGame5",
			"sampleGame4","sampleGame3","sampleGame2","sampleGame1","sampleGame2","sampleGame3",
			"sampleGame4","sampleGame5","sampleGame6"};
	

	public GameClientView(ClientInputHandler cih, UUID playerId) {
		this.cih = cih;
		this.playerId = playerId;
		
		
		//initBoard();
	//	cards = new JPanel(new CardLayout());
		//cards.add(buildClientMainView());
	//	cards.add(buildClientGameView());
		buildClientMainView();
	//	buildClientGameView();
		
		
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
	        
	        chatWindow = new JTextArea(5,20);
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
	        
	        
	        
	        
			ImageIcon img = new ImageIcon("/skrull-client/src/res/Paper.jpg");
			JButton button = new JButton(img);
			p2.add(button);
			
			
			
			
	    	getContentPane().add(p2, BorderLayout.NORTH);
	        pack();
	        setVisible(true);

	}
	public void buildClientGameView() {
				Handler handler = new Handler();
		//		exitButton = new JButton("Exit");
		//		exitButton.addActionListener(handler);
				
				JFrame gameWindowFrame = new JFrame("Skrull");						//overall window frame title
				
	//			JPanel userPanel = new JPanel();									
	//			userPanel.add(exitButton, null);
				
				
		/*******************************************************************************************		
				//hoping to set up UI in a way that we have the left side of the window as the game 
				//and the right side as the housing for the chat client
		*********************************************************************************************/		
		//		JPanel myChattingPanel = new JPanel();
		//		myChattingPanel.add(buildChatClient());
				
				
				JPanel gameBoardPanel = new JPanel();
				gameBoardPanel.setPreferredSize(new Dimension(600,0));
				gameBoardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));	//trying to create a border that will surround the game panel; not sure what the 3 does
				gameBoardPanel.setBackground(Color.WHITE);
				
				gameBoardPanel.setLayout(new FlowLayout());
				
				//display either the sample rock paper scissor board or tic tac toe board
				//within the game panel
				
				//gameBoardPanel.add(sampleTicTacToeBoard());
				//gameBoardPanel.add(sampleRockPaperScissorBoard());
				
				gameWindowFrame.getContentPane().add(gameBoardPanel,BorderLayout.WEST);		
		//		gameWindowFrame.getContentPane().add(myChattingPanel, BorderLayout.EAST);	
		//		gameWindowFrame.getContentPane().add(userPanel,BorderLayout.SOUTH);
				
				gameWindowFrame.setSize(800, 600);
				gameWindowFrame.setResizable(false);
				gameWindowFrame.setVisible(true);
				
			}
		/************************************************************************************************************/
	public void buildClientMainView(){
				
				JFrame mainFrame = new JFrame("User: " + playerId.toString());
				
				Handler handler = new Handler();
				
				JPanel left = new JPanel();
				JPanel middle = new JPanel();
				JPanel right = new JPanel();
				
				left.setPreferredSize(new Dimension(300,0));
				middle.setPreferredSize(new Dimension(300,0));
				right.setPreferredSize(new Dimension(200,0));
				
				startButton = new JButton("Start Game");
				startButton.addActionListener(handler);
				
				joinButton = new JButton("Join Game");
				joinButton.addActionListener(handler);
				
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
				
				JScrollPane activeGameScroller = new JScrollPane(activeGamesToJoin,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				activeGameScroller.setBorder(BorderFactory.createTitledBorder("Join A Game"));
				middle.add(activeGameScroller);
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
				mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mainFrame.setSize(800,600);
				mainFrame.setVisible(true);
				mainFrame.setResizable(false);
				
			}
			
	private Component buildChatClient(){
				
				//chat panel will have textArea, textField, SendButton, 
		
				Handler handler = new Handler();
				
				chatTextInputField = new JTextField();
				chatTextInputField.setText("Enter Message...");
				chatTextInputField.setForeground(Color.gray);
				chatTextInputField.addActionListener(handler);
				
				chatWindow = new JTextArea(5,20);
		        chatWindow.setText("Welcome to Skrull Gaming...");
				chatWindow.setEditable(false);
				
				sendButton = new JButton("send chat");
				
				JPanel chatPanel = new JPanel(new GridBagLayout());					
				GridBagConstraints constraints = new GridBagConstraints();
				

		        sendButton.setActionCommand(IClientAction.ActionType.CHAT.toString());
		        
		        sendButton.addActionListener(handler);
		        
		        JScrollPane scroll = new JScrollPane(chatWindow, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		        
		        
		        constraints.fill = GridBagConstraints.BOTH;
		        constraints.gridx = 1;
		        constraints.gridy = 0;
		        constraints.ipady = 100;
		        chatPanel.add(scroll, constraints);
		        
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

	private Component sampleTicTacToeBoard(){
		
		JPanel gameBoard = new JPanel();
		gameBoard.setLayout(new GridBagLayout());
		GridBagConstraints constrainers = new GridBagConstraints();
		gameBoard.setBackground(Color.WHITE);
		constrainers.fill = GridBagConstraints.BOTH;
		constrainers.ipadx = 50;
		constrainers.ipady = 50;
		constrainers.anchor = GridBagConstraints.CENTER;
		
		JButton b1 = new JButton("--");
		constrainers.gridx = 0;
		constrainers.gridy = 0;
		constrainers.insets = new Insets(75,2,10,20);
		gameBoard.add(b1,constrainers);
		
		JButton b2 = new JButton("--");
		constrainers.gridx = 1;
		constrainers.gridy = 0;
		gameBoard.add(b2,constrainers);
		
		JButton b3 = new JButton("--");
		constrainers.gridx = 2;
		constrainers.gridy = 0;
		gameBoard.add(b3,constrainers);
		
		JButton b4 = new JButton("--");
		constrainers.gridx = 0;
		constrainers.gridy = 1;
		gameBoard.add(b4,constrainers);
		
		JButton b5 = new JButton("--");
		constrainers.gridx = 1;
		constrainers.gridy = 1;
		gameBoard.add(b5,constrainers);
		
		JButton b6 = new JButton("--");
		constrainers.gridx = 2;
		constrainers.gridy = 1;
		gameBoard.add(b6,constrainers);
		
		JButton b7 = new JButton("--");
		constrainers.gridx = 0;
		constrainers.gridy = 2;
		gameBoard.add(b7,constrainers);
		
		JButton b8 = new JButton("--");
		constrainers.gridx = 1;
		constrainers.gridy = 2;
		gameBoard.add(b8,constrainers);
		
		JButton b9 = new JButton("--");
		constrainers.gridx = 2;
		constrainers.gridy = 2;
		gameBoard.add(b9,constrainers);
		
		return gameBoard;
		
	}
	
	private Component sampleRockPaperScissorBoard(){
		
		JPanel rpsBoard = new JPanel();
		rpsBoard.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		
		BufferedImage buttonIcon = null;
		BufferedImage buttonIcon2 = null;
		BufferedImage buttonIcon3 = null;
		
		try {
			buttonIcon = ImageIO.read(new File(IMAGE_DIR + "Paperscaled.jpg"));
			buttonIcon2 = ImageIO.read(new File(IMAGE_DIR + "Rockscaled2.jpg"));
			buttonIcon3 = ImageIO.read(new File(IMAGE_DIR + "Scissorsscaled.jpg"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("error reading from " + IMAGE_DIR);
			e.printStackTrace();
		}
		
		JButton paperButton = new JButton(new ImageIcon(buttonIcon));
		JButton rockButton = new JButton(new ImageIcon(buttonIcon2));
		JButton scissorButton = new JButton(new ImageIcon(buttonIcon3));
;
		paperButton.setBorder(BorderFactory.createEmptyBorder());
		rockButton.setBorder(BorderFactory.createEmptyBorder());
		scissorButton.setBorder(BorderFactory.createEmptyBorder());
		
		paperButton.setBackground(Color.WHITE);
		rockButton.setBackground(Color.WHITE);
		scissorButton.setBackground(Color.WHITE);
		
		rpsBoard.setBackground(Color.white);
		c.insets = new Insets(200,0,15,15);
		c.gridx = 0;
		rpsBoard.add(rockButton,c);
		c.gridx = 1;
		rpsBoard.add(paperButton,c);
		c.gridx = 2;
		rpsBoard.add(scissorButton,c);
		return rpsBoard;
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