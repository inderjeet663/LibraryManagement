package libraryManagement.controllerClasses.User;

import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import libraryManagement.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDate;


public class AddUserController {
    //Fields
    @FXML
    private TextField idTxtFld;
    @FXML
    private TextField firstNameTxtFld;
    @FXML
    private TextField lastNameTxtFld;
    @FXML
    private DatePicker DOBFld;
    @FXML
    private TextField mobileTxtFld;
    @FXML
    private TextField emailTxtFld;
    @FXML
    private TextField passwordTxtFld;
    @FXML
    private TextField confirmPasswordTxtFld;
    @FXML
    private ImageView imageView;
    @FXML
    private Label headerLbl;
    //error lables
    @FXML
    private Label idErrLbl;
    @FXML
    private Label fNameErrLbl;
    @FXML
    private Label lNameErrLbl;
    @FXML
    private Label DOBErrLbl;
    @FXML
    private Label mobileErrLbl;
    @FXML
    private Label emailErrLbl;
    @FXML
    private Label passwordErrLbl;
    @FXML
    private Label confirmPasswordErrLbl;
    @FXML
    private Label photoErrLbl;

    //Buttons
    @FXML
    private Button saveBtn;
    @FXML
    private Button resetBtn;
    @FXML
    private Button uploadBtn;
    @FXML
    private Button homeBtn;
    @FXML
    private Button logoutBtn;
    @FXML
    private Button exitBtn;
    private boolean isLoaded = false;
    private boolean flag = false;

    private String id, idErr;
    private String fName, fNameErr;
    private String lName, lNameErr;
    private String DOB;
    private String mobile, mobileErr;
    private String email, emailErr;
    private String password, passwordErr;
    private String confirmPassword, confirmPasswordErr;
    private byte[] photo;

    public void onSaveBtnClick() {
        if (!(firstNameTxtFld.getText().isEmpty() || lastNameTxtFld.getText().isEmpty() || DOBFld.getValue() == null || mobileTxtFld.getText().isEmpty() || emailTxtFld.getText().isEmpty() || passwordTxtFld.getText().isEmpty() || confirmPasswordTxtFld.getText().isEmpty() || imageView.getImage() == null)) {
            id = idTxtFld.getText();
            fName = firstNameTxtFld.getText();
            lName = lastNameTxtFld.getText();
            DOB = String.valueOf(DOBFld.getValue());
            mobile = mobileTxtFld.getText();
            email = emailTxtFld.getText();
            password = passwordTxtFld.getText();
            confirmPassword = confirmPasswordTxtFld.getText();

            if (imageView.getImage() == null) {
                photoErrLbl.setText("Add a photo!");
            } else {
                photoErrLbl.setText("");
                BufferedImage bImage = SwingFXUtils.fromFXImage(imageView.getImage(), null);
                ByteArrayOutputStream s = new ByteArrayOutputStream();

                try {
                    ImageIO.write(bImage, "jpg", s);
                    photo = s.toByteArray();
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (AddNewUserStage.oldUser == null) {
                if (idErrLbl.getText().equals("") && fNameErrLbl.getText().equals("") && lNameErrLbl.getText().equals("") && mobileErrLbl.getText().equals("") && emailErrLbl.getText().equals("") && passwordErrLbl.getText().equals("") && confirmPasswordErrLbl.getText().equals("") && photoErrLbl.getText().equals("")) {
                    flag = Library.addNewUser(new User(id, fName, lName, DOB, mobile, email, password, photo));
                }
            }
            if (AddNewUserStage.oldUser != null) {
                if (idErrLbl.getText().equals("") && fNameErrLbl.getText().equals("") && lNameErrLbl.getText().equals("") && mobileErrLbl.getText().equals("") && emailErrLbl.getText().equals("") && passwordErrLbl.getText().equals("") && confirmPasswordErrLbl.getText().equals("") && photoErrLbl.getText().equals("")) {
                    User newUser = new User(id, fName, lName, DOB, mobile, email, password, photo);
                    flag = Library.updateUser(AddNewUserStage.oldUser, newUser);
                }
            }
            if (flag) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("User has been saved,successfully! ");
                resetFields();
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Some error! data not saved.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all fields!");

            alert.showAndWait();
        }
    }

    public void onResetBtnClick() {
        resetFields();
    }

    public void onIdFocus() {
        id = idTxtFld.getText();
        idErr = Validation.idValidate(id);
        idErrLbl.setText(idErr);
    }

    public void onfNameFocus() {
        fName = firstNameTxtFld.getText();
        fNameErr = Validation.nameValidate(fName);
        fNameErrLbl.setText(fNameErr);
    }

    public void onlNameFocus() {
        lName = lastNameTxtFld.getText();
        lNameErr = Validation.nameValidate(lName);
        lNameErrLbl.setText(lNameErr);
    }

    public void onMobileFocus() {
        mobile = mobileTxtFld.getText();
        mobileErr = Validation.mobileValidate(mobile);
        mobileErrLbl.setText(mobileErr);
    }

    public void onEmailFocus() {
        email = emailTxtFld.getText();
        emailErr = Validation.emailValidate(email);
        emailErrLbl.setText(emailErr);
    }

    public void onPasswordFocus() {
        password = passwordTxtFld.getText();
        confirmPassword = confirmPasswordTxtFld.getText();
        passwordErr = Validation.passwordValidate(password);
        passwordErrLbl.setText(passwordErr);
        if (!password.equals(confirmPassword)) {
            confirmPasswordErr = "Not matched !";
            confirmPasswordErrLbl.setText(confirmPasswordErr);
        } else {
            confirmPasswordErr = "";
            confirmPasswordErrLbl.setText("");
        }
    }

    public void onConfirmPasswordFocus() {
        password = passwordTxtFld.getText();
        confirmPassword = confirmPasswordTxtFld.getText();
        if (!password.equals(confirmPassword)) {
            confirmPasswordErr = "Not matched !";
            confirmPasswordErrLbl.setText(confirmPasswordErr);
        } else {
            confirmPasswordErr = "";
            confirmPasswordErrLbl.setText("");
        }
    }

    public void onHomeBtnClick() {
        ((Stage) homeBtn.getScene().getWindow()).close();
        AddNewUserStage.oldUser = null;
        MainStage.primaryStage.show();

    }

    public void loadData() {

        if (AddNewUserStage.oldUser != null) {
            headerLbl.setText("Update User");
            idTxtFld.setText(AddNewUserStage.oldUser.getUserId());
            firstNameTxtFld.setText(AddNewUserStage.oldUser.getFirstName());
            lastNameTxtFld.setText(AddNewUserStage.oldUser.getLastName());
            DOBFld.setValue(LocalDate.parse(AddNewUserStage.oldUser.getDOB()));
            mobileTxtFld.setText(AddNewUserStage.oldUser.getMobile());
            emailTxtFld.setText(AddNewUserStage.oldUser.getEmail());
            passwordTxtFld.setText(AddNewUserStage.oldUser.getPassword());
            confirmPasswordTxtFld.setText(AddNewUserStage.oldUser.getPassword());
            //loading image
            if (!isLoaded) {
                isLoaded = true;
                Image img = new Image(new ByteArrayInputStream(AddNewUserStage.oldUser.getPhoto()));
                imageView.setImage(img);
            }
        }
    }

    public void onUploadBtnClick() {
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JPG file (*.jpg)", "*.jpg");

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(uploadBtn.getScene().getWindow());
        if (file != null) {
            try {
                FileInputStream input = new FileInputStream(file);
                Image image = new Image(input);
                imageView.setImage(image);
                RandomAccessFile f = new RandomAccessFile(file, "r");
                try {
                    photo = new byte[(int) f.length()];
                    f.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public void resetFields() {
        idTxtFld.setText("");
        firstNameTxtFld.setText("");
        lastNameTxtFld.setText("");
        DOBFld.setValue(null);
        mobileTxtFld.setText("");
        emailTxtFld.setText("");
        passwordTxtFld.setText("");
        confirmPasswordTxtFld.setText("");
        imageView.setImage(null);

        //label reset
        fNameErrLbl.setText("");
        lNameErrLbl.setText("");
        DOBErrLbl.setText("");
        mobileErrLbl.setText("");
        emailErrLbl.setText("");
        passwordErrLbl.setText("");
        confirmPasswordErrLbl.setText("");
        idErrLbl.setText("");
        photoErrLbl.setText("");
    }
}
