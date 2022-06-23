package com.example.vt_labs_1.controllers;

import com.example.vt_labs_1.utility.Data;
import javafx.fxml.FXML;

public class AccessExcController {

    @FXML
    public void back(){
        Data.errorStage.close();
    }
}
