package skrull.game.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.IGameModel;
import skrull.game.model.IPlayer;

public class WinnerPanel extends UserPanel {


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

		JButton winnerButton = new JButton("WINNER is Player ");
		
		winnerButton.setForeground(Color.cyan);
		winnerButton.setBackground(Color.black);
		winnerButton.setBorder(BorderFactory.createEmptyBorder());
		
		this.setLayout( new GridBagLayout() );
		GridBagConstraints winnerConstraints = new GridBagConstraints();
		
		winnerConstraints.ipadx = 400;
		winnerConstraints.ipady = 50;
		winnerConstraints.gridx = 1;
		winnerConstraints.gridy = 1;
		winnerConstraints.fill = GridBagConstraints.BOTH;
		
		
		this.add(winnerButton,winnerConstraints);
		
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
