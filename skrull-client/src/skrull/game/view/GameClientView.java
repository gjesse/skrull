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

	private int gameId = IGameFactory.DEFAULT_GAME_ID;

	private JButton ticTacToeButtons[] = new JButton[9];
	JButton exitButton;

	JButton startButton;
	JButton joinButton;
	JPanel cards;
	JPanel right;
	JList newGameList;
	JList activeGamesToJoin;
	JPanel gameBoardPanel;
	UserPanel userPanel;
	JPanel winnerPanel;
	JFrame mainFrame;
	private int count = 0;
	private String marker = "";
	String createGame = IClientAction.ActionType.CREATE_GAME.toString();
	String joinGame = IClientAction.ActionType.JOIN_GAME.toString();
	String sendChat = IClientAction.ActionType.CHAT.toString();
	String pickMe = "Choose Me!";

	private GameType gameType;
	private ChatPanel chatPanel;
	

	public GameClientView(ClientInputHandler cih, UUID playerId) {
		this.cih = cih;
		this.playerId = playerId;
		this.gameType = GameType.DEFAULT; // start in default game
		
		//gameId, player, type, view.getGameType(), view.getChatText(), null)
		//ClientAction ca = new ClientAction(cih, playerId, gameType, getChatText(), null);
		userPanel = new DefaultPanel(cih);
		
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
	 
	private void buildClientMainView(UserPanel myPanel){
		
		//USER PANEL WILL EITHER BE THE DEFAULT PANEL, 
		//TIC TAC TOE PANEL, ROCK PAPER SCISSORS PANEL,
		//THE WINNER PANEL, OR THE LOSER PANEL
		
		
		/*
		 * TODO
		 * NEED TO CHECK IF THE gameId EXISTS; IF IT DOESNT THEN WE 
		 * CREATE A NEW_GAME PANEL
		 * 
		 * OTHERWISE NEED TO GET THE EXISTING PANEL?
		 * */
		
		userPanel = myPanel;
		
		mainFrame = new JFrame("GameType: "+gameType+"---"+"User: " + playerId.toString()){};

		mainFrame.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				logger.debug("close event received");
				cih.handleWindowEvent(e);
				System.exit(0);
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		/*******************RIGHT PROPERTIES************************/	
		
		right = new JPanel();
		
		right.setPreferredSize(new Dimension(200,0));
	
		chatPanel = new ChatPanel(cih);
		//ADDING THE CHAT PANEL TO THE RIGHT SIDE OF THE WINDOW
		right.add( chatPanel );
		
		right.setBorder(BorderFactory.createEtchedBorder());
		
		//SETTING THE LAYOUT OF THE OVERALL FRAME
		GridLayout mainGrid = new GridLayout(0,2);
		
		mainFrame.setLayout(mainGrid);

		mainFrame.getContentPane().add(userPanel);		
		mainFrame.getContentPane().add(right);
		
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setSize(800,600);
		mainFrame.setVisible(true);
		mainFrame.setResizable(false);
				
	}

	
	private void updateBoard(IGameModel model) {
		
		//chatTextInputField.setText("got a message from the model - player id " + playerId + " " + model.getGameType());
		
		chatPanel.setText(model.getChatContents());
		
		Collection<IGameModel> games = model.getActiveGames();
		Collection<String> activeGames = new HashSet<String>();
		
		for (IGameModel game: games){
			activeGames.add(game.getGameId() + ":" + game.getGameType());
			logger.debug("active game: " + game);
		}
		
		// this needs to redraw the join list for the default game but it's not now
		activeGamesToJoin = new JList(activeGames.toArray());
	}

	public String getChatText(){
		//lJOptionPane.showMessageDialog(null,"INSIDE GAMECLIENTVIEW--about to get text from the GameClientView calling chatPanel.text()");
		return chatPanel.getChatText();
	}
	
    // Inner classes for Event Handling 
   class Handler implements ActionListener {
       // Event handling is handled locally
	   @Override
       public void actionPerformed(ActionEvent e) {
		   
/*		   String command = e.getActionCommand();
		   
		   //going to need to also pass the game type to the clientInputHandler
		   //so that once the create game is clicked the case will also see what kind of
		   //game to create
		  
		 //for testing purposes
		   if(command == createGame){
			   //getting selection from the list box the user is using
			   GameType gameSelection = (GameType)newGameList.getSelectedValue();
			   // save the game type for the handler
			   setGameType(gameSelection);
			   if(gameSelection == IGameFactory.GameType.TIC_TAC_TOE){
				   mainFrame.setVisible(false);
				   userPanel = new TicTacToePanel(cih);
				   buildClientMainView( userPanel );
			   }
			   else if(gameSelection == IGameFactory.GameType.ROCK_PAPER_SCISSORS){
				   mainFrame.setVisible(false);
				   userPanel = new RockPaperScissorsPanel(cih);
				   buildClientMainView( userPanel );
				   
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
		   }*/
		   JOptionPane.showMessageDialog(null,"In the handler for GameClientView");
		   //cih.handleInput(e);
       }
	   
	
   }

	@Override
	public int getGameId() {
		return gameId;
	}
	
	@Override
	public GameType getGameType() {
		return userPanel.getGameType();
	}

}
