package com.example.vt_labs_1.controllers;

import com.example.vt_labs_1.client.Client;
import com.example.vt_labs_1.exceptions.Disconnect;
import com.example.vt_labs_1.utility.CommandManager;
import com.example.vt_labs_1.utility.Data;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ConnectController implements Initializable {
    @FXML
    private TextField port;
    @FXML
    private Label request;
    @FXML
    private Button button;

    @FXML
    public void connect() {
        boolean success = false;
        try {
            while (!success) {
                if (!(port == null) && !port.getText().isEmpty()) {
                    int p = Integer.parseInt(port.getText());
                    Data.client = new Client("localhost", p, Data.user);
                    Data.commandManager = new CommandManager(Data.client);
                    success = true;
                    Data.primaryStage.setScene(Data.successScene);
                }
            }
        } catch (Disconnect e) {
            Data.addMessage(e.getMessage());
            Data.primaryStage.setTitle("Error");
            Data.primaryStage.setScene(Data.exceptionScene);
        } catch (Exception e) {
            e.printStackTrace();
            Data.primaryStage.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        request.textProperty().bind(Data.factory.getStringBinding("EnterPortRequest"));
        button.textProperty().bind(Data.factory.getStringBinding("ConnectButton"));
    }
}

