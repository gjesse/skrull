package skrull.game.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import org.apache.log4j.Logger;

import skrull.game.factory.IGameFactory;
import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.IGameModel;
import skrull.game.model.IPlayer;
import skrull.util.logging.SkrullLogger;
/**
 * GameClientView extends JFrame and implements IGameClientView.
 * GameClientView shall be the starter for all other views to
 * be built. It also provides a model changed function for the
 * view.
 * */

public class GameClientView extends JFrame implements IGameClientView{

	private static final Logger logger = SkrullLogger.getLogger(GameClientView.class);
	private static final long serialVersionUID = 733356106858477245L;
	private ActionListener cih;

	private int gameId = IGameFactory.DEFAULT_GAME_ID;

	JButton exitButton;

	JButton startButton;
	JButton joinButton;
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
	private IPlayer player = null;
	

	public GameClientView(ActionListener cih, IPlayer player) {
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
		

		//if we are still on the main screen with the default panel (or have switched bac)
		if( model.getGameType() == GameType.DEFAULT ){
			
			mainFrame.removeAll();
			mainFrame.setVisible(false);
			userPanel = new DefaultPanel(cih, player);
			userPanel.repaint();
			mainFrame.repaint();
			buildClientMainView(userPanel);
		}
		else
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

	}
	private void updateBoard(IGameModel model) {
			
		//chatTextInputField.setText("got a message from the model - player id " + playerId + " " + model.getGameType());
		if( model.getWinner() != null){
			//if there is a winner then display the winner panel
			whoWon = model.getWinner();	
			userPanel = new WinnerPanel(cih, whoWon);
			mainFrame.removeAll();
			
			//chat panel needs to either chat or come off

			gameType = model.getGameType();
			
			mainFrame.setVisible(false);
			
			userPanel.repaint();
			mainFrame.repaint();
			
			buildClientMainView(userPanel);
			
		}
/*		if( model.getWinner() != null && model.getGameType() == GameType.TIC_TAC_TOE){
			System.out.println("Winner is "+model.getWinner());
			setBroadcastMessage( model.getWinner()+" is the winner!");
		}
		*/
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
		
		userPanel = myPanel;
/*		if(player.getPlayerToken() == null){
			mainFrame = new JFrame("gameId: "+gameId+"---"+"GameType: "+gameType+"---"+"User: " + player );
		}
		else{
			mainFrame = new JFrame("gameId: "+gameId+"---"+"GameType: "+gameType+"---"+"User: " + player.getPlayerToken() );
		}*/
		mainFrame = new JFrame("gameId: "+gameId+"---"+"GameType: "+gameType+"---"+"User: " + player );
		mainFrame.addWindowListener((WindowListener)cih);
		
		
		
		/*******************RIGHT PROPERTIES************************/	
		
		right = new JPanel();
		
		right.setPreferredSize(new Dimension(200,0));
		
		if(whoWon == null){			//if there was not a winner then we will create the chat panel to display
			chatPanel = new ChatPanel(cih);
		
			//ADDING THE CHAT PANEL TO THE RIGHT SIDE OF THE WINDOW
			right.add( chatPanel );
		}
		else{
			GridBagLayout gb = new GridBagLayout();
			GridBagConstraints c = new GridBagConstraints();
			right.setLayout(gb);
			c.ipadx = 50;
			c.ipady = 50;
			c.insets = new Insets(0,0,0,0);
			ReturnToMainButton returnToMain = new ReturnToMainButton(cih);
			right.add(returnToMain,c);
			whoWon = null;
		}
		right.setBorder(BorderFactory.createEtchedBorder());
		
		//SETTING THE LAYOUT OF THE OVERALL FRAME
		GridLayout mainGrid = new GridLayout(0,2);
		
		mainFrame.setLayout(mainGrid);

		mainFrame.getContentPane().add(userPanel);		
		mainFrame.getContentPane().add(right);
		
		mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		mainFrame.setSize(800,600);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setVisible(true);
		mainFrame.setResizable(false);
				
	}

	public String getChatText(){
		//lJOptionPane.showMessageDialog(null,"INSIDE GAMECLIENTVIEW--about to get text from the GameClientView calling chatPanel.text()");
		return chatPanel.getChatText();
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
		if(userPanel.getJoinGameString() == null){
			setBroadcastMessage("You need to make a selection first!");
		}
		return userPanel.getJoinGameString();
	}
	//@Override
	public String getCreateGameString(){
		System.out.println("newgamelist!!!!!!!! "+userPanel.getCreateGameString());
		if(userPanel.getCreateGameString()== null){
			setBroadcastMessage("You need to make a selection first!");
		}
		return userPanel.getCreateGameString();
	}

}
