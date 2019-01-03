package sokoban;

public class GameMain {

	public static void main(String[] args) {
			World game;

			LevelReader reader = new LevelReader();

			game = new World(reader.toString());

			game.run();
	}

}
