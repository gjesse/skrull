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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import skrull.game.factory.IGameFactory;
import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.IGameModel;
import skrull.rmi.client.IClientListener;

public class GameClientView extends JFrame implements IGameClientView{

	private static final long serialVersionUID = 733356106858477245L;
	 private static final String IMAGE_DIR = System.getProperty("image.dir");
	private ClientInputHandler cih;
	private UUID playerId;
	private JTextField chatTextInputField;
	private int gameId = IGameFactory.DEFAULT_GAME_ID;
	JTextArea chatWindow;
	private JButton ticTacToeButtons[] = new JButton[9];
	JButton exitButton;
	JButton sendButton;
	JButton startButton;
	JButton joinButton;
	JPanel cards;
	JPanel right;
	JList newGameList;
	JList activeGamesToJoin;
	JPanel gameBoardPanel;
	JPanel userPanel;
	JFrame mainFrame;
	String createGame = IClientAction.ActionType.CREATE_GAME.toString();
	String joinGame = IClientAction.ActionType.JOIN_GAME.toString();
	String sendChat = IClientAction.ActionType.CHAT.toString();
	String pickMe = "Choose Me!";
	String sampleGamesToJoin[] = {"sampleGame1","sampleGame2","sampleGame3","sampleGame4",
			"sampleGame5","sampleGame6","sampleGame7","sampleGame8","sampleGame9","sampleGame1",
			"sampleGame2","sampleGame3","sampleGame4","sampleGame5","sampleGame6","sampleGame7",
			"sampleGame8","sampleGame9","sampleGame8","sampleGame7","sampleGame6","sampleGame5",
			"sampleGame4","sampleGame3","sampleGame2","sampleGame1","sampleGame2","sampleGame3",
			"sampleGame4","sampleGame5","sampleGame6"};
	

	public GameClientView(ClientInputHandler cih, UUID playerId) {
		this.cih = cih;
		this.playerId = playerId;

		userPanel = new JPanel();
		userPanel = (JPanel)buildUserPanel();
		buildClientMainView(userPanel);	
	}
	/*
	 * @see skrull.game.view.IGameClientView#modelChanged(skrull.game.model.IGameModel)
	 * */
	 @Override
	public void modelChanged(IGameModel model) {
		System.out.println("model changed.. " + model);
		updateBoard(model);
		this.gameId = model.getGameId();
	}
	 public void itemStateChanged(ItemEvent evt){
		 CardLayout cl = (CardLayout)(cards.getLayout());
		 cl.show(cards,(String)evt.getItem());
	 }
	private void buildClientMainView(JPanel myPanel){
				
		userPanel = myPanel;
		
		mainFrame = new JFrame("User: " + playerId.toString());

		right = new JPanel();
		
		right.setPreferredSize(new Dimension(200,0));
	
		/*******************RIGHT PROPERTIES************************/	
		right.add(buildChatClient());
		right.setBorder(BorderFactory.createEtchedBorder());
		
		//finally able to add the left and middle to their own panel
		//however the chat client has grown in size
		
		GridLayout mainGrid = new GridLayout(0,2);
		mainFrame.setLayout(mainGrid);

		mainFrame.getContentPane().add(userPanel);
		
		mainFrame.getContentPane().add(right);
		
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(800,600);
		mainFrame.setVisible(true);
		mainFrame.setResizable(false);
				
	}
	private JPanel buildUserPanel(){
		
		Handler handler = new Handler();
		
		userPanel = new JPanel();
		JPanel left = new JPanel();
		JPanel middle = new JPanel();
		
		left.setSize(new Dimension(300,0));
		middle.setSize(new Dimension(300,0));
		userPanel.setSize(new Dimension(600,0));
		userPanel.setLayout(new GridLayout(0,2));
		
		startButton = new JButton(createGame);
		startButton.addActionListener(handler);
		
		joinButton = new JButton(joinGame);
		joinButton.addActionListener(handler);
		
		/*******************LEFT PROPERTIES************************/
		left.setLayout(new GridLayout(3,0));	
		
		newGameList = new JList(IGameFactory.GameType.values());
			
		newGameList.setBorder(BorderFactory.createTitledBorder("Create A New Game"));
		
		left.add(newGameList);
		left.add(startButton);
		left.setBorder(BorderFactory.createEtchedBorder());
		
		/*******************MIDDLE PROPERTIES************************/
		middle.setLayout(new GridLayout(3,0));
		activeGamesToJoin = new JList(sampleGamesToJoin);
		
		JScrollPane activeGameScroller = new JScrollPane(activeGamesToJoin,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		activeGameScroller.setBorder(BorderFactory.createTitledBorder("Join A Game"));
		middle.add(activeGameScroller);
		middle.add(joinButton);
		middle.setBorder(BorderFactory.createEtchedBorder());
		
		userPanel.add(left);
		userPanel.add(middle);
		return userPanel;
	}
	private JPanel buildChatClient(){

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

	private JPanel sampleTicTacToeBoard(){
		
		JPanel gameBoard = new JPanel();
		
		gameBoardPanel = new JPanel();
		gameBoardPanel.setPreferredSize(new Dimension(600,0));
		//gameBoardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));	
		gameBoardPanel.setBackground(Color.WHITE);
		
		TicTacToeHandler gameHandler = new TicTacToeHandler();
		
		gameBoard.setLayout(new GridBagLayout());
		GridBagConstraints constrainers = new GridBagConstraints();
		gameBoard.setBackground(Color.WHITE);
		
		constrainers.fill = GridBagConstraints.BOTH;
		constrainers.ipadx = 25;
		constrainers.ipady = 25;
		constrainers.anchor = GridBagConstraints.CENTER;
		constrainers.gridx = 0;
		constrainers.gridy = 0;
		constrainers.insets = new Insets(75,2,2,2);
		
		for(int i = 0; i < 9; i++){
			ticTacToeButtons[i] = new JButton(pickMe);
			
			ticTacToeButtons[i].addActionListener(gameHandler);
			if(constrainers.gridx == 3){
				constrainers.gridx = 0;
				constrainers.gridy++;
			}
			gameBoard.add(ticTacToeButtons[i],constrainers);
			constrainers.gridx++;
			 
		}
		
		gameBoardPanel.add(gameBoard);
		return gameBoardPanel;
		
	}
	class TicTacToeHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent buttonEvent) {
			// TODO need to determine turn and change the button text to 
			//show whether and where X or O has been placed 
			
			JButton buttonPressed = (JButton)buttonEvent.getSource();
			buttonPressed.setText("X");
			buttonPressed.setEnabled(false);
		}
		
	}
	class RockPaperScissorHandler implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent buttonEvent) {
			// TODO Auto-generated method stub
		
		}
	
	}
	private JPanel sampleRockPaperScissorBoard(){
		
		JPanel rpsBoard = new JPanel();
		rpsBoard.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		
		
		BufferedImage buttonIcon = null;
		BufferedImage buttonIcon2 = null;
		BufferedImage buttonIcon3 = null;
		
		try {
			buttonIcon = ImageIO.read(new File("C:/Users/Jeremiah/Code/skrull/skrull-client/src/res/Paperscaled.jpg"));
			buttonIcon2 = ImageIO.read(new File("C:/Users/Jeremiah/Code/skrull/skrull-client/src/res/Rockscaled2.jpg"));
			buttonIcon3 = ImageIO.read(new File("C:/Users/Jeremiah/Code/skrull/skrull-client/src/res/Scissorsscaled.jpg"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JButton paperButton = new JButton(new ImageIcon(buttonIcon));
		JButton rockButton = new JButton(new ImageIcon(buttonIcon2));
		JButton scissorButton = new JButton(new ImageIcon(buttonIcon3));

/*		paperButton.setBorder(BorderFactory.createEmptyBorder());
		rockButton.setBorder(BorderFactory.createEmptyBorder());
		scissorButton.setBorder(BorderFactory.createEmptyBorder());
		*/
		paperButton.setBackground(Color.WHITE);
		rockButton.setBackground(Color.WHITE);
		scissorButton.setBackground(Color.WHITE);
		
	
		rpsBoard.setBackground(Color.white);
		c.insets = new Insets(25,0,15,15);
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
		   
		   String command = e.getActionCommand();
		   
		   //going to need to also pass the game type to the clientInputHandler
		   //so that once the create game is clicked the case will also see what kind of
		   //game to create
		  
		 //for testing purposes
		   if(command == createGame){
			   //getting selection from the list box the user is using
			   GameType gameSelection = (GameType)newGameList.getSelectedValue();
			   
			   if(gameSelection == IGameFactory.GameType.TIC_TAC_TOE){
				   buildClientMainView(sampleTicTacToeBoard());
			   }
			   else if(gameSelection == IGameFactory.GameType.ROCK_PAPER_SCISSORS){
				   buildClientMainView(sampleRockPaperScissorBoard());
			   }
			   else if(gameSelection == null)
				   JOptionPane.showMessageDialog(null,"No selection was made");
			   
			   else
				   JOptionPane.showMessageDialog(null,"DEFAULT");
			   
			   System.out.println("printing out the game selection rather than numbered index: "+ gameSelection);
			   
		   }
		   else if(command == joinGame){
			   String joinGameSelection = (String)activeGamesToJoin.getSelectedValue();
			   System.out.println("printing out the join game selection rather than numbered index: "+ joinGameSelection); 
		   }
		   
		   cih.handleInput(e);
       }
	   
	
   }

@Override
public int getGameId() {
	// TODO Auto-generated method stub
	return 0;
}
   


	
}