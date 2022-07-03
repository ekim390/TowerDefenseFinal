package test;

import javafx.application.Application;
import main.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameStartTest {
    @BeforeClass
    public static void setUpClass() throws InterruptedException {
        // Initialise Java FX
        Thread t = new Thread("JavaFX GameStart Thread") {
            public void run() {
                Application.launch(GameStart.class);
            }
        };
        t.setDaemon(true);
        t.start();
        Thread.sleep(500);
    }

    /* M4
       Checks to see that Enemy1 attacks Base/decrements health by correct value
    */
    @Test
    public void testEnemy1AttackBase() {
        Enemy e1 = new Enemy1(1175, 270);
        double orgHealth = 100;
        double e1Dps = e1.getDPS();
        Base.setHealth(orgHealth);
        e1.attackBase();
        assertEquals((orgHealth - e1Dps), Base.getHealth(), 0);
        e1.attackBase();
        assertEquals((orgHealth - 2 * e1Dps), Base.getHealth(), 0);
    }

    /* M4
       Checks to see that Enemy2 attacks Base/decrements health by correct value
     */
    @Test
    public void testEnemy2AttackBase() {
        Enemy e2 = new Enemy2(1175, 270);
        double orgHealth = 200;
        double e2Dps = e2.getDPS();
        Base.setHealth(orgHealth);
        e2.attackBase();
        assertEquals((orgHealth - e2Dps), Base.getHealth(), 0);
        e2.attackBase();
        assertEquals((orgHealth - 2 * e2Dps), Base.getHealth(), 0);
    }

    /* M4
       Checks to see that Enemy3 attacks Base/decrements health by correct value
     */
    @Test
    public void testEnemy3AttackBase() {
        Enemy e3 = new Enemy3(1175, 270);
        double orgHealth = 300;
        double e3Dps = e3.getDPS();
        Base.setHealth(orgHealth);
        e3.attackBase();
        assertEquals((orgHealth - e3Dps), Base.getHealth(), 0);
        e3.attackBase();
        assertEquals((orgHealth - 2 * e3Dps), Base.getHealth(), 0);
    }

    /* M4
       Checks to see that different enemies cause
       health to decrement by different values
     */
    @Test
    public void testEnemyAttackBaseDifferently() {
        Enemy e1 = new Enemy1(1175, 270);
        Enemy e2 = new Enemy2(1175, 270);
        Enemy e3 = new Enemy3(1175, 270);

        double e1Dps = e1.getDPS();
        double e2Dps = e2.getDPS();
        double e3Dps = e3.getDPS();
        assertNotEquals(e1Dps, e2Dps, 0);
        assertNotEquals(e1Dps, e3Dps, 0);
        assertNotEquals(e2Dps, e3Dps, 0);

        // enemy1 attack's remaining health
        double orgHealth = 100;
        Base.setHealth(orgHealth);
        e1.attackBase();
        double enemy1AttackRemainHP = Base.getHealth();
        // enemy2 attack's remaining health
        Base.setHealth(orgHealth);
        e2.attackBase();
        double enemy2AttackRemainHP = Base.getHealth();
        // enemy3 attack's remaining health
        Base.setHealth(orgHealth);
        e3.attackBase();
        double enemy3AttackRemainHP = Base.getHealth();

        assertNotEquals(enemy1AttackRemainHP, enemy2AttackRemainHP, 0);
        assertNotEquals(enemy1AttackRemainHP, enemy3AttackRemainHP, 0);
        assertNotEquals(enemy2AttackRemainHP, enemy3AttackRemainHP, 0);

    }

    /* M4
       Testing createEnemy(0) method to see if it correctly
       creates an enemy1 object
     */
    @Test
    public void testCreateEnemy1() {
        Enemy e1 = new Enemy1(1175, 249);

        Enemy e1test = Enemy1.createEnemy(1);

        assertEquals(e1.getXVal(), e1test.getXVal(), 0);
        assertEquals(e1.getYVal(), e1test.getYVal(), 0);

        assertEquals(e1test.getClassification(), "Yellow");
        assertTrue(e1test.getClassification().equals(e1.getClassification()));
    }

    /* M4
       Testing createEnemy(1) method to see if it correctly
       creates an enemy2 object
     */
    @Test
    public void testCreateEnemy2() {
        Enemy e2 = new Enemy2(1175, 270);

        Enemy e2test = Enemy2.createEnemy(2);

        assertEquals(e2.getXVal(), e2test.getXVal(), 0);
        assertEquals(e2.getYVal(), e2test.getYVal(), 0);

        assertEquals(e2test.getClassification(), "Green");
        assertTrue(e2test.getClassification().equals(e2.getClassification()));
    }

    /* M4
       Testing createEnemy(2) method to see if it correctly
       creates an enemy3 object
     */
    @Test
    public void testCreateEnemy3() {
        Enemy e3 = new Enemy3(1175, 295);

        Enemy e3test = Enemy3.createEnemy(3);

        assertEquals(e3.getXVal(), e3test.getXVal(), 0);
        assertEquals(e3.getYVal(), e3test.getYVal(), 0);

        assertEquals(e3test.getClassification(), "Pink");
        assertTrue(e3test.getClassification().equals(e3.getClassification()));
    }

    /* M5
       Testing to see if enemy health is different for each type of enemy
     */
    @Test
    public void testEnemyHealth() {
        Enemy e1 = new Enemy1(1175, 270);
        Enemy e2 = new Enemy2(1175, 270);
        Enemy e3 = new Enemy3(1175, 270);

        assertNotEquals(e1.getHealth(), e2.getHealth());
        assertNotEquals(e2.getHealth(), e3.getHealth());
        assertNotEquals(e1.getHealth(), e3.getHealth());

    }

    /*M4
    Checks if enemies walks in correct direction.
              _________ upper path
             |
             |middle path
    _________|
    lower path


    Creates enemy1 on the upper path.
    Checks if the enemy on the upper path correctly walks to the left
    */
    @Test
    public void testEnemyWalkUpperPath() {
        ArrayList<Enemy> listOfEnemies = new ArrayList<>();

        Enemy1 e1 = new Enemy1(1100, 250);
        listOfEnemies.add(e1);

        double initialX = listOfEnemies.get(0).getXVal();
        double initialY = listOfEnemies.get(0).getYVal();
        double walkingSpeed = listOfEnemies.get(0).getWalkingSpeed();

        ArrayList<Enemy> updatedEnemyList = GameStart.allEnemyWalk(listOfEnemies);

        double newX = updatedEnemyList.get(0).getXVal();
        double newY = updatedEnemyList.get(0).getYVal();

        //checks to see if the amount shifted is correct
        assertEquals(initialX - newX, walkingSpeed, 0);
        // checks to see if the change in X is negative, indicating shift left
        assertTrue(newX - initialX < 0);
        // checks to see if the change in Y is 0, indicating no shift up or down
        assertTrue(newY - initialY == 0);
    }

    /*M4
    Checks if enemies walks in correct direction.
              _________ upper path
             |
             |middle path
    _________|
    lower path


    Creates enemy on the middle path
    Checks if the enemy on the middle path correctly walks downwards
    */
    @Test
    public void testEnemyWalkMiddlePath() {
        ArrayList<Enemy> listOfEnemies = new ArrayList<>();
        Enemy1 e1 = new Enemy1(600, 500);
        listOfEnemies.add(e1);

        double initialX = listOfEnemies.get(0).getXVal();
        double initialY = listOfEnemies.get(0).getYVal();
        double walkingSpeed = listOfEnemies.get(0).getWalkingSpeed();

        ArrayList<Enemy> updatedEnemyList = GameStart.allEnemyWalk(listOfEnemies);

        double newX = updatedEnemyList.get(0).getXVal();
        double newY = updatedEnemyList.get(0).getYVal();

        //checks to see if the amount shifted is correct
        assertEquals(newY - initialY, walkingSpeed, 0);
        // checks to see if the change in X is negative, indicating shift down
        assertTrue(initialY - newY < 0);
        // checks to see if the change in Y is 0, indicating no shift left or right
        assertTrue(newX - initialX == 0);
    }

    /*M4
    Checks if enemies walks in correct direction.
              _________ upper path
             |
             |middle path
    _________|
    lower path


    Creates enemy1 on the lower path close to the base.
    Checks if the enemy on the lower path correctly walks left into the base and disappears.
    */
    @Test
    public void testEnemyWalkLowerPath() {
        ArrayList<Enemy> listOfEnemies = new ArrayList<>();
        Enemy1 e1 = new Enemy1(202, 570);
        listOfEnemies.add(e1);

        double initialX = listOfEnemies.get(0).getXVal();
        double initialY = listOfEnemies.get(0).getYVal();
        double walkingSpeed = listOfEnemies.get(0).getWalkingSpeed();

        ArrayList<Enemy> updatedEnemyList = GameStart.allEnemyWalk(listOfEnemies);

        double newX = updatedEnemyList.get(0).getXVal();
        double newY = updatedEnemyList.get(0).getYVal();

        //checks to see if the amount shifted is correct
        assertEquals(initialX - newX, walkingSpeed, 0);
        // checks to see if the change in X is negative, indicating shift left
        assertTrue(newX - initialX < 0);
        // checks to see if the change in Y is 0, indicating no shift down
        assertTrue(newY - initialY == 0);

        ArrayList<Enemy> updated2EnemyList = GameStart.allEnemyWalk(updatedEnemyList);

        assertEquals(updated2EnemyList.size(), 0);
        assertTrue(updated2EnemyList.isEmpty());
    }

    /* M4
       Checks to see that enemy1 is walking correctly in the left direction following the path
       and that the speed it walks at is correct
     */
    @Test
    public void testEnemy1IsWalking() {
        Enemy e1 = new Enemy1(1100, 250);

        ArrayList<Enemy> enemiesWalking = new ArrayList<>();

        enemiesWalking.add(e1);

        double initX1Pos = enemiesWalking.get(0).getXVal();
        double initY1Pos = enemiesWalking.get(0).getYVal();

        ArrayList<Enemy> newEnemiesWalking = GameStart.allEnemyWalk(enemiesWalking);

        double newX1Pos = newEnemiesWalking.get(0).getXVal();
        double newY1Pos = newEnemiesWalking.get(0).getYVal();

        assertNotEquals(initX1Pos, newX1Pos, 0);
        assertEquals(Math.abs(newX1Pos - initX1Pos), newEnemiesWalking.get(0).getWalkingSpeed(), 0);
        assertEquals(initY1Pos, newY1Pos, 0);
    }

    /* M4
       Checks to see that enemy2 is walking correctly in the left direction following the path
       and that the speed it walks at is correct
     */
    @Test
    public void testEnemy2IsWalking() {
        Enemy e2 = new Enemy2(1100, 250);

        ArrayList<Enemy> enemiesWalking = new ArrayList<>();

        enemiesWalking.add(e2);

        double initX2Pos = enemiesWalking.get(0).getXVal();
        double initY2Pos = enemiesWalking.get(0).getYVal();

        ArrayList<Enemy> newEnemiesWalking = GameStart.allEnemyWalk(enemiesWalking);

        double newX2Pos = newEnemiesWalking.get(0).getXVal();
        double newY2Pos = newEnemiesWalking.get(0).getYVal();

        assertNotEquals(initX2Pos, newX2Pos, 0);
        assertEquals(Math.abs(newX2Pos - initX2Pos), newEnemiesWalking.get(0).getWalkingSpeed(), 0);
        assertEquals(initY2Pos, newY2Pos, 0);
    }

    /* M4
       Checks to see that enemy3 is walking correctly in the left direction following the path
       and that the speed it walks at is correct
     */
    @Test
    public void testEnemy3IsWalking() {
        Enemy e3 = new Enemy3(1100, 250);

        ArrayList<Enemy> enemiesWalking = new ArrayList<>();

        enemiesWalking.add(e3);

        double initX3Pos = enemiesWalking.get(0).getXVal();
        double initY3Pos = enemiesWalking.get(0).getYVal();

        ArrayList<Enemy> newEnemiesWalking = GameStart.allEnemyWalk(enemiesWalking);

        double newX3Pos = newEnemiesWalking.get(0).getXVal();
        double newY3Pos = newEnemiesWalking.get(0).getYVal();

        assertNotEquals(initX3Pos, newX3Pos, 0);
        assertEquals(Math.abs(newX3Pos - initX3Pos), newEnemiesWalking.get(0).getWalkingSpeed(), 0);
        assertEquals(initY3Pos, newY3Pos, 0);
    }

}