module signaller {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;

    opens signaller to javafx.fxml;
    exports signaller;
}
