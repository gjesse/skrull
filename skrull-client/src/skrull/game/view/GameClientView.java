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
import skrull.game.model.IPlayer;
import skrull.util.logging.SkrullLogger;

public class GameClientView extends JFrame implements IGameClientView{

	private static final Logger logger = SkrullLogger.getLogger(GameClientView.class);
	private static final long serialVersionUID = 733356106858477245L;
	 private static final String IMAGE_DIR = System.getProperty("image.dir");
	private ClientInputHandler cih;

	private int gameId = IGameFactory.DEFAULT_GAME_ID;

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
	String createGame = IClientAction.ActionType.CREATE_GAME.toString();
	String joinGame = IClientAction.ActionType.JOIN_GAME.toString();
	String sendChat = IClientAction.ActionType.CHAT.toString();
	String pickMe = "Choose Me!";

	IPlayer whoWon;
	
	private GameType gameType;
	private ChatPanel chatPanel;
	private IPlayer player;
	

	public GameClientView(ClientInputHandler cih, IPlayer player) {
		this.cih = cih;
		this.player = player;
		this.gameType = GameType.DEFAULT; // start in default game
		

		userPanel = new DefaultPanel(cih, player);
		buildClientMainView(userPanel);	
		
	}
	/*
	 * @see skrull.game.view.IGameClientView#modelChanged(skrull.game.model.IGameModel)
	 * */
	
	 @Override
	public void modelChanged(IGameModel model) {
		 
		 //if game id from game is not equal to model.getGameId
		 //then want to create the new view 
		 
		 if(gameId != model.getGameId() ){
			 logger.debug("game id " + gameId + " not old game id, creating a new view");
			 this.gameId = model.getGameId();
			 updateView(model);
		 }
		 
		 updateBoard(model);
		

	}
	private void updateView(IGameModel model){
		
		//if we are still on the main screen with the default panel
/*		if( model.getGameType() == GameType.DEFAULT ){
			

		}
		else */
		if(model.getGameType() == GameType.TIC_TAC_TOE){
			System.out.println("INSIDE THE TTT PART OF UPDATE BOARD");
			System.out.println("game Type  ="+ gameType + " model.getGameType = "+model.getGameType() );
	
			mainFrame.removeAll();
			mainFrame.setVisible(false);

			userPanel = new TicTacToePanel(cih, player);
			userPanel.repaint();
			mainFrame.repaint();
			buildClientMainView(userPanel);

		}
		else if(model.getGameType() == GameType.ROCK_PAPER_SCISSORS){
			//JOptionPane.showMessageDialog(null,"about to make a new RPS Panel");
			
			userPanel = new RockPaperScissorsPanel(cih, player);
			mainFrame.removeAll();
			mainFrame.setVisible(false);

			userPanel.repaint();
			mainFrame.repaint();
			buildClientMainView(userPanel);
		}
		else if( model.isGameOver() ){
			//if there is a winner then display the winner panel
			whoWon = model.getWinner();
			
			userPanel = new WinnerPanel(cih, player);
			mainFrame.removeAll();
			mainFrame.setVisible(false);
			userPanel.repaint();
			mainFrame.repaint();
			buildClientMainView(userPanel);
		}
	}
	private void updateBoard(IGameModel model) {
			
		//chatTextInputField.setText("got a message from the model - player id " + playerId + " " + model.getGameType());
		
		userPanel.modelChanged(model);
		chatPanel.setText(model.getChatContents());
		setBroadcastMessage( model.getBroadcastMessage() );
		gameType = model.getGameType();

	}
	@Override	
	public void setBroadcastMessage(String broadcastMessage) {
		//TODO check if blank or null string
		//if its blank or null do nothing otherwise print out
		if(broadcastMessage == null){
			System.out.println("nothing to broadcast");
		}
		else
			if(gameType == GameType.ROCK_PAPER_SCISSORS)
				userPanel.getMessage();
			chatPanel.addMessage(broadcastMessage);
	}
	public String getMessage(){
		return userPanel.getMessage();
	}
	public IPlayer getWinner(){
		return whoWon;
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
		
		mainFrame = new JFrame("gameId: "+gameId+"---"+"GameType: "+gameType+"---"+"User: " + player ){};

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
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
		mainFrame.setResizable(false);
				
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

	@Override
	public String getJoinGameString() {
		return userPanel.getJoinGameString();
	}
	public int getSelectedButton(){
		return userPanel.getSelectedButton();
	}


}
