package skrull.game.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import org.apache.log4j.Logger;

import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.AbstractGameModel;
import skrull.game.model.IGameModel;

import skrull.game.model.IPlayer;

import skrull.game.model.Move;

import skrull.util.logging.SkrullLogger;

public class RockPaperScissorsPanel extends UserPanel {
	

	private static final long serialVersionUID = 7079367552263468840L;
	private static final Logger logger = SkrullLogger.getLogger(GameClientView.class);
	private static final String IMAGE_DIR = System.getProperty("image.dir");
	ActionListener cih;
	JButton rockButton;
	JButton paperButton;
	JButton scissorButton;
	JButton selected;
	int indexOfButton;
	private boolean eventFired = false;
	String activePlayer;
	

	
	public RockPaperScissorsPanel(ActionListener cih, IPlayer player){
		super(player);
		this.cih = cih;
		sampleRockPaperScissorBoard();
	}
	
	private void sampleRockPaperScissorBoard(){
		
	
		this.setLayout(new GridBagLayout());
		
		ReturnToMainButton returnToMain = new ReturnToMainButton(cih);
		this.add(returnToMain);
		
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
		
		paperButton = new JButton(new ImageIcon(buttonIcon));
		rockButton = new JButton(new ImageIcon(buttonIcon2));
		scissorButton = new JButton(new ImageIcon(buttonIcon3));

		paperButton.setBackground(Color.WHITE);
		rockButton.setBackground(Color.WHITE);
		scissorButton.setBackground(Color.WHITE);
		
		paperButton.setActionCommand("MOVE");
		rockButton.setActionCommand("MOVE");
		scissorButton.setActionCommand("MOVE");
		
		rockButton.setText("1");
		paperButton.setText("2");
		scissorButton.setText("3");
		
		rockButton.setBorder(null);
		paperButton.setBorder(null);
		scissorButton.setBorder(null);
		//rockButton.setForeground(getBackground());
		//paperButton.setForeground(getBackground());
		//scissorButton.setForeground(getBackground());

		rockButton.addActionListener(cih);
		paperButton.addActionListener(cih);
		scissorButton.addActionListener(cih);
		
		
		this.setBackground(Color.white);
		c.insets = new Insets(25,0,15,5);
		c.gridx = 0;
		c.gridy = 0;
		this.add(rockButton,c);
		c.gridx = 0;
		c.gridy = 1;
		this.add(paperButton,c);
		c.gridx = 0;
		c.gridy = 2;
		this.add(scissorButton,c);
	}


	@Override
	public GameType getGameType() {

		return gameType = GameType.ROCK_PAPER_SCISSORS;
	}
	
	@Override
	public void modelChanged(IGameModel model) {
		//TODO: this needs work
		if(eventFired == true){
			ClientAction ca = (ClientAction)((AbstractGameModel)model).getLastAction();
			Move move = (Move)ca.getMove();
			
			activePlayer = "Active Player:"+model.getActivePlayer();
			this.setMessage(activePlayer);
			
			//index of the button that changed
			//int idx = move.getMoveIndex();
			int idx = move.getMoveIndex()-1;
			this.getComponent(idx);
			selected = (JButton)this.getComponent(idx);
			selected.setEnabled(false);
			
			
			String mySelection =  model.getActivePlayer().getPlayerToken();
			String charToString = String.valueOf(mySelection);
			System.out.println("char to String: "+charToString+" charToken: "+mySelection);
			selected.setText(charToString );
		

		  this.repaint();
		  eventFired = false;
		}
	
	}
	public void setMessage(String s){
		activePlayer = s;
		
	}
	public String getMessage(){
		return activePlayer;
	}
	public void setSelectedButton(String s){
		indexOfButton = Integer.parseInt(s);
		System.out.println("setSelectedButton "+indexOfButton);
	}

	@Override
	public int getSelectedButton() {
		return indexOfButton;
	}
}
