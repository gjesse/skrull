package skrull.game.view;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.swing.*;
import org.apache.log4j.Logger;

import skrull.game.factory.IGameFactory;
import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.IGameModel;
import skrull.util.logging.SkrullLogger;

public class GameClientView extends JFrame implements IGameClientView{

	private static final Logger logger = SkrullLogger.getLogger(GameClientView.class);
	private static final long serialVersionUID = 733356106858477245L;
	 private static final String IMAGE_DIR = System.getProperty("image.dir");
	private ClientInputHandler cih;
	private UUID playerId;
	private JTextField chatTextInputField;
	private int gameId = IGameFactory.DEFAULT_GAME_ID;
	JTextArea chatWindow;
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
		updateBoard(model);
		this.gameId = model.getGameId();
	}
	 public void itemStateChanged(ItemEvent evt){
		 CardLayout cl = (CardLayout)(cards.getLayout());
		 cl.show(cards,(String)evt.getItem());
	 }
/*	private void initBoard() {
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
	        
	        JButton chatButton = new JButton(sendChat);
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

	}*/
	private JPanel buildClientGameView() {
				Handler handler = new Handler();

				JFrame gameWindowFrame = new JFrame("Skrull");						//overall window frame title

		/*******************************************************************************************		
				//hoping to set up UI in a way that we have the left side of the window as the game 
				//and the right side as the housing for the chat client
		*********************************************************************************************/		

				gameBoardPanel = new JPanel();
				gameBoardPanel.setPreferredSize(new Dimension(600,0));
				gameBoardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));	//trying to create a border that will surround the game panel; not sure what the 3 does
				gameBoardPanel.setBackground(Color.WHITE);
				
				return gameBoardPanel;
				//gameBoardPanel.setLayout(new FlowLayout());
				//mainFrame.getContentPane().add(gameBoardPanel);
				
				//display either the sample rock paper scissor board or tic tac toe board
				//within the game panel
				
				//gameBoardPanel.add(sampleTicTacToeBoard());
				//gameBoardPanel.add(sampleRockPaperScissorBoard());
				
		//		gameWindowFrame.getContentPane().add(gameBoardPanel,BorderLayout.WEST);		
		//		gameWindowFrame.getContentPane().add(myChattingPanel, BorderLayout.EAST);	
		//		gameWindowFrame.getContentPane().add(userPanel,BorderLayout.SOUTH);
				
		//		gameWindowFrame.setSize(800, 600);
		//		gameWindowFrame.setResizable(false);
		//		gameWindowFrame.setVisible(true);
				
			}
		/************************************************************************************************************/
	
/*	private void createGUI(){

		mainFrame = new JFrame();
		GameClientView gcv = new GameClientView(cih, playerId);
		gcv.buildClientMainView(mainFrame.getContentPane());
		
		mainFrame.setVisible(true);
		
	}*/
	private void buildClientMainView(JPanel myPanel){
				
/*		
		JPanel firstUserView = new JPanel();//card 1
		JPanel gameView = new JPanel();//card2
		JPanel tttView = new JPanel();
		JPanel rpsView = new JPanel();
		//at this point the first card(panel) should
		//be set up similar to when calling the buildClientMainView
		//so it should have 3 columns with the approriate lists
		//and the chat client on the right side
		
		firstUserView.add(buildUserPanel());
		firstUserView.add(buildChatClient());
		gameView.add(buildClientGameView());
		gameView.add(buildChatClient());
		
		cards = new JPanel(new CardLayout());
		cards.add(firstUserView);
		cards.add(gameView);
		pane.add(cards);*/
	//	mainFrame.getContentPane();
		
		//userPanel = new JPanel();
		//userPanel = (JPanel)buildUserPanel();
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
		
		right.setBackground(Color.black);
		
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
	
/*	class StartListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			
			GameType gameSelection = (GameType)newGameList.getSelectedValue();
			System.out.println("printing out the game selection rather than numbered index: "+ gameSelection);
			
		}
	}
	class JoinListener implements ActionListener{
		public void actionPerformed(ActionEvent joinEvent){
			
		   
		    * TODO
		    * need to get the actual games available to join rather than using the sample 
		    * list of games.
		    * 
			
		   //GameType joinGameSelection = (GameType)activeGamesToJoin.getSelectedValue();
		  
		   String joinGameSelection = (String)activeGamesToJoin.getSelectedValue();
		   System.out.println("printing out the join game selection rather than numbered index: "+ joinGameSelection);
		   
		}
	}*/
	
	
/*	May need to use this for the list with some alterations
 * class MyListSelectionListener implements ListSelectionListener {
		
	    // This method is called each time the user changes the set of selected items
	    public void valueChanged(ListSelectionEvent evt) {
	        // When the user release the mouse button and completes the selection,
	        // getValueIsAdjusting() becomes false
	        if (!evt.getValueIsAdjusting()) {
	            JList list = (JList)evt.getSource();

	            // Get all selected items
	            Object[] selected = list.getSelectedValues();

	            // Iterate all selected items
	            for (int i=0; i<selected.length; i++) {
	                Object sel = selected[i];
	            }
	        }
	    }
	}		*/
	private JPanel buildChatClient(){
				
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

	private JPanel sampleTicTacToeBoard(){
		
		gameBoardPanel = new JPanel();
		gameBoardPanel.setPreferredSize(new Dimension(600,0));
		gameBoardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));	//trying to create a border that will surround the game panel; not sure what the 3 does
		gameBoardPanel.setBackground(Color.WHITE);
		
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
		
		gameBoardPanel.add(gameBoard);
		return gameBoardPanel;
		
	}
	
	private JPanel sampleRockPaperScissorBoard(){
		
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
			logger.error("error reading from " + IMAGE_DIR, e);
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
		Collection<IGameModel> games = model.getActiveGames();
		Collection<String> activeGames = new HashSet<String>();
		for (IGameModel game: games){
			activeGames.add(game.getGameId() + ":" + game.getGameType());
		}
		// this needs to redraw the join list for the default game but it's not now
		activeGamesToJoin = new JList(activeGames.toArray());
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
			   GameType gameSelection = (GameType)newGameList.getSelectedValue();
			   if(gameSelection == IGameFactory.GameType.TIC_TAC_TOE){
				   buildClientMainView(sampleTicTacToeBoard());
			   }
			   else if(gameSelection == IGameFactory.GameType.ROCK_PAPER_SCISSORS){
				   buildClientMainView(sampleRockPaperScissorBoard());
			   }
			   else
				   JOptionPane.showInputDialog("DEFAULT");
			   
			   System.out.println("printing out the game selection rather than numbered index: "+ gameSelection);
			   
		   }
		   else if(command == joinGame){
			   String joinGameSelection = (String)activeGamesToJoin.getSelectedValue();
			   System.out.println("printing out the join game selection rather than numbered index: "+ joinGameSelection); 
			   //buildClientMainView();
		   }
		   
		   cih.handleInput(e);
       }
	   
	
   }

@Override
public int getGameId() {
	return gameId;
}
   


	
}
