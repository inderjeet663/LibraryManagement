package libraryManagement;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.stage.StageStyle.TRANSPARENT;

public class LoginStage {
    public LoginStage() {
        MainStage.isExecuted = true;
        //showing login stage
        //1. Load login fxmlfile into loader
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxmlFiles/User/loginWindow.fxml"));
        //2.create root for login stage
        Parent loginRoot = null; //create loginRoot
        try {
            loginRoot = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage loginStage = new Stage(); //crate stage
        loginStage.initOwner(MainStage.primaryStage.getScene().getWindow()); // load owner of stage
        Scene loginScene = new Scene(loginRoot); // creating scene
        loginScene.setFill(null);//this will not show white corner out of radius
        loginStage.setScene(loginScene);// set scene to login stage
        loginStage.resizableProperty().setValue(false); // not allow to resize

        loginStage.initModality(Modality.WINDOW_MODAL);//not allow to close from task bar
        loginStage.initStyle(TRANSPARENT);// hide minimise and close button window around stage
        loginStage.showAndWait(); // show and wait for action
    }
}
