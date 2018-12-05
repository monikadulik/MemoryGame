package application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class SidePanel extends VBox {

    private TimeShower timeShower;
    private StatsShower pairsShower;
    private StatsShower clicksShower;
    private AnimationPane animationPane;

    private static int MAX_HEIGHT;
    private static final int MIN_HEIGHT = 390;
    private static final int MIN_WIDTH = 250;
    private static final String PANEL_STYLE = "side-panel";

    public SidePanel(int gridHeight) {

        MAX_HEIGHT = gridHeight*140-40;
        this.getStyleClass().add(PANEL_STYLE);
        this.setMinWidth(MIN_WIDTH);
        this.setMinHeight(MIN_HEIGHT);
        this.setMaxHeight(MAX_HEIGHT);

        Image pairsImage = new Image(getClass().getResource("/images/text/findsLabel.png").toString());
        Image clicksImage = new Image(getClass().getResource("/images/text/clicksLabel.png").toString());

        timeShower = new TimeShower();
        pairsShower = new StatsShower(new ImageView(pairsImage));
        clicksShower = new StatsShower(new ImageView(clicksImage));
        animationPane = new AnimationPane(gridHeight*130);

        Button newGame = new Button("Nowa gra");
        newGame.setOnAction(event -> {
            timeShower.stopTimer();
            animationPane.stopAnimation();
            newGame.getScene().getWindow().hide();
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/view/Menu-MemoryGame.fxml"));
                root.getStylesheets().add(getClass().getResource("/view/style.css").toString());

                Stage stage = new Stage();
                stage.setTitle("MemoryGame");
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
                stage.getIcons().add(new Image(this.getClass().getResource("/images/CardBack.png").toString()));
                stage.setResizable(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        this.getChildren().addAll(timeShower, pairsShower, clicksShower, newGame,animationPane);
    }

    public void setStatistics(int pairs, int clicks){
        pairsShower.setStatistic(pairs);
        clicksShower.setStatistic(clicks);
    }

    public void addWonGraphic(){
        Label won = new Label();
        won.setGraphic(new ImageView(
                new Image(getClass().getResource("/images/text/won.png").toString()))
        );
        this.getChildren().add(won);
    }

    public void stopAnimation(){
        animationPane.stopAnimation();
        timeShower.stopTimer();
        this.getChildren().remove(animationPane);
    }


}
