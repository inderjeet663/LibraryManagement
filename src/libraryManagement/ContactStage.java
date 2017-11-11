package libraryManagement;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ContactStage {
    public ContactStage() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxmlFiles/User/contactWindow.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        }catch(IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.initOwner(MainStage.primaryStage.getScene().getWindow());
        stage.setScene(scene);
        MainStage.primaryStage.hide();
        stage.resizableProperty().setValue(false);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.showAndWait();
    }
}
