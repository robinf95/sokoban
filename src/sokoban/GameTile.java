package sokoban;

public class GameTile{
	private TilesEnum tile;
	private int x, y;

	public enum TilesEnum {
		WALL("#", false, "../resources/gate.bmp"),
		EMPTY(" ", true,  "../resources/floor.bmp"),
		PLAYERONE("1", false, "../resources/player2D.png"),
		BOX("$", false, "../resources/boxP.png"),
		TARGET(".", true, "../resources/pressure_pad.png");
		//Kann man auf Feld gehen?
		private final boolean moveOn;
		private final String tile;
		private final String imgPath;
		TilesEnum(String c, boolean moveOn, String imgPath) {
			this.tile = c;
			this.moveOn = moveOn;
			this.imgPath = imgPath;
		}

		@Override
		public String toString() {
			return this.tile;
		}

		public boolean isMovable(){
			return this.moveOn;
		}
		
		public String getPath() { return this.imgPath; }

		public static TilesEnum getTile(char c) {
			switch(c) {
				case '1': return PLAYERONE;
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
