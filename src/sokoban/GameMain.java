package sokoban;

import java.util.Scanner;

public class GameMain {

	public static void main(String[] args) {
			World game;
			boolean isMultiplayer = false;
			LevelReader reader = new LevelReader();

			System.out.println("Multiplayer? (1):  ");

			try(Scanner sc = new Scanner(System.in)) {

				isMultiplayer = (sc.nextInt() == 1);


				game = new World(reader.toString(), isMultiplayer);

				game.run();
			}
	}

}
