package com.example.vt_labs_1.utility;

import com.example.vt_labs_1.client.Client;
import com.example.vt_labs_1.controllers.tools.ObservableResourceFactory;
import com.example.vt_labs_1.data.Movie;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.Locale;
import java.util.ResourceBundle;

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
    public static Scene canvasScene;
    public static Stage canvasStage;
    public static TableRows updatableObject;
    public static Stage errorStage;
    public static Scene accessScene;
    public static Scene updaterScene;
    public static ResourceBundle resourceBundle;
    public static ObservableResourceFactory factory;
    public static Locale locale;
    public static Locale ruLocale = new Locale("ru");
    public static Locale uaLocale = new Locale("ua");
    public static Locale finLocale = new Locale("fin");
    public static Locale enLocale = new Locale("en");
    public static LinkedList<TableRows> rows;
    public static LinkedList<AnimationTimer> animations = new LinkedList<>();

    public static void addMessage(String s) {
        message += s + "\n";
    }

    public static String messageOut(){
        String m = message;
        message = "";
        return m;
    }
}
