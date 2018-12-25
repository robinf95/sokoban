package sokoban;

public class Box extends MovableTile {
	private boolean inTarget = false;
	public Box(int x, int y) {
		super(x, y, TilesEnum.BOX);
	}

	public void setInTarget(){
		this.inTarget = !this.inTarget;
	}

	public boolean isInTarget(){
		return this.inTarget;
	}
}
