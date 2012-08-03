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
	 public void itemStateChanged(ItemEvent evt){
		 CardLayout cl = (CardLayout)(cards.getLayout());
		 cl.show(cards,(String)evt.getItem());
	 }
	private void buildClientMainView(UserPanel myPanel){
				
		userPanel = myPanel;
		
		mainFrame = new JFrame("User: " + playerId.toString()){};

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
		
		right = new JPanel(){
			protected void paintComponent(Graphics g){
				Graphics2D g2d = (Graphics2D)g;
				
			    if ( !isOpaque() )
			    {
			        super.paintComponent(g);
			        return;
			    }
			 
			    int w = getWidth( );
			    int h = getHeight( );
			    Color color1 = getBackground( );
			    Color color2 = color1.darker( );
			    
			    // Paint a gradient from top to bottom
			    GradientPaint gp = new GradientPaint(
			        0, 0, color1,
			        0, h, color2 );

			    g2d.setPaint( gp );
			    g2d.fillRect( 0, 0, w, h );
			    
			    setOpaque( false );
			    super.paintComponent( g );
			    setOpaque( true );  
			}
		};
		
		right.setPreferredSize(new Dimension(200,0));
	
		/*******************RIGHT PROPERTIES************************/	
		
		chatPanel = new ChatPanel(cih);
		right.add( chatPanel );
		
		right.setBorder(BorderFactory.createEtchedBorder());
		
		//finally able to add the left and middle to their own panel
		//however the chat client has grown in size
		
		GridLayout mainGrid = new GridLayout(0,2);
		mainFrame.setLayout(mainGrid);

		mainFrame.getContentPane().add(userPanel);
		
		mainFrame.getContentPane().add(right);
		
		mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setSize(800,600);
		mainFrame.setVisible(true);
		mainFrame.setResizable(false);
				
	}




	public JPanel winnerPanel(){
		winnerPanel = new JPanel(){
			protected void paintComponent(Graphics g){
				Graphics2D g2d = (Graphics2D)g;
				
			    if ( !isOpaque() )
			    {
			        super.paintComponent(g);
			        return;
			    }
			 
			    int w = getWidth( );
			    int h = getHeight( );
			    Color color1 = getBackground( );
			    Color color2 = color1.darker( );
			    
			    // Paint a gradient from top to bottom
			    GradientPaint gp = new GradientPaint(
			        0, 0, color1,
			        0, h, color2 );

			    g2d.setPaint( gp );
			    g2d.fillRect( 0, 0, w, h );
			    
			    setOpaque( false );
			    super.paintComponent( g );
			    setOpaque( true );  
			}
		
			
		};
		JButton winnerButton = new JButton("WINNER!!!");
		
		winnerButton.setForeground(Color.cyan);
		winnerButton.setBackground(Color.black);
		winnerButton.setBorder(BorderFactory.createEmptyBorder());
		winnerPanel.setLayout(new GridBagLayout());
		GridBagConstraints winnerConstraints = new GridBagConstraints();
		winnerConstraints.ipadx = 400;
		winnerConstraints.ipady = 50;
		winnerConstraints.gridx = 1;
		winnerConstraints.gridy = 1;
		winnerConstraints.fill = GridBagConstraints.BOTH;
		
		
		winnerPanel.add(winnerButton,winnerConstraints);
		
		winnerPanel.setPreferredSize(new Dimension(600,0));
		//winnerPanel.setBackground(Color.white);
		
		return winnerPanel;
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
		   
		   cih.handleInput(e);
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
