package test;
import javafx.application.Application;
import main.*;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class EnemyTest {
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

    /* M5
    Checks to see if Tower1, Tower2 and Tower3 are in proximity to Enemy1
    */
    @Test
    public void testEnemy1Proximity() {

        Enemy e1 = new Enemy1(1175, 270);

        // Tower1 not in proximity of Enemy1
        Tower1 t1 = new Tower1();
        t1.setXVal(120);
        t1.setYVal(150);
        assertFalse("enemy is too far to attack", t1.enemyInProximity(e1));

        // Tower2 not in proximity of Enemy1
        Tower2 t2 = new Tower2();
        t2.setXVal(500);
        t2.setYVal(500);
        assertFalse("enemy is too far to attack", t2.enemyInProximity(e1));

        // Tower3 in proximity of Enemy1
        Tower3 t3 = new Tower3();
        t3.setXVal(1100);
        t3.setYVal(200);
        assertTrue("enemy is close enough to attack", t3.enemyInProximity(e1));
    }

    /* M5
    Checks to see if Tower1, Tower2 and Tower3 are in proximity to Enemy2
    */
    @Test
    public void testEnemy2Proximity() {
        Enemy e2 = new Enemy2(1175, 270);

        // Tower1 not in proximity of Enemy2
        Tower1 t1 = new Tower1();
        t1.setXVal(120);
        t1.setYVal(150);
        assertFalse("enemy is too far to attack", t1.enemyInProximity(e2));

        // Tower2 not in proximity of Enemy2
        Tower2 t2 = new Tower2();
        t2.setXVal(500);
        t2.setYVal(500);
        assertFalse("enemy is too far to attack", t2.enemyInProximity(e2));

        // Tower3 in proximity of Enemy2
        Tower3 t3 = new Tower3();
        t3.setXVal(1100);
        t3.setYVal(200);
        assertTrue("enemy is close enough to attack", t3.enemyInProximity(e2));
    }

    /* M5
    Checks to see if Tower1, Tower2 and Tower3 are in proximity to Enemy3
    */
    @Test
    public void testEnemy3Proximity() {
        Enemy e3 = new Enemy3(1175, 270);

        // Tower1 not in proximity of Enemy3
        Tower1 t1 = new Tower1();
        t1.setXVal(120);
        t1.setYVal(150);
        assertFalse("enemy is too far to attack", t1.enemyInProximity(e3));

        // Tower2 not in proximity of Enemy3
        Tower2 t2 = new Tower2();
        t2.setXVal(500);
        t2.setYVal(500);
        assertFalse("enemy is too far to attack", t2.enemyInProximity(e3));

        // Tower3 in proximity of Enemy3
        Tower3 t3 = new Tower3();
        t3.setXVal(1100);
        t3.setYVal(200);
        assertTrue("enemy is close enough to attack", t3.enemyInProximity(e3));
    }

    /* M6
    Checks to see if Tower1, Tower2, and Tower3 are in proximity to the Final Enemy
    */
    @Test
    public void testFinalEnemyProximity() {
        Enemy ef = new FinalEnemy(1175, 250);

        // Tower1 not in proximity of Enemy3
        Tower1 t1 = new Tower1();
        t1.setXVal(120);
        t1.setYVal(150);
        assertFalse("enemy is too far to attack", t1.enemyInProximity(ef));

        // Tower2 not in proximity of Enemy3
        Tower2 t2 = new Tower2();
        t2.setXVal(500);
        t2.setYVal(500);
        assertFalse("enemy is too far to attack", t2.enemyInProximity(ef));

        // Tower3 in proximity of Enemy3
        Tower3 t3 = new Tower3();
        t3.setXVal(1100);
        t3.setYVal(200);
        assertTrue("enemy is close enough to attack", t3.enemyInProximity(ef));
    }

    /* M6
    Checks to see if the Final enemy is correctly created created in round 5
    */
    @Test
    public void testFinalEnemySpawns() {
        Enemy cE = Enemy.createEnemy(2, 5);
        boolean b = cE instanceof FinalEnemy;
        assertTrue(b);
    }


}