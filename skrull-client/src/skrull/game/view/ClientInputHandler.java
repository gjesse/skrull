package skrull.game.view;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.util.UUID;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import skrull.SkrullException;
import skrull.game.factory.IGameFactory;
import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.IPlayer;
import skrull.game.model.Move;
import skrull.game.view.IClientAction.ActionType;
import skrull.rmi.SkrullRMIException;
import skrull.rmi.client.IServerUpdater;
import skrull.rmi.client.ServerUpdater;
import skrull.util.logging.SkrullLogger;

public class ClientInputHandler {
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

	public void handleInput(ActionEvent actionEvent) {
		
		gameId = view.getGameId();
		
		ActionType type = ActionType.valueOf(actionEvent.getActionCommand());
		
		try {
			switch(type){
			
			case CHAT:
				// TODO: a builder or factory seems to be in order for the ClientActions
				
					//serverUpdater.ProcessClientAction(new ClientAction(gameId, player, type, view.getGameType(), view.getChatText(), null));
				//neeed to get the game type
			/*	System.out.println("about to send chat to server!");
				System.out.println(gameId);
				System.out.println(player);
				System.out.println(type);
				System.out.println(view.getGameType());
				System.out.println(view.getChatText());*/
				
				serverUpdater.processClientAction(new ClientAction(gameId, player, type, view.getGameType(), view.getChatText(), null));
				break;
				
			case CREATE_GAME:
				// TODO: a builder or factory seems to be in order for the ClientActions
				GameType gameType = view.getGameType();
				serverUpdater.processClientAction(new ClientAction(gameId, player, type, gameType, view.getChatText(), null));	
				serverUpdater.processClientAction(new ClientAction(0, player, ActionType.QUIT, GameType.DEFAULT, view.getChatText(), null));			
				

				break;
				
			case JOIN_GAME:

				String selectedGame[] =  view.getJoinGameString().split(":");
				int index = Integer.parseInt(selectedGame[0]);
				GameType toJoinType = GameType.valueOf(selectedGame[1]);
				serverUpdater.processClientAction(new ClientAction(index, player, type, toJoinType, view.getChatText(), null));			
				serverUpdater.processClientAction(new ClientAction(0, player, ActionType.QUIT, GameType.DEFAULT, view.getChatText(), null));			

				break;
				
			case MOVE:
				// TODO: a builder or factory seems to be in order for the ClientActions
				//

					
					Move viewMove = new Move();				
					actionEvent.getActionCommand();	//will set actionEvent To MOVE
					
					JButton buttonPressed = (JButton)actionEvent.getSource();
					int buttonIndex = Integer.parseInt(buttonPressed.getText());
					
					viewMove.setMoveIndex(buttonIndex);
					viewMove.setPlayer(player);
					
			
					/*System.out.println("TOKEN "+token);
					System.out.println(player.getPlayerToken());*/
					
					//find out who is active player and make their buttons uneditable
					// NOTE - we don't set anything until we get a message back from the model

					
					/*System.out.println("BUTTON INDEX/MOVE INDEX "+viewMove.getMoveIndex());
					System.out.println("PLAYER "+viewMove.getPlayer());
					System.out.println(gameId);
					System.out.println(player);
					System.out.println(type);
					System.out.println(view.getGameType());
					System.out.println(view.getChatText());
					
					System.out.println(viewMove.getMoveIndex());
					System.out.println(viewMove.getPlayer());*/
					
				serverUpdater.processClientAction(new ClientAction(gameId, player, type, view.getGameType(), view.getChatText(), viewMove));
				break;
			
			case QUIT:
				// TODO: a builder or factory seems to be in order for the ClientActions
				serverUpdater.processClientAction(new ClientAction(gameId, player, type, view.getGameType(), view.getChatText(), null));			
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
		return new ClientAction(gameId, player, ActionType.JOIN_SERVER, GameType.DEFAULT, null, null);
	}

	public void setView(IGameClientView view) {
		this.view = view;
	}

	public void handleWindowEvent(WindowEvent e) {
		gameId = view.getGameId();

		try {
			serverUpdater.processClientAction(new ClientAction(gameId, player, ActionType.QUIT, view.getGameType(), view.getChatText(), null));
			System.exit(0);
		} catch (RemoteException e1) {
			
			logger.fatal("cant' contact server", e1);
			e1.printStackTrace();
		} catch (SkrullException e1) {
			handleSkrullException(e1);
		}			
	}
}
