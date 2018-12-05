package application;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class TimeShower extends VBox {

    private Label name;
    private Label time;
    private Timeline timeline;


    private static String TIME_STYLE = "label-content";

    public TimeShower() {

        name = new Label();
        Image image = new Image(getClass().getResource("/images/text/timeLabel.png").toString());
        name.setGraphic(new ImageView(image));

        time = new Label("00:00");
        time.getStyleClass().add(TIME_STYLE);

        this.getChildren().addAll(name, time);
        startTimer();
    }

    public void startTimer() {
        long startTime = System.currentTimeMillis();
        DateFormat timeFormat = new SimpleDateFormat("mm:ss");

        timeline = new Timeline(new KeyFrame(Duration.millis(500), event -> {
            long diff = System.currentTimeMillis() - startTime;
            if (diff < 0)
                time.setText("00:00");
            else
                time.setText(timeFormat.format(diff));
        })
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void stopTimer() {
        timeline.stop();
    }

}
