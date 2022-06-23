package com.example.vt_labs_1;

import com.example.vt_labs_1.utility.Data;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class App extends Application {


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/login.fxml"));
        Data.loginScene = new Scene(fxmlLoader.load(), 600, 400);
        fxmlLoader = new FXMLLoader(getClass().getResource("view/port.fxml"));
        Data.connectScene = new Scene(fxmlLoader.load(), 600, 400);
        fxmlLoader = new FXMLLoader(getClass().getResource("view/exc.fxml"));
        Data.exceptionScene = new Scene(fxmlLoader.load(), 600, 400);
        fxmlLoader = new FXMLLoader(getClass().getResource("view/success.fxml"));
        Data.successScene = new Scene(fxmlLoader.load(), 600, 400);
        fxmlLoader = new FXMLLoader(getClass().getResource("view/menu.fxml"));
        Data.menuScene = new Scene(fxmlLoader.load(), 600, 400);
        fxmlLoader = new FXMLLoader(getClass().getResource("view/table.fxml"));
        Data.tableScene = new Scene(fxmlLoader.load(), 600, 400);
        fxmlLoader = new FXMLLoader(getClass().getResource("view/asker.fxml"));
        Data.askerScene = new Scene(fxmlLoader.load(), 600, 400);
        fxmlLoader = new FXMLLoader(getClass().getResource("view/accessexc.fxml"));
        Data.accessScene = new Scene(fxmlLoader.load(), 300, 200);
//        fxmlLoader = new FXMLLoader(getClass().getResource("view/updater.fxml"));
//        Data.updaterScene = new Scene(fxmlLoader.load(), 600, 400);
    }

    @Override
    public void start(Stage stage) {
        Data.primaryStage = stage;
        login(stage);
//        canvasTest(stage);
    }

    public void login(Stage stage) {
        stage.setTitle("Hello!");
        stage.setScene(Data.loginScene);
        stage.show();
    }

    private void canvasTest(Stage stage) {
        stage.setTitle("Canvas");
        stage.setScene(Data.canvasScene);
        stage.show();
    }

}
