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
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import skrull.game.factory.IGameFactory;
import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.IGameModel;
import skrull.game.view.GameClientView.Handler;


public class TicTacToePanel extends UserPanel {
	ClientInputHandler cih;
	private JButton[] ticTacToeButtons = new JButton[9];
	String chooseMe = "Choose Me";
	public TicTacToePanel(ClientInputHandler cih){
		
		this.cih = cih;
		sampleTicTacToeBoard();
	}
	private void sampleTicTacToeBoard(){

//TODO HARD CODE THE SIZE OF THE BUTTONS
		this.setPreferredSize(new Dimension(600,0));
		//gameBoardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));	
		this.setBackground(Color.WHITE);
		
		TicTacToeHandler gameHandler = new TicTacToeHandler();
		
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
			
			
			JButton buttonPressed = (JButton)buttonEvent.getSource();
			
			//buttonPressed.setText(model...........getToken);
			buttonPressed.setEnabled(false);		
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
		// TODO update the board with tokens
		//loop through the board and update what is written on buttons
		
	}
}
