package com.example.vt_labs_1.controllers;

import com.example.vt_labs_1.utility.Data;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class AccessExcController implements Initializable {

    @FXML
    private Label msg;
    @FXML
    private Button back;

    @FXML
    public void back(){
        Data.errorStage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        msg.textProperty().bind(Data.factory.getStringBinding("AccessExceptionLabel"));
        back.textProperty().bind(Data.factory.getStringBinding("BackButton"));
    }
}
