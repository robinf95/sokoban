package sokoban;

import javafx.beans.binding.Bindings;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class GameFieldGUI{
    private GameField gameField;
    private GridPane gameFieldGUI;

    public void GameFieldGUI( GameField gameField){
        this.gameField = gameField;
        gameFieldGUI = new GridPane();
        createGameFieldGUI();
    }

    private void createGameFieldGUI(){
        ArrayList<ArrayList<GameTile>> level = gameField.getLevel();
        level.forEach(row->{
            int rowIndex = level.indexOf(row);
            row.forEach(column->{
                int columnIndex = row.indexOf(column);
                ImageView imageDisplay = new ImageView();

                //lese aktuelle Zelle
                GameTile actualTile = (level.get(rowIndex).get(columnIndex));

                //binde image Property von Tile an Zelle
                Bindings.bindBidirectional(imageDisplay.imageProperty(), actualTile.getTileType().getImg().imageProperty());

                //Zur Gridpane hinzufügen
                gameFieldGUI.add(imageDisplay, columnIndex, rowIndex);
            });
        });
    }

    public GridPane getGameFieldGUI(){
        return gameFieldGUI;
    }
}
