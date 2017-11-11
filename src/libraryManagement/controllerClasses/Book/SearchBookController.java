package libraryManagement.controllerClasses.Book;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import libraryManagement.*;

public class SearchBookController {
    @FXML
    private TableView<Book> searchTableView;
    @FXML
    private TableColumn<Book , String> isbnClm;
    @FXML
    private TableColumn<Book , String> nameClm;
    @FXML
    private TableColumn<Book , String> subjectClm;
    @FXML
    private TableColumn<Book , String> authorClm;
    @FXML
    private TableColumn<Book , String> publisherClm;
    @FXML
    private TableColumn<Book , String> editionClm;
    @FXML
    private TableColumn<Book , String> languageClm;
    @FXML
    private TableColumn<Book , String> copiesClm;
    @FXML
    private TableColumn<Book , String> actionClm;

    @FXML
    private Button searchBtn;
    @FXML
    private Button homeBtn;
    @FXML
    private Label instructionLbl;
    @FXML
    private TextField isbnTxtFld;
    @FXML
    private TextField nameTxtFld;
    @FXML
    private TextField subjectTxtFld;
    @FXML
    private TextField authorTxtFld;
    @FXML
    private TextField publisherTxtFld;
    @FXML
    private TextField editionTxtFld;

    String err ;

    public static String action;
    public void onSearchBtnClick() {
        instructionLbl.setText("");
            if (subjectTxtFld.getText().trim().equals("")) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning!");
                alert.setHeaderText(null);
                alert.setContentText("Please fill subject!");
                alert.showAndWait();
            } else {
                String isbn = null;
                String name = null;
                String subject = null;
                String author = null;
                String publisher = null;
                int edition = 0;
                searchTableView.getItems().clear();
                    subject = subjectTxtFld.getText().trim();
                    if(!isbnTxtFld.getText().isEmpty()) {
                        isbn = isbnTxtFld.getText().trim();
                        if(!(err = Validation.isbnValidate(isbn)).equals("")) {

                            instructionLbl.setText(err+" In ISBN");

                        }
                    }
                    if(!nameTxtFld.getText().isEmpty()) {
                        name = nameTxtFld.getText().trim();
                        if(!(err = Validation.fieldValidate(name)).equals("")) {
                            instructionLbl.setText(err+" In Name");
                        }
                    }
                    if(!authorTxtFld.getText().isEmpty()) {
                        author = authorTxtFld.getText().trim();
                        if(!(err = Validation.fieldValidate(author)).equals("")) {

                            instructionLbl.setText(err+" In Author");

                        }
                    }
                    if(!publisherTxtFld.getText().isEmpty()) {
                        publisher = publisherTxtFld.getText().trim();
                        if(!(err = Validation.fieldValidate(publisher)).equals("")) {
                            instructionLbl.setText(err+" In Publisher");

                        }}
                    if(!editionTxtFld.getText().isEmpty()) {
                        try {
                            edition = Integer.parseInt(editionTxtFld.getText().trim());
                        }catch (NumberFormatException e) {
                            instructionLbl.setText("Wrong edition!");
                        }
                        }
                    if(instructionLbl.getText().equals("")) {
                    ObservableList<Book> searchBookList = Library.searchBook(isbn, name,subject,author,publisher,edition);
                      if (searchBookList != null) {
                        if (!searchBookList.isEmpty()) {
                            fillTable(searchBookList);
                        } else {
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setTitle("Information");
                            alert.setHeaderText(null);
                            alert.setContentText("Book not found!");
                            alert.showAndWait();
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Information");
                        alert.setHeaderText(null);
                        alert.setContentText("Book list is empty!");
                        alert.showAndWait();
                    }
                }
            }
    }
    public void fillTable(ObservableList<Book> bookList) {
        isbnClm.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        nameClm.setCellValueFactory(new PropertyValueFactory<>("name"));
        subjectClm.setCellValueFactory(new PropertyValueFactory<>("subject"));
        authorClm.setCellValueFactory(new PropertyValueFactory<>("author"));
        publisherClm.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        editionClm.setCellValueFactory(new PropertyValueFactory<>("edition"));
        languageClm.setCellValueFactory(new PropertyValueFactory<>("language"));
        copiesClm.setCellValueFactory(new PropertyValueFactory<>("copies"));
        //Adding button to cell
        Callback<TableColumn<Book, String>, TableCell<Book, String>> cellFactory
                = //
                new Callback<TableColumn<Book, String>, TableCell<Book, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Book, String> param) {
                        final TableCell<Book, String> cell = new TableCell<Book, String>() {

                            final Button btn = new Button(action);

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    btn.setOnAction(event -> {
                                        Book book = getTableView().getItems().get(getIndex());
                                        if (btn.getText().equalsIgnoreCase("Delete")) {
                                            Library.deleteBook(book);
                                            //refreshing table after deletion
                                            searchTableView.getItems().clear();
                                            searchTableView.getItems().addAll(bookList);
                                        }
                                        if (btn.getText().equalsIgnoreCase("Issue")) {
                                           Book tempBook= getTableView().getItems().get(getIndex());
                                           new IssueBookStage(tempBook , ((Stage)searchBtn.getScene().getWindow()));
                                            searchTableView.getItems().clear();
                                            bookList.clear();
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
        searchTableView.setItems(bookList);
    }
    public void onHomeBtnClick() {
        ((Stage) homeBtn.getScene().getWindow()).close();
        MainStage.primaryStage.show();
    }
}

