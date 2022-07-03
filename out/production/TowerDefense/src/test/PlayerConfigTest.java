package test;
import main.Base;
import main.PlayerConfig;
import main.Player;
import org.junit.Test;
import static org.junit.Assert.*;
public class PlayerConfigTest {

    /* M2 - Entering Invalid Player Name gives Correct Alert.
    Entering a null string in the text field for "Name" gives an error.
    Entering a string with only multiple empty spaces in the text field for "Name" gives an error
    Part of M2 Implementation requirements for the player configuration on the "Welcome" screen
    */
    @Test
    public void testNameInputValid() {
        String invalid;
        String n = "";
        invalid = PlayerConfig.checkName(n);
        assertEquals("Incorrect name was entered but never caught",
                invalid, "Invalid name");
        n = "  ";
        invalid = PlayerConfig.checkName(n);
        assertEquals("Incorrect name was entered but never caught",
                invalid, "Invalid name");
    }

    /* M2 - Player starting money is different based on difficulty levels.
    Difficulty Level of 1 allows the player to start with $2000.
    Difficulty level of 2 allows the player to start with $1500.
    Difficulty Level of 3 allows the player to start with $1000.
    Checks to see that the starting money in the Player class is
    different when inputting different difficulty levels.
    */
    @Test
    public void testDifficultyCostDifferent() {
        PlayerConfig.startingMoney(1);
        assertEquals("Expected money was not equal to actual money",
                Player.getMoney(), 2000);
        int lev1Money = Player.getMoney();

        PlayerConfig.startingMoney(2);
        assertEquals("Expected money was not equal to actual money",
                Player.getMoney(), 1500);
        int lev2Money = Player.getMoney();

        PlayerConfig.startingMoney(3);
        assertEquals("Expected money was not equal to actual money",
                Player.getMoney(), 1000);
        int lev3Money = Player.getMoney();
        assertTrue(lev1Money != lev2Money);
        assertTrue(lev1Money != lev3Money);
        assertTrue(lev2Money != lev3Money);

    }

    /* M2 - Player starting health is different based on difficulty levels
    Difficulty level of 1 allows the player to start with 500 health
    Difficulty level of 2 allows the player to start with 400 health
    Difficulty level of 3 allows the player to start with 300 health
    Checks to see that the starting health in the Base class is
    different when inputting different difficulty levels.
    */
    @Test
    public void testDifficultyHealthDifferent1() {
        PlayerConfig.startingHealth(1);
        assertEquals("Expected health was not equal to actual health",
                Base.getHealth(), 500, 0);
        double lev1Health = Base.getHealth();

        PlayerConfig.startingHealth(2);
        assertEquals("Expected health was not equal to actual health",
                Base.getHealth(), 400, 0);
        double lev2Health = Base.getHealth();

        PlayerConfig.startingHealth(3);
        assertEquals("Expected health was not equal to actual health",
                Base.getHealth(), 300, 0);
        double lev3Health = Base.getHealth();

        assertTrue(lev1Health != lev2Health);
        assertTrue(lev1Health != lev3Health);
        assertTrue(lev2Health != lev3Health);
    }

    /* M2 - Entering null difficulty gives alert.
    Causes method to return "Must choose a difficulty"
    */
    @Test
    public void testDifficultyIsNull() {
        assertEquals("Player difficulty was set to null",
                PlayerConfig.checkDifficulty(null), "Must choose a difficulty");
    }

}

