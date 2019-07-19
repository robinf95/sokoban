package sokoban;

import javafx.application.Application;

import java.util.InputMismatchException;
import java.util.Scanner;

public class World {
	public static GameField gameField;
	private GameLogic gameLogic;
	
	public World(String levelString) {
		gameField = new GameField(levelString);
		gameLogic = new GameLogic(gameField);
	}

	public GameField getGameField() {
		return gameField;
	}

	public void draw() {
		Application.launch(GameFieldGUI.class);
		System.out.println(gameField.toString());
	}

	public void input(String input){
		try {
			gameLogic.makeMovementPlayer(input.charAt(0) );
			this.draw();
		} catch (InputMismatchException e) {
			System.err.println("Falsche Eingabe!");
		}

	}

	public void run() {
		try (Scanner sc = new Scanner(System.in)) {
			String input;
			this.draw();
			//Führt das Programm solange aus bis die Boxen im Ziel sind
			while (!gameLogic.checkWin()) {
				System.out.printf("Score: %d/%d%n", gameLogic.getBoxesInTarget(), gameField.getBoxesAmount());
				System.out.printf("Eingabe: ");
				input = sc.next();
				if (input.charAt(0) == 'e') {
					System.out.println("Spiel beendet");
					return;
				}
				this.input(input);
			}
			System.out.println("Herzlichen Glückwunsch! Level bestanden!");
			System.exit(0);
		}
	}
	
}
