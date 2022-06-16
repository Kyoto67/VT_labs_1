package com.example.vt_labs_1.controllers;

import com.example.vt_labs_1.App;
import com.example.vt_labs_1.utility.Data;
import com.example.vt_labs_1.utility.User;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginController {

    @FXML
    private TextField log;
    @FXML
    private PasswordField pass;
    private static Stage stage;

    @FXML
    public void createUserSignUp(){
        if(!(log ==null) && !log.getText().isEmpty() && !(pass==null) && !pass.getText().isEmpty()){

            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                //pass
            }
            byte[] hashedPass = md.digest(pass.getText().getBytes(StandardCharsets.UTF_8));
            BigInteger no = new BigInteger(1, hashedPass);
            String password = no.toString(16);
            while (password.length() < 32) {
                password = "0" + password;
            }
            Data.user = new User(log.getText(), password, false);
            LoginController.stage.setTitle("Connect");
            LoginController.stage.setScene(Data.connectScene);
            Data.working=false;
        }
    }

    @FXML
    public void createUserSignIn(){
        if(!(log ==null) && !log.getText().isEmpty() && !(pass==null) && !pass.getText().isEmpty()){
            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException e) {
                //pass
            }
            byte[] hashedPass = md.digest(pass.getText().getBytes(StandardCharsets.UTF_8));
            BigInteger no = new BigInteger(1, hashedPass);
            String password = no.toString(16);
            while (password.length() < 32) {
                password = "0" + password;
            }
            Data.user = new User(log.getText(), password, false);
            LoginController.stage.close();
            Data.working=false;
        }
    }


    public static void setStage(Stage stage) {
        LoginController.stage = stage;
    }
}
