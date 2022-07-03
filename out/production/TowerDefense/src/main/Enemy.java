package main;
import javafx.scene.image.ImageView;

public abstract class Enemy {
    protected String classification;
    protected double health;
    protected double dps;
    protected double walkingSpeed;
    protected double xVal;
    protected double yVal;
    protected ImageView imageView = new ImageView();
    protected static double enemyStartX = 1175;

    public void setXVal(double x) {
        xVal = x;
    }
    public double getXVal() {
        return xVal;
    }

    public void setYVal(double y) {
        yVal = y;
    }
    public double getYVal() {
        return yVal;
    }

    public void setClassification(String c) {
        classification = c;
    }
    public String getClassification() {
        return classification;
    }

    public void setHealth(double h) {
        health = h;
    }
    public double getHealth() {
        return health;
    }

    public void setDPS(int d) {
        dps = d;
    }
    public double getDPS() {
        return dps;
    }

    public void setWalkingSpeed(int w) {
        walkingSpeed = w;
    }
    public double getWalkingSpeed() {
        return walkingSpeed;
    }

    public ImageView getImageView() {
        return imageView;
    }
    public void setImageView(ImageView i) {
        imageView = i;
    }

    public void attackBase() {
        if ((Base.getHealth() - dps) < 0) {
            Base.setHealth(0);
        } else {
            Base.setHealth(Base.getHealth() - dps);
        }
    }

    public boolean enemyWalk() {
        switch (classification) {
        case "Yellow":
            if (!(xVal < 200)) {
                if ((xVal < 632) && (yVal < 545)) {
                    yVal = yVal + walkingSpeed;
                } else {
                    xVal = xVal - walkingSpeed;
                }
                imageView.setX(xVal);
                imageView.setY(yVal);
                return false;
            }
            break;
        case "Green":
            if (!(xVal < 200)) {
                if ((xVal < 658) && (yVal < 566)) {
                    yVal = yVal + walkingSpeed;
                } else {
                    xVal = xVal - walkingSpeed;
                }
                imageView.setX(xVal);
                imageView.setY(yVal);
                return false;
            }
            break;
        case "Pink":
            if (!(xVal < 200)) {
                if ((xVal < 680) && (yVal < 586)) {
                    yVal = yVal + walkingSpeed;
                } else {
                    xVal = xVal - walkingSpeed;
                }
                imageView.setX(xVal);
                imageView.setY(yVal);
                return false;
            }
            break;
        default:
            throw new IllegalStateException("Unexpected value");
        }
        return true;
    }

    public static Enemy createEnemy(int z) {
        if (z == 1) {
            return Enemy1.createEnemy();
        } else if (z == 2) {
            return Enemy2.createEnemy();
        } else if (z == 3) {
            return Enemy3.createEnemy();
        }
        return null;
    }

    public boolean isEnemyHealthy() {
        if (health <= 0) {
            return false;
        }
        return true;
    }

    public abstract ImageView draw();
}