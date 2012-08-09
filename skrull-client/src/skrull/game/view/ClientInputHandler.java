package skrull.game.view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.rmi.RemoteException;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import skrull.SkrullException;
import skrull.game.factory.IGameFactory;
import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.IPlayer;
import skrull.game.model.Move;
import skrull.game.view.IClientAction.ActionType;
import skrull.rmi.client.IServerUpdater;
import skrull.rmi.client.ServerUpdater;
import skrull.util.logging.SkrullLogger;

public class ClientInputHandler implements WindowListener, ActionListener{
	private IPlayer player;
	private int gameId; // is this necessary?
	public IServerUpdater serverUpdater;
	private IGameClientView view;
	private static final Logger logger = SkrullLogger.getLogger(ClientInputHandler.class);

	public ClientInputHandler(ServerUpdater serverUpdater, IPlayer player ) {
		this.serverUpdater = serverUpdater;
		this.player = player;
		this.gameId =  IGameFactory.DEFAULT_GAME_ID; // starting game id
	}

	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		
		gameId = view.getGameId();
		
		ActionType type = ActionType.valueOf(actionEvent.getActionCommand());
		
		try {
			switch(type){
			
			case CHAT:
				
				serverUpdater.processClientAction(new ClientAction(gameId, player, type, view.getGameType(), view.getChatText(), null));
				break;
				
			case CREATE_GAME:
				//need to handle when no selection was made in the list and the user
				//chooses to start a new game
				
					
				// TODO: a builder or factory seems to be in order for the ClientActions
				GameType gameType = view.getGameType();
				serverUpdater.processClientAction(new ClientAction(gameId, player, type, gameType, view.getChatText(), null));	
				serverUpdater.processClientAction(new ClientAction(0, player, ActionType.QUIT, GameType.DEFAULT, view.getChatText(), null));			
				

				break;
				
			case JOIN_GAME:
				//need to handle when user hits join but nothing is selected in the list

				String selectedGame[] =  view.getJoinGameString().split(":");
				int index = Integer.parseInt(selectedGame[0]);
				GameType toJoinType = GameType.valueOf(selectedGame[1]);
				serverUpdater.processClientAction(new ClientAction(index, player, type, toJoinType, view.getChatText(), null));			
				serverUpdater.processClientAction(new ClientAction(0, player, ActionType.QUIT, GameType.DEFAULT, view.getChatText(), null));			

				break;
				
			case MOVE:
	
					
					Move viewMove = new Move();				
					actionEvent.getActionCommand();	//will set actionEvent To MOVE
					
					JButton buttonPressed = (JButton)actionEvent.getSource();
					int buttonIndex = Integer.parseInt(buttonPressed.getText());
					
					viewMove.setMoveIndex(buttonIndex);
					viewMove.setPlayer(player);
					
					serverUpdater.processClientAction(new ClientAction(gameId, player, type, view.getGameType(), view.getChatText(), viewMove));
				break;
			
			case QUIT:
				
			
				int confirmResult = JOptionPane.showConfirmDialog((Component)view, "Return to main screen?", "Quit?", 1);
				System.out.println(confirmResult);
				
				if (JOptionPane.OK_OPTION == confirmResult){
				
					try {
						serverUpdater.processClientAction(new ClientAction(gameId, player, ActionType.QUIT, view.getGameType(), view.getChatText(), null));
						serverUpdater.processClientAction(getStartupAction());	

						// System.exit(0);
					} catch (RemoteException e1) {
						
						logger.fatal("cant' contact server", e1);
						view.setBroadcastMessage(e1.getMessage());
					} catch (SkrullException e1) {
						handleSkrullException(e1);
					}
				}
				
				break;
				
			default:
				throw new UnsupportedOperationException(actionEvent + actionEvent.getActionCommand());
			}
		
		} catch (SkrullException ex) {
			handleSkrullException(ex);

		} catch (RemoteException ex){
			view.setBroadcastMessage( ex.getMessage() );
			logger.fatal("Can't contact server", ex);
		}
	}

	private void handleSkrullException(SkrullException ex) {
		view.setBroadcastMessage( ex.getClientMessage() );
		logger.error("excepition received", ex);		
	}

	public IClientAction getStartupAction() {
		return new ClientAction(IGameFactory.DEFAULT_GAME_ID, player, ActionType.JOIN_SERVER, GameType.DEFAULT, null, null);
	}

	public void setView(IGameClientView view) {
		this.view = view;
	}


	/// windowListener methods
	
	@Override
	public void windowOpened(WindowEvent e) {
		
	}
	
	@Override
	public void windowIconified(WindowEvent e) {
		
	}
	
	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}
	
	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		logger.debug("closing event received");
		int confirmResult = JOptionPane.showConfirmDialog((Component)view, "Are you sure?", "Leaving so soon?", 1);
		System.out.println(confirmResult);
		
		if (JOptionPane.OK_OPTION == confirmResult){
		
			try {
				serverUpdater.processClientAction(new ClientAction(gameId, player, ActionType.QUIT, view.getGameType(), view.getChatText(), null));
				System.exit(0);

			} catch (RemoteException e1) {
				
				logger.fatal("cant' contact server", e1);
				e1.printStackTrace();
			} catch (SkrullException e1) {
				handleSkrullException(e1);
			} finally {
				// if there was an error quitting the game (like if the server wasn't there)
				// then still exit but with error status
				System.exit(1);
			}
		}
	}
	
	@Override
	public void windowClosed(WindowEvent e) {
		
	}

	
	@Override
	public void windowActivated(WindowEvent e) {
		
	}


}
