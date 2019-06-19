package sokoban;

import java.util.ArrayList;

import sokoban.GameTile.TilesEnum;


public class GameField {
	//zweidimensionale Liste zum speichern des Levels
	private ArrayList<ArrayList<GameTile>> level;
	//Levelbreite

	//Abstraktion Player und Boxen sind eine Schicht �ber dem Level,
	//so k�nnen diese besser bewegt werden.
	private Player[] player = new Player[2];
	private ArrayList<Box> boxes;
	private boolean isMultiplayer;

	public GameField(String levelString, boolean isMultiplayer) {
		this.isMultiplayer = isMultiplayer;
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
				level.add( new ArrayList<>());
				continue;
			}

			//aktuelles Feld auslesen
			currentTile = TilesEnum.getTile(levelString.charAt(i));
			//wenn spieler1 und spieler2 noch nicht gesetzt
			if(  currentTile == TilesEnum.PLAYERONE && player[0] == null) {
				this.player[0] = new Player( x, y, true);
				currentTile = TilesEnum.EMPTY;
			}else if(  currentTile == TilesEnum.PLAYERTWO && player[1] == null) {
				this.player[1] = new Player(x, y, false);
				currentTile = TilesEnum.EMPTY;
			}else if( currentTile == TilesEnum.BOX ){
				boxes.add(new Box(x, y));
				currentTile = TilesEnum.EMPTY;
			}

			if(currentTile == TilesEnum.TARGET){
				level.get(y).add(new GameTile(currentTile, x, y));
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
				// wenn Player auf der Koordinate
				if(player[0].compareCoordinate(x, y))
					outputString += player[0].toString();
				//nur checken wenn 2 spielen
				else if(isMultiplayer){
					if(player[1].compareCoordinate(x, y))
						outputString += player[1].toString();
				}
				// �berpr�fen ob eine Box auf der Koordinate
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

	public boolean checkField(int x, int y){
		//Ist das Feld begehbar und werden die Feldgrenzen nicht �berschritten?
		if(x >= 0 && y >= 0 && level.get(y).get(x).getTileType().isMovable())
			return true;

		return false;
	}

	public Player[] getPlayer(){
		return this.player;
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

	public ArrayList<ArrayList<GameTile>> getLevel() {
		return level;
	}
}