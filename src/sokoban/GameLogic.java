package sokoban;

public class GameLogic {
    GameField gf;
    Player playerOne = null;

    public GameLogic(GameField gf, Player playerOne){
        this.gf = gf;
        this.playerOne = playerOne;
    }

    public void makeMovement(char c, GameTile tile){
        int x = tile.getX(), y = tile.getY();

        switch(c){
            case 'w':
                y += 1;
            case 's':
                y -= 1;
            case 'a':
                x -=1;
            case 'd':
                x += 1;
        }

        if(gf.checkField( x , y )) {
            tile.setX(x);
            tile.setY(y);

        }else if(gf.tileIsMoveable(x, y)){
            this.makeMovement( c, gf.getTile(x, y));
        }
    }

}
