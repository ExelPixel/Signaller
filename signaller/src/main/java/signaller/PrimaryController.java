package signaller;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import signaller.model.Model;
import signaller.view.View;

public class PrimaryController {

    @FXML
    private Pane trackPane;
    private Model model;
    private View view;

    public void setModel(Model model) {
        this.model = model;
        this.view = new View(trackPane, model);
    }

    @FXML
    private void handleGenerate() throws IOException {
        model.initializeRailway();
    }

    @FXML
    private void handleStart() throws IOException {
        view.drawBlockset();
    }

    @FXML
    private void handleDebug() throws IOException {
        model.changeDebugMode();
    }

}
