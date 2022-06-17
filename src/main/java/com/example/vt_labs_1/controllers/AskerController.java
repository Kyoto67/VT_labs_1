package com.example.vt_labs_1.controllers;

import com.example.vt_labs_1.data.*;
import com.example.vt_labs_1.utility.Data;
import com.example.vt_labs_1.utility.GeneratingRandomInfo;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.Date;

public class AskerController {

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
    public void compileMovie(){
        Movie movie = new Movie();
        try{
            movie.setName(movie_name.getText());
            movie.setCoordinates(new Coordinates(Double.parseDouble(coordinates_x.getText()),Integer.parseInt(coordinates_y.getText())));
            movie.setCreationDate(new Date());
            movie.setGenre(MovieGenre.valueOf(genre.getText()));
            movie.setMpaaRating(MpaaRating.valueOf(mpaa.getText()));
            movie.setOscarsCount(Long.parseLong(oscarscount.getText()));
            Person director = new Person();
            director.setName(director_name.getText());
            director.setHeight(Double.parseDouble(height.getText()));
            director.setEyeColor(Color.valueOf(eye.getText()));
            director.setHairColor(Color.valueOf(hair.getText()));
            director.setLocation(new Location(Double.parseDouble(loc_x.getText()), Double.parseDouble(loc_y.getText()), Double.parseDouble(loc_z.getText()), loc_name.getText()));
            movie.setDirector(director);
            Data.movie = movie;
            Data.primaryStage.setTitle(Data.user.getUsername()+": Menu.");
            Data.primaryStage.setScene(Data.menuScene);
        } catch (Exception e){
            //exc
        }
    }

    @FXML
    public void compileRandom(){
        Data.movie = GeneratingRandomInfo.generateOneObject();
        Data.primaryStage.setTitle(Data.user.getUsername()+": Menu.");
        Data.primaryStage.setScene(Data.menuScene);
    }

}
