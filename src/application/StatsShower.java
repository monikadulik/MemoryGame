package application;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class StatsShower extends VBox{

    private Label name;
    private Label statistic;

    private static String STAT_STYLE = "label-content";

    public StatsShower(ImageView title) {
        name = new Label();
        name.setGraphic(title);

        statistic = new Label("0");
        statistic.getStyleClass().add(STAT_STYLE);

        this.getChildren().addAll(name, statistic);
    }

    public void setStatistic(int value) {
        this.statistic.setText(Integer.toString(value));
    }
}
