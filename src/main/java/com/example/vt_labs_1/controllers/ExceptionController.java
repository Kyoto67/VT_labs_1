package com.example.vt_labs_1.controllers;

import com.example.vt_labs_1.utility.Data;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ExceptionController {
    @FXML
    public Label msg;

    @FXML
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
}
