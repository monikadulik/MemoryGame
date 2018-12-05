package application;

import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class AnimationPane extends Pane {

    //  Spritesheet parameters
    private static final int COLUMNS = 5;
    private static final int ROWS = 1;
    private static final int FRAMES_NUMBER = 5;
    private static final int OFFSET_X = 0;
    private static final int OFFSET_Y = 0;
    private static final int WIDTH = 50;
    private static final int HEIGHT = 46;

    //  Animation elements
    private Animation animForward;
    private TranslateTransition transForward;
    private ImageView charForward;
    private Animation animBackward;
    private TranslateTransition transBackward;
    private ImageView charBackward;

    //  Sprites paths
    private static final String SPRITE_FORWARD = "/images/spriteGumbalFor.png";
    private static final String SPRITE_BACKWARD = "/images/spriteGumbalBack.png";

    private MediaPlayer music;
    private final double sidePanelHeight;

    public AnimationPane(double sidePanelHeight) {

        if (sidePanelHeight<391)
            this.sidePanelHeight = 390;
        else
            this.sidePanelHeight = sidePanelHeight;


        createCharacterForward();
        createCharacterBackward();
        this.getChildren().addAll(charForward, charBackward);

        music = new MediaPlayer(new Media(this.getClass().getResource("/sounds/gameMelody.wav").toExternalForm()));
        music.autoPlayProperty();
        music.setCycleCount(50);

        this.startAnimation();
    }

    private void createCharacterForward(){
        charForward = new ImageView(new Image(this.getClass().getResource(SPRITE_FORWARD).toString()));
        animForward = new SpriteAnimation(
                charForward, Duration.millis(600),
                FRAMES_NUMBER, COLUMNS, ROWS,
                OFFSET_X, OFFSET_Y, WIDTH, HEIGHT
        );
        animForward.setCycleCount(Animation.INDEFINITE);

        transForward = new TranslateTransition(Duration.millis(7000), charForward);
        transForward.setCycleCount(Animation.INDEFINITE);
        transForward.setFromY(sidePanelHeight - 370);
        transForward.setFromX(10);
        transForward.setToX(195);
    }

    private void createCharacterBackward(){
        charBackward = new ImageView(new Image(this.getClass().getResource(SPRITE_BACKWARD).toString()));
        animBackward = new SpriteAnimation(
                charBackward, Duration.millis(600),
                FRAMES_NUMBER, COLUMNS, ROWS,
                OFFSET_X, OFFSET_Y, WIDTH, HEIGHT
        );
        animBackward.setCycleCount(Animation.INDEFINITE);

        transBackward = new TranslateTransition(Duration.millis(7000), charBackward);
        transBackward.setCycleCount(Animation.INDEFINITE);
        transBackward.setFromY(sidePanelHeight - 370);
        transBackward.setFromX(185);
        transBackward.setToX(0);
    }

    public void startAnimation(){
        animForward.play();
        animBackward.play();
        transForward.play();
        transBackward.play();
        music.play();
    }

    public void stopAnimation(){
        animForward.stop();
        animBackward.stop();
        transForward.stop();
        transBackward.stop();
        music.stop();
    }


}
