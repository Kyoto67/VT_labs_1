package com.example.vt_labs_1.controllers;

import com.example.vt_labs_1.data.*;
import com.example.vt_labs_1.exceptions.ArgumentException;
import com.example.vt_labs_1.utility.Data;
import com.example.vt_labs_1.utility.GeneratingRandomInfo;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class TableUpdaterController implements Initializable {

    @FXML
    private TextField movie_name;
    @FXML
    private TextField genre;
    @FXML
    private TextField mpaa;
    @FXML
    private TextField coordinates_x;
    @FXML
    private TextField coordinates_y;
    @FXML
    private TextField oscarscount;
    @FXML
    private TextField director_name;
    @FXML
    private TextField height;
    @FXML
    private TextField eye;
    @FXML
    private TextField hair;
    @FXML
    private TextField country;
    @FXML
    private TextField loc_x;
    @FXML
    private TextField loc_y;
    @FXML
    private TextField loc_z;
    @FXML
    private TextField loc_name;
    @FXML
    private Button randomButton;
    @FXML
    private Button nextButton;

    @FXML
    public void compileMovie() {
        Movie movie = new Movie();
        try {
            movie.setName(movie_name.getText());
            movie.setCoordinates(new Coordinates(Double.parseDouble(coordinates_x.getText()), Integer.parseInt(coordinates_y.getText())));
            movie.setCreationDate(new Date());
            movie.setGenre(MovieGenre.valueOf(genre.getText()));
            movie.setMpaaRating(MpaaRating.valueOf(mpaa.getText()));
            movie.setOscarsCount(Long.parseLong(oscarscount.getText()));
            Person director = new Person();
            director.setName(director_name.getText());
            director.setHeight(Double.parseDouble(height.getText()));
            director.setEyeColor(Color.valueOf(eye.getText()));
            director.setHairColor(Color.valueOf(hair.getText()));
            director.setNationality(Country.valueOf(country.getText()));
            director.setLocation(new Location(Double.parseDouble(loc_x.getText()), Double.parseDouble(loc_y.getText()), Double.parseDouble(loc_z.getText()), loc_name.getText()));
            movie.setDirector(director);
            Data.movie = movie;
            Data.commandManager.managerWork("update "+Data.updatableObject.getS0());
            Data.primaryStage.setTitle("User: " + Data.user.getUsername() + ". Page: Table.");
            Data.primaryStage.setScene(Data.tableScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void compileRandom() throws ArgumentException {
        Data.movie = GeneratingRandomInfo.generateOneObject();
        Data.commandManager.managerWork("update "+Data.updatableObject.getS0());
        Data.primaryStage.setTitle("User: " + Data.user.getUsername() + ". Page: Table.");
        Data.primaryStage.setScene(Data.tableScene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        movie_name.setText(Data.updatableObject.getS1());
        genre.setText(Data.updatableObject.getS4());
        mpaa.setText(Data.updatableObject.getS5());
        coordinates_x.setText(Data.updatableObject.getS6());
        coordinates_y.setText(Data.updatableObject.getS7());
        oscarscount.setText(Data.updatableObject.getS3());
        director_name.setText(Data.updatableObject.getS8());
        height.setText(Data.updatableObject.getS9());
        eye.setText(Data.updatableObject.getS10());
        hair.setText(Data.updatableObject.getS11());
        country.setText(Data.updatableObject.getS12());
        loc_x.setText(Data.updatableObject.getS13());
        loc_y.setText(Data.updatableObject.getS14());
        loc_z.setText(Data.updatableObject.getS15());
        loc_name.setText(Data.updatableObject.getS16());
        randomButton.textProperty().bind(Data.factory.getStringBinding("GenerateRandomObjectButton"));
        nextButton.textProperty().bind(Data.factory.getStringBinding("NextButton"));

    }
}
