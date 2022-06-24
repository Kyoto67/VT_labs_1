package com.example.vt_labs_1.utility;

import com.example.vt_labs_1.App;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class TableRows {

    private Button change;
    private String s0;
    private String s1;
    private String s2;
    private String s3;
    private String s4;
    private String s5;
    private String s6;
    private String s7;
    private String s8;
    private String s9;
    private String s10;
    private String s11;
    private String s12;
    private String s13;
    private String s14;
    private String s15;
    private String s16;
    private String s17;

    public TableRows(String[] strs){
        this.s0=strs[0];
        this.s1=strs[1];
        this.s2=strs[2];
        this.s3=strs[3];
        this.s4=strs[4];
        this.s5=strs[5];
        this.s6=strs[6];
        this.s7=strs[7];
        this.s8=strs[8];
        this.s9=strs[9];
        this.s10=strs[10];
        this.s11=strs[11];
        this.s12=strs[12];
        this.s13=strs[13];
        this.s14=strs[14];
        this.s15=strs[15];
        this.s16=strs[16];
        this.s17=strs[17];
        this.change = new Button();
        change.textProperty().bind(Data.factory.getStringBinding("changeButton"));

        EventHandler<ActionEvent> update = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (s17.equals(Data.user.getUsername())) {
                    Data.updatableObject = getThis();
                    Data.primaryStage.setTitle("Отредактируйте поля.");
                    try {
                        Data.primaryStage.setScene(new Scene(new FXMLLoader(App.class.getResource("view/tableupdater.fxml"), Data.resourceBundle).load(), 300, 200));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    try {
                        errorWindowOpen();
                    } catch (IOException e) {
                    }
                }
            }
        };

        change.setOnAction(update);

    }

    public String getS0() {
        return s0;
    }

    public String getS1() {
        return s1;
    }

    public String getS2() {
        return s2;
    }

    public String getS3() {
        return s3;
    }

    public String getS4() {
        return s4;
    }

    public String getS5() {
        return s5;
    }

    public String getS6() {
        return s6;
    }

    public String getS7() {
        return s7;
    }

    public String getS8() {
        return s8;
    }

    public String getS9() {
        return s9;
    }

    public String getS10() {
        return s10;
    }

    public String getS11() {
        return s11;
    }

    public String getS12() {
        return s12;
    }

    public String getS13() {
        return s13;
    }

    public String getS14() {
        return s14;
    }

    public String getS15() {
        return s15;
    }

    public String getS16() {
        return s16;
    }

    public String getS17() {
        return s17;
    }

    public TableRows getThis(){
        return this;
    }

    public Button getChange() {
        return change;
    }

    private void errorWindowOpen() throws IOException {
        Data.errorStage = new Stage();
        Data.errorStage.setTitle("Error");
        Data.errorStage.setScene(Data.accessScene);
        Data.errorStage.initModality(Modality.WINDOW_MODAL);
        Data.errorStage.show();
    }
}
