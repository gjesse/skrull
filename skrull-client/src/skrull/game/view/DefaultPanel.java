package skrull.game.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import skrull.game.factory.IGameFactory;
import skrull.game.factory.IGameFactory.GameType;

public class DefaultPanel extends UserPanel {
	
	GameType gameType;
	JButton startButton;
	JButton joinButton;
	JList newGameList;
	JList activeGamesToJoin;
	ClientInputHandler cih;
	String createGame = IClientAction.ActionType.CREATE_GAME.toString();
	String joinGame = IClientAction.ActionType.JOIN_GAME.toString();
	
	public DefaultPanel(ClientInputHandler cih){
		this.cih = cih;
		buildUserPanel();
	}
	
	private void buildUserPanel(){
	
	Handler handler = new Handler();

	JPanel left = new JPanel();
		
	JPanel middle = new JPanel();
	
	left.setSize(new Dimension(300,0));
	middle.setSize(new Dimension(300,0));
	this.setSize(new Dimension(600,0));
	this.setLayout(new GridLayout(0,2));
	
	startButton = new JButton(createGame);
	startButton.addActionListener(handler);
	
	joinButton = new JButton(joinGame);
	joinButton.addActionListener(handler);
	
	//*******************LEFT PROPERTIES************************//*
	
	left.setLayout(new GridBagLayout());
	GridBagConstraints gbc = new GridBagConstraints();
	gbc.gridx =0;
	gbc.gridy = 0;
	gbc.ipadx = 30;
	gbc.ipady = 75;
	gbc.insets = new Insets(0,10,10,10);
	
	
	newGameList = new JList(IGameFactory.GameType.values());
		
	newGameList.setBorder(BorderFactory.createTitledBorder("Create A New Game"));
	
	left.add(newGameList,gbc);
	gbc.gridy = 1;
	gbc.ipadx = 10;
	gbc.ipady = 30;
	gbc.insets = new Insets(3,3,3,3);
	left.add(startButton,gbc);
	left.setBorder(BorderFactory.createEtchedBorder());
	
	//*******************MIDDLE PROPERTIES************************//*
	
	//middle.setLayout(new GridLayout(2,0));
	middle.setLayout(new GridBagLayout());
	//GridBagConstraints gbc = new GridBagConstraints();
	gbc.gridx =0;
	gbc.gridy = 0;
	gbc.ipadx = 75;
	gbc.ipady = -17;
	gbc.insets = new Insets(0,0,10,0);
	//activeGamesToJoin = new JList(sampleGamesToJoin);

	JScrollPane activeGameScroller = new JScrollPane(activeGamesToJoin,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	

	activeGameScroller.setBorder(BorderFactory.createTitledBorder("Join A Game"));
	middle.add(activeGameScroller,gbc);
	
	gbc.gridy = 1;
	gbc.ipadx = 10;
	gbc.ipady = 30;
	gbc.insets = new Insets(6,3,3,3);
	
	middle.add(joinButton,gbc);
	middle.setBorder(BorderFactory.createEtchedBorder());
	
	this.add(left);
	this.add(middle);
	
}
	@Override
	public GameType getGameType() {
		return gameType;
	}
	public class Handler implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			// TODO Auto-generated method stub
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
}
