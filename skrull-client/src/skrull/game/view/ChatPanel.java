package skrull.game.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import skrull.game.factory.IGameFactory.GameType;


/**
 * ChatPanel builds the chat panel for the 
 * user to interact with and is part of 
 * every view that the user will see except
 * once a game has finished with a winner.
 * Users can chat in a general lobby within
 * the default view. Once the users
 * start or join a game the chat is
 * restricted to the users within that game.
 * */
public class ChatPanel extends JPanel {

	private static final long serialVersionUID = -7900339949223125405L;
	JButton sendButton;
	JTextArea messageCenter;
	private JTextField chatTextInputField;
	String sendChat = IClientAction.ActionType.CHAT.toString();
	JTextArea chatWindow;
	ActionListener cih;
	private final static String newline = "\n";
	JScrollPane messageScrollPane;
	public ChatPanel(ActionListener cih2){	
		this.cih = cih2;
		buildChatClient();
		
	}
	private JPanel buildChatClient(){
		
		//creating text field for user to enter chat text
		chatTextInputField = new JTextField();
		chatTextInputField.setText("Enter Message...");
		chatTextInputField.setForeground(Color.gray);
		chatTextInputField.addActionListener(cih);
		
		//creating chat window in which the users messages will be displayed
		chatWindow = new JTextArea(5,20);
        chatWindow.setText("Welcome to Skrull Gaming...");
		chatWindow.setEditable(false);
		
		//button that will initiate the sending of messages
		sendButton = new JButton(sendChat);
        sendButton.setActionCommand(IClientAction.ActionType.CHAT.toString());
        
        // TODO REMOVE ME   String sendChat = IClientAction.ActionType.CHAT.toString();
        
        sendButton.addActionListener(cih);
		
		//changing the border of the panel
		this.setBorder(BorderFactory.createEmptyBorder());
		
		//setting up layout for panel
		this.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		
		//adding the chatWindow to a scroll pane so that user messages can be scrollable
        JScrollPane scroll = new JScrollPane(chatWindow, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        
        //building the message center
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.insets = new Insets(20,0,0,0);
        this.add( buildMessageCenter(),constraints );
        
        constraints.insets = new Insets(50,0,0,0);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.ipady = 100;
        this.add(scroll, constraints);
        
        //adding text field
        constraints.insets = new Insets(0,0,0,0);
        constraints.ipady = 10;
        constraints.gridx = 1;
        constraints.gridy = 2;
        chatTextInputField.setBorder(BorderFactory.createLoweredBevelBorder());
        this.add(chatTextInputField,constraints);
        
        //adding the button
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.ipady = 0;
        this.add(sendButton, constraints);
        
		return this;
	}
	public JScrollPane buildMessageCenter(){
		
		messageCenter = new JTextArea(5,30);
		messageCenter.setText("Message Center"+newline);
		messageCenter.setEditable(false);
		messageCenter.setForeground(Color.gray);
		messageScrollPane = new JScrollPane( messageCenter,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
		
		return messageScrollPane;
		
	}
	public void addMessage(String message){
		
		messageCenter.setText(message + newline);
		
	}
	public String getChatText(){
		//JOptionPane.showMessageDialog(null,"INSIDE CHAT PANEL GETCHATTEXT()--about to get text from ChatTextInputField");
		return chatTextInputField.getText();
	}
	

	public void setText(String chatContents) {
		// TODO Auto-generated method stub
		chatWindow.setText(chatContents);
		
	}
	public GameType getGameType() {
		return GameType.DEFAULT;
	}

}
