package main;

import java.util.ArrayList;

public class Player {
    private static String name;
    private static int level;
    private static int money;
    private static int moneySpent = 0;
    private static int enemiesKilled = 0;
    private static ArrayList<Tower> towersOwned = new ArrayList<>();

    public static void setName(String n) {
        name = n;
    }
    public static String getName() {
        return name;
    }

    public static void setLevel(Integer l) {
        level = l;
    }
    public static int getLevel() {
        return level;
    }

    public static void setMoney(int m) {
        money = m;
    }
    public static int getMoney() {
        return money;
    }

    public static void setEnemiesKilled() {
        enemiesKilled++;
    }
    public static int getEnemiesKilled() {
        return enemiesKilled;
    }

    public static void setMoneySpent(int m) {
        moneySpent += m;
    }
    public static int getMoneySpent() {
        return moneySpent;
    }

    public static void setTowersOwned(Tower t) {
        towersOwned.add(t);
    }
    public static ArrayList<Tower> getTowersOwned() {
        return towersOwned;
    }

    public static void purchaseTower(Tower t) {
        money = Player.getMoney() - t.getPrice();
        towersOwned.add(t);
    }

    public static void upgradeTower(int upgradePrice) {
        money = Player.getMoney() - upgradePrice;
    }
}