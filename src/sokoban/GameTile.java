package sokoban;

public class GameTile{
	private int x, y;
	private TilesEnum tile;

	public enum TilesEnum {
		WALL("#", false),
		EMPTY(" ", true),
		PLAYER("@", false),
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

		public boolean isMoveable(){
			return this.moveOn;
		}
		
		public static TilesEnum getTile(char c) {
			switch(c) {
			case '@': return PLAYER;
			case ' ': return EMPTY;
			case '#': return WALL;
			case '$': return BOX;
			case '.': return TARGET;
			default:  return EMPTY;
			}
		}
	}
	
	public GameTile(int x, int y, TilesEnum tile) {
		this.tile = tile;
		this.x = x;
		this.y = y;
	}
	
	public GameTile(TilesEnum tile) {
		this.tile = tile;
	}
	
	@Override
	public String toString() {
		return this.tile.toString();
	}
	
	public TilesEnum getTile() {
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
}
