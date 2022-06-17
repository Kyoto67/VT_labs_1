package com.example.vt_labs_1.utility;

import com.example.vt_labs_1.client.Client;
import com.example.vt_labs_1.data.Movie;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Data {
    public static User user;
    public static Client client;
    public static Stage primaryStage;
    public static Scene connectScene;
    public static Scene exceptionScene;
    public static Scene loginScene;
    public static Scene menuScene;
    public static Scene successScene;
    public static Scene tableScene;
    public static String message = "";
    public static CommandManager commandManager;
    public static Movie movie = null;
    public static Scene askerScene;
    public static void addMessage(String s) {
        message += s + "\n";
    }

    public static String messageOut(){
        String m = message;
        message = "";
        return m;
    }
}
