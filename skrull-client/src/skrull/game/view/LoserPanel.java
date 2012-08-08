package skrull.game.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.IGameModel;
import skrull.game.model.IPlayer;

public class LoserPanel extends UserPanel {


	private static final long serialVersionUID = -6731913040598693722L;

	public LoserPanel(ClientInputHandler cih, IPlayer player){
		super(player);
		buildLoserPanel();
	}
	
	private void buildLoserPanel(){
		
		JButton loserButton = new JButton("OH NO! YOU LOST! KEEP PRACTICING!");
		
		loserButton.setForeground(Color.cyan);
		loserButton.setBackground(Color.black);
		loserButton.setBorder(BorderFactory.createEmptyBorder());
		
		this.setLayout( new GridBagLayout() );
		GridBagConstraints loserConstraints = new GridBagConstraints();
		
		loserConstraints.ipadx = 400;
		loserConstraints.ipady = 50;
		loserConstraints.gridx = 1;
		loserConstraints.gridy = 1;
		loserConstraints.fill = GridBagConstraints.BOTH;
		
		
		this.add(loserButton,loserConstraints);
		
		this.setPreferredSize(new Dimension(600,0));
		this.setBackground(Color.black);
		
	}
	
	
	@Override
	public GameType getGameType() {
		// TODO Auto-generated method stub
		return null;
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
