package main;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

public class Tower1 extends Tower {
    private Image sprite = new Image("/Images/blueTower.png");
    private Line l;
    public Tower1() {
        price = 30 * playerLevel;
        dps = (4 - (0.4 * (playerLevel - 1)));
    }

    public ImageView draw() {
        imageView = new ImageView();
        imageView.setImage(sprite);
        imageView.setFitHeight(75);
        imageView.setFitWidth(75);
        imageView.setX(xVal);
        imageView.setY(yVal);
        return imageView;
    }

    public Line createAttackObject(Enemy e) {
        l = new Line(xVal + (75.0 / 2), yVal + (75.0 / 2),
                e.getXVal() + (35.0 / 2), e.getYVal() + (35.0 / 2));
        l.setStroke(Color.RED);
        l.setStrokeWidth(5);
        return l;
    }

    public boolean attackEnemy(Enemy e) {
        if (e.getImageView().intersects(l.getBoundsInLocal())) {
            e.setHealth(e.getHealth() - dps);
            return true;
        }
        return false;
    }

}