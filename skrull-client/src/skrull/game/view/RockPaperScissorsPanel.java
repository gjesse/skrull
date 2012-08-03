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

import org.apache.log4j.Logger;

import skrull.game.factory.IGameFactory.GameType;
import skrull.util.logging.SkrullLogger;

public class RockPaperScissorsPanel extends UserPanel {
	
	private static final Logger logger = SkrullLogger.getLogger(GameClientView.class);
	private static final String IMAGE_DIR = System.getProperty("image.dir");
	ClientInputHandler cih;
	public RockPaperScissorsPanel(ClientInputHandler cih){
		
	}
	
private void sampleRockPaperScissorBoard(){
		
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
		
		JButton paperButton = new JButton(new ImageIcon(buttonIcon));
		JButton rockButton = new JButton(new ImageIcon(buttonIcon2));
		JButton scissorButton = new JButton(new ImageIcon(buttonIcon3));

		paperButton.setBackground(Color.WHITE);
		rockButton.setBackground(Color.WHITE);
		scissorButton.setBackground(Color.WHITE);
		
	
		this.setBackground(Color.white);
		c.insets = new Insets(25,0,15,15);
		c.gridx = 0;
		this.add(rockButton,c);
		c.gridx = 1;
		this.add(paperButton,c);
		c.gridx = 2;
		this.add(scissorButton,c);
	}

class RockPaperScissorHandler implements ActionListener{
	
	@Override
	public void actionPerformed(ActionEvent buttonEvent) {
		// TODO Auto-generated method stub
	
	}

}

@Override
public GameType getGameType() {
	// TODO Auto-generated method stub
	return GameType.ROCK_PAPER_SCISSORS;
}
}
