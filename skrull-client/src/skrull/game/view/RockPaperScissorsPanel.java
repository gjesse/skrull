package skrull.game.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.IGameModel;
import skrull.game.view.IClientAction.ActionType;
import skrull.util.logging.SkrullLogger;

public class RockPaperScissorsPanel extends UserPanel {
	
	private static final Logger logger = SkrullLogger.getLogger(GameClientView.class);
	private static final String IMAGE_DIR = System.getProperty("image.dir");
	ClientInputHandler cih;
	JButton rockButton;
	JButton paperButton;
	JButton scissorButton;
	
	public RockPaperScissorsPanel(ClientInputHandler cih){
		
		this.cih = cih;
		sampleRockPaperScissorBoard();
	}
	
	private void sampleRockPaperScissorBoard(){
		
	
		RockPaperScissorHandler rpsHandler = new RockPaperScissorHandler();
		
		this.setLayout(new GridBagLayout());
		
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
		rockButton.setForeground(getBackground());
		paperButton.setForeground(getBackground());
		scissorButton.setForeground(getBackground());

		rockButton.addActionListener(rpsHandler);
		paperButton.addActionListener(rpsHandler);
		scissorButton.addActionListener(rpsHandler);
		
		
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

	class RockPaperScissorHandler implements ActionListener{
		
		@Override
		public void actionPerformed(ActionEvent buttonEvent) {


			JButton buttonPressed = (JButton)buttonEvent.getSource();
			
			buttonPressed.setEnabled(false);
			cih.handleInput(buttonEvent);
		}
	
	}

	@Override
	public GameType getGameType() {
		// TODO Auto-generated method stub
		//return GameType.ROCK_PAPER_SCISSORS;
		return gameType = GameType.ROCK_PAPER_SCISSORS;
	}
	
	@Override
	public void modelChanged(IGameModel model) {
		

		SwingUtilities.invokeLater(new Runnable() {
		    public void run() {
		    	rockButton.revalidate(); // triggers a repaint
		    	paperButton.revalidate(); 
		    	scissorButton.revalidate(); 

		    }
		  });

		this.repaint();
	}
}
