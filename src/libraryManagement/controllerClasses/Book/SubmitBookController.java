package libraryManagement.controllerClasses.Book;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import libraryManagement.*;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.ByteArrayInputStream;
import java.util.List;


public class SubmitBookController {
    @FXML
    private RadioButton idRadioBtn;
    @FXML
    private RadioButton nameRadioBtn;
    @FXML
    private TextField   searchTxtFld;
    @FXML
    private Button      searchBtn;
    @FXML
    private Button      homeBtn;
    @FXML
    private Label       searchErrLbl;

    @FXML
    private TableView<User> userTable;
    @FXML
    private TableView<Book> bookTable;

    @FXML
    private TableColumn<User,String> idClm;
    @FXML
    private TableColumn<User,String> nameClm;
    @FXML
    private TableColumn<User,String> DOBClm;
    @FXML
    private TableColumn<User,String> mobileClm;
    @FXML
    private TableColumn<User,String> emailClm;
    @FXML
    private TableColumn<User,String> viewClm;

    @FXML
    private TableColumn<Book,String> bookIsbnClm;
    @FXML
    private TableColumn<Book,String> bookNameClm;
    @FXML
    private TableColumn<Book,String> bookSubjectClm;
    @FXML
    private TableColumn<Book,String> bookPublisherClm;
    @FXML
    private TableColumn<Book,String> bookEditionClm;
    @FXML
    private TableColumn<Book,String> bookLanguageClm;
    @FXML
    private TableColumn<Book,String> issueDateClm;
    @FXML
    private TableColumn<Book,String> bookActionClm;

    @FXML
    private ImageView imgView;

    public void onSearchBtnClick() {
        if (searchTxtFld.getText().equals("")) {
            searchErrLbl.setText("Please Fill field !");
        } else {
            searchErrLbl.setText("");
            String id = null;
            String name = null;
           userTable.getItems().clear();
            bookTable.getItems().clear();
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
                        fillUserTable(userList);
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
    public void fillUserTable(ObservableList<User> userList) {



        idClm.setCellValueFactory(new PropertyValueFactory<>("userId"));
        nameClm.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        DOBClm.setCellValueFactory(new PropertyValueFactory<>("DOB"));
        mobileClm.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        emailClm.setCellValueFactory(new PropertyValueFactory<>("email"));
        //Adding button to cell
        Callback<TableColumn<User, String>, TableCell<User, String>> userCellFactory
                = //
                new Callback<TableColumn<User, String>, TableCell<User, String>>() {
                    @Override
                    public TableCell call(final TableColumn<User, String> param) {
                        final TableCell<User, String> cell = new TableCell<User, String>() {

                            final Button btn = new Button("View Books");

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                            User user = getTableView().getItems().get(getIndex());
                                        Image img = new Image(new ByteArrayInputStream(user.getPhoto()));
                                        imgView.setImage(img);
                                           fillBookTable(user);
                                    });
                                    setGraphic(btn);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };
       viewClm.setCellFactory(userCellFactory);
        userTable.setItems(userList);
    }
    public void fillBookTable( User user) {
//        session = DataConnection.getDataConnection().getFactory().openSession();
//        tx = session.beginTransaction();
        ObservableList<IssuedBooksRecord> allIssuedBooksRecord = Library.getIssuedBooksRecord();
        ObservableList<Book> userIssuedBooks = FXCollections.observableArrayList();
        ObservableList<IssuedBooksRecord> userIssuedBooksRecord= FXCollections.observableArrayList();
        ObservableList<Book> allBooksList = Library.getAllBooks();
        if (allIssuedBooksRecord == null) {
            System.out.println("No Book Found!");
        } else {
            for(IssuedBooksRecord record : allIssuedBooksRecord) {
                if(record.getUserId().equals(user.getUserId())) {
                    userIssuedBooksRecord.add(record);
                  for(Book book : allBooksList) {
                      if(book.getId()==record.getBookId()) {
                          userIssuedBooks.add(book);
                      }
                  }
                }
            }
            bookIsbnClm.setCellValueFactory(new PropertyValueFactory<>("isbn"));
            bookNameClm.setCellValueFactory(new PropertyValueFactory<>("name"));
            bookSubjectClm.setCellValueFactory(new PropertyValueFactory<>("subject"));
            bookPublisherClm.setCellValueFactory(new PropertyValueFactory<>("publisher"));
            bookEditionClm.setCellValueFactory(new PropertyValueFactory<>("edition"));
            bookLanguageClm.setCellValueFactory(new PropertyValueFactory<>("language"));
            issueDateClm.setCellValueFactory(new PropertyValueFactory<>(null));
            //Adding button to cell
            Callback<TableColumn<Book, String>, TableCell<Book, String>> bookCellFactory
                    = //
                    new Callback<TableColumn<Book, String>, TableCell<Book, String>>() {
                        @Override
                        public TableCell call(final TableColumn<Book, String> param) {
                            final TableCell<Book, String> cell = new TableCell<Book, String>() {

                                final Button btn = new Button("Submit");

                                @Override
                                public void updateItem(String item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (empty) {
                                        setGraphic(null);
                                        setText(null);
                                    } else {
                                        btn.setOnAction(event -> {
                                            Book book = getTableView().getItems().get(getIndex());
                                            Library.submitBook(book);
                                            bookTable.getItems().clear();
                                            userTable.getItems().clear();
                                        });
                                        setGraphic(btn);
                                        setText(null);
                                    }
                                }
                            };
                            return cell;
                        }
                    };
            //Adding issued date cell
            Callback<TableColumn<Book, String>, TableCell<Book, String>> dateCellFactory
                    = //
                    new Callback<TableColumn<Book, String>, TableCell<Book, String>>() {
                        @Override
                        public TableCell call(final TableColumn<Book, String> param) {
                            final TableCell<Book, String> cell = new TableCell<Book, String>() {
                                String date;
                                @Override
                                public void updateItem(String item, boolean empty) {
                                    super.updateItem(item, empty);
                                    if (empty) {
                                        setGraphic(null);
                                        setText(null);
                                    } else {
                                        for(IssuedBooksRecord r : userIssuedBooksRecord ) {
                                        if(r.getBookId()==(getTableView().getItems().get(getIndex()).getId())) {
                                            date = r.getIssuedDate();
                                            }
                                        }
                                        setGraphic(null);
                                        setText(date);
                                    }
                                }
                            };
                            return cell;
                        }
                    };
            bookActionClm.setCellFactory(bookCellFactory);
            issueDateClm.setCellFactory(dateCellFactory);
            bookTable.setItems(userIssuedBooks);
        }
    }
    public void onHomeBtnClick() {
        ((Stage) homeBtn.getScene().getWindow()).close();
        MainStage.primaryStage.show();

    }
}
