module com.example.vt_labs_1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.example.vt_labs_1 to javafx.fxml;
    exports com.example.vt_labs_1;
    exports com.example.vt_labs_1.controllers;
    opens com.example.vt_labs_1.controllers to javafx.fxml;
}