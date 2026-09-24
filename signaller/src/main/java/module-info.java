module signaller {
    requires javafx.controls;
    requires javafx.fxml;

    opens signaller to javafx.fxml;
    exports signaller;
}
