package main;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * EndGame controller for EndGame UI
 */
public class EndGame extends Application {
    @FXML
    private Button tryAgainBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private Label moneySpent;
    @FXML
    private Label enemyKilled;
    @FXML
    private Label baseHealth;

    /**
     * Start for javafx screen
     * @param stage stage
     * @throws Exception handler
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/EndGame.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }
    public void initialize() {
        moneySpent.setText(String.valueOf(Player.getMoneySpent()));
        baseHealth.setText(String.valueOf((int) Base.getHealth()));
        enemyKilled.setText(String.valueOf(Player.getEnemiesKilled()));
    }

    @FXML
    private void pressTryAgainButton(ActionEvent event) throws Exception {
        Stage stage;
        stage = (Stage) tryAgainBtn.getScene().getWindow();
        Welcome welcomeScreen = new Welcome();
        welcomeScreen.start(stage);
    }

    @FXML
    private void pressExitGameButton(ActionEvent event) throws Exception {
        Stage stage;

        stage = (Stage) exitBtn.getScene().getWindow();
        stage.close();

    }
    public static void main(String[] args) {
        launch(args);
    }
}