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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import skrull.game.factory.IGameFactory;
import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.AbstractGameModel;
import skrull.game.model.IGameModel;
import skrull.game.model.IMove;

import skrull.game.model.IPlayer;

import skrull.game.model.Move;

import skrull.game.view.GameClientView.Handler;


public class TicTacToePanel extends UserPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8769270277983184938L;
	ClientInputHandler cih;
	private JButton[] ticTacToeButtons = new JButton[9];
	String chooseMe = "Choose Me";
	int indexOfButton;
	JButton selected;
	boolean eventFired = false;
	
	public TicTacToePanel(ClientInputHandler cih, IPlayer player){
		super(player);
		this.cih = cih;
		sampleTicTacToeBoard();
	}
	private void sampleTicTacToeBoard(){

//TODO HARD CODE THE SIZE OF THE BUTTONS
		this.setPreferredSize(new Dimension(600,0));
		//gameBoardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));	
		this.setBackground(Color.WHITE);
		

		TicTacToeHandler gameHandler = new TicTacToeHandler();
		ReturnToMainButton returnToMain = new ReturnToMainButton(gameHandler);
		this.add(returnToMain);
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
			
			ticTacToeButtons[i].addActionListener(gameHandler);
			if(constrainers.gridx == 3){
				constrainers.gridx = 0;
				constrainers.gridy++;
			}
			this.add(ticTacToeButtons[i],constrainers);
			constrainers.gridx++;	 
		}
		
		
	}
	class TicTacToeHandler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent buttonEvent) {
			// TODO need to determine turn and change the button text to 
			

		//	eventFired = true;
		//	JButton buttonSelected = (JButton)buttonEvent.getSource();
		//	setSelectedButton(buttonSelected.getText());
			
			cih.handleInput(buttonEvent);
		}
		
	}
	@Override
	public GameType getGameType() {
		// TODO Auto-generated method stub
		//return GameType.TIC_TAC_TOE;
		return gameType = GameType.TIC_TAC_TOE;
	}
	@Override
	public void modelChanged(IGameModel model) {
		
		System.out.println("about to check if GAME OVER!");
		if( model.isGameOver() ){
			System.out.println("GAME OVER!");
			if( model.getWinner().equals(this.player) ){
				JOptionPane.showConfirmDialog(null, "Player "+model.getWinner()+" is the winner!");
			}
		}
		System.out.println("the GAME WASNT OVER!");
		
		for( IPlayer player: model.getPlayers() ){
			if(player.equals(this.player)){
				this.player.setPlayerToken(player.getPlayerToken() );
			}
			System.out.println("token!!! "+player.getPlayerToken()+" for player:"+player);
			System.out.println("our player"+this.player.getPlayerToken()+" for player:"+player);
		}
		for( IMove buttonMove : model.getBoard().getBoard() ){
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
		// TODO Auto-generated method stub
		return indexOfButton;
	}
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}
}
