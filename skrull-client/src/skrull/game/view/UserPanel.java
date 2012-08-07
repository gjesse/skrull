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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import skrull.game.factory.IGameFactory;
import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.IGameModel;
import skrull.game.view.GameClientView.Handler;

public abstract class UserPanel extends JPanel {
	GameType gameType;
	public UserPanel(){
		
	}
	protected void paintComponent(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		
	    if ( !isOpaque() )
	    {
	        super.paintComponent(g);
	        return;
	    }
	 
	    int w = getWidth( );
	    int h = getHeight( );
	    Color color1 = getBackground( );
	    Color color2 = color1.darker( );
	    
	    // Paint a gradient from top to bottom
	    GradientPaint gp = new GradientPaint(
	        0, 0, color1,
	        0, h, color2 );

	    g2d.setPaint( gp );
	    g2d.fillRect( 0, 0, w, h );
	    
	    setOpaque( false );
	    super.paintComponent( g );
	    setOpaque( true );  
	}
	public abstract GameType getGameType();
	public abstract void modelChanged(IGameModel model);
	public String getJoinGameString() {
		return "";
	}
	public int getButtonIndex() {
		// TODO Auto-generated method stub
		return 0;
	}
	public abstract int getSelectedButton();
	
	
}
