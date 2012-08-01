package skrull.game.model;

import java.io.Serializable;

public interface IMove extends Serializable{

	int getMoveIndex();

	int getMoveCount();

	IPlayer getPlayer();

}
