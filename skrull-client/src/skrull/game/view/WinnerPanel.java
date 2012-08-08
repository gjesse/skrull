package skrull.game.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.IGameModel;
import skrull.game.model.IPlayer;
import skrull.game.model.Move;

public class WinnerPanel extends UserPanel {

	String newline ="\n";
	private static final long serialVersionUID = 6676770154441309212L;
	ActionListener cih;

	public WinnerPanel(ActionListener cih2, IPlayer player){
		super(player);

		this.cih = cih2;
		winnerPanel();
	}
	
	@Override
	public GameType getGameType() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void winnerPanel(){
		
		this.setLayout( new GridBagLayout() );
		GridBagConstraints winnerConstraints = new GridBagConstraints();
		
		JFormattedTextField winnerField = new JFormattedTextField();
		
		winnerConstraints.ipady = 100;
		winnerConstraints.ipadx = 400;
		winnerConstraints.gridx = 1;
		winnerConstraints.gridy = 1;
		winnerConstraints.fill = GridBagConstraints.BOTH;
		
		winnerField.setText("  Winner of the game is..."+newline+player);
		winnerField.setBackground(Color.black);
		winnerField.setForeground(Color.cyan);
		winnerField.setEditable(false);
		this.add(winnerField,winnerConstraints);
/*		JButton winnerButton = new JButton("WINNER is... ");
		JButton lowerButton = new JButton(player+"");
		lowerButton.setForeground(Color.cyan);
		lowerButton.setBackground(Color.black);
		lowerButton.setBorder(BorderFactory.createEmptyBorder());
		winnerButton.setForeground(Color.cyan);
		winnerButton.setBackground(Color.black);
		winnerButton.setBorder(BorderFactory.createEmptyBorder());
		

		
		winnerConstraints.ipadx = 400;
		winnerConstraints.ipady = 50;
		winnerConstraints.gridx = 1;
		winnerConstraints.gridy = 1;
		winnerConstraints.fill = GridBagConstraints.BOTH;
		
		
		this.add(winnerButton,winnerConstraints);
		winnerConstraints.gridx = 1;
		winnerConstraints.gridy = 2;
		this.add(lowerButton,winnerConstraints);*/
		this.setPreferredSize(new Dimension(600,0));
		//winnerPanel.setBackground(Color.white);	
		
	}

	@Override
	public void modelChanged(IGameModel model) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getSelectedButton() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

}
