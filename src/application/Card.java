package application;

import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Card extends Button {

    //    Graphics
    private ImageView picFront;
    private ImageView picBack;
    private int graphicNumber;

    public Card(int graphicNumber, ImageView picFront) {

        this.picFront = picFront;
        picBack = new ImageView(new Image(getClass().getResource("/images/CardBack.png").toString(),
                100.0, 100.0, true, true));

        this.graphicNumber = graphicNumber;
        this.setGraphic(picBack);
    }

    public int getGraphicNumber() {
        return graphicNumber;
    }

    public void conceal() {
        this.setGraphic(picBack);
    }

    public void reveal() {
        this.setGraphic(picFront);

        RotateTransition rt = new RotateTransition(Duration.millis(3000), picFront);
        rt.setByAngle(360);
        rt.play();
    }

    public void wrongAnimation() {
        TranslateTransition tt1 = new TranslateTransition(Duration.millis(200));
        tt1.setNode(this);
        tt1.setToX(10);
        tt1.setCycleCount(4);
        tt1.setAutoReverse(true);
        tt1.play();

        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
                    Thread.sleep(1400);
                } catch (InterruptedException e) {
                }
                return null;
            }
        };
        sleeper.setOnSucceeded(event -> this.conceal());
        new Thread(sleeper).start();
    }


}
