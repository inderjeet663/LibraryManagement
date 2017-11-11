package libraryManagement.controllerClasses.User;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import libraryManagement.*;

public class SearchUserController {
    @FXML
    private RadioButton idRadioBtn;
    @FXML
    private RadioButton nameRadioBtn;
    @FXML
    private TextField searchTxtFld;
    @FXML
    private Label searchErrLbl;
    @FXML
    private Button searchBtn;
    @FXML
    private Button homeBtn;
    @FXML
    private TableView<User> searchTableView;
    @FXML
    private TableColumn<User, String> idClm;
    @FXML
    private TableColumn<User, String> nameClm;
    @FXML
    private TableColumn<User, String> DOBClm;
    @FXML
    private TableColumn<User, String> mobileClm;
    @FXML
    private TableColumn<User, String> emailClm;
    @FXML
    private TableColumn<User, String> actionClm;

    public static String action;

    public void onSearchBtnClick() {
        if (searchTxtFld.getText().equals("")) {
            searchErrLbl.setText("Please Fill field !");
        } else {
            searchErrLbl.setText("");
            String id = null;
            String name = null;
            searchTableView.getItems().clear();
            if (searchTxtFld != null) {

                if (idRadioBtn.isSelected()) {
                    id = searchTxtFld.getText();
                }
                if (nameRadioBtn.isSelected()) {
                    name = searchTxtFld.getText();
                }
                ObservableList<User> userList = Library.searchUser(id, name);
                if (userList != null) {
                    if (!userList.isEmpty()) {
                        fillTable(userList);
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText(null);
                        alert.setContentText("User not found!");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText(null);
                    alert.setContentText("User list is empty!");
                    alert.showAndWait();
                }
            }
        }
    }

    public void fillTable(ObservableList<User> userList) {
        idClm.setCellValueFactory(new PropertyValueFactory<>("userId"));
        nameClm.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        DOBClm.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        mobileClm.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        emailClm.setCellValueFactory(new PropertyValueFactory<>("email"));
        //Adding button to cell
        Callback<TableColumn<User, String>, TableCell<User, String>> cellFactory
                = //
                new Callback<TableColumn<User, String>, TableCell<User, String>>() {
                    @Override
                    public TableCell call(final TableColumn<User, String> param) {
                        final TableCell<User, String> cell = new TableCell<User, String>() {

                            final Button btn = new Button(action);

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        User user = getTableView().getItems().get(getIndex());
                                        if (btn.getText().equalsIgnoreCase("Delete")) {
                                            Library.deleteUser(user);
                                            //refreshing table after deletion
                                            searchTableView.getItems().clear();
                                            searchTableView.getItems().addAll(userList);
                                        }
                                        if (btn.getText().equalsIgnoreCase("Update")) {
                                            User currentUser = getTableView().getItems().get(getIndex());
                                            ((Stage) (btn.getScene().getWindow())).close();
                                            new AddNewUserStage(currentUser);
                                        }
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
        actionClm.setCellFactory(cellFactory);
        searchTableView.setItems(userList);
      }

    public void onHomeBtnClick() {
        MainStage.primaryStage.show();
        ((Stage) homeBtn.getScene().getWindow()).close();
    }
}
