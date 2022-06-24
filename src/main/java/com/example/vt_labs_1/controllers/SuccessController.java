package com.example.vt_labs_1.controllers;

import com.example.vt_labs_1.App;
import com.example.vt_labs_1.exceptions.ArgumentException;
import com.example.vt_labs_1.utility.Data;
import com.example.vt_labs_1.utility.TableRows;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class SuccessController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private Button button;

    public void next() {
        try {
            Data.canvasStage = new Stage();
            canvasInit();
        } catch (Exception e) {
            Data.message = e.getMessage();
            Data.primaryStage.setTitle("Error");
            Data.primaryStage.setScene(Data.exceptionScene);
        }
        Data.primaryStage.setTitle("User: " + Data.user.getUsername() + ". Page: Menu.");
        Data.primaryStage.setScene(Data.menuScene);
    }

    private void canvasInit() {

        FileInputStream fis = null;
        try {
            fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/com/example/vt_labs_1/images/meow.png");
        } catch (FileNotFoundException e) {
        }

        Pane layout = new Pane();
        Group group = new Group(layout);
        Image meow = new Image(fis, 150, 100, true, true);
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    LinkedList<TableRows> data = readCollectionData();
                    LinkedList<String> users = new LinkedList<>();
                    LinkedList<Color> colors = new LinkedList<>();
                    data.forEach(tableRows -> {
                        if (!users.contains(tableRows.getS17())) {
                            users.add(tableRows.getS17());
                        }
                    });
                    for (int i = 0; i < users.size(); i++) {
                        colors.add(Color.rgb(randomRGB(), randomRGB(), randomRGB()));
                    }
                    data.forEach(tableRows -> {

                        Canvas canvas = new Canvas(150.00f, 100.00f);
                        GraphicsContext graphics_context =
                                canvas.getGraphicsContext2D();
                        double coordinate_x = Double.parseDouble(tableRows.getS6());
                        double coordinate_y = Double.parseDouble(tableRows.getS7());
                        Color color = colors.get(users.indexOf(tableRows.getS17()));

                        Data.animations.add(new AnimationTimer() {
                            double startX = 0;
                            double endX = 10;
                            double y = 0;
                            double x = startX;
                            double speed = 0.000001;
                            GraphicsContext graphicsContext = graphics_context;

                            @Override
                            public void handle(long now) {

                                graphics_context.setFill(Color.BLACK);
                                graphics_context.fillRect(x, y, 150, 100);

                                // set fill for rectangle
                                graphics_context.setFill(color);
                                graphics_context.fillRect(x + 10, y + 10, 130, 80);
                                graphics_context.drawImage(meow, x + 10, y);

                                x += speed;

                                if (x >= endX) {
                                    this.stop();
                                }
                            }
                        });

                        Tooltip tip = new Tooltip();
                        tip.setText(
                                "id: " + tableRows.getS0() + "\n" +
                                        "movie name: " + tableRows.getS1() + "\n" +
                                        "creation date: " + tableRows.getS2() + "\n" +
                                        "oscars count: " + tableRows.getS3() + "\n" +
                                        "genre: " + tableRows.getS4() + "\n" +
                                        "rating: " + tableRows.getS5() + "\n" +
                                        "coordinate x: " + tableRows.getS6() + "\n" +
                                        "coordinate y: " + tableRows.getS7() + "\n" +
                                        "director:\n" +
                                        "name: " + tableRows.getS8() + "\n" +
                                        "height: " + tableRows.getS9() + "\n" +
                                        "eye color: " + tableRows.getS10() + "\n" +
                                        "hair color: " + tableRows.getS11() + "\n" +
                                        "nationality: " + tableRows.getS12() + "\n" +
                                        "loc_x: " + tableRows.getS13() + "\n" +
                                        "loc_y: " + tableRows.getS14() + "\n" +
                                        "loc_z: " + tableRows.getS15() + "\n" +
                                        "loc_name: " + tableRows.getS16() + "\n" + "\n" +
                                        "movie owner: " + tableRows.getS17());
                        Button baton = new Button();
                        baton.setBackground(new Background(new BackgroundFill(null, null, null)));
                        baton.setGraphic(canvas);
                        baton.setLayoutX(coordinate_x);
                        baton.setLayoutY(coordinate_y);
                        baton.setTooltip(tip);
                        EventHandler<ActionEvent> update = new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                if (tableRows.getS17().equals(Data.user.getUsername())) {
                                    Data.updatableObject = tableRows;
                                    try {
                                        Data.updaterScene = new Scene(new FXMLLoader(App.class.getResource("view/updater.fxml"), Data.resourceBundle).load(), 300, 200);
                                    } catch (IOException e) {
                                    }
                                    Data.canvasStage.setTitle("Отредактируйте поля.");
                                    Data.canvasStage.setScene(Data.updaterScene);
                                } else {
                                    try {
                                        errorWindowOpen();
                                    } catch (IOException e) {
                                    }
                                }
                            }
                        };
                        baton.setOnAction(update);
                        group.getChildren().add(baton);
                    });
                    Data.canvasScene = new Scene(group, 600, 400);
                } catch (Exception e) {
                }
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                }
            }
        });
        thread.start();
    }

    private LinkedList<TableRows> readCollectionData() throws ArgumentException {
        String data = Data.commandManager.managerWork("getTable");
        LinkedList<TableRows> rows = new LinkedList<>();
        String[] strs = new String[18];
        Scanner scanner = new Scanner(data);
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
                over = true;
            }
        }
        return rows;
    }

    private int randomRGB() {
        return (int) (Math.random() * 255);
    }

    private void errorWindowOpen() throws IOException {
        Data.errorStage = new Stage();
        Data.errorStage.setTitle("Error");
        Data.errorStage.setScene(Data.accessScene);
        Data.errorStage.initModality(Modality.WINDOW_MODAL);
        Data.errorStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        label.textProperty().bind(Data.factory.getStringBinding("ConnectionSuccessfulLabel"));
        button.textProperty().bind(Data.factory.getStringBinding("NextButton"));
    }
}