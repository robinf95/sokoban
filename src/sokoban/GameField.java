package sokoban;

import java.util.ArrayList;

import sokoban.GameTile.TilesEnum;


public class GameField {
	//zweidimensionale Liste zum speichern des Levels
	private ArrayList<ArrayList<GameTile>> level;
	//Levelbreite
	private int levelWidth;
	private Player playerOne = null;
	private int boxFields = 0, targetFields = 0;
	
	
	public GameField(String levelString, int levelWidth) {
		createLevel(levelString, levelWidth);
	}
	
	void createLevel(String levelString, int levelWidth) {
		level = new ArrayList<>();
		this.levelWidth = levelWidth;
		int y = -1, x = 0;
		TilesEnum currentTile;
		
		for(int i=0; i <= levelString.length()-1; i++) {
			//Wenn noch keine zeile (y) besteht oder levelbreite erreicht wurde
			//--> erzeuge neue Spalte
			if(levelString.charAt(i) == 'X' || level.isEmpty()) {
				y++;
				x = 0;
				level.add( new ArrayList<GameTile>());
				continue;
			}
			
			//aktuelles Feld auslesen
			currentTile = TilesEnum.getTile(levelString.charAt(i));
			//wenn spieler und spieler noch nicht gesetzt
			if(  currentTile == TilesEnum.PLAYER && playerOne == null) {
				this.playerOne = new Player(x, y);
				level.get(y).add(playerOne);
			}else if( currentTile == TilesEnum.BOX ){
				level.get(y).add(new Box(x, y));
				this.boxFields++;
			}else {
				if(currentTile == TilesEnum.TARGET)  targetFields++;
				level.get(y).add(new GameTile(x, y, currentTile));
			}
			x++;
		}
	}
	
	@Override
	public String toString() {
		String outputString = "";
		
		for(ArrayList<GameTile> outerIndex : level) {
			for(GameTile tile : outerIndex)
				outputString += tile.toString();
			
			outputString += "\n";
		}
		
		return outputString;
	}

	public Player getPlayerOne(){
		return this.playerOne;
	}

	public GameTile getTile(int x, int y){
		return this.level.get(y).get(x);
	}

	public boolean checkField(int x, int y){
		if( x >= 0 && y >= 0 && level.get(y).get(x).getTile().isMoveable())
			return true;

		return false;
	}

	public boolean tileIsMoveable(int x, int y){
		//überprüfen ob das nächste feld auch beweglich ist
		return this.level.get(y).get(x) instanceof MovableTile;
	}

}
