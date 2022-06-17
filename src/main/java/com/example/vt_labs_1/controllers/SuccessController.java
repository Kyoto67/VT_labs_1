package com.example.vt_labs_1.controllers;

import com.example.vt_labs_1.utility.Data;

public class SuccessController {

    public void next(){
        Data.primaryStage.setTitle("User: "+Data.user.getUsername()+". Page: Menu.");
        Data.primaryStage.setScene(Data.menuScene);
    }
}
