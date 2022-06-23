package com.example.vt_labs_1.controllers;

import com.example.vt_labs_1.client.Client;
import com.example.vt_labs_1.exceptions.Disconnect;
import com.example.vt_labs_1.utility.CommandManager;
import com.example.vt_labs_1.utility.Data;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class ConnectController {
    @FXML
    private TextField port;

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

}

