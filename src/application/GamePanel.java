package application;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

import java.util.Random;

public class GamePanel extends HBox {

    //    Elements
    private Game game;
    private GridPane cardPanel;
    private SidePanel sidePanel;
    private Card[][] cards;

    //    0 -> Card graphic number, 1 -> Button number
    private int[][] cardMap;
    private final static int NUMBER_OF_CARDS = 36;

    //    Size
    private final int GRID_HEIGHT;
    private final int GRID_WIDTH;

    private ImageView[] frontGraphics;


    public GamePanel(int gridHeight, int gridWidth) {

        GRID_HEIGHT = gridHeight;
        GRID_WIDTH = gridWidth;

        frontGraphics = new ImageView[NUMBER_OF_CARDS];
        for (int i = 0; i < NUMBER_OF_CARDS; i++) {
            frontGraphics[i] = new ImageView(
                    new Image(getClass().getResource("/images/fronts/card" + i + ".png").toString(),
                            100.0, 100.0, true, true)
            );
        }


        cardPanel = new GridPane();
        sidePanel = new SidePanel(gridHeight);
        game = new Game(gridHeight, gridWidth);

        shuffleCards();
        createCards();
        addCardsToPanel();

        cardPanel.getStyleClass().add("grid-pane");
        cardPanel.setHgap(25);
        cardPanel.setVgap(25);

        this.getStylesheets().add(getClass().getResource("/view/style.css").toString());
        this.getStyleClass().add("hbox");
        this.setAlignment(Pos.CENTER);
        this.setSpacing(25);
        this.getChildren().addAll(cardPanel, sidePanel);
    }

    private void createCards() {
        int count = 1;
        cards = new Card[GRID_HEIGHT][GRID_WIDTH];
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                int graphicNumber = getGraphicNumber(count);
                ImageView graphic = frontGraphics[graphicNumber];
                cards[i][j] = new Card(graphicNumber,graphic);
                cards[i][j].setId("but_" + count);
                cards[i][j].setOnMouseClicked(event -> {
                            game.cardClicked((Card) event.getTarget());
                            sidePanel.setStatistics(game.getPairsTotal(), game.getClicksTotal());
                            if (game.isWon()) {
                                sidePanel.addWonGraphic();
                                sidePanel.stopAnimation();
                            }
                        }
                );
                count++;
            }
        }
    }

    private int getGraphicNumber(int cardNumber) {
        for (int i = 0; i < cardMap.length; i++) {
            if (cardMap[i][1] == cardNumber) return cardMap[i][0];
        }
        return 0;
    }

    private void addCardsToPanel() {
        for (int i = 0; i < GRID_HEIGHT; i++) {
            for (int j = 0; j < GRID_WIDTH; j++) {
                cardPanel.add(cards[i][j], j, i);
            }
        }
    }

    private void shuffleCards() {
        cardMap = new int[GRID_HEIGHT * GRID_WIDTH][2];

        Random ran = new Random();
        int cardSet = ran.nextInt(NUMBER_OF_CARDS);

//        Pick graphics
        for (int i = 0; i < cardMap.length; i++) {
            cardMap[i][0] = cardMap[++i][0] = cardSet++ % NUMBER_OF_CARDS;
        }

//        Fill table with button numbers
        for (int i = 0; i < cardMap.length; i++) {
            cardMap[i][1] = i + 1;
        }

//        Shuffle 2'nd column
        for (int i = 0; i < cardMap.length; i++) {

            int r = ran.nextInt(cardMap.length);
            int temp = cardMap[i][1];
            cardMap[i][1] = cardMap[r][1];
            cardMap[r][1] = temp;
        }

    }

}
