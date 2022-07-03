package main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Enemy1 extends Enemy {
    private Image sprite = new Image("/Images/Yellow.png");
    public Enemy1(double x, double y) {
        classification = "Yellow";
        health = 450;
        walkingSpeed = 3;
        dps = 60 + increasedDPS;
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
        return new Enemy1(enemyStartX, 249);
    }

}