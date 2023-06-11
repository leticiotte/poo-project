module com.example.marisa {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens com.example.marisa.view to javafx.fxml;
    opens com.example.marisa.controller to javafx.fxml;

    exports com.example.marisa.view;
    exports com.example.marisa.controller;
}