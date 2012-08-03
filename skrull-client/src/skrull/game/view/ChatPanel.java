package skrull.game.view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import skrull.game.factory.IGameFactory;
import skrull.game.view.GameClientView.Handler;

public class ChatPanel extends JPanel {
	
	JButton sendButton;
	private JTextField chatTextInputField;
	JTextArea chatWindow;
	ClientInputHandler thisClientIH;
	public ChatPanel(ClientInputHandler cih){	
		thisClientIH = cih;
		buildChatClient();

		
		
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
	private JPanel buildChatClient(){

		Handler handler = new Handler();
		
		chatTextInputField = new JTextField();
		chatTextInputField.setText("Enter Message...");
		chatTextInputField.setForeground(Color.gray);
		chatTextInputField.addActionListener(handler);
		
		chatWindow = new JTextArea(5,20);
        chatWindow.setText("Welcome to Skrull Gaming...");
		chatWindow.setEditable(false);
		
		sendButton = new JButton("send chat");

		this.setBorder(BorderFactory.createEmptyBorder());
		this.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		

        sendButton.setActionCommand(IClientAction.ActionType.CHAT.toString());
        sendButton.addActionListener(handler);
        
        JScrollPane scroll = new JScrollPane(chatWindow, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        
        constraints.insets = new Insets(160,0,0,0);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.ipady = 100;
        this.add(scroll, constraints);
        constraints.insets = new Insets(0,0,0,0);
        constraints.ipady = 10;
        constraints.gridx = 1;
        constraints.gridy = 1;
        chatTextInputField.setBorder(BorderFactory.createLoweredBevelBorder());
        
        this.add(chatTextInputField,constraints);
        
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.ipady = 0;
        this.add(sendButton, constraints);
        
		return this;
	}
	public String getChatText(){
		return chatTextInputField.getText();
	}
	
	class Handler implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent event) {
			// TODO Auto-generated method stub
			thisClientIH.handleInput(event);
		}
		
	}

	public void setText(String chatContents) {
		// TODO Auto-generated method stub
		chatWindow.setText(chatContents);
		
	}

}
