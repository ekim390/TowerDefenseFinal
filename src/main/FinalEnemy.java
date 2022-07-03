package main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FinalEnemy extends Enemy {
    private Image sprite = new Image("/Images/finalEnemy.png");
    public FinalEnemy(double x, double y) {
        classification = "finalEnemy";
        health = 7000;
        walkingSpeed = 2;
        dps = 100;
        xVal = x;
        yVal = y;
    }
    public ImageView draw() {
        imageView = new ImageView();
        imageView.setImage(sprite);
        imageView.setFitHeight(70);
        imageView.setFitWidth(70);
        imageView.setX(xVal);
        imageView.setY(yVal);
        return imageView;
    }

    static Enemy createEnemy() {
        return new FinalEnemy(enemyStartX, 250);
    }

}