package skrull.game.view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.IGameModel;
import skrull.game.model.IPlayer;


/**
 * UserPanel is an abstract class that provides functionality 
 * to other panels that will be used in the user view.
 * */
public abstract class UserPanel extends JPanel {

	private static final long serialVersionUID = -4818072651236836682L;
	GameType gameType;
	IPlayer player;
	public UserPanel(IPlayer player){
		this.player = player;
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
	/**
	 * Returns the game type for the specific game/panel
	 * @return
	 */
	public abstract GameType getGameType();
	
	/**
	 * Handle view updates based on the current state of 
	 * the model
	 * @param model
	 */
	public abstract void modelChanged(IGameModel model);

	public String getJoinGameString() {
		return "";
	}

	
	protected IPlayer getPlayer(){
		return player;
	}

	public abstract int getSelectedButton();
	public abstract String getMessage();
	public String getCreateGameString() {
		return "";
	}

}
