package application;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class GameStage {

    public GameStage(int gridHeight, int gridWidth) {

        GamePanel gamePanel = new GamePanel(gridHeight,gridWidth);

        Stage stage = new Stage();
        stage.setScene(new Scene(gamePanel));

        stage.setTitle("MemoryGame - " + gridHeight + " x " + gridWidth);
        stage.getIcons().add(new Image(getClass().getResource("/images/CardBack.png").toString(),
                100.0, 100.0, true, true));
        stage.setResizable(false);
        stage.show();

    }
}
