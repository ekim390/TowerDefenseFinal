package main;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Enemy3 extends Enemy {
    private Image sprite = new Image("/Images/Pink.png");
    public Enemy3(double x, double y) {
        classification = "Pink";
        health = 550;
        walkingSpeed = 4;
        dps = 80 + increasedDPS;
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
        return new Enemy3(enemyStartX, 295);
    }

}
