package sokoban;

import java.util.InputMismatchException;

public class GameLogic {
    GameField gf;
    Player player;
    private int boxesInTarget = 0;

    public GameLogic(GameField gf){
        this.gf = gf;
        this.player = gf.getPlayerOne();
    }

    public void makeMovementPlayer (char c) throws InputMismatchException {
        int x = player.getX(), y = player.getY();
        int newX = 0, newY = 0;
        Box box;

        // newX/Y speichert die Bewegungsrichtung
        switch(c){
            case 's':
                y += newY += 1; break;
            case 'w':
                y += newY -= 1; break;
            case 'a':
                x += newX -=1; break;
            case 'd':
                x += newX += 1; break;
            default: throw new InputMismatchException();

        }

        //Hole Box falls nächstes Feld box
        box = gf.getBox(x, y);
        //Feld begehbar und kein BoxFeld?
        if (gf.checkField(x, y) && box == null) {
            player.setX(x);
            player.setY(y);
        } else if (box != null){
            if (this.makeMovementBox(box, x+newX, y+newY)) {
                player.setX(x);
                player.setY(y);
            }
        }
    }

    /* überprüfen ob Box bewegt werden kann, nächstes Feld muss frei sein
    und darf keine Box sein */
    private boolean makeMovementBox(Box box, int x, int y){
        if(gf.checkField( x , y )) {
            box.setX(x);
            box.setY(y);
            this.checkInTarget(box, x, y);
            return true;
        }
        return false;

    }

    // überprüfen ob die Box das Ziel erreicht oder wieder verlassen hat
    private void checkInTarget(Box box, int x, int y){
        if (gf.getTile(x ,y).getTileType() == GameTile.TilesEnum.TARGET) {
            box.setInTarget(); // Switch true/false
            this.boxesInTarget++;
        } else if (box.isInTarget()) {
            box.setInTarget();
            this.boxesInTarget--;
        }
    }

    public int getBoxesInTarget() {
        return this.boxesInTarget;
    }

    public boolean checkWin(){
        return this.boxesInTarget == gf.getBoxesAmount();
    }

}
