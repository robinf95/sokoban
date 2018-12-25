package sokoban;

import java.util.InputMismatchException;

public class GameLogic {
    GameField gf;
    Player player = null;

    public GameLogic(GameField gf){
        this.gf = gf;
        this.player = gf.getPlayerOne();
    }

    public void makeMovementPlayer (char c) throws InputMismatchException {
        int x = player.getX(), y = player.getY();
        int newX = 0, newY = 0;
        Box box;

        switch(c){
            case 's':
                y += newY += 1; break;//newY speichert die Bewegungsrichtung
            case 'w':
                y += newY -= 1; break;
            case 'a':
                x += newX -=1; break;
            case 'd':
                x += newX += 1; break;
            default: throw new InputMismatchException();

        }

        //Feld frei?
        if(gf.checkField( x , y )) {
            player.setX(x  );
            player.setY(y );
        }else{
            box = gf.getBox(x, y);
            //überprüfen ob nächstes feld box
            if( box!= null){
                if(this.makeMovementBox( box, x+newX, y+newY)) {
                    player.setX(x  );
                    player.setY(y );
                }
            }

        }
    }

    private boolean makeMovementBox(Box box, int x, int y){
        if(gf.checkField( x , y )) {
            box.setX(x);
            box.setY(y);
            return true;
        }
        return false;

    }

}
