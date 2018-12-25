package sokoban;

import java.util.ArrayList;

import sokoban.GameTile.TilesEnum;


public class GameField {
	//zweidimensionale Liste zum speichern des Levels
	private ArrayList<ArrayList<GameTile>> level;
	//Levelbreite

	//Abstraktion Player und Boxen sind eine Schicht über dem Level,
	//so können diese besser bewegt werden.
	private Player playerOne = null;
	private ArrayList<Box> boxes;
	private int targetFields = 0;
	
	
	public GameField(String levelString) {
		createLevel(levelString);
	}
	
	private void createLevel(String levelString) {
		level = new ArrayList<>();
		boxes = new ArrayList<>();
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
				currentTile = TilesEnum.EMPTY;
			}else if( currentTile == TilesEnum.BOX ){
				boxes.add(new Box(x, y));
				currentTile = TilesEnum.EMPTY;
			}

			if(currentTile == TilesEnum.TARGET){
				level.get(y).add(new GameTile(currentTile, x, y));
				targetFields++;
			} else{
				level.get(y).add(new GameTile(currentTile));
			}

			x++;
		}
	}
	
	@Override
	//level ausgeben
	public String toString() {
		String outputString = "";

		for(int y= 0; y < level.size(); y++){
			for(int x = 0; x< level.get(y).size(); x++){
				//wenn player auf der koordinate
				if(playerOne.compareCoordinate(x, y))
					outputString += playerOne.toString();
				//überprüfen ob eine Box auf der Koordinate
				else if((this.getBox(x, y)) != null)
					outputString += TilesEnum.BOX.toString();
				else
					outputString += level.get(y).get(x).toString();
			}
			outputString += "\n";
		}
		return outputString;
	}

	public GameTile getTile(int x, int y){
		return this.level.get(y).get(x);
	}


	//Ist das Feld begehbar und werden die Feldgrenzen nicht überschritten
	public boolean checkField(int x, int y){
		if( x >= 0 && y >= 0 && level.get(y).get(x).getTile().isMovable())
			return true;

		return false;
	}

	public Player getPlayerOne() {
		return playerOne;
	}

	public Box getBox(int x, int y){
		for(Box b : this.boxes ){
			if(b.getX() == x && b.getY() == y)
				return b;
		}
		return null;
	}

	public int getBoxesAmount(){
		return this.boxes.size();
	}

}
