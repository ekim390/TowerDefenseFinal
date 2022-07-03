package main;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.animation.AnimationTimer;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class GameStart extends Application {
    private Button endBtn;
    private static ArrayList<Tower> currentTowers;
    private static Stage newStage;
    private static ArrayList<Enemy> currentEnemies;
    private static Group root = new Group();
    private int round;
    /**
     * Game screen using Javafx
     * @param stage stage
     * @throws Exception handler
     */
    @Override
    public void start(Stage stage) throws Exception {
        newStage = stage;
        currentTowers = Map.getTowersPlaced();
        currentEnemies = new ArrayList<>();
        round = Map.getRound();

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

        Font fT = Font.font("Sinhala MN", FontWeight.BOLD, FontPosture.REGULAR, 25);

        Text text = new Text();
        text.setFont(fT);
        text.setX(730);
        text.setY(105);
        text.setText(moneyStr);

        Text text2 = new Text();
        text2.setFont(fT);
        text2.setX(730);
        text2.setY(65);
        text2.setText(healthStr);

        Text text3 = new Text();
        text3.setFont(Font.font("Sinhala MN", FontWeight.BOLD, FontPosture.REGULAR, 50));
        text3.setX(150);
        text3.setY(90);
        text3.setStrokeWidth(.5);

        Text text4 = new Text();
        text4.setFont(Font.font("Sinhala MN", FontWeight.BOLD, FontPosture.REGULAR, 30));
        text4.setX(60);
        text4.setY(120);

        if (round == 4) {
            text3.setText("Final Round Start!");
        } else if (round == 5) {
            text3.setText("ACTUAL Final Round Start!");
            text3.setX(60);
            text3.setY(80);
            text4.setText("(haha you thought)");
        } else {
            text3.setText("Round " + round + " Start!");
        }

        Font f1 = Font.font("Sinhala MN", FontWeight.BOLD, 18);

        endBtn = new Button("End Game");
        endBtn.setFont(f1);
        endBtn.setLayoutX(1000);
        endBtn.setLayoutY(45);
        endBtn.setPrefWidth(150);
        endBtn.setPrefHeight(60);
        endBtn.setOnAction(event -> {
            try {
                pressEndBtn();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        root = new Group(imageView, text, text2, text3, text4, endBtn);

        if (currentTowers != null) {
            for (int i = 0; i < currentTowers.size(); i++) {
                Tower curr = currentTowers.get(i);
                root.getChildren().add(curr.getImageView());
                Upgrade upCurr = currentTowers.get(i).getUpgrade();
                Text upgrade = new Text(curr.getXVal() + 30, curr.getYVal() - 5,
                        "+" + upCurr.getUpgradeLevel());
                upgrade.setFill(Color.WHITE);
                root.getChildren().add(upgrade);
            }
        }

        Enemy first = Enemy.createEnemy(1, round);
        currentEnemies.add(first);
        root.getChildren().add(first.draw());

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.setX(150);
        stage.setY(0);
        stage.show();

        new AnimationTimer() {
            private int i = 0; // counter decide when new enemies will come up next
            private int z = 0; // decide which type of enemy will come up next
            private int numOfEnemies = numEnemies(round);

            @Override
            public void handle(long now) {
                text.setText("MONEY: " + String.valueOf(Player.getMoney()));
                text2.setText("HEALTH: " + String.valueOf(nf.format(Base.getHealth()) + "hp"));

                z = (int) (Math.random() * 3) + 1; // return 1, 2, or 3
                if (i == 60 && numOfEnemies > 0) {
                    Enemy newEnemy = Enemy.createEnemy(z, round);
                    if (newEnemy != null) { // catch
                        currentEnemies.add(newEnemy);
                        root.getChildren().add(newEnemy.draw());
                        numOfEnemies = numOfEnemies - 1;
                    }
                    i = 0;
                }
                i = i + 1;

                allTowerAttack();
                currentEnemies = allEnemyWalk(currentEnemies);

                if (currentEnemies.size() == 0 && numOfEnemies == 0) {
                    if (round != 5) {
                        try {
                            stop();
                            roundWon();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            stop();
                            wonGame();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
                if (!Base.isBaseHealthy()) {
                    try {
                        stop();
                        endGame();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }

    public static void main(String[] args) {
        launch(args);
    }

    public void pressEndBtn() throws Exception {
        Stage stage;

        stage = (Stage) endBtn.getScene().getWindow();
        stage.close();

    }

    public static ArrayList<Enemy> allEnemyWalk(ArrayList<Enemy> currentEnemies) {
        boolean isEnemyAttacking;
        Enemy curr;
        int b = 0;
        while (b < currentEnemies.size()) {
            curr = currentEnemies.get(b);
            isEnemyAttacking = curr.enemyWalk();
            if (isEnemyAttacking) {
                curr.attackBase();
                if (curr.getClassification().equals("finalEnemy")) {
                    curr.setXVal(1175);
                    curr.setYVal(250);
                    curr.getImageView().setX(curr.getXVal());
                    curr.getImageView().setY(curr.getYVal());
                } else {
                    curr.getImageView().setVisible(false);
                    root.getChildren().remove(curr.getImageView());
                    root.getChildren().remove(curr);
                    currentEnemies.remove(curr);
                }
            } else {
                b++;
            }
        }
        return currentEnemies;
    }

    public static void allTowerAttack() {
        Tower currTower;
        Enemy closestEnemy;
        for (int i = 0; i < currentTowers.size(); i++) {
            currTower = currentTowers.get(i);
            if (currentEnemies.size() > 0) {
                closestEnemy = currTower.closestEnemy(currentEnemies);
                if (currTower.enemyInProximity(closestEnemy)) {
                    Node n = currTower.createAttackObject(closestEnemy);
                    attackAnimation(n, closestEnemy, currTower);
                    if (!closestEnemy.isEnemyHealthy()) {
                        closestEnemy.getImageView().setVisible(false);
                        root.getChildren().remove(closestEnemy.getImageView());
                        currentEnemies.remove(closestEnemy);
                        Player.setEnemiesKilled();
                    }
                }
            }
        }
    }

    public static void attackAnimation(Node n, Enemy e, Tower t) {
        root.getChildren().add(n);
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                t.attackEnemy(e);
                root.getChildren().remove(n);
                stop();
            }
        }.start();

    }

    public static int numEnemies(int round) {
        int n;
        switch (round) {
        case 1:
            n = 12;
            break;
        case 2:
            n = 22;
            break;
        case 3:
            n = 33;
            break;
        case 4:
            n = 44;
            break;
        case 5:
            n = 0;
            break;
        default:
            throw new IllegalStateException("Unexpected value: " + round);
        }
        return n;
    }

    public void roundWon() throws Exception {
        Map.setRound(round + 1);
        Enemy.setIncreasedDPS(round * 150);
        Stage stage;
        stage = newStage;
        GameConfig gameConfigScreen = new GameConfig();
        gameConfigScreen.start(stage);
    }

    public void wonGame() throws Exception {
        Stage stage;
        stage = newStage;
        WinGame winGameScreen = new WinGame();
        winGameScreen.start(stage);
    }

    public void endGame() throws Exception {
        Stage stage;
        stage = newStage;
        EndGame endGameScreen = new EndGame();
        endGameScreen.start(stage);
    }

}