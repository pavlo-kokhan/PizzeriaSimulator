module org.example.pizzeriasimulator {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens org.example.pizzeriasimulator to javafx.fxml;
    exports org.example.pizzeriasimulator;
}