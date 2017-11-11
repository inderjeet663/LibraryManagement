package libraryManagement.controllerClasses.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import libraryManagement.*;


public class ViewAllUsersController {
    @FXML
    private TableView<User> allUserTableView;
    @FXML
    private TableColumn<User, String> userIdCol;
    @FXML
    private TableColumn<User, String> firstNameCol;
    @FXML
    private TableColumn<User, String> lastNameCol;
    @FXML
    private TableColumn<User, String> DOBCol;
    @FXML
    private TableColumn<User, String> mobileCol;
    @FXML
    private TableColumn<User, String> emailCol;
    @FXML
    private TableColumn<User, String> classCol;
    @FXML
    private TableColumn<User, String> semesterCol;
    @FXML
    private Button homeBtn;

    public void loadData() {
        ObservableList<User> userList = Library.getAllUsers();
        userIdCol.setCellValueFactory(new PropertyValueFactory<>("userId"));
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameCol.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        DOBCol.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        mobileCol.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
        allUserTableView.setItems(userList);
    }
    public void onHomeBtnClick() {
        ((Stage) homeBtn.getScene().getWindow()).close();
        MainStage.primaryStage.show();
    }
}

