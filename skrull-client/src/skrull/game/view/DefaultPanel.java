package skrull.game.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import skrull.game.factory.IGameFactory;
import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.IGameModel;
import skrull.util.logging.SkrullLogger;

public class DefaultPanel extends UserPanel {
	
	private static final Logger logger = SkrullLogger.getLogger(GameClientView.class);
	//GameType gameType;
	JButton startButton;
	JButton joinButton;
	JList newGameList;
	JList activeGamesToJoin;
	ClientInputHandler cih;
	String createGame = IClientAction.ActionType.CREATE_GAME.toString();
	String joinGame = IClientAction.ActionType.JOIN_GAME.toString();
	private JScrollPane activeGameScroller;
	
	public DefaultPanel(ClientInputHandler cih){
		this.cih = cih;
		buildUserPanel();
	}

	private void buildUserPanel(){
	
		//handler needed for the buttons
		Handler handler = new Handler();

		this.setSize(new Dimension(600,0));
		this.setLayout(new GridLayout(0,2));
		
		startButton = new JButton(createGame);
		startButton.setActionCommand(createGame);
		startButton.addActionListener(handler);
		
		joinButton = new JButton(joinGame);
		joinButton.setActionCommand(joinGame);
		joinButton.addActionListener(handler);
	
		this.add( buildLeftPanel() );
		this.add( buildMiddlePanel() );
	
}
	private JPanel buildLeftPanel(){
		
		JPanel leftPanel = new JPanel(){
			//painting the left panel with a gradient
			
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
		leftPanel.setSize(new Dimension(300,0));
		
		//setting up the layout of the panel
		leftPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.gridx =0;
		gbc.gridy = 0;
		gbc.ipadx = 30;
		gbc.ipady = 75;
		gbc.insets = new Insets(0,10,10,10);
		
		newGameList = new JList(IGameFactory.GameType.values());
			
		newGameList.setBorder(BorderFactory.createTitledBorder("Create A New Game"));
		
		leftPanel.add(newGameList,gbc);
		gbc.gridy = 1;
		gbc.ipadx = 10;
		gbc.ipady = 30;
		gbc.insets = new Insets(3,3,3,3);
		leftPanel.add(startButton,gbc);
		leftPanel.setBorder(BorderFactory.createEtchedBorder());
		
		return leftPanel;
		
	}
	
	private JPanel buildMiddlePanel(){
		
		JPanel middlePanel = new JPanel(){
			
			//painting the middle panel with a gradient
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
		middlePanel.setSize(new Dimension(300,0));
		
		//setting up the layout of the panel
		GridBagConstraints gbc = new GridBagConstraints();
		middlePanel.setLayout(new GridBagLayout());

		gbc.gridx =0;
		gbc.gridy = 0;
		gbc.ipadx = 75;
		gbc.ipady = 0;
		gbc.insets = new Insets(0,0,0,0);
	
		//using a scrollPane for the list of active games
		activeGamesToJoin = new JList(new DefaultListModel());
		activeGameScroller = new JScrollPane(activeGamesToJoin,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		activeGameScroller.setPreferredSize(new Dimension(100,155));
		activeGameScroller.setBorder(BorderFactory.createTitledBorder("Join A Game"));
		middlePanel.add(activeGameScroller,gbc);
		
		gbc.gridy = 1;
		gbc.ipadx = 10;
		gbc.ipady = 30;
		gbc.insets = new Insets(12,3,3,3);
		
		middlePanel.add(joinButton,gbc);
		middlePanel.setBorder(BorderFactory.createEtchedBorder());
		
		return middlePanel;
	}
	
	@Override
	public GameType getGameType() {
		//TODO get user selection of game type from the Jlist
		if(gameType == null){
			gameType = GameType.DEFAULT;
		}
		return gameType;
	}
	
	public class Handler implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			
			   String command = e.getActionCommand();
			   
			   GameType gameSelection = (GameType)newGameList.getSelectedValue();
			   
			   // save the game type for the handler
			   gameType = gameSelection;
			   
			   
			   
			//  if(command == createGame){
				   //getting selection from the list box the user is using

				   
				   /*if(gameSelection == IGameFactory.GameType.TIC_TAC_TOE){
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
	public void modelChanged(IGameModel model) {
		// TODO Auto-generated method stub

		Collection<IGameModel> games = model.getActiveGames();
		//Collection<String> activeGames = new ArrayList<String>();
		DefaultListModel m = new DefaultListModel();
		//activeGamesToJoin = new JList(m);

		for (IGameModel game: games){
			//activeGames.add(game.getGameId() + ":" + game.getGameType());
			m.addElement(game.getGameId() + ":" + game.getGameType());

			logger.debug("active game: " + game);
		}
		activeGamesToJoin.setModel(m);
		
		  SwingUtilities.invokeLater(new Runnable() {
			    public void run() {
			    	activeGamesToJoin.revalidate(); // triggers a repaint of all the items in the JList.
			    	//activeGameScroller.repaint(); // Not sure if this one is needed
			    }
			  });
		//activeGamesToJoin.revalidate();
		//activeGamesToJoin.repaint();
		
		// this needs to redraw the join list for the default game but it's not now
		
		//System.out.println("repaint");
		this.repaint();
		
	}
}
