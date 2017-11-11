package libraryManagement;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class viewAllUsersStage {
    public viewAllUsersStage() {
        //showing stage
        //Load  fxmlfile into loader
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxmlFiles/User/viewAllUsersWindow.fxml"));
        //create root for stage
        Parent root = null; //create Root
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage(); //crate stage
        stage.initOwner(MainStage.primaryStage.getScene().getWindow()); // load owner of stage
        MainStage.primaryStage.hide();
        Scene scene = new Scene(root); // creating scene
        scene.setFill(null);//this will not show white corner out of radius
        stage.setScene(scene);// set scene to login stage
        stage.resizableProperty().setValue(false); // not allow to resize
        stage.initModality(Modality.WINDOW_MODAL);//not allow to close from task bar
        stage.showAndWait(); // show and wait for action
    }
}
