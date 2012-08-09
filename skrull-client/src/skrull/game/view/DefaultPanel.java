package skrull.game.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.Collection;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import skrull.game.factory.IGameFactory;
import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.IGameModel;
import skrull.game.model.IPlayer;
import skrull.util.logging.SkrullLogger;

public class DefaultPanel extends UserPanel {
	


	/**
	 * DefaultPanel is the default view that the user 
	 * will see upon launching the game. DefaultPanel
	 * has a list of games to create and if available 
	 * a list of games to join. DefaultPanel also
	 * has modelChanged to update the view with 
	 * any new additions to the list of games to join.
	 */
	private static final long serialVersionUID = -3874495546834222191L;
	private static final Logger logger = SkrullLogger.getLogger(GameClientView.class);
	//GameType gameType;
	JButton startButton;
	JButton joinButton;
	JList newGameList;
	JList activeGamesToJoin;
	ActionListener cih;
	String createGame = IClientAction.ActionType.CREATE_GAME.toString();
	String joinGame = IClientAction.ActionType.JOIN_GAME.toString();
	private JScrollPane activeGameScroller;
	
	public DefaultPanel(ActionListener cih, IPlayer player){
		super(player);
		this.cih = cih;
		buildUserPanel();
	}

	private void buildUserPanel(){
	

		this.setSize(new Dimension(600,0));
		this.setLayout(new GridLayout(0,2));
		
		startButton = new JButton(createGame);
		startButton.setActionCommand(createGame);
		startButton.addActionListener(cih);
		
		joinButton = new JButton(joinGame);
		joinButton.setActionCommand(joinGame);
		joinButton.addActionListener(cih);
	
		this.add( buildLeftPanel() );
		this.add( buildMiddlePanel() );
	
}
	private JPanel buildLeftPanel(){
		
		JPanel leftPanel = new JPanel(){
			//painting the left panel with a gradient
			
	
			private static final long serialVersionUID = -2664464346248898687L;

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
		gbc.ipadx = 20;
		gbc.ipady = 93;
		gbc.insets = new Insets(0,0,0,0);
		
		DefaultListModel m = new DefaultListModel();
		for (GameType t: IGameFactory.GameType.values()){
			if (t.isDefault()) 
				continue;
			m.addElement(t);
		}
		newGameList = new JList(m);
			
		newGameList.setBorder(BorderFactory.createTitledBorder("Create A New Game"));
		
		leftPanel.add(newGameList,gbc);
		gbc.gridy = 1;
		gbc.ipadx = 57;
		gbc.ipady = 30;
		gbc.insets = new Insets(10,0,0,0);
		leftPanel.add(startButton,gbc);
		leftPanel.setBorder(BorderFactory.createEtchedBorder());
		
		return leftPanel;
		
	}
	
	private JPanel buildMiddlePanel(){
		
		JPanel middlePanel = new JPanel(){
			

			private static final long serialVersionUID = -5187558315946252680L;

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
		gbc.ipadx = 70;
		gbc.ipady = 0;
		gbc.insets = new Insets(0,0,0,0);
	
		//using a scrollPane for the list of active games
		activeGamesToJoin = new JList(new DefaultListModel());
		activeGameScroller = new JScrollPane(activeGamesToJoin,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		activeGameScroller.setPreferredSize(new Dimension(100,155));
		activeGameScroller.setBorder(BorderFactory.createTitledBorder("Join A Game"));
		middlePanel.add(activeGameScroller,gbc);
		
		
		gbc.gridy = 1;
		gbc.ipadx = 65;
		gbc.ipady = 30;
		gbc.insets = new Insets(10,0,0,0);
		
		middlePanel.add(joinButton,gbc);
		middlePanel.setBorder(BorderFactory.createEtchedBorder());
		
		return middlePanel;
	}
	
	@Override
	public GameType getGameType() {
		gameType = (GameType)newGameList.getSelectedValue();
		if(gameType == null){
			gameType = GameType.DEFAULT;
		}
		return gameType;
	}
	
	@Override
	public String getJoinGameString() {
		return (String)activeGamesToJoin.getSelectedValue();
	};
	


	
	@Override
	public void modelChanged(IGameModel model) {

		Collection<IGameModel> games = model.getActiveGames();
		//Collection<String> activeGames = new ArrayList<String>();
		DefaultListModel m = new DefaultListModel();
		//activeGamesToJoin = new JList(m);

		for (IGameModel game: games){
			//activeGames.add(game.getGameId() + ":" + game.getGameType());
			// TODO; this should go to a method on the model
			// like game.isJoinable();
			if (game.getGameType().isDefault())
				continue;
			if (game.isGameOver())
				continue;
			if (!game.needsPlayers())
				continue;
			
			//if (game.isInProgress()){
			//	continue;
			//}
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

		this.repaint();
		
	}

	@Override
	public int getSelectedButton() {
		return 0;
	}

	@Override
	public String getMessage() {
		return null;
	}
}
