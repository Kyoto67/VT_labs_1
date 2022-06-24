package com.example.vt_labs_1.controllers;

import com.example.vt_labs_1.commands.getTable;
import com.example.vt_labs_1.utility.Data;
import com.example.vt_labs_1.utility.TableRows;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.table.TableFilter;

import java.net.URL;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class TableController implements Initializable {

    @FXML
    private TableColumn<TableRows, Button> buttons;
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
    @FXML
    private Button loadButton;
    @FXML
    private Button backButton;

    public void getterTable() throws Exception {
        String raw = Data.commandManager.managerWork("getTable");
        LinkedList<TableRows> rows = new LinkedList<>();
        String[] strs = new String[18];
        Scanner scanner = new Scanner(raw);
        scanner.useDelimiter(System.getProperty("line.separator"));
        boolean over = false;
        while (!over) {
            try {
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
            } catch (Exception ing) {
                over=true;
            }
        }
        Data.rows=rows;
        ObservableList<TableRows> rowsObservableList = FXCollections.observableArrayList(rows);
        table.setItems(rowsObservableList);
        TableFilter.forTableView(table).apply();
        table.getSelectionModel().clearSelection();
    }

    private void initCols() {
        buttons.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getChange()));
        id.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getS0()));
        movieName.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getS1()));
        date.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getS2()));
        oscarscount.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getS3()));
        genre.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getS4()));
        mpaa.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getS5()));
        coordinate_x.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getS6()));
        coordinate_y.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getS7()));
        director_name.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getS8()));
        height.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getS9()));
        eye.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getS10()));
        hair.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getS11()));
        country.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getS12()));
        loc_x.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getS13()));
        loc_y.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getS14()));
        loc_z.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getS15()));
        loc_name.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getS16()));
        user.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getS17()));
    }

    public void back() {
        Data.primaryStage.setTitle("User: " + Data.user.getUsername() + ". Page: Menu.");
        Data.primaryStage.setScene(Data.menuScene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCols();
        loadButton.textProperty().bind(Data.factory.getStringBinding("LoadButton"));
        backButton.textProperty().bind(Data.factory.getStringBinding("BackButton"));

    }

}
