package main;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import java.util.ArrayList;


public class Shop extends Application {
    private ArrayList<Tower> towerForSale = new ArrayList<>();
    private static Tower newTower;
    private int select = -1;
    @FXML
    private Label moneyLabel;
    @FXML
    private CheckBox tower1Check;
    @FXML
    private CheckBox tower2Check;
    @FXML
    private CheckBox tower3Check;
    @FXML
    private Button purchaseBtn;
    @FXML
    private Button leaveBtn;
    @FXML
    private Label moneyL;
    @FXML
    private Label price1L;
    @FXML
    private Label price2L;
    @FXML
    private Label price3L;

    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Shop.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Shop");
        stage.setScene(scene);
        stage.alwaysOnTopProperty();
        stage.show();
    }

    @FXML
    private void pressPurchaseButton(ActionEvent event) throws Exception {
        Alert myAlert = new Alert(Alert.AlertType.INFORMATION);
        String invalid = checkInvalidPurchase(select, towerForSale.get(select));
        if (invalid != null) {
            myAlert.setHeaderText(invalid);
            myAlert.showAndWait();
        } else {
            if (towerForSale.get(select).getPrice() <= Player.getMoney()) {
                Player.purchaseTower(towerForSale.get(select));
                Stage stage;
                stage = (Stage) purchaseBtn.getScene().getWindow();
                PlaceTowers placeTowersScreen = new PlaceTowers();
                placeTowersScreen.start(stage);
            }
        }
    }

    @FXML
    private void pressLeaveButton(ActionEvent event) throws Exception {
        Stage goBack;
        goBack = (Stage) leaveBtn.getScene().getWindow();
        GameConfig gameScreen = new GameConfig();
        gameScreen.start(goBack);
    }
    @FXML
    private void pressCheckBox1(ActionEvent event) throws Exception {
        tower1Check.setSelected(true);
        tower2Check.setSelected(false);
        tower3Check.setSelected(false);
        select = 0;
    }
    @FXML
    private void pressCheckBox2(ActionEvent event) throws Exception {
        tower1Check.setSelected(false);
        tower2Check.setSelected(true);
        tower3Check.setSelected(false);
        select = 1;
    }
    @FXML
    private void pressCheckBox3(ActionEvent event) throws Exception {
        tower1Check.setSelected(false);
        tower2Check.setSelected(false);
        tower3Check.setSelected(true);
        select = 2;
    }

    public void initialize() {
        towerForSale.add(new Tower1());
        towerForSale.add(new Tower2());
        towerForSale.add(new Tower3());
        moneyL.setText(String.valueOf(Player.getMoney()));
        price1L.setText(String.valueOf(towerForSale.get(0).getPrice()));
        price2L.setText(String.valueOf(towerForSale.get(1).getPrice()));
        price3L.setText(String.valueOf(towerForSale.get(2).getPrice()));
    }

    public static String checkInvalidPurchase(int select, Tower t) {
        String invalid = null;
        if (select == -1) {
            invalid = "Invalid Tower. Please select tower you want to purchase!";
        } else if (t.getPrice() > Player.getMoney()) {
            invalid = "Not enough money. Please select a tower that you can afford!";
        }
        return invalid;
    }

    public static void main(String[] args) {
        launch(args);
    }
}