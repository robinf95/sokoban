package sokoban;

import java.util.InputMismatchException;
import java.util.Scanner;

public class World {
	GameField gameField;
	GameLogic gameLogic;
	
	public World(String levelString) {
		gameField = new GameField(levelString);
		gameLogic = new GameLogic(gameField);
	}
	
	public void draw() {
		System.out.println(gameField.toString());
	}

	public void input(String input){
		try{
			gameLogic.makeMovementPlayer(input.charAt(0) );
			this.draw();
		}catch(InputMismatchException e){
			System.err.println("Falsche Eingabe!");
		}

	}

	public void run() {
		try (Scanner sc = new Scanner(System.in)) {
			String input = "";
			this.draw();
	//Führt das Programm solang aus bis die Boxen im Ziel sind
			while (!gameLogic.checkWin()) {
				System.out.printf("Eingabe:");
				input = sc.next();
				if(input == "e") return;
				this.input(input);
			}
		}
	}
	
}
