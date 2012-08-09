package skrull.game.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.IGameModel;
import skrull.game.model.IMove;

import skrull.game.model.IPlayer;


/**
 * TicTacToePanel build the TicTacToe game
 * view that will display when the user 
 * selects to either create or join a 
 * TicTacToe game. modelChanged is 
 * implemented to update the game board
 * whenever each user makes a move
 * */
public class TicTacToePanel extends UserPanel {

	private static final long serialVersionUID = 8769270277983184938L;
	ActionListener cih;
	private JButton[] ticTacToeButtons = new JButton[9];
	String chooseMe = "Choose Me";
	int indexOfButton;
	JButton selected;
	boolean eventFired = false;
	
	public TicTacToePanel(ActionListener cih, IPlayer player){
		super(player);
		this.cih = cih;
		sampleTicTacToeBoard();
	}
	private void sampleTicTacToeBoard(){

		this.setPreferredSize(new Dimension(600,0));
		//gameBoardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));	
		this.setBackground(Color.WHITE);
		

		
//		ReturnToMainButton returnToMain = new ReturnToMainButton(cih);
//		this.add(returnToMain);
//		returnToMain.s
		this.setLayout(new GridBagLayout());
		GridBagConstraints constrainers = new GridBagConstraints();
		this.setBackground(Color.WHITE);
		
		constrainers.fill = GridBagConstraints.BOTH;
		constrainers.ipadx = 25;
		constrainers.ipady = 25;
		constrainers.anchor = GridBagConstraints.CENTER;
		constrainers.gridx = 0;
		constrainers.gridy = 0;
		constrainers.insets = new Insets(75,2,2,2);
		
		for(int i = 0; i < 9; i++){
			ticTacToeButtons[i] = new JButton(i+"");
			ticTacToeButtons[i].setSize(chooseMe.length(), chooseMe.length());
			ticTacToeButtons[i].setActionCommand("MOVE");
			
			ticTacToeButtons[i].addActionListener(cih);
			if(constrainers.gridx == 3){
				constrainers.gridx = 0;
				constrainers.gridy++;
			}
			this.add(ticTacToeButtons[i],constrainers);
			constrainers.gridx++;	 
		}
		
		constrainers.gridx = 0;
		constrainers.gridy = 4;
		constrainers.gridwidth = 3;
		ReturnToMainButton returnToMain = new ReturnToMainButton(cih);
		this.add(returnToMain,constrainers);
		
	}

	@Override
	public GameType getGameType() {

		return gameType = GameType.TIC_TAC_TOE;
	}
	@Override
	public void modelChanged(IGameModel model) {
		
		System.out.println("about to check if GAME OVER!");
		if( model.isGameOver() ){
			System.out.println("GAME OVER!");
			IPlayer winner = model.getWinner();
			if(winner != null && winner.equals(this.player) ){
				System.out.println("Player "+" is the winner!");
			}
		}
		else 
			System.out.println("the GAME WASNT OVER!");
		
		for( IPlayer player: model.getPlayers() ){
			if(player.equals(this.player)){
				this.player.setPlayerToken(player.getPlayerToken() );
			}
			System.out.println("token!!! "+player.getPlayerToken()+" for player:"+player);
			System.out.println("our player"+this.player.getPlayerToken()+" for player:"+player);
		}
		for( IMove buttonMove : model.getBoard().getBoardMoves() ){
			if (buttonMove != null){
				ticTacToeButtons[buttonMove.getMoveIndex()].setText( buttonMove.getPlayer().getPlayerToken() );
				ticTacToeButtons[buttonMove.getMoveIndex()].setEnabled(false);
				
			}
		}
		
		
	}
	public void setSelectedButton(String s){
		indexOfButton = Integer.parseInt(s);
		System.out.println("setSelectedButton "+indexOfButton);
	}

	@Override
	public int getSelectedButton() {
		return indexOfButton;
	}
	@Override
	public String getMessage() {
		return null;
	}
}
