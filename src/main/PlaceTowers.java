package main;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;


public class PlaceTowers extends Application {
    private Button placeBtn;
    private Button backBtn;
    private ArrayList<Tower> currentTowers;
    private static Tower newTower;
    private ImageView tempTower;
    private ArrayList<Rectangle> path = new ArrayList<>();
    private ArrayList<Rectangle> base = new ArrayList<>();

    @Override
    public void start(Stage stage) throws Exception {
        currentTowers = Map.getTowersPlaced();
        newTower = Player.getTowersOwned().get(Player.getTowersOwned().size() - 1);

        Image image = new Image("/Images/map2.png");

        ImageView imageView = new ImageView(image);
        imageView.setX(0);
        imageView.setY(0);
        imageView.setFitHeight(900);
        imageView.setFitWidth(1200);
        imageView.setPreserveRatio(true);

        int startingMoney = Player.getMoney();
        double startingHealth = Base.getHealth();

        NumberFormat nf = new DecimalFormat("#####.##");
        String moneyStr = "MONEY: " + String.valueOf(startingMoney);
        String healthStr = "HEALTH: " + String.valueOf(nf.format(startingHealth) + "hp");

        Text text = new Text();
        text.setFont(Font.font("Sinhala MN", FontWeight.BOLD, FontPosture.REGULAR, 25));
        text.setX(730);
        text.setY(105);
        text.setText(moneyStr);

        Text text2 = new Text();
        text2.setFont(Font.font("Sinhala MN", FontWeight.BOLD, FontPosture.REGULAR, 40));
        text2.setX(250);
        text2.setY(90);
        text2.setText("Place Your Tower");

        Text text3 = new Text();
        text3.setFont(Font.font("Sinhala MN", FontWeight.BOLD, FontPosture.REGULAR, 25));
        text3.setX(730);
        text3.setY(65);
        text3.setText(healthStr);

        Font f1 = Font.font("Sinhala MN", FontWeight.BOLD, 18);
        placeBtn = new Button("Place Tower");
        placeBtn.setFont(f1);
        placeBtn.setLayoutX(50);
        placeBtn.setLayoutY(45);
        placeBtn.setPrefWidth(150);
        placeBtn.setPrefHeight(60);
        placeBtn.setOnAction(event -> {
            try {
                pressPlaceBtn();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        backBtn = new Button("Go Back");
        backBtn.setFont(f1);
        backBtn.setLayoutX(1000);
        backBtn.setLayoutY(45);
        backBtn.setPrefWidth(150);
        backBtn.setPrefHeight(60);
        backBtn.setOnAction(event -> {
            try {
                pressBackBtn();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Rectangle path1 = new Rectangle(204.5, 549.5, 505.5, 66);
        path1.setFill(Color.TRANSPARENT);
        path.add(path1);
        Rectangle path2 = new Rectangle(630, 250, 80, 300);
        path2.setFill(Color.TRANSPARENT);
        path.add(path2);
        Rectangle path3 = new Rectangle(709, 250, 491, 74.75);
        path3.setFill(Color.TRANSPARENT);
        path.add(path3);
        Rectangle base1 = new Rectangle(118, 370, 86.5, 360);
        base1.setFill(Color.TRANSPARENT);
        base.add(base1);
        Rectangle base2 = new Rectangle(135, 340, 60, 30);
        base2.setFill(Color.TRANSPARENT);
        base.add(base2);
        Rectangle base3 = new Rectangle(150, 310, 30, 30);
        base3.setFill(Color.TRANSPARENT);
        base.add(base3);
        Rectangle base4 = new Rectangle(160, 275, 19, 35);
        base4.setFill(Color.TRANSPARENT);
        base.add(base4);

        newTower.setXVal(0);
        newTower.setYVal(0);
        tempTower = newTower.draw();
        tempTower.toFront();
        double[] delta = {0, 0};
        tempTower.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                // record a delta distance for the drag and drop operation.
                delta[0] = tempTower.getX() - mouseEvent.getScreenX();
                delta[1] = tempTower.getY() - mouseEvent.getScreenY();
            }
        });
        tempTower.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent mouseEvent) {
                tempTower.setX(mouseEvent.getScreenX() + delta[0]);
                tempTower.setY(mouseEvent.getScreenY() + delta[1]);
            }
        });
        tempTower.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                tempTower.setX(mouseEvent.getScreenX() + delta[0]);
                tempTower.setY(mouseEvent.getScreenY() + delta[1]);
                newTower.setXVal(mouseEvent.getScreenX() + delta[0]);
                newTower.setYVal(mouseEvent.getScreenY() + delta[1]);
            }
        });

        Group root = new Group(imageView, text, text2, text3, path1, path2,
                path3, base1, base2, base3, base4, placeBtn, backBtn, tempTower);

        if (currentTowers != null) {
            for (int i = 0; i < currentTowers.size(); i++) {
                Tower curr = currentTowers.get(i);
                root.getChildren().add(curr.getImageView());
            }
        }

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setX(150);
        stage.setY(0);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void pressPlaceBtn() throws Exception {
        boolean onTower = isTowerOnTower(currentTowers, tempTower);
        boolean onBase = isTowerOnBase(base, tempTower);
        boolean onPath = isTowerOnPath(path, tempTower);
        Alert myAlert = new Alert(Alert.AlertType.ERROR);
        myAlert.setResizable(true);
        if (onTower) {
            myAlert.setHeaderText("A Tower has already been placed here. "
                    + "Please move Tower.");
            myAlert.showAndWait();
        } else if (onBase) {
            myAlert.setHeaderText("Tower cannot be placed on base. "
                    + "Please move Tower");
            myAlert.showAndWait();
        } else if (onPath) {
            myAlert.setHeaderText("Tower cannot be placed on path. "
                    + "Please move Tower");
            myAlert.showAndWait();
        } else {
            Player.setMoneySpent(newTower.getPrice());
            placeTower();
            Stage stage;
            stage = (Stage) placeBtn.getScene().getWindow();
            GameConfig gameConfig = new GameConfig();
            gameConfig.start(stage);
        }
    }

    public void pressBackBtn() throws Exception {
        Player.setMoney(Player.getMoney() + newTower.getPrice());
        Stage myStage;
        myStage = (Stage) backBtn.getScene().getWindow();
        Shop shop = new Shop();
        shop.start(myStage);
    }

    public static boolean isTowerOnTower(ArrayList<Tower> curr, ImageView imageT) {
        boolean onTower = false;
        if (curr == null) {
            onTower = false;
        } else {
            for (int i = 0; i < curr.size(); i++) {
                if (imageT.intersects(curr.get(i).getImageView().getBoundsInLocal())) {
                    onTower = true;
                    break;
                }
            }
        }
        return onTower;
    }

    public static boolean isTowerOnBase(ArrayList<Rectangle> base, ImageView imageT) {
        boolean onBase = false;
        for (int i = 0; i < base.size(); i++) {
            if (imageT.intersects(base.get(i).getBoundsInLocal())) {
                onBase = true;
                break;
            }
        }
        return onBase;
    }

    public static boolean isTowerOnPath(ArrayList<Rectangle> path, ImageView imageT) {
        boolean onBase = false;
        for (int i = 0; i < path.size(); i++) {
            if (imageT.intersects(path.get(i).getBoundsInLocal())) {
                onBase = true;
                break;
            }
        }
        return onBase;
    }

    public void placeTower() {
        tempTower.setDisable(true);
        newTower.setImageView(tempTower);
        Map.addTower(newTower);
    }
}
