package libraryManagement.controllerClasses.Book;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import libraryManagement.Book;
import libraryManagement.Library;
import libraryManagement.MainStage;
import libraryManagement.User;

public class ViewAllBooksController {
    @FXML
    private Button homeBtn;
    @FXML
    private TableView<Book> bookTable;
    @FXML
    private TableColumn<Book,String> isbnClm;
    @FXML
    private TableColumn<Book,String> nameClm;
    @FXML
    private TableColumn<Book,String> subjectClm;
    @FXML
    private TableColumn<Book,String> authorClm;
    @FXML
    private TableColumn<Book,String> editionClm;
    @FXML
    private TableColumn<Book,String> shelfClm;
    @FXML
    private TableColumn<Book,String> publisherClm;
    @FXML
    private TableColumn<Book,String> addedDateClm;
    @FXML
    private TableColumn<Book,String> copiesClm;

    public void loadData (){
        ObservableList<Book> bookList = Library.getAllBooks();
        isbnClm.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        nameClm.setCellValueFactory(new PropertyValueFactory<>("name"));
        subjectClm.setCellValueFactory(new PropertyValueFactory<>("subject"));
        authorClm.setCellValueFactory(new PropertyValueFactory<>("author"));
        editionClm.setCellValueFactory(new PropertyValueFactory<>("edition"));
        shelfClm.setCellValueFactory(new PropertyValueFactory<>("shelf"));
        publisherClm.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        addedDateClm.setCellValueFactory(new PropertyValueFactory<>("addDate"));
        copiesClm.setCellValueFactory(new PropertyValueFactory<>("copies"));
        bookTable.setItems(bookList);
    }
    public void onHomeBtnClick(){
        ((Stage) homeBtn.getScene().getWindow()).close();
        MainStage.primaryStage.show();
    }
}
