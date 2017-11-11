package libraryManagement.controllerClasses.Book;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import libraryManagement.*;

public class AllIssuedBooksController {

    @FXML
    private Button homeBtn;
    @FXML
    private TableView<Book> issuedBookTable;
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
    private TableColumn<Book,String> issuedDateClm;
    @FXML
    private TableColumn<Book,String> userIdClm;
    @FXML
    private TableColumn<Book,String> userNameClm;

    public void loadData() {
        ObservableList<User> userList = FXCollections.observableArrayList();
        for(User user : Library.getAllUsers()) {
            for(IssuedBooksRecord r : Library.getIssuedBooksRecord()) {
                if(user.getUserId().equals(r.getUserId())) {
                    if(!userList.contains(user))
                        System.out.println("Rnnining");
                    userList.add(user);
                }
            }
        }
        if(!userList.isEmpty()) {
            fillTable(userList);
        }else {
            System.out.println("No user found");
        }


    }
    public void fillTable(ObservableList<User> userList) {
        isbnClm.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        nameClm.setCellValueFactory(new PropertyValueFactory<>("name"));
        subjectClm.setCellValueFactory(new PropertyValueFactory<>("subject"));
        authorClm.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        editionClm.setCellValueFactory(new PropertyValueFactory<>("edition"));
        shelfClm.setCellValueFactory(new PropertyValueFactory<>("language"));
        issuedDateClm.setCellValueFactory(new PropertyValueFactory<>(null));
        userIdClm.setCellValueFactory(new PropertyValueFactory<>(null));
        userNameClm.setCellValueFactory(new PropertyValueFactory<>(null));
        Callback<TableColumn<Book, String>, TableCell<Book, String>> userIdCellFactory
                = //
                new Callback<TableColumn<Book, String>, TableCell<Book, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Book, String> param) {
                        final TableCell<Book, String> cell = new TableCell<Book, String>() {
                            String userId;

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    for (User user : userList) {
                                            userId = user.getUserId();
                                    }
                                    setGraphic(null);
                                    setText(userId);
                                }
                            }
                        };
                        return cell;
                    }
                };

        //User Name cell factory
        Callback<TableColumn<Book, String>, TableCell<Book, String>> userNameCellFactory
                = //
                new Callback<TableColumn<Book, String>, TableCell<Book, String>>() {
                    @Override
                    public TableCell call(final TableColumn<Book, String> param) {
                        final TableCell<Book, String> cell = new TableCell<Book, String>() {
                            String userName;

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    for (User user : userList) {
                                        userName = user.getFirstName()+" "+user.getLastName();
                                    }
                                    setGraphic(null);
                                    setText(userName);
                                }
                            }
                        };
                        return cell;
                    }
                };
        //Issue date cell factory
        Callback<TableColumn<Book, String>, TableCell<Book, String>> issueDateCellFactory
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
                                    for (IssuedBooksRecord r : Library.getIssuedBooksRecord()) {
                                        date= r.getIssuedDate();
                                    }
                                    setGraphic(null);
                                    setText(date);
                                }
                            }
                        };
                        return cell;
                    }
                };
        userIdClm.setCellFactory(userIdCellFactory);
        userNameClm.setCellFactory(userNameCellFactory);
        issuedDateClm.setCellFactory(issueDateCellFactory);
        issuedBookTable.setItems(Library.getAllIssuedBooks());
    }
    public void onHomeBtnClick() {
        ((Stage)homeBtn.getScene().getWindow()).close();
        MainStage.primaryStage.show();
    }
}
