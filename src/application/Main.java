package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/Menu-MemoryGame.fxml"));
        root.getStylesheets().add(getClass().getResource("/view/style.css").toString());

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("MemoryGame");
        primaryStage.show();
        primaryStage.getIcons().add(new Image(this.getClass().getResource("/images/CardBack.png").toString()));
        primaryStage.setResizable(false);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
