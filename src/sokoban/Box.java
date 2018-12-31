package sokoban;

public class Box extends GameTile {
	private boolean inTarget = false;
	public Box(int x, int y) {
		super(TilesEnum.BOX, x, y);
	}

	public void setInTarget(){
		this.inTarget = !this.inTarget;
	}

	public boolean isInTarget(){
		return this.inTarget;
	}
}
