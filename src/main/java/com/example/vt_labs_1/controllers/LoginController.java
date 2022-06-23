package com.example.vt_labs_1.controllers;

import com.example.vt_labs_1.utility.Data;
import com.example.vt_labs_1.utility.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.math.BigInteger;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    public TextField log;
    @FXML
    public PasswordField pass;
    @FXML
    public Label username;
    @FXML
    public Label password;
    @FXML
    public Button signup;
    @FXML
    public Button signin;
    @FXML
    public Label changelanguage;
    @FXML
    public Button rus;
    @FXML
    public Button fin;
    @FXML
    public Button ua;
    @FXML
    public Button en;


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

    @FXML
    public void chooseRu(){
        Data.locale=Data.ruLocale;
        Data.factory.setResources(ResourceBundle.getBundle("com.example.vt_labs_1.controllers.tools.locale", Data.locale));
        Data.primaryStage.setScene(Data.loginScene);
    }

    @FXML
    public void chooseFin(){
        Data.locale=Data.finLocale;
        Data.factory.setResources(ResourceBundle.getBundle("com.example.vt_labs_1.controllers.tools.locale", Data.locale));
        Data.primaryStage.setScene(Data.loginScene);
    }

    @FXML
    public void chooseUa(){
        Data.locale=Data.uaLocale;
        Data.factory.setResources(ResourceBundle.getBundle("com.example.vt_labs_1.controllers.tools.locale", Data.locale));
        Data.primaryStage.setScene(Data.loginScene);
    }

    @FXML
    public void chooseEn(){
        Data.locale=Data.enLocale;
        Data.factory.setResources(ResourceBundle.getBundle("com.example.vt_labs_1.controllers.tools.locale", Data.locale));
        Data.primaryStage.setScene(Data.loginScene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        username.textProperty().bind(Data.factory.getStringBinding("UserNameLabel"));
        password.textProperty().bind(Data.factory.getStringBinding("PasswordLabel"));
        signup.textProperty().bind(Data.factory.getStringBinding("SignUpButton"));
        signin.textProperty().bind(Data.factory.getStringBinding("SignInButton"));
        changelanguage.textProperty().bind(Data.factory.getStringBinding("ChangeLanguage"));
        rus.textProperty().bind(Data.factory.getStringBinding("rus"));
        fin.textProperty().bind(Data.factory.getStringBinding("fin"));
        ua.textProperty().bind(Data.factory.getStringBinding("ua"));
        en.textProperty().bind(Data.factory.getStringBinding("en"));

    }
}
