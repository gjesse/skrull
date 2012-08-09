package skrull.game.model;

import java.io.Serializable;
import java.util.Collection;

import skrull.SkrullGameException;
import skrull.game.factory.IGameFactory.GameType;
import skrull.game.view.IClientAction;
import skrull.game.view.IClientAction.ActionType;

/**
 *   IGameModel 
 *
 *   The Interface for the game model.
 *   Blocks out the constructors, getters, and setters for the abstract model
 *   and specific game models.
 */
public interface IGameModel extends Serializable {

	/**
	 * Process an {@link IClientAction.ActionType} of type CHAT
	 * 
	 * @param aAction
	 */
	public abstract void chatUpdate(IClientAction aAction);

	/**
	 * Process an {@link IClientAction.ActionType} of type JOIN_GAME
	 * 
	 * Validation must be done by the implementation before
	 * adding the new player to the game
	 * 
<<<<<<< HEAD
	 * Failure to add the player should throw {@link SkrullGameException}
	 * 
=======
>>>>>>> refs/remotes/origin/khmasterlast102pm
	 * @param aAction
	 * @throws SkrullGameException 
	 */
	public abstract void joinGame(IClientAction aAction) throws SkrullGameException;

	/**
	 * Process an {@link IClientAction.ActionType} of type MOVE
	 * @param aAction
	 * @throws SkrullGameException
	 */
	public abstract void processMove(IClientAction aAction) throws SkrullGameException;

	/**
	 * indicate if the game is over
	 * @return
	 */
	public abstract boolean isGameOver();

	/**
	 * Returns the winning {@link IPlayer} if there is one, or null
	 * @return IPlayer
	 */
	public abstract IPlayer getWinner();
	
	/**
	 * Return the active {@link IPlayer}
	 * @return IPlayer
	 */
	public abstract IPlayer getActivePlayer();

	/**
	 * Perform an activity check for the players in this game
	 * 
	 * 	2 types of checks should be performed here: 
	 * 		1. make sure the active player hasn't exceeded some reasonable threshold (ie walked away from the computer)
	 *  	2. make sure we still have network connectivity to the client
	 */
	public abstract void checkActivity();

	/**
	 * 
	 * @return the current {@link GameType}
	 */
	public abstract GameType getGameType();

	/**
	 * @return the current game id
	 */
	public abstract int getGameId();

	/**
	 * Process an {@link IClientAction.ActionType} of type QUIT
	 * 
	 * @param action
	 */
	public abstract void quit(IClientAction action);

	/**
	 * @return all {@link IPlayer}s in the game
	 */
	public abstract Collection<IPlayer> getPlayers();
	
	/**
	 * @return The contents of the chat buffer
	 */
	public abstract String getChatContents();

	/**
	 * Provide a general status message to all clients that can be displayed
	 * @return the message
	 */
	public abstract String getBroadcastMessage();

	/**
	 * Each game maintains a reference to all active games. This method allows that reference to 
	 * be updated
	 * @param activeGames
	 */
	public abstract void setActiveGames(Collection<IGameModel> activeGames);
	
	/**
	 * Each game maintains a reference to all active games. This method gets that reference.
	 * @param activeGames
	 */
	public abstract Collection<IGameModel> getActiveGames();

	/**
	 * 
	 * @return the current count of moves
	 */
	public int getMoveCount();


	/**
	 * 
	 * @return the max number of moves that can be made for this game
	 */
	public int getMaxMoves();


	/**
	 * Process an {@link IClientAction.ActionType} of type CREATE_GAME
	 * 
	 * Note that actual game creation is taken care of upstream - this will 
	 * be called after the game is created. This method is needed to provide a way of 
	 * passing the value of the model back to the views.
	 * 
	 * @param action
	 */
	public abstract void createGame(IClientAction action);
	
	/**
	 * @return whether or not the game still needs players to begin
	 */
	public abstract boolean needsPlayers();

	/**
	 * 
	 * @return IBoard the active board object
	 */
	public abstract IBoard getBoard();
	
	
}
