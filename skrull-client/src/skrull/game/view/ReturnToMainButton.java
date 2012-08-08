package skrull.game.view;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import skrull.game.view.IClientAction.ActionType;

public class ReturnToMainButton extends JButton {

	private static final long serialVersionUID = -8646692752305847592L;
	
	ReturnToMainButton(ActionListener listener){
		super("Return to main / quit");
		this.addActionListener(listener);
		this.setActionCommand(ActionType.QUIT.toString());
	}

}
