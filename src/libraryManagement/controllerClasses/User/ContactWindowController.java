package libraryManagement.controllerClasses.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import libraryManagement.MainStage;

public class ContactWindowController {
    @FXML
    private Button homeBtn;
    public void onHomeBtnClick() {
        ((Stage)homeBtn.getScene().getWindow()).close();
        MainStage.primaryStage.show();
    }
}
