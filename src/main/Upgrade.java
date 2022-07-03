package main;

public class Upgrade {
    private int upgradePrice;
    private int upgradeLevel;

    public void setUpgradePrice(int u) {
        upgradePrice = u;
    }
    public int getUpgradePrice() {
        return upgradePrice;
    }
    public void setUpgradeLevel(int u) {
        upgradeLevel = u;
    }
    public int getUpgradeLevel() {
        return upgradeLevel;
    }

    public Upgrade() {
        upgradeLevel = 0;
        upgradePrice = 30;
    }

    public void upgrade() {
        Player.upgradeTower(upgradePrice);
        upgradePrice *= 2;
        upgradeLevel += 1;
    }

    public String checkInvalidUpgrade() {
        String invalid = null;
        if (Player.getMoney() < upgradePrice) {
            invalid = "Not Enough Money. Cannot Purchase Upgrade for Tower.";
        }
        return invalid;
    }
}
