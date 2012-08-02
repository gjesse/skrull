package skrull.game.view;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.UUID;

import org.apache.log4j.Logger;

import skrull.game.factory.IGameFactory;
import skrull.game.factory.IGameFactory.GameType;
import skrull.game.model.IPlayer;
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

	public ClientInputHandler(ServerUpdater serverUpdater, UUID playerId ) {
		this.serverUpdater = serverUpdater;
		this.player = new Player(playerId);
		this.gameId =  IGameFactory.DEFAULT_GAME_ID; // starting game id
	}

	public void handleInput(ActionEvent actionEvent) {
		gameId = view.getGameId();
		ActionType type = ActionType.valueOf(actionEvent.getActionCommand());
		
		try {
			switch(type){
			
			case CHAT:
				// TODO: a builder or factory seems to be in order for the ClientActions
					serverUpdater.ProcessClientAction(new ClientAction(gameId, player, type, view.getGameType(), view.getChatText(), null));
			
				break;
				
			case CREATE_GAME:
				// TODO: a builder or factory seems to be in order for the ClientActions
				GameType gameType = view.getGameType();
				serverUpdater.ProcessClientAction(new ClientAction(gameId, player, type, gameType, view.getChatText(), null));			
				serverUpdater.ProcessClientAction(new ClientAction(gameId, player, ActionType.QUIT, GameType.DEFAULT, view.getChatText(), null));			

				break;
				
			case JOIN_GAME:
				// TODO: a builder or factory seems to be in order for the ClientActions
				serverUpdater.ProcessClientAction(new ClientAction(gameId, player, type, view.getGameType(), view.getChatText(), null));			
				break;
				
			case MOVE:
				// TODO: a builder or factory seems to be in order for the ClientActions
				serverUpdater.ProcessClientAction(new ClientAction(gameId, player, type, view.getGameType(), view.getChatText(), null));			
				break;
			
			case QUIT:
				// TODO: a builder or factory seems to be in order for the ClientActions
				serverUpdater.ProcessClientAction(new ClientAction(gameId, player, type, view.getGameType(), view.getChatText(), null));			
				break;
				
			default:
				throw new UnsupportedOperationException(actionEvent + actionEvent.getActionCommand());
			}
		
		} catch (SkrullRMIException ex) {
			// TODO: communicate something interesting back to the client
			// if we couldn't contact the server
			logger.fatal("Can't contact server", ex);
		}
	}

	public IClientAction getStartupAction() {
		return new ClientAction(gameId, player, ActionType.JOIN_SERVER, GameType.DEFAULT, null, null);
	}

	public void setView(IGameClientView view) {
		this.view = view;
	}

	public void handleWindowEvent(WindowEvent e) {
		try {
			serverUpdater.ProcessClientAction(new ClientAction(gameId, player, ActionType.QUIT, view.getGameType(), view.getChatText(), null));
		} catch (SkrullRMIException e1) {
			logger.fatal("cant' contact server", e1);
		}			
	}
}
