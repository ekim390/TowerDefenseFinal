package main;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Welcome controller for welcome UI
 */
public class Welcome extends Application {
    @FXML
    private Button startBtn;
    @FXML
    private Button endBtn;

    /**
     * Start for javafx screen
     * @param stage stage
     * @throws Exception handler
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Welcome.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    @FXML
    private void pressStartButton(ActionEvent event) throws Exception {
        Stage stage;
        stage = (Stage) startBtn.getScene().getWindow();
        PlayerConfig configScreen = new PlayerConfig();
        configScreen.start(stage);
    }

    @FXML
    private void pressEndButton(ActionEvent event) throws Exception {
        Stage stage;

        stage = (Stage) endBtn.getScene().getWindow();
        stage.close();

    }
    public static void main(String[] args) {
        launch(args);
    }
}