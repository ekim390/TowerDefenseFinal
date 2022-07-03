package test;
import javafx.application.Application;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import main.*;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class TowerPlaceTest {
    @BeforeClass
    public static void setUpClass() throws InterruptedException {
        // Initialise Java FX
        Thread t = new Thread("JavaFX PlaceTowers Thread") {
            public void run() {
                Application.launch(PlaceTowers.class);
            }
        };
        t.setDaemon(true);
        t.start();
        Thread.sleep(500);
    }

    /* M3 - Checks if tower is not on tower.
    If the boolean value returned is False, then the new tower should always be placed.
    The new tower cannot have its location (xval, yval) in the location of another tower.
    */
    @Test
    public void towerIsNotOnTowerTest() {
        assertFalse("Null array was passed in successfully",
                PlaceTowers.isTowerOnTower(null, null));
        ArrayList<Tower> t = new ArrayList<>();
        Tower.setLevel(1);
        Tower1 t1 = new Tower1();
        t1.setXVal(120);
        t1.setYVal(150);
        ImageView i = t1.draw();

        assertFalse("if current towers null, new tower should always be placed",
                PlaceTowers.isTowerOnTower(null, i));

        Tower2 t2 = new Tower2();
        t2.setXVal(0);
        t2.setYVal(0);
        t2.setImageView(t2.draw());

        Tower3 t3 = new Tower3();
        t3.setXVal(700);
        t3.setYVal(700);
        t3.setImageView(t2.draw());

        Tower2 t4 = new Tower2();
        t4.setXVal(1000);
        t4.setYVal(1000);
        t3.setImageView(t2.draw());

        t.add(t2);
        t.add(t3);
        t.add(t4);
        assertFalse("Was able to place two towers on top of each other",
                PlaceTowers.isTowerOnTower(t, i));
    }

    /* M3 - Checks if tower is on tower.
    If the current tower returns True, then the new tower should not be placed.
    The new tower cannot have its location (xval, yval) on another tower.
    */
    @Test
    public void towerIsOnTowerTest() {
        assertFalse("Null array was passed in successfully",
                PlaceTowers.isTowerOnTower(null, null));
        ArrayList<Tower> t = new ArrayList<>();
        Tower.setLevel(1);
        Tower1 t1 = new Tower1();
        t1.setXVal(120);
        t1.setYVal(150);
        ImageView i = t1.draw();

        Tower2 t2 = new Tower2();
        t2.setXVal(0);
        t2.setYVal(0);
        t2.setImageView(t2.draw());

        Tower3 t3 = new Tower3();
        t3.setXVal(120);
        t3.setYVal(150);
        t3.setImageView(t3.draw());

        t.add(t2);
        t.add(t3);
        assertTrue("Not asserted even though towers are touching",
                PlaceTowers.isTowerOnTower(t, i));
    }

    /* M3 - Checks if tower is on path.
    Asserts whether a tower is on path or not.
    The tower cannot be placed on top (above) the path.
    */
    @Test
    public void towerIsOnPathTest() {
        assertFalse("Null array was passed in successfully",
                PlaceTowers.isTowerOnTower(null, null));
        ArrayList<Rectangle> p = new ArrayList<>();
        Rectangle path1 = new Rectangle(0, 0, 50, 50);
        p.add(path1);
        Rectangle path2 = new Rectangle(200, 200, 400, 400);
        p.add(path2);

        Tower.setLevel(1);
        Tower1 t1 = new Tower1();
        t1.setXVal(10);
        t1.setYVal(10);
        ImageView i = t1.draw();

        assertTrue("Not asserted even though tower is touching path",
                PlaceTowers.isTowerOnPath(p, i));
    }

    /* M3 - Checks if tower is not on path.
    Asserts whether a tower is on path or not.
    The tower cannot be placed on top (above) the path.
    */
    @Test
    public void towerIsNotOnPathTest() {
        assertFalse("Null array was passed in successfully",
                PlaceTowers.isTowerOnTower(null, null));
        ArrayList<Rectangle> p = new ArrayList<>();
        Rectangle path1 = new Rectangle(0, 0, 50, 50);
        p.add(path1);
        Rectangle path2 = new Rectangle(200, 200, 10, 10);
        p.add(path2);

        Tower.setLevel(1);
        Tower1 t1 = new Tower1();
        t1.setXVal(500);
        t1.setYVal(500);
        ImageView i = t1.draw();

        assertFalse("Was able to place tower on top of path",
                PlaceTowers.isTowerOnPath(p, i));
    }
}