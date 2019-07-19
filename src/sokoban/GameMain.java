package sokoban;

public class GameMain {
	private static World game;

	public static void main(String[] args) {

			LevelReader reader = new LevelReader();

			game = new World(reader.toString());

			game.run();
	}

}
