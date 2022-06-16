package com.example.vt_labs_1;

import com.example.vt_labs_1.client.Client;
import com.example.vt_labs_1.client.Console;
import com.example.vt_labs_1.controllers.ConnectController;
import com.example.vt_labs_1.controllers.LoginController;
import com.example.vt_labs_1.utility.CommandManager;
import com.example.vt_labs_1.utility.Data;
import com.example.vt_labs_1.utility.User;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.vt_labs_1.utility.Data.working;

public class App extends Application {

    private Client client;
    private Stage primaryStage;
    private User user;
    private Scene loginScene;
    private Scene connectScene;

    public User getUser() {
        return user;
    }


    public static void main(String[] args) throws Exception {
        launch();
        Thread.sleep(1000);
        launch();
        CommandManager commandManager = new CommandManager(Data.client);
        try {
            Console.run(commandManager);
        } catch (Exception e) {

        }
    }

    @Override
    public void init() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/login.fxml"));
        loginScene = new Scene(fxmlLoader.load(), 320, 240);
        fxmlLoader = new FXMLLoader(getClass().getResource("view/port.fxml"));
        connectScene = new Scene(fxmlLoader.load(), 320, 240);
        Data.connectScene = connectScene;
    }

    @Override
    public void start(Stage stage) throws Exception {
        LoginController.setStage(stage);
        ConnectController.setStage(stage);
        login(stage);
    }

    public void login(Stage stage) throws Exception {
        stage.setTitle("Hello!");
        stage.setScene(loginScene);
        stage.show();
    }
}
