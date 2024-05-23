module seventh.assignment.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.kordamp.bootstrapfx.core;

    opens ui to javafx.fxml;
    exports ui;
}