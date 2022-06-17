package com.example.vt_labs_1.controllers;

import com.example.vt_labs_1.commands.getTable;
import com.example.vt_labs_1.utility.Data;
import com.example.vt_labs_1.utility.TableRows;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

public class TableController {

    @FXML
    private TableColumn<TableRows, String> id;
    @FXML
    private TableColumn<TableRows, String> movieName;
    @FXML
    private TableColumn<TableRows, String> date;
    @FXML
    private TableColumn<TableRows, String> oscarscount;
    @FXML
    private TableColumn<TableRows, String> genre;
    @FXML
    private TableColumn<TableRows, String> mpaa;
    @FXML
    private TableColumn<TableRows, String> coordinate_x;
    @FXML
    private TableColumn<TableRows, String> coordinate_y;
    @FXML
    private TableColumn<TableRows, String> director_name;
    @FXML
    private TableColumn<TableRows, String> height;
    @FXML
    private TableColumn<TableRows, String> eye;
    @FXML
    private TableColumn<TableRows, String> hair;
    @FXML
    private TableColumn<TableRows, String> country;
    @FXML
    private TableColumn<TableRows, String> loc_x;
    @FXML
    private TableColumn<TableRows, String> loc_y;
    @FXML
    private TableColumn<TableRows, String> loc_z;
    @FXML
    private TableColumn<TableRows, String> loc_name;
    @FXML
    private TableColumn<TableRows, String> user;

    @FXML
    private TableView<TableRows> table;

    public void getterTable() throws Exception {
        initCols();
        String raw = Data.commandManager.managerWork("getTable");
        ObservableList<TableRows> rows = FXCollections.observableArrayList();
        String[] strs = new String[18];
        Scanner scanner = new Scanner(raw);
        scanner.useDelimiter(System.getProperty("line.separator"));
        while (!scanner.hasNext("")) {
            strs[0] = scanner.next();
            strs[1] = scanner.next();
            strs[2] = scanner.next();
            strs[3] = scanner.next();
            strs[4] = scanner.next();
            strs[5] = scanner.next();
            strs[6] = scanner.next();
            strs[7] = scanner.next();
            strs[8] = scanner.next();
            strs[9] = scanner.next();
            strs[10] = scanner.next();
            strs[11] = scanner.next();
            strs[12] = scanner.next();
            strs[13] = scanner.next();
            strs[14] = scanner.next();
            strs[15] = scanner.next();
            strs[16] = scanner.next();
            strs[17] = scanner.next();
            rows.add(new TableRows(strs));
        }
        table.setItems(rows);
    }

    private void initCols() {
        id.setCellValueFactory(new PropertyValueFactory<>("s0"));
        movieName.setCellValueFactory(new PropertyValueFactory<>("s1"));
        date.setCellValueFactory(new PropertyValueFactory<>("s2"));
        oscarscount.setCellValueFactory(new PropertyValueFactory<>("s3"));
        genre.setCellValueFactory(new PropertyValueFactory<>("s4"));
        mpaa.setCellValueFactory(new PropertyValueFactory<>("s5"));
        coordinate_x.setCellValueFactory(new PropertyValueFactory<>("s6"));
        coordinate_y.setCellValueFactory(new PropertyValueFactory<>("s7"));
        director_name.setCellValueFactory(new PropertyValueFactory<>("s8"));
        height.setCellValueFactory(new PropertyValueFactory<>("s9"));
        eye.setCellValueFactory(new PropertyValueFactory<>("s10"));
        hair.setCellValueFactory(new PropertyValueFactory<>("s11"));
        country.setCellValueFactory(new PropertyValueFactory<>("s12"));
        loc_x.setCellValueFactory(new PropertyValueFactory<>("s13"));
        loc_y.setCellValueFactory(new PropertyValueFactory<>("s14"));
        loc_z.setCellValueFactory(new PropertyValueFactory<>("s15"));
        loc_name.setCellValueFactory(new PropertyValueFactory<>("s16"));
        user.setCellValueFactory(new PropertyValueFactory<>("s17"));
    }

    public void back(){
        Data.primaryStage.setTitle("User: " + Data.user.getUsername()+". Page: Menu.");
        Data.primaryStage.setScene(Data.menuScene);
    }
}
