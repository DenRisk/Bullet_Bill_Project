package Controller;

import Physics.MoveEngine;
import Physics.Time;
import Sprite.Sphere;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;

public class GameScreenController implements Initializable {

    @FXML
    private Button closebtn;
    @FXML
    private Button playbtn;
    @FXML
    private Button breakbtn;
    @FXML
    private Button repeatbtn;
    @FXML
    private Label fpsLabel;
    @FXML
    private Canvas canvasTL;
    @FXML
    private Canvas canvasTR;
    @FXML
    private Canvas canvasBL;
    @FXML
    private Canvas canvasBR;
    @FXML
    private Pane paneTL;
    @FXML
    private Pane paneTR;
    @FXML
    private Pane paneBL;
    @FXML
    private Pane paneBR;


    Timeline loop;
    private double frameTime = 0.01;

    Timer myTimer;
    public static double secondspassed = 0;
    AnimationTimer animationTimerXY;
    private boolean living = true;
    Sphere xySphere;
    Sphere xzSphere;
    Sphere yzSphere;

    boolean change = true;
    boolean stopMaker = false;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        xySphere = new Sphere(200, 50,0, 15, Color.BLACK, 50, 0, 0, 1);
        paneTL.getChildren().add(xySphere);

        xzSphere = new Sphere(200,0,50,15, Color.BLACK, 50,0,0,2);
        paneTR.getChildren().add(xzSphere);

        yzSphere = new Sphere(0, 50, 200, 15, Color.BLACK, 50, 0, 0, 3);
        paneBL.getChildren().add(yzSphere);

        loop = new Timeline(new KeyFrame(Duration.seconds(frameTime), event -> {
            if (living == true) {

                xySphere.setCenterX(xySphere.getX0());
                xySphere.setCenterY(xySphere.getY0());

                xzSphere.setCenterX(xzSphere.getX0());
                xzSphere.setCenterY(xzSphere.getZ0());

                yzSphere.setCenterX(yzSphere.getZ0());
                yzSphere.setCenterY(yzSphere.getY0());

                if (change == true) {
                    Time.curTime = System.currentTimeMillis();
                    change = false;
                }

                Time.updateTime();

                new MoveEngine(xySphere, xzSphere, yzSphere);

            }

        }));
        loop.setCycleCount(Timeline.INDEFINITE);



    }

    public void quitGame() {
        Stage stage = (Stage) closebtn.getScene().getWindow();
        stage.close();
    }

    public void playGame() {
        if (stopMaker == false) {
            Time.curTime = System.currentTimeMillis();
            loop.play();
            stopMaker = true;
        }

    }

    public void stopGame()  {
        if (stopMaker == true) {
            loop.pause();
            stopMaker = false;
        }
    }

    public void repeatGame() {
        Time.curTime = System.currentTimeMillis();

        xySphere.updatePos(200, 50, 0);
        xySphere.updateVelocity(50, 0,0);

        xzSphere.updatePos(200, 0, 50);
        xzSphere.updateVelocity(50, 0,0);

        yzSphere.updatePos(0, 50, 200);
        yzSphere.updateVelocity(50, 0,0);

    }
}
