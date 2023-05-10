module com.example.marisa {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.marisa to javafx.fxml;
    exports com.example.marisa;
}