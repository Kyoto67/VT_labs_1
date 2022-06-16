package com.example.vt_labs_1.controllers;

import com.example.vt_labs_1.App;
import com.example.vt_labs_1.client.Client;
import com.example.vt_labs_1.utility.Data;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConnectController {
    @FXML
    private TextField port;
    private static Stage stage;

    public static void setStage(Stage stage) {
        ConnectController.stage = stage;
    }

    public void connect(){
        boolean success=false;
        while(!success){
            if(!(port ==null) && !port.getText().isEmpty()) {
                try {
                    int p = Integer.parseInt(port.getText());
                    Data.client= new Client("localhost", p, Data.user);
                    success=true;
                    ConnectController.stage.close();
                    Data.working=false;
                } catch (Exception ignored){
                    //pass
                }
            }
        }
    }
}
