package libraryManagement;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

import static javafx.stage.StageStyle.TRANSPARENT;

public class IssueBookStage {
    public static Book book;
    public static Stage searchStage;
    public IssueBookStage(Book book , Stage searchStage) {
        IssueBookStage.book = book;
        IssueBookStage.searchStage = searchStage;
        //showing stage
        //Load  fxmlfile into loader
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxmlFiles/Book/issueBookPane.fxml"));
        //create root  stage
        Parent root = null; //create Root
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = new Stage(); //crate stage
        stage.initOwner(IssueBookStage.searchStage.getScene().getWindow()); // load owner of stage
        Scene scene = new Scene(root); // creating scene
        scene.setFill(null);//this will not show white corner out of radius
        stage.setScene(scene);// set scene to login stage
        stage.resizableProperty().setValue(false); // not allow to resize

        stage.initModality(Modality.WINDOW_MODAL);//not allow to close from task bar
        stage.initStyle(TRANSPARENT);// hide minimise and close button window around stage
        stage.showAndWait(); // show and wait for action
    }
}
