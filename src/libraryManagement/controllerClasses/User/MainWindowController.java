package libraryManagement.controllerClasses.User;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import libraryManagement.*;
import org.hibernate.Session;

public class MainWindowController {
    @FXML
    private Button issueBookBtn;
    @FXML
    private Button submitBookBtn;
    @FXML
    private Button addNewBookBtn;
    @FXML
    private Button deleteBookBtn;
    @FXML
    private Button viewAllBookBtn;
    @FXML
    private Button viewIssuedBookBtn;

    @FXML
    private Button addNewUserBtn;
    @FXML
    private Button deleteUserBtn;
    @FXML
    private Button updateUserBtn;
    @FXML
    private Button viewAllUserBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private Label loggedInAdminLbl;

    public void onIssueBtnClick() {
    new SearchBookStage("Issue");
    }

    public void onSubmitBtnClick() {
new SubmitBookStage();
    }

    public void onAddNewBtnClick() {
        new AddNewBookStage();
    }

    public void onDeleteBtnClick() {
        new SearchBookStage("Delete");
    }

    public void onViewAllBtnClick() {
        new ViewAllBookStage();
    }

    public void onViewIssuedBtnClick() {
      new AllIssuedBookStage();
    }

    public void onAddNewUserBtnClick() {
        new AddNewUserStage();
    }

    public void onDeleteUserBtnClick() {
        new SearchStage("Delete");
    }

    public void onUpdateUserBtnClick() {
        new SearchStage("Update");
    }

    public void onViewAllUserBtnClick() {
        new viewAllUsersStage();
    }

    public void onExitBtnClick() {
        Main.exit = true;
        ((Stage) exitBtn.getScene().getWindow()).close();
    }
public void onContactBtnClick() {
        new ContactStage();
}
    public void onMouseEntered() {
        loggedInAdminLbl.setText(MainStage.admin);
        Session session = DataConnection.getDataConnection().getFactory().openSession();
    }
}
