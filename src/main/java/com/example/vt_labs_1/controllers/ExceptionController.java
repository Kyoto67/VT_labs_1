package com.example.vt_labs_1.controllers;

import com.example.vt_labs_1.utility.Data;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ExceptionController implements Initializable {
    @FXML
    public Label msg;
    @FXML
    public Button loginButton;
    @FXML
    public Button tryAgainButton;

    public void soutError(){
        msg.setText(Data.messageOut());
    }

    public void loginAgain(){
        Stage stage = Data.primaryStage;
        stage.setTitle("Hello!");
        stage.setScene(Data.loginScene);
        stage.show();
    }

    public void exit(){
        Data.primaryStage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        msg.textProperty().bind(Data.factory.getStringBinding("ConnectionErrorNotificationLabel"));
        loginButton.textProperty().bind(Data.factory.getStringBinding("ExitButton"));
        tryAgainButton.textProperty().bind(Data.factory.getStringBinding("TryAgainButton"));

    }
}
