package signaller;

import java.io.IOException;
import javafx.fxml.FXML;
import signaller.model.Model;

public class PrimaryController {

    private Model model;

    public void setModel(Model model) {
        this.model = model;
    }

    @FXML
    private void handleGenerate() throws IOException {
        model.initializeRailway();
    }

    @FXML
    private void handleStart() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void handleDebug() throws IOException {
        model.changeDebugMode();
    }

}
