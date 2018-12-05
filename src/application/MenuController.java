package application;

import application.Game;
import application.GameStage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private Button x34, x66, create;

    @FXML
    private TextField create_height, create_width;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void showInfo(ActionEvent event) {
        Stage stage = new Stage();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/Info-MemoryGame.fxml"));
            root.getStylesheets().add(getClass().getResource("/view/style.css").toString());
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(x34.getScene().getWindow());
            stage.setTitle("Info");
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            stage.getIcons().add(new Image(this.getClass().getResource("/images/CardBack.png").toString()));
            stage.setResizable(false);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void createStage(ActionEvent event) {

        try {

            if (!create_height.getText().isEmpty() && !create_width.getText().isEmpty()) {

                String textH = create_height.getText();
                String textW = create_width.getText();
                if (textH.matches("[1-6]") && textW.matches("[1-9]")) {

                    int height = Integer.valueOf(textH);
                    int width = Integer.valueOf(textW);
                    if (height * width % 2 != 0) {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Nieparzysta liczba pól!");
                        alert.show();
                    } else {
                        new GameStage(height, width);
                        create.getScene().getWindow().hide();
                    }

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Podaj cyfry!");
                    alert.setContentText("Maksymalna wysokość to 6!\nMaksymalna szerokość to 9!");
                    alert.show();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Zostawiono puste pole!");
                alert.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    void x34Action(ActionEvent event) {
        x34.getScene().getWindow().hide();
        new GameStage(3, 4);
    }

    @FXML
    void x66Action(ActionEvent event) {
        x66.getScene().getWindow().hide();
        new GameStage(6, 6);
    }

}
