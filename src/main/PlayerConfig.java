package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.util.ArrayList;

public class PlayerConfig extends Application {
    @FXML
    private TextField charName;
    @FXML
    private ComboBox<Integer> dif;
    @FXML
    private Button beginBtn;

    /**
     * Player Configuration screen using Javafx
     * @param stage stage
     * @throws Exception handler
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/PlayerConfig.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    public static void startingMoney(Integer i) {
        int money;
        if (i.equals(1)) {
            money = 1200;
        } else if (i.equals(2)) {
            money = 900;
        } else {
            money = 600;
        }
        Player.setMoney(money);
    }

    public static void startingHealth(Integer i) {
        double health;
        if (i.equals(1)) {
            health = 500;
        } else if (i.equals(2)) {
            health = 400;
        } else {
            health = 300;
        }
        Base.setHealth(health);
        Base.setInitialHealth(health);
    }

    public static String checkName(String name) {
        String invalid = null;
        if (name.isEmpty()
                || (name.trim().length() == 0)
                || (name == null)) {
            invalid = "Invalid name";
        }
        return invalid;
    }

    public static String checkDifficulty(Integer dif) {
        String invalid = null;
        if (dif == null) {
            invalid = "Must choose a difficulty";
        }
        return invalid;
    }

    @FXML
    private void pressBeginButton(ActionEvent event) throws Exception {
        Alert myAlert = new Alert(Alert.AlertType.ERROR);
        String invalidName = checkName(charName.getText());
        String invalidDiff = checkDifficulty(dif.getValue());
        if (invalidName != null) {
            myAlert.setHeaderText(invalidName);
            myAlert.showAndWait();
        } else if (invalidDiff != null) {
            myAlert.setHeaderText(invalidDiff);
            myAlert.showAndWait();
        } else {
            Player.setName(charName.getText());
            Player.setLevel(dif.getValue());
            Map.setRound(1);
            Map.setTowersPlaced(new ArrayList<>());
            Tower.setLevel(dif.getValue());
            startingMoney(dif.getValue());
            startingHealth(dif.getValue());
            Stage stage;
            stage = (Stage) beginBtn.getScene().getWindow();
            GameConfig gameConfigScreen = new GameConfig();
            gameConfigScreen.start(stage);
        }
    }
}
