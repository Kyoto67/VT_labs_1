package com.example.vt_labs_1.controllers;

import com.example.vt_labs_1.exceptions.ArgumentException;
import com.example.vt_labs_1.utility.Data;
import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    private final ObservableList<String> list = FXCollections.observableArrayList("add", "add_if_min", "clear",
            "execute_script", "help", "info", "print_field_descending_oscars_count", "remove_all_by_oscars_count",
            "remove_any_by_director", "remove_by_id", "remove_greater", "remove_lower", "show", "update");

    @FXML
    private ChoiceBox commandChoice;
    @FXML
    private TextField argument;
    @FXML
    private TextArea result;
    @FXML
    private Button executeButton;
    @FXML
    private Label chooseCommand;
    @FXML
    private Button exitButton;
    @FXML
    private Label resultLabel;
    @FXML
    private Label enterArgument;
    @FXML
    private Button tableButton;
    @FXML
    private Button createMovieButton;
    @FXML
    private Button canvasButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        commandChoice.setItems(list);
        commandChoice.setValue(list.get(0));
        argument.promptTextProperty().bind(Data.factory.getStringBinding("ValueLabel"));
        executeButton.textProperty().bind(Data.factory.getStringBinding("ExecuteButton"));
        chooseCommand.textProperty().bind(Data.factory.getStringBinding("ChooseCommandRequestLabel"));
        exitButton.textProperty().bind(Data.factory.getStringBinding("ExitButton"));
        resultLabel.textProperty().bind(Data.factory.getStringBinding("ResultLabel"));
        enterArgument.textProperty().bind(Data.factory.getStringBinding("EnterArgumentRequestLabel"));
        tableButton.textProperty().bind(Data.factory.getStringBinding("TableButton"));
        createMovieButton.textProperty().bind(Data.factory.getStringBinding("CreateMovieButton"));
        canvasButton.textProperty().bind(Data.factory.getStringBinding("CanvasButton"));

    }

    @FXML
    public void run() {
        String command = (String) commandChoice.getValue();
        if (!(argument == null) && !(argument.getText().isEmpty())) {
            command += " " + argument.getText();
        }
        try {
            result.setText(Data.commandManager.managerWork(command));
        } catch (ArgumentException e) {
            result.setText(e.getMessage());
        }
    }

    @FXML
    public void exit() throws Exception {
        Data.commandManager.managerWork("exit");
        Data.primaryStage.close();
    }

    @FXML
    public void openTable() {
        Data.primaryStage.setTitle("User: " + Data.user.getUsername() + ". Page: Movies table");
        Data.primaryStage.setScene(Data.tableScene);
    }

    @FXML
    public void openAsker() {
        Data.primaryStage.setTitle("User: " + Data.user.getUsername() + ". Page: Enter data.");
        Data.primaryStage.setScene(Data.askerScene);
    }

    @FXML
    public void canvas(){
        Data.canvasStage.setTitle("User: " + Data.user.getUsername() + ". Page: Canvas.");
        Data.canvasStage.setScene(Data.canvasScene);
        Data.canvasStage.show();

        startAnimation();
    }

    private void startAnimation(){
        LinkedList<AnimationTimer> ani = Data.animations;

        ani.forEach((a) -> a.start());
    }

}
