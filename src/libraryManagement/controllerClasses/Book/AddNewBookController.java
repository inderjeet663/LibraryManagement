package libraryManagement.controllerClasses.Book;

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


public class AddNewBookController {
    @FXML
    private TextField isbnTxtFld;
    @FXML
    private TextField subjectTxtFld;
    @FXML
    private TextField nameTxtFld;
    @FXML
    private TextField authorTxtFld;
    @FXML
    private TextField publisherTxtFld;
    @FXML
    private Spinner<Integer> editionFld;
    @FXML
    private DatePicker addDateFld;
    @FXML
    private Spinner<Integer> shelfFld;
    @FXML
    private TextField copiesTxtFld;
    @FXML
    private TextField languageTxtFld;
    @FXML
    private TextField descriptionTxtFld;
    @FXML
    private TextField pageTxtFld;
    @FXML
    private ImageView imageView;

    //Buttons
    @FXML
    private Button saveBtn;
    @FXML
    private Button resetBtn;
    @FXML
    private Button homeBtn;
    @FXML
    private Button uploadBtn;

    //Error Labels
    @FXML
    private Label isbnErrLbl;
    @FXML
    private Label subjectErrLbl;
    @FXML
    private Label  nameErrLbl;
    @FXML
    private Label authorErrLbl;
    @FXML
    private Label publisherErrLbl;
    @FXML
    private Label editionErrLbl;
    @FXML
    private Label addDateErrLbl;
    @FXML
    private Label  shelfErrLbl;
    @FXML
    private Label copiesErrLbl;
    @FXML
    private Label languageErrLbl;
    @FXML
    private Label descriptionErrLbl;
    @FXML
    private Label pageErrLbl;
    @FXML
    private Label photoErrLbl;

    byte[] photo;
    private boolean flag=false;
    //Variables  for Error Handling
    String isbnErr , subjectErr,nameErr,authorErr,publisherErr,editonErr,addDateErr,shelfErr,pageErr,languageErr
            ,descriptionErr,copiesErr;

   private  String isbn ;
   private  String subject;
    private String name;
    private String author;
    private String  publisher;
    private int     edition ;
    private String  addDate;
    private int     shelf ;
    private String  language;
    private String  description ;
    private int     page;
    private int     copies;
    public void onSaveBtnClick() {
      if( !(isbnTxtFld.getText().isEmpty() ||
        subjectTxtFld.getText().isEmpty()||
       nameTxtFld.getText().isEmpty()||
       authorTxtFld.getText().isEmpty()||
        publisherTxtFld.getText().isEmpty()||
       addDateFld.getValue()==null ||
        languageTxtFld.getText().isEmpty()||
       descriptionTxtFld.getText().isEmpty()||
        pageTxtFld.getText().isEmpty()||
        copiesTxtFld.getText().isEmpty())
        ){
        isbn = isbnTxtFld.getText().trim();
        subject = subjectTxtFld.getText().trim();
        name = nameTxtFld.getText().trim();
        author = authorTxtFld.getText().trim();
        publisher = publisherTxtFld.getText().trim();
        edition = editionFld.getValueFactory().getValue();
        addDate = String.valueOf(addDateFld.getValue());
        shelf = shelfFld.getValueFactory().getValue();
        language = languageTxtFld.getText().trim();
        description = descriptionTxtFld.getText().trim();

        //Handling number format exception
        try {
        page = Integer.parseInt(pageTxtFld.getText().trim());
        }catch (NumberFormatException e) {
            pageErr="Wrong Input";
            pageErrLbl.setText(pageErr);
        }
        try {
            copies = Integer.parseInt(copiesTxtFld.getText().trim());
        }catch (NumberFormatException e) {
            copiesErr = "Wrong Input";
            copiesErrLbl.setText(copiesErr);
        }
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
        if(isbnErrLbl.getText().equals("")&& subjectErrLbl.getText().equals("") && nameErrLbl.getText().equals("") && authorErrLbl.getText().equals("")
                && publisherErrLbl.getText().equals("") && editionErrLbl.getText().equals("") && addDateErrLbl.getText().equals("")
                && shelfErrLbl.getText().equals("") && languageErrLbl.getText().equals("") && descriptionErrLbl.getText().equals("")
                &&pageErrLbl.getText().equals("") && copiesErrLbl.getText().equals("")&& photoErrLbl.getText().equals("")) {
            flag = Library.addBook(new Book(isbn, subject, name, author, publisher, edition, page, addDate, shelf, copies, language, description,photo));
        }
            if(flag) {
            Alert alert= new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information");
            alert.setHeaderText(null);
            alert.setContentText("Book Saved , Successfully ");
            alert.showAndWait();
            resetFields();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Some Error");
            alert.setContentText("Error! Data not saved");
            alert.showAndWait();
        }
      } else {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setHeaderText(null);
          alert.setTitle("Some Error");
          alert.setContentText("Please fill all fields!");
          alert.showAndWait();
      }
        }
        public void onIsbnFocus() {
            isbn = isbnTxtFld.getText().trim();
       isbnErr = Validation.isbnValidate(isbn);
       isbnErrLbl.setText(isbnErr);
        }
        public void onSubjectFocus() {
            subject = subjectTxtFld.getText().trim();
           subjectErr = Validation.fieldValidate(subject);
            subjectErrLbl.setText(subjectErr);
    }
        public void onNameFocus() {
            name = nameTxtFld.getText().trim();
          nameErr = Validation.fieldValidate(name);
            nameErrLbl.setText(nameErr);
    }
        public void onAuthorFocus() {

            author = authorTxtFld.getText().trim();
            authorErr = Validation.fieldValidate(author);
            authorErrLbl.setText(authorErr);
        }
        public void onPublisherFocus (){
            publisher = publisherTxtFld.getText().trim();
        publisherErr = Validation.fieldValidate(publisher);
        publisherErrLbl.setText(publisherErr);
    }

        public void onLanguageFocus() {
            language = languageTxtFld.getText().trim();
            languageErr = Validation.fieldValidate(language);
        languageErrLbl.setText(languageErr);
        }
        public void onDescriptionFocus()
        {
            description = descriptionTxtFld.getText().trim();
         descriptionErr  = Validation.fieldValidate(description);
        descriptionErrLbl.setText(descriptionErr);
        }
        public void onPageFocus() {
            if (pageTxtFld.getText().trim().equals("")) {
                pageErr = "Please Fill !";

            } else {
                try {
                    page = Integer.parseInt(pageTxtFld.getText());
                    pageErr = "";
                } catch (NumberFormatException e) {
                    pageErr = "Wrong Input";
                    pageErrLbl.setText(pageErr);
                }
               // pageErr = Validation.integerValidate(page);

            } pageErrLbl.setText(pageErr);
        }
        public void onCopiesFocus() {
            if (copiesTxtFld.getText().trim().equals("")) {
                copiesErr = "Please Fill !";
            } else {
                try {
                    copies = Integer.parseInt(copiesTxtFld.getText());
                    copiesErr = "";
                } catch (NumberFormatException e) {
                    copiesErr = "Wrong Input";
                    copiesErrLbl.setText(copiesErr);
                }
            }copiesErrLbl.setText(copiesErr);
        }

    public void onResetBtnClick() {
     resetFields();
    }
    public void onHomeBtnClick() {
        ((Stage) homeBtn.getScene().getWindow()).close();
        MainStage.primaryStage.show();
    }
    public void resetFields() {

        isbnTxtFld.setText("");
        subjectTxtFld.setText("");
        nameTxtFld.setText("");
        authorTxtFld.setText(null);
        publisherTxtFld.setText("");
        editionFld.getValueFactory().setValue(1);
        addDateFld.setValue(null);
        shelfFld.getValueFactory().setValue(1);
        languageTxtFld.setText("");
        descriptionTxtFld.setText("");
        pageTxtFld.setText("");
        copiesTxtFld.setText("");
        imageView.setImage(null);


        //label reset
        isbnErrLbl.setText("");
        subjectErrLbl.setText("");
        nameErrLbl.setText("");
        authorErrLbl.setText("");
        publisherErrLbl.setText("");
        editionErrLbl.setText("");
        addDateErrLbl.setText("");
        shelfErrLbl.setText("");
        languageErrLbl.setText("");
        descriptionErrLbl.setText("");
        pageErrLbl.setText("");
        copiesErrLbl.setText("");
        photoErrLbl.setText("");
    }
    public void onUploadBtnClick() {
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("JPG file (*.jpg)", "*.jpg");

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(uploadBtn.getScene().getWindow());
        if (file != null) {
            try {
                FileInputStream input = new FileInputStream(file);
                Image image = new Image(input,150,150,false,true);
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

}
