package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class UpgradeController extends Application {
    private Tower currTower = GameConfig.getUpdateTower();
    private Stage gameConfig = GameConfig.getCurrStage();
    @FXML
    private Button upgradeBtn;
    @FXML
    private Label UpdateLevelB = new Label();
    @FXML
    private Label UpdateLevelA = new Label();
    @FXML
    private Label UpdatePriceB = new Label();
    @FXML
    private Label UpdatePriceA = new Label();
    @FXML
    private Label dPSB = new Label();
    @FXML
    private Label dPSA = new Label();
    @FXML
    private Label ProximityB = new Label();
    @FXML
    private Label ProximityA = new Label();
    @FXML
    private Label MoneyB = new Label();
    @FXML
    private Label MoneyA = new Label();
    @FXML
    private Label HealthB = new Label();
    @FXML
    private Label HealthA = new Label();
    @FXML
    private ImageView image = new ImageView();

    @Override
    public void start(Stage subStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Upgrade.fxml"));
        if (currTower != null) {
            initialize();
        }
        Scene scene = new Scene(root);
        subStage.setScene(scene);
        subStage.show();
    }

    public void initialize() throws Exception {
        Upgrade upgrade = currTower.getUpgrade();
        NumberFormat nf = new DecimalFormat("#####.##");
        UpdateLevelB.setText(String.valueOf(upgrade.getUpgradeLevel()));
        UpdateLevelA.setText(String.valueOf(upgrade.getUpgradeLevel() + 1));
        UpdatePriceB.setText(String.valueOf(upgrade.getUpgradePrice()));
        UpdatePriceA.setText(String.valueOf(upgrade.getUpgradePrice() * 2));
        ProximityB.setText(String.valueOf(currTower.getProximity()));
        ProximityA.setText(String.valueOf(currTower.getProximity() + 3));
        String towerType = currTower.getClassification();
        double gainedDPS = .2;
        if (towerType.equals("Blue")) {
            image.setImage(new Image("/Images/blueTower.png"));
            gainedDPS = .5;
            MoneyB.setText("N/A");
            MoneyA.setText("N/A");
            HealthB.setText("N/A");
            HealthA.setText("N/A");
        } else if (towerType.equals("Yellow")) {
            image.setImage(new Image("/Images/yellowTower.png"));
            MoneyB.setText("N/A");
            MoneyA.setText("N/A");
            HealthB.setText(String.valueOf(nf.format(currTower.getGainedHealth())));
            HealthA.setText(String.valueOf(nf.format(currTower.getGainedHealth() + .05)));
        } else if (towerType.equals("Pink")) {
            image.setImage(new Image("/Images/pinkTower.png"));
            MoneyB.setText(nf.format(currTower.getGainedMoney()));
            MoneyA.setText(nf.format(currTower.getGainedMoney() + 1));
            HealthB.setText("N/A");
            HealthA.setText("N/A");
        }
        dPSB.setText(String.valueOf(nf.format(currTower.getDPS())));
        dPSA.setText(String.valueOf(nf.format(currTower.getDPS() + gainedDPS)));
    }

    @FXML
    private void pressUpgradeButton(ActionEvent event) throws Exception {
        Alert myAlert = new Alert(Alert.AlertType.INFORMATION);
        String invalid = currTower.getUpgrade().checkInvalidUpgrade();
        if (invalid != null) {
            myAlert.setHeaderText(invalid);
            myAlert.showAndWait();
        } else {
            Player.setMoneySpent(currTower.getUpgrade().getUpgradePrice());
            currTower.upgradeAttack();

            Stage stage;
            stage = (Stage) upgradeBtn.getScene().getWindow();
            stage.close();

            GameConfig gameConfiguration = new GameConfig();
            gameConfiguration.start(gameConfig);
        }
    }

}
