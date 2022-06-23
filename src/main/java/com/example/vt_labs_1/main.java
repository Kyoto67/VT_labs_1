package com.example.vt_labs_1;

import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class main extends Application {

    private static double SCENE_WIDTH = 800;
    private static double SCENE_HEIGHT = 600;

    static Random random = new Random();

    Canvas canvas;
    GraphicsContext graphicsContext;

    AnimationTimer loop;

    Scene scene;

    @Override
    public void start(Stage primaryStage) {

        BorderPane root = new BorderPane();

        canvas = new Canvas(SCENE_WIDTH, SCENE_HEIGHT);

        graphicsContext = canvas.getGraphicsContext2D();

        Pane layerPane = new Pane();

        layerPane.getChildren().addAll(canvas);

        root.setCenter(layerPane);

        scene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.show();

        startAnimation();

    }

    private void startAnimation() {

        loop = new AnimationTimer() {

            double startX = 100;
            double endX = 200;
            double y = 100;
            double x = startX;
            double speed = 0.2;

            @Override
            public void handle(long now) {

                graphicsContext.fillOval(x, y, 5,5);

                x+=speed;

                if( x >= endX) {
                    loop.stop();
                }
            }
        };

        loop.start();

    }

    public static void main(String[] args) {
        launch(args);
    }
}