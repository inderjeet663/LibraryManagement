package libraryManagement.controllerClasses.User;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import libraryManagement.Admin;
import libraryManagement.Library;
import libraryManagement.Main;
import libraryManagement.MainStage;

import java.util.Optional;

public class LoginWindowController {
    @FXML
    private Button loginBtn;
    @FXML
    private Button forgotAccountBtn;
    @FXML
    private Button exitBtn;
    @FXML
    private TextField idTxtFld;
    @FXML
    private TextField passwordTxtFld;
    @FXML
    private Label errorLbl;

    public void onLoginBtnClick() {
        //get data form textFields
        if (idTxtFld.getText().equals("") || passwordTxtFld.getText().equals("")) {
            errorLbl.setText("Please Fill Fields!");
        } else {
            char[] idArr = idTxtFld.getText().toCharArray();
            for (char c : idArr) {
                if (Character.isLetter(c)) {
                    errorLbl.setText("wrong id");
                    return;
                } else {
                    errorLbl.setText("");
                }
            }
            errorLbl.setText("");
            int id = 0;
            String password = null;
            try {
                id = Integer.parseInt(idTxtFld.getText());
                password = passwordTxtFld.getText();
            } catch (NumberFormatException e) {
                errorLbl.setText("Wrong id!");
            }
           Admin admin =  Library.login(id, password);
            if (admin!=null) {
                MainStage.admin = admin.getName();
                ((Stage) loginBtn.getScene().getWindow()).close();
            } else
                errorLbl.setText("Account not found");
        }

    }
    public void onForgotAccountBtnClick() {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Forgot Account");
        dialog.setHeaderText(null);
        dialog.setContentText("Enter registered mobile no:");

// Traditional way to get the response value.
        Optional<String> result = dialog.showAndWait();

        if (result.isPresent()) {
            String mobile = result.get();
            Admin admin = Library.forgotAccount(mobile);
            if (admin == null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Account not found!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Id: " + admin.getId()+ "\n" + "Password: " + admin.getPassword());
            alert.showAndWait();
            }
        }
        }

    public void onExitBtnClick() {
        Main.exit = true;
        ((Stage) exitBtn.getScene().getWindow()).close();
    }
}
