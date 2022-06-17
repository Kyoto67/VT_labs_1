package com.example.vt_labs_1.controllers;

import com.example.vt_labs_1.utility.Data;
import com.example.vt_labs_1.utility.User;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginController {

    @FXML
    public TextField log;
    @FXML
    public PasswordField pass;

    @FXML
    public void createUserSignUp() {
        if (!(log == null) && !log.getText().isEmpty() && !(pass == null) && !pass.getText().isEmpty()) {
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
            Data.user = new User(log.getText(), password, true);
            Data.primaryStage.setTitle("Connect");
            Data.primaryStage.setScene(Data.connectScene);
        }
    }

    @FXML
    public void createUserSignIn() {
        if (!(log == null) && !log.getText().isEmpty() && !(pass == null) && !pass.getText().isEmpty()) {
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
            Data.primaryStage.setTitle("Connect");
            Data.primaryStage.setScene(Data.connectScene);
        }
    }
}
