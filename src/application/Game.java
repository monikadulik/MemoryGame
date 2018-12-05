package application;

import javafx.scene.media.AudioClip;

import java.util.Random;

public class Game {

    //    Counters
    private int clickCounter = 0;
    private Card firstTryButton;
    private Card secondTryButton;
    private int clicksTotal = 0;
    private int pairsTotal = 0;
    private boolean isWon = false;

    //    Media
    private AudioClip click;
    private AudioClip negative;
    private AudioClip match;
    private AudioClip win;

    //    Size
    private final int GRID_HEIGHT;
    private final int GRID_WIDTH;


    public Game(int gridHeight, int gridWidth) {
        GRID_HEIGHT = gridHeight;
        GRID_WIDTH = gridWidth;

        readSounds();
    }

    public boolean isWon() {
        return isWon;
    }

    public int getClicksTotal() {
        return clicksTotal;
    }

    public int getPairsTotal() {
        return pairsTotal;
    }

    public void cardClicked(Card clickedCard) {
        click.play();
        clicksTotal++;
        clickedCard.reveal();

        if (clickCounter == 0) {
            clickCounter++;
            firstTryButton = clickedCard;

        } else if (clickCounter == 1) {

            clickCounter = 0;

            if (firstTryButton == clickedCard) {
                negative.play();
                clickedCard.wrongAnimation();
            } else {
                secondTryButton = clickedCard;

                if (isMatch()) {
                    match.play();

                    firstTryButton.setDisable(true);
                    secondTryButton.setDisable(true);
                    pairsTotal++;

                    if (pairsTotal == (GRID_HEIGHT * GRID_WIDTH) / 2) {
                        isWon = true;
                        win.play();
                    }

                } else {
                    negative.play();
                    firstTryButton.wrongAnimation();
                    secondTryButton.wrongAnimation();
                }
            }
        }
    }

    private boolean isMatch() {
        return
                firstTryButton.getGraphicNumber() == secondTryButton.getGraphicNumber()
                && !firstTryButton.getId().equals(secondTryButton.getId());
    }


    private void readSounds() {
        click = new AudioClip(this.getClass().getResource("/sounds/click.wav").toString());
        negative = new AudioClip(this.getClass().getResource("/sounds/negative.wav").toString());
        match = new AudioClip(this.getClass().getResource("/sounds/match.wav").toString());
        win = new AudioClip(this.getClass().getResource("/sounds/win.wav").toString());
    }

}
