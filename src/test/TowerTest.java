package test;

import javafx.application.Application;
import main.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TowerTest {
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

    /* M5 - Checks distanceCalculator returns the right result.
    If the boolean value returned is False, then the distance returned is not correct.
    The distance yielded by the method should be equal to the actual one.
    */
    @Test
    public void distCalculatorTest() {
        Enemy myEnemy = new Enemy1(20, 20);
        Tower myTow = new Tower1();
        myTow.setXVal(10);
        myTow.setYVal(10);
        assertEquals(myTow.distCalculator(myEnemy), 10 * Math.sqrt(2),
                (Math.abs(myTow.distCalculator(myEnemy) - 10 * Math.sqrt(2))));
    }

    /* M5 - Checks testClosestEnemy returns the closest enemy from the
    current Tower1.
    */
    @Test
    public void testTower1ClosestEnemy() {
        Tower t1 = new Tower1();
        t1.setXVal(10);
        t1.setYVal(10);

        ArrayList<Enemy> listOfEnemies = new ArrayList<>();

        Enemy e1 = new Enemy1(30, 30);
        listOfEnemies.add(e1);
        Enemy e2 = new Enemy2(200, 200);
        listOfEnemies.add(e2);
        Enemy e3 = new Enemy3(400, 20);
        listOfEnemies.add(e3);

        Enemy closestEnemy;
        closestEnemy = t1.closestEnemy(listOfEnemies);

        assertEquals(closestEnemy, e1);

        Enemy e4 = new Enemy1(15, 15);
        listOfEnemies.add(e4);
        closestEnemy = t1.closestEnemy(listOfEnemies);

        assertEquals(closestEnemy, e4);
    }

    /* M5 - Checks testClosestEnemy returns the closest enemy from the
    current Tower2.
    */
    @Test
    public void testTower2ClosestEnemy() {
        Tower t2 = new Tower2();
        t2.setXVal(50);
        t2.setYVal(50);

        ArrayList<Enemy> listOfEnemies = new ArrayList<>();

        Enemy e1 = new Enemy1(10, 10);
        listOfEnemies.add(e1);
        Enemy e2 = new Enemy2(200, 200);
        listOfEnemies.add(e2);
        Enemy e3 = new Enemy3(400, 20);
        listOfEnemies.add(e3);

        Enemy closestEnemy;
        closestEnemy = t2.closestEnemy(listOfEnemies);

        assertEquals(closestEnemy, e1);

        Enemy e4 = new Enemy1(45, 45);
        listOfEnemies.add(e4);
        closestEnemy = t2.closestEnemy(listOfEnemies);

        assertEquals(closestEnemy, e4);
    }

    /* M5 - Checks testClosestEnemy returns the closest enemy from the
    current Tower3.
    */
    @Test
    public void testTower3ClosestEnemy() {
        Tower t3 = new Tower3();
        t3.setXVal(100);
        t3.setYVal(100);

        ArrayList<Enemy> listOfEnemies = new ArrayList<>();

        Enemy e1 = new Enemy1(90, 95);
        listOfEnemies.add(e1);
        Enemy e2 = new Enemy2(200, 200);
        listOfEnemies.add(e2);
        Enemy e3 = new Enemy3(400, 20);
        listOfEnemies.add(e3);

        Enemy closestEnemy;
        closestEnemy = t3.closestEnemy(listOfEnemies);

        assertEquals(closestEnemy, e1);

        Enemy e4 = new Enemy1(99, 99);
        listOfEnemies.add(e4);
        closestEnemy = t3.closestEnemy(listOfEnemies);

        assertEquals(closestEnemy, e4);
    }

    /*M5
    Checks if Tower1 does damage correctly to Enemy1 type Enemy
    */
    @Test
    public void testTower1AttackEnemy1() {
        Tower t1 = new Tower1();
        Enemy e1 = new Enemy1(52, 52);
        e1.draw();

        double originalHealth = e1.getHealth();

        t1.setXVal(50);
        t1.setYVal(50);
        t1.createAttackObject(e1);

        boolean attacked;
        attacked = t1.attackEnemy(e1);

        assertEquals(attacked, true);
        assertEquals(e1.getHealth(), originalHealth - t1.getDPS(), 0);
    }

    /*M5
    Checks if Tower1 does damage correctly to Enemy2 type Enemy
    */
    @Test
    public void testTower1AttackEnemy2() {
        Tower t1 = new Tower1();
        Enemy e2 = new Enemy2(52, 52);
        e2.draw();

        double originalHealth = e2.getHealth();

        t1.setXVal(50);
        t1.setYVal(50);
        t1.createAttackObject(e2);

        boolean attacked;
        attacked = t1.attackEnemy(e2);

        assertEquals(attacked, true);
        assertEquals(e2.getHealth(), originalHealth - t1.getDPS(), 0);
    }

    /*M5
    Checks if Tower1 does damage correctly to Enemy3 type Enemy
    */
    @Test
    public void testTower1AttackEnemy3() {
        Tower t1 = new Tower1();
        Enemy e3 = new Enemy3(52, 52);
        e3.draw();

        double originalHealth = e3.getHealth();

        t1.setXVal(50);
        t1.setYVal(50);
        t1.createAttackObject(e3);

        boolean attacked;
        attacked = t1.attackEnemy(e3);

        assertEquals(attacked, true);
        assertEquals(e3.getHealth(), originalHealth - t1.getDPS(), 0);
    }

    /* M5
    Checks to see if Tower2 does damage to Enemy1 and heals the base
    */
    @Test
    public void testTower2AttackEnemy1() {
        Tower t2 = new Tower2();
        Enemy e1 = new Enemy1(52, 52);
        e1.draw();

        double originalEnemyHealth = e1.getHealth();
        double originalBaseHealth = Base.getHealth();

        t2.setXVal(50);
        t2.setYVal(50);
        t2.createAttackObject(e1);

        boolean attacked;
        attacked = t2.attackEnemy(e1);

        assertEquals(attacked, true);
        assertEquals(e1.getHealth(), originalEnemyHealth - t2.getDPS(), 0);

        int i = 2;
        while (i <= 5) {
            attacked = t2.attackEnemy(e1);
            i++;
        }

        assertEquals(Base.getHealth(), originalBaseHealth + 0.05, 0);
    }

    /* M5
    Checks to see if Tower2 does damage to Enemy2 and heals the base
    */
    @Test
    public void testTower2AttackEnemy2() {
        Tower t2 = new Tower2();
        Enemy e2 = new Enemy2(52, 52);
        e2.draw();

        double originalEnemyHealth = e2.getHealth();
        double originalBaseHealth = Base.getHealth();

        t2.setXVal(50);
        t2.setYVal(50);
        t2.createAttackObject(e2);

        boolean attacked;
        attacked = t2.attackEnemy(e2);

        assertEquals(attacked, true);
        assertEquals(e2.getHealth(), originalEnemyHealth - t2.getDPS(), 0);

        int i = 2;
        while (i <= 5) {
            attacked = t2.attackEnemy(e2);
            i++;
        }

        assertEquals(Base.getHealth(), originalBaseHealth + 0.05, 0);
    }

    /* M5
    Checks to see if Tower2 does damage to Enemy3 and heals the base
    */
    @Test
    public void testTower2AttackEnemy3() {
        Tower t2 = new Tower2();
        Enemy e3 = new Enemy3(52, 52);
        e3.draw();

        double originalEnemyHealth = e3.getHealth();
        double originalBaseHealth = Base.getHealth();

        t2.setXVal(50);
        t2.setYVal(50);
        t2.createAttackObject(e3);

        boolean attacked;
        attacked = t2.attackEnemy(e3);

        assertEquals(attacked, true);
        assertEquals(e3.getHealth(), originalEnemyHealth - t2.getDPS(), 0);

        int i = 2;
        while (i <= 5) {
            attacked = t2.attackEnemy(e3);
            i++;
        }

        assertEquals(Base.getHealth(), originalBaseHealth + 0.05, 0);
    }

    /* M5
    Checks to see if Tower3 does damage to Enemy1 and increases money
    for every 20 times Tower3 successfully attacks Enemy1
    */
    @Test
    public void testTower3AttackEnemy1() {
        Tower t3 = new Tower3();
        Enemy e1 = new Enemy1(52, 52);
        e1.draw();

        double originalHealth = e1.getHealth();
        double originalMoney = Player.getMoney();

        t3.setXVal(50);
        t3.setYVal(50);
        t3.createAttackObject(e1);

        boolean attacked;
        attacked = t3.attackEnemy(e1);

        assertEquals(attacked, true);
        assertEquals(Player.getMoney(), originalMoney, 0);
        assertEquals(e1.getHealth(), originalHealth - t3.getDPS(), 0);

        int i = 2;
        while (i <= 20) {
            attacked = t3.attackEnemy(e1);
            i++;
        }

        assertEquals(attacked, true);
        assertEquals(e1.getHealth(), originalHealth - (t3.getDPS() * 20), .000001);
        assertEquals(Player.getMoney(), originalMoney + 1, 0);
    }

    /* M5
    Checks to see if Tower3 does damage to Enemy2 and increases money
    for every 20 times Tower3 successfully attacks Enemy2
    */
    @Test
    public void testTower3AttackEnemy2() {
        Tower t3 = new Tower3();
        Enemy e2 = new Enemy2(52, 52);
        e2.draw();

        double originalHealth = e2.getHealth();
        double originalMoney = Player.getMoney();

        t3.setXVal(50);
        t3.setYVal(50);
        t3.createAttackObject(e2);

        boolean attacked;
        attacked = t3.attackEnemy(e2);

        assertEquals(attacked, true);
        assertEquals(Player.getMoney(), originalMoney, 0);
        assertEquals(e2.getHealth(), originalHealth - t3.getDPS(), 0);

        int i = 2;
        while (i <= 20) {
            attacked = t3.attackEnemy(e2);
            i++;
        }

        assertEquals(attacked, true);
        assertEquals(e2.getHealth(), originalHealth - (t3.getDPS() * 20), .000001);
        assertEquals(Player.getMoney(), originalMoney + 1, 0);
    }

    /* M5
    Checks to see if Tower3 does damage to Enemy3 and increases money
    for every 20 times Tower3 successfully attacks Enemy3
    */
    @Test
    public void testTower3AttackEnemy3() {
        Tower t3 = new Tower3();
        Enemy e3 = new Enemy3(52, 52);
        e3.draw();

        double originalHealth = e3.getHealth();
        double originalMoney = Player.getMoney();

        t3.setXVal(50);
        t3.setYVal(50);
        t3.createAttackObject(e3);

        boolean attacked;
        attacked = t3.attackEnemy(e3);

        assertEquals(attacked, true);
        assertEquals(Player.getMoney(), originalMoney, 0);
        assertEquals(e3.getHealth(), originalHealth - t3.getDPS(), 0);

        int i = 2;
        while (i <= 20) {
            attacked = t3.attackEnemy(e3);
            i++;
        }

        assertEquals(attacked, true);
        assertEquals(e3.getHealth(), originalHealth - (t3.getDPS() * 20), .000001);
        assertEquals(Player.getMoney(), originalMoney + 1, 0);
    }

    /* M6
    Checks if method upgradeAttack() correctly increases the stats of Tower 1
    DPS and proximity
    */
    @Test
    public void testUpgradeAttackTower1() {
        Tower curr = new Tower1();
        double proximity = curr.getProximity();
        double dps = curr.getDPS();
        curr.upgradeAttack();

        assertNotEquals(proximity, curr.getProximity());
        assertNotEquals(dps, curr.getDPS());
        assertEquals(proximity + 3, curr.getProximity(), 0);
        assertEquals(dps + .3, curr.getDPS(), 0);
    }

    /* M6
    Checks if method upgradeAttack() correctly increases the stats of Tower 2
    DPS, gainedHealth, and proximity
    */
    @Test
    public void testUpgradeAttackTower2() {
        Tower curr = new Tower2();
        double proximity = curr.getProximity();
        double dps = curr.getDPS();
        double gainedHealth = curr.getGainedHealth();
        curr.upgradeAttack();

        assertNotEquals(proximity, curr.getProximity());
        assertNotEquals(dps, curr.getDPS());
        assertNotEquals(gainedHealth, curr.getGainedHealth(), 0);
        assertEquals(proximity + 3, curr.getProximity(), 0);
        assertEquals(dps + .2, curr.getDPS(), 0);
        assertEquals(gainedHealth + .05, curr.getGainedHealth(), 0);
    }

    /* M6
    Checks if method upgradeAttack() correctly increases the stats of Tower 3
    DPS, gainedMoney, and proximity
    */
    @Test
    public void testUpgradeAttackTower3() {
        Tower curr = new Tower3();
        double proximity = curr.getProximity();
        double dps = curr.getDPS();
        int gainedMoney = curr.getGainedMoney();
        curr.upgradeAttack();

        assertNotEquals(proximity, curr.getProximity());
        assertNotEquals(dps, curr.getDPS());
        assertNotEquals(gainedMoney, curr.getGainedMoney());
        assertEquals(proximity + 3, curr.getProximity(), 0);
        assertEquals(dps + .2, curr.getDPS(), 0);
        assertEquals(gainedMoney + 1, curr.getGainedMoney());
    }

}