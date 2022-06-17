package com.example.vt_labs_1.controllers;

import com.example.vt_labs_1.exceptions.ArgumentException;
import com.example.vt_labs_1.utility.Data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        commandChoice.setItems(list);
        commandChoice.setValue(list.get(0));
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

}
