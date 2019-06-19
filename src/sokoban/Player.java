package sokoban;

public class Player extends GameTile {

	public Player(int x, int y, boolean playerOne) {
		super(( playerOne ? TilesEnum.PLAYERONE : TilesEnum.PLAYERTWO ), x, y);
	}
	
}
