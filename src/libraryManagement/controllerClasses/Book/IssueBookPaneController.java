package libraryManagement.controllerClasses.Book;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import libraryManagement.IssueBookStage;
import libraryManagement.Library;
import libraryManagement.User;

import javax.xml.soap.Text;

public class IssueBookPaneController {
    @FXML
    private TextField userIdTxtFld;
    @FXML
    private DatePicker dateFld;
    @FXML
    private Label errLbl;
    @FXML
    private Button issueBtn;
    @FXML
    private Button cancelBtn;

    boolean flag = false;

    public void onIssueBtnClick() {
        if(!(dateFld.getValue()==null || userIdTxtFld.getText().isEmpty())) {
            String userId = userIdTxtFld.getText().trim();

            String issueDate = dateFld.getValue().toString();

            User foundUser = null;
            for (User user : Library.getAllUsers()) {
                if (user.getUserId().equals(userId)) {
                    foundUser = user;
                }
            }
            if(foundUser!=null) {
                flag = Library.issueBook(IssueBookStage.book, foundUser, issueDate);
            if(flag) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Information");
                alert.setContentText("Book Issued");
                alert.showAndWait();
                ((Stage)(issueBtn.getScene().getWindow())).close();
            }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Information");
                alert.setContentText("User not found!");
                alert.showAndWait();
        }
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Warning");
            alert.setContentText("Fill Id & Date!");
            alert.showAndWait();
        }
    }
    public void onCancelBtnClick() {
        ((Stage)cancelBtn.getScene().getWindow()).close();
    }
}
