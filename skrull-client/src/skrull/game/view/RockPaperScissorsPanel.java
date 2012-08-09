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
import skrull.game.model.IMove;

import skrull.game.model.IPlayer;

import skrull.game.model.Move;

import skrull.util.logging.SkrullLogger;

/**
 * RockPaperScissorsPanel builds the
 * game panel for rock, paper, scissors
 * and lays out the components that will be 
 * used if the user selects to either
 * start or join a RockPaperScissors game.
 * */
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

	String activePlayer;
	private JButton[] rpsButtons = new JButton[3];
	private BufferedImage[] buttonIcons = new BufferedImage[3];
	
	public RockPaperScissorsPanel(ActionListener cih, IPlayer player){
		super(player);
		this.cih = cih;
		sampleRockPaperScissorBoard();
	}
	
	private void sampleRockPaperScissorBoard(){
		
	
		this.setLayout(new GridBagLayout());
		

		ReturnToMainButton returnToMain = new ReturnToMainButton(cih);
		
		GridBagConstraints c = new GridBagConstraints();
		
		
/*		BufferedImage buttonIcon = null;
		BufferedImage buttonIcon2 = null;
		BufferedImage buttonIcon3 = null;*/

		
		try {
			buttonIcons[0] = ImageIO.read(new File(IMAGE_DIR + "Rockscaled2.jpg"));
			buttonIcons[1] = ImageIO.read(new File(IMAGE_DIR + "Paperscaled.jpg"));
			buttonIcons[2] = ImageIO.read(new File(IMAGE_DIR + "Scissorsscaled.jpg"));
			
/*			buttonIcon = ImageIO.read(new File(IMAGE_DIR + "Paperscaled.jpg"));
			buttonIcon2 = ImageIO.read(new File(IMAGE_DIR + "Rockscaled2.jpg"));
			buttonIcon3 = ImageIO.read(new File(IMAGE_DIR + "Scissorsscaled.jpg"));*/
			
		} catch (IOException e) {
			logger.error("error reading from " + IMAGE_DIR, e);
		}
		
		this.setBackground(Color.white);
		c.insets = new Insets(25,0,15,5);
		c.gridx = 0;
		c.gridy =0;
		for(int i = 0; i < 3; i++){
			//should assign into button 1 icon of rock,
			//button 2 with icon of paper, and button 3
			//with icon of scissors
			rpsButtons[i]= new JButton(new ImageIcon(buttonIcons[i]));
			rpsButtons[i].setActionCommand("MOVE");
			rpsButtons[i].addActionListener(cih);
			rpsButtons[i].setText(i+"");
			this.add(rpsButtons[i],c);
			c.gridy++;	
		}
		
/*		paperButton = new JButton(new ImageIcon(buttonIcon));
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
		*/
		

	/*	c.gridx = 0;
		c.gridy = 0;
		
		this.add(rockButton,c);
		c.gridx = 0;
		c.gridy = 1;
		this.add(paperButton,c);
		c.gridx = 0;
		c.gridy = 2;

		this.add(scissorButton,c);
*/
		c.gridx = 0;
		c.gridy = 3;
		this.add(returnToMain, c);

	}


	@Override
	public GameType getGameType() {

		return gameType = GameType.ROCK_PAPER_SCISSORS;
	}
	
	@Override
	public void modelChanged(IGameModel model) {
		//TODO: this needs work
		System.out.println("INSIDE THE modelChanged OF ROCK PAPER SCISSORS!");
		System.out.println("move count "+model.getMoveCount());
		System.out.println( "number of players "+model.getPlayers().size() );
		
		
		System.out.println("token with active player is "+ model.getActivePlayer().getPlayerToken() );
		
		if(model.getMoveCount() == 0){
			//we are in a new game where nobody has clicked
			System.out.println( "number of players "+model.getPlayers().size() );
			System.out.println("active player is "+ model.getActivePlayer() );
			
		}
	
		//if both players have input their moves
		//we want to update the board with their moves
		System.out.println("about to set TOKENS");
		for(IPlayer player: model.getPlayers() ){
			this.player.setPlayerToken(player.getPlayerToken());
			System.out.println("player token: "+ player.getPlayerToken() );
		}
/*				for( IPlayer player: model.getPlayers() ){
					if(player.equals(this.player)){
						this.player.setPlayerToken(player.getPlayerToken() );
					}
					System.out.println("token!!! "+player.getPlayerToken()+" for player:"+player);
					System.out.println("our player"+this.player.getPlayerToken()+" for player:"+player);
				}
				
				for( IMove buttonMove : model.getBoard().getBoard() ){
					if (buttonMove != null){
						//ticTacToeButtons[buttonMove.getMoveIndex()].setText( buttonMove.getPlayer().getPlayerToken() );
						//ticTacToeButtons[buttonMove.getMoveIndex()].setEnabled(false);		
					}
				}*/		
		
	
		
		
/*		if( !model.isGameOver() ){
			System.out.println("game isnt over so..");
			if(model.getMoveCount() != 0){
				System.out.println("game has more than 0 moves "+ model.getMoveCount() );
				
				ClientAction ca = (ClientAction)((AbstractGameModel)model).getLastAction();
				Move move = (Move)ca.getMove();
				
				activePlayer = "Active Player:"+model.getActivePlayer();
				this.setMessage(activePlayer);
				
				//index of the button that changed
				int idx = move.getMoveIndex();
				this.getComponent(idx);
				selected = (JButton)this.getComponent(idx);
				selected.setEnabled(false);
				
				
				String mySelection =  model.getActivePlayer().getPlayerToken();
				
				System.out.println("Player Token: "+mySelection);
				selected.setText(mySelection );
			
	
				this.repaint();
			}

		}*/
	
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
