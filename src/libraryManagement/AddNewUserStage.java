package libraryManagement;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.stage.StageStyle.TRANSPARENT;

public class AddNewUserStage {
    public static User oldUser;

    public AddNewUserStage() {
        //showing stage
        //Load  fxmlfile into loader
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxmlFiles/User/addUserWindow.fxml"));
        //create root for stage
        Parent root = null;
        try {
            //Load file into root
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage(); //crate stage
        stage.initOwner(MainStage.primaryStage.getScene().getWindow()); // load owner of stage
        //Hiding main stage after showing current stage
        MainStage.primaryStage.hide();
        Scene scene = new Scene(root);
        // creating scene
        scene.setFill(null);//this will not show white corner out of radius
        stage.setScene(scene);// set scene to login stage
        stage.resizableProperty().setValue(false); // not allow to resize
        stage.initModality(Modality.WINDOW_MODAL);//not allow to close from task bar
        stage.showAndWait(); // show and wait for action
    }

    //This constructor will be executed if  admin wants to update user info
    public AddNewUserStage(User currentUser) {
        oldUser = currentUser;
        //showing stage
        // Load  fxmlfile into loader
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxmlFiles/User/addUserWindow.fxml"));
        //create root for stage
        //Passing oldUser data
        Parent root = null; //create Root
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage(); //crate stage
        MainStage.primaryStage.hide();//hide main stage as update user stage is shown
        Scene scene = new Scene(root); // creating scene
        scene.setFill(null);//this will not show white corner out of radius
        stage.setScene(scene);// set scene to login stage
        stage.resizableProperty().setValue(false); // not allow to resize
        stage.initModality(Modality.WINDOW_MODAL);//not allow to close from task bar
        stage.show(); // show and wait for action
    }
}
