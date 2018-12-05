package application;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpriteAnimation extends Transition {

    private final ImageView imageView;
    private final int numberOfFrames, columns, rows, offsetX, offsetY, width, height;
    private int lastIndex;

    public SpriteAnimation(ImageView imageView, Duration duration, int count, int columns, int rows,
                           int offsetX, int offsetY, int width,   int height) {
        this.imageView = imageView;
        this.numberOfFrames = count;
        this.columns   = columns;
        this.rows = rows;
        this.offsetX   = offsetX;
        this.offsetY   = offsetY;
        this.width     = width;
        this.height    = height;
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
    }

    protected void interpolate(double k) {
        int index = Math.min((int) Math.floor(k * numberOfFrames), numberOfFrames - 1);
        if (index != lastIndex) {
            int x = (index % columns) * width  + offsetX;
            int y = (index / columns) * height + offsetY;
            imageView.setViewport(new Rectangle2D(x, y, width, height));
            lastIndex = index;
        }
    }
}