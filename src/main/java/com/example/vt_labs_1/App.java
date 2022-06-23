package com.example.vt_labs_1;

import com.example.vt_labs_1.controllers.tools.ObservableResourceFactory;
import com.example.vt_labs_1.utility.Data;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.example.vt_labs_1.utility.Data.factory;

public class App extends Application {


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void init() throws IOException {
        Data.locale = new Locale("ru");
        factory = new ObservableResourceFactory();
        factory.setResources(ResourceBundle.getBundle("com.example.vt_labs_1.controllers.tools.locale", Data.locale));
        Data.resourceBundle = factory.getResources();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/login.fxml"), Data.resourceBundle);
        Data.loginScene = new Scene(fxmlLoader.load(), 600, 400);
        fxmlLoader = new FXMLLoader(getClass().getResource("view/port.fxml"), Data.resourceBundle);
        Data.connectScene = new Scene(fxmlLoader.load(), 600, 400);
        fxmlLoader = new FXMLLoader(getClass().getResource("view/exc.fxml"), Data.resourceBundle);
        Data.exceptionScene = new Scene(fxmlLoader.load(), 600, 400);
        fxmlLoader = new FXMLLoader(getClass().getResource("view/success.fxml"), Data.resourceBundle);
        Data.successScene = new Scene(fxmlLoader.load(), 600, 400);
        fxmlLoader = new FXMLLoader(getClass().getResource("view/menu.fxml"), Data.resourceBundle);
        Data.menuScene = new Scene(fxmlLoader.load(), 600, 400);
        fxmlLoader = new FXMLLoader(getClass().getResource("view/table.fxml"), Data.resourceBundle);
        Data.tableScene = new Scene(fxmlLoader.load(), 600, 400);
        fxmlLoader = new FXMLLoader(getClass().getResource("view/asker.fxml"), Data.resourceBundle);
        Data.askerScene = new Scene(fxmlLoader.load(), 600, 400);
        fxmlLoader = new FXMLLoader(getClass().getResource("view/accessexc.fxml"), Data.resourceBundle);
        Data.accessScene = new Scene(fxmlLoader.load(), 300, 200);
//        fxmlLoader = new FXMLLoader(getClass().getResource("view/updater.fxml"), Data.resourceBundle);
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
