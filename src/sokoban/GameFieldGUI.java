package sokoban;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
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
        GridPane root = new GridPane();
        ArrayList<ArrayList<GameTile>> level = World.getGameField().getLevel();
        level.forEach(row->{
            int rowIndex = level.indexOf(row);
            row.forEach(column->{
                int columnIndex = row.indexOf(column);

                //lese aktuelle Zelle
                GameTile actualTile = (level.get(rowIndex).get(columnIndex));
                
                final Image img = new Image(getClass().getResource(actualTile.getTileType().getPath()).toExternalForm());
                imgView = new ImageView(img);

                //binde image Property von Tile an Zelle
                // Bindings.bindBidirectional(imgView.imageProperty(), actualTile.getTileType().getPath().imageProperty());

                //Zur Gridpane hinzufügen
                root.add(imgView, columnIndex, rowIndex);
            });
        });
        Scene scene = new Scene(root, 1000, 1000);
        scene.setOnKeyPressed((e) -> {
            World.getGameLogic().makeMovementPlayer(e.getCode().toString().toLowerCase().charAt(0));
        });
        

        stage.setTitle("Sokoban GUI");
        stage.setScene(scene);
        stage.show();
    }
    
}
