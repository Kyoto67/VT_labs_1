package com.example.vt_labs_1.controllers.tools;

import java.util.ListResourceBundle;

public class locale_fin extends ListResourceBundle {

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
//                Buttons
                {"SignUpButton","Registration"},
                {"SignInButton","Authorisation"},
                {"UserNameLabel", "Käyttäjätunnus:"},
                {"PasswordLabel", "Password:"},
                {"EnterPortRequest", "Anna yhteysportti:"},
                {"ConnectButton", "Connect"},
                {"ConnectionErrorNotificationLabel", "Yhteysvirhe!"},
                {"TryAgainButton", "Yritä uudelleen"},
                {"ExitButton", "Exit"},
                {"ConnectionSuccessfulLabel", "Yhteys ja valtuutus onnistui!"},
                {"NextButton", "OK"},
                {"ChooseCommandRequestLabel", "Valitse komento:"},
                {"EnterArgumentRequestLabel", "Anna argumentti:"},
                {"ResultLabel", "Tulos:"},
                {"HelloLabel", "Hello!"},
                {"ValueLabel", "Value"},
                {"CreateMovieButton", "Create Movie object"},
                {"ExecuteButton", "Run"},
                {"CanvasButton", "Visualize"},
                {"TableButton", "Table"},
                {"ExitButton", "Exit"},
                {"LoadButton", "Load"},
                {"BackButton", "Back"},
                {"GenerateRandomObjectButton","Generate a random object"},
                {"AccessExceptionLabel", "Sinulla ei ole tarpeeksi oikeuksia tämän objektin muokkaamiseen."},
                {"ChangeLanguage", "Vaihda kieli:"},
                {"rus", "Russian"},
                {"fin", "Finnish"},
                {"ua", "Ukrainalainen"},
                {"en", "English"},
                {"changeButton","Muutos"}
        };
    }
}
