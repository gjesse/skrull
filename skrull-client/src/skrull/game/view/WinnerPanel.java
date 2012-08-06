package skrull.game.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.IGameModel;

public class WinnerPanel extends UserPanel {

	ClientInputHandler cih;
	public WinnerPanel(ClientInputHandler cih){
		
		this.cih = cih;
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

}
