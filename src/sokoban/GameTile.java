package sokoban;

public class GameTile{
	private TilesEnum tile;
	private int x, y;

	public enum TilesEnum {
		WALL("#", false),
		EMPTY(" ", true),
		PLAYERONE("1", false),
		PLAYERTWO("2", false),
		BOX("$", false),
		TARGET(".", true);
		//Kann man auf Feld gehen?
		private final boolean moveOn;
		private final String tile;
		TilesEnum(String c, boolean moveOn) {
			this.tile = c;
			this.moveOn = moveOn;
		}
		
		@Override
		public String toString() {
			return this.tile;
		}

		public boolean isMovable(){
			return this.moveOn;
		}
		
		public static TilesEnum getTile(char c) {
			switch(c) {
				case '1': return PLAYERONE;
				case '2': return PLAYERTWO;
				case ' ': return EMPTY;
				case '#': return WALL;
				case '$': return BOX;
				case '.': return TARGET;
				default:  return EMPTY;
			}
		}
	}
	
	public GameTile(TilesEnum tile) {
		this.tile = tile;
	}

	public GameTile(TilesEnum tile, int x, int y){
		this.tile = tile;
		this.x = x;
		this.y = y;
	}
	
	@Override
	public String toString() {
		return this.tile.toString();
	}
	
	public TilesEnum getTileType() {
		return this.tile;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y  = y;
	}

	public int getX(){
		return this.x;
	}

	public int getY(){
		return this.y;
	}

	public boolean compareCoordinate(int x, int y){
		return getY() == y && getX() == x;
	}
}
