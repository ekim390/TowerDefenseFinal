package main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Enemy2 extends Enemy {
    private Image sprite = new Image("/Images/Green.png");
    public Enemy2(double x, double y) {
        classification = "Green";
        health = 500;
        walkingSpeed = 3.5;
        dps = 70 + increasedDPS;
        xVal = x;
        yVal = y;
    }

    public ImageView draw() {
        imageView = new ImageView();
        imageView.setImage(sprite);
        imageView.setFitHeight(35);
        imageView.setFitWidth(35);
        imageView.setX(xVal);
        imageView.setY(yVal);
        return imageView;
    }

    static Enemy createEnemy() {
        return new Enemy2(enemyStartX, 270);
    }

}
