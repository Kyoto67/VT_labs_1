package com.example.vt_labs_1.controllers.tools;

import java.util.ListResourceBundle;

public class locale_en extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
//                Buttons
                {"SignUpButton","Sign up"},
                {"SignInButton","Sign in"},
                {"UserNameLabel", "username:"},
                {"PasswordLabel", "password:"},
                {"EnterPortRequest", "Please, enter a port for connection:"},
                {"ConnectButton", "Connect"},
                {"ConnectionErrorNotificationLabel", "Connection error!"},
                {"TryAgainButton", "Try again"},
                {"ExitButton", "Exit"},
                {"ConnectionSuccessfulLabel", "Connection Successful!"},
                {"NextButton", "OK"},
                {"ChooseCommandRequestLabel", "Choose command:"},
                {"EnterArgumentRequestLabel", "Enter an argument:"},
                {"ResultLabel", "Result:"},
                {"HelloLabel", "Hello!"},
                {"ValueLabel", "Value"},
                {"CreateMovieButton", "Create Movie"},
                {"ExecuteButton", "Execute"},
                {"CanvasButton", "Visualization"},
                {"TableButton", "Table"},
                {"ExitButton", "Exit"},
                {"LoadButton", "Load"},
                {"BackButton", "Back"},
                {"GenerateRandomObjectButton","Generate a random object"},
                {"AccessExceptionLabel", "You do not have enough permissions to edit this object."},
                {"ChangeLanguage", "Change language:"},
                {"rus", "Russian"},
                {"fin", "Finnish"},
                {"ua", "Ukrainian"},
                {"en", "English"},
                {"changeButton","Change"}
        };
    }
}
