package sokoban;

import java.util.Scanner;

public class GameMain {

	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in)) {
			World game;
			String input = "";
			LevelReader reader = new LevelReader();

			game = new World(reader.toString());


			while(input != "e"){
				input = sc.next();
				game.input(input);
			}
		}


	}

}
