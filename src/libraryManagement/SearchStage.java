package libraryManagement;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import libraryManagement.controllerClasses.User.SearchUserController;

import java.io.IOException;

import static javafx.stage.StageStyle.TRANSPARENT;

public class SearchStage {


    public SearchStage(String action) {
        SearchUserController.action = action;
        //showing lstage
        //Load fxmlfile into loader
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxmlFiles/User/searchUserWindow.fxml"));
        //create root for  stage
        Parent root = null; //create Root
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage(); //crate stage
        MainStage.primaryStage.hide();
        Scene searchScene = new Scene(root); // creating scene
        searchScene.setFill(null);//this will not show white corner out of radius
        stage.setScene(searchScene);// set scene to login stage
        stage.resizableProperty().setValue(false); // not allow to resize
        stage.initModality(Modality.WINDOW_MODAL);//not allow to close from task bar
         stage.showAndWait(); // show and wait for action
    }
}
