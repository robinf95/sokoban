package sokoban;

import java.util.InputMismatchException;
import java.util.Scanner;

public class World {
	private GameField gameField;
	private GameLogic gameLogic;
	private boolean isPlayerOneTurn = true;
	private boolean isMultiplayer;
	
	public World(String levelString, boolean isMultiplayer) {
		this.isMultiplayer = isMultiplayer;
		gameField = new GameField(levelString, isMultiplayer);
		gameLogic = new GameLogic(gameField);
	}
	
	public void draw() {
		System.out.println(gameField.toString());
	}

	public boolean input(String input, boolean isPlayerOne){
		try {
			gameLogic.makeMovementPlayer(input.charAt(0), isPlayerOne, isMultiplayer);
			this.draw();
			return !isPlayerOne;
		} catch (InputMismatchException e) {
			System.err.println("Falsche Eingabe!");
			return isPlayerOne;
		}

	}

	public void run(Scanner sc) {
		String input;
		this.draw();
		//F�hrt das Programm solange aus bis die Boxen im Ziel sind
		while (!gameLogic.checkWin()) {
			System.out.printf("Score: %d/%d%n", gameLogic.getBoxesInTarget(), gameField.getBoxesAmount());
			System.out.printf("Eingabe: ");
			input = sc.next();
			if (input.charAt(0) == 'e') {
				System.out.println("Spiel beendet");
				return;
			}
			//gibt Spieler zurück, welcher dran ist
			isPlayerOneTurn = this.input(input, isPlayerOneTurn);

			//wenn keine Multiplayer dann ist player1 immer am zug
			if(!isMultiplayer) isPlayerOneTurn = true;
		}
		System.out.println("Herzlichen Glueckwunsch! Level bestanden!");
		System.exit(0);
	}
	
}
