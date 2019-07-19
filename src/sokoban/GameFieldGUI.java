package sokoban;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class GameFieldGUI extends Application {
    private ImageView imgView;

    @Override
    public void start(Stage stage) {
        GameField gameField = World.gameField;
        GridPane root = new GridPane();
        ArrayList<ArrayList<GameTile>> level = gameField.getLevel();
        level.forEach(row->{
            int rowIndex = level.indexOf(row);
            row.forEach(column->{
                int columnIndex = row.indexOf(column);

                //lese aktuelle Zelle
                GameTile actualTile = (level.get(rowIndex).get(columnIndex));
                
                final Image img = new Image(getClass().getResource(actualTile.getTileType().getPath()).toExternalForm());
                imgView = new ImageView(img);

                //binde image Property von Tile an Zelle
                //Bindings.bindBidirectional(imageDisplay.imageProperty(), actualTile.getTileType().getPath().imageProperty());

                //Zur Gridpane hinzufügen
                root.add(imgView, columnIndex, rowIndex);
            });
        });
        Scene scene = new Scene(root, 1000, 1000);

        stage.setTitle("Sokoban GUI");
        stage.setScene(scene);
        stage.show();
    }
    
}
