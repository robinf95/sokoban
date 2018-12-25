package sokoban;

import java.util.InputMismatchException;

public class World {
	GameField gameField;
	GameLogic gameLogic;
	
	public World(String levelString) {
		gameField = new GameField(levelString);
		gameLogic = new GameLogic(gameField);
	}
	
	private void draw() {
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
	
	
}
