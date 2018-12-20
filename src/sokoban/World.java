package sokoban;

public class World {
	GameField gameField;
	Display display;
	String canvas;
	int levelWidth;
	
	public World(String levelString, int levelWidth) {
		this.levelWidth = levelWidth;
		gameField = new GameField(levelString, levelWidth);
		display = new Display(this.gameField);
	}
	
	public void draw() {
		display.drawField();
	}
	
	
}
