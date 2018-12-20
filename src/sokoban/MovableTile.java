package sokoban;

public class MovableTile extends GameTile {

	public MovableTile(int x, int y, TilesEnum tile) {
		super(x, y, tile);
	}
	
	public void move(char dir) {
		switch (dir) {
			case 'w':
				this.setY(y + 1);
			case 's':
				this.setY(y - 1);
			case 'a':
				this.setX(x - 1);
			case 'd':
				this.setX(x + 1);
		}
	}
}
