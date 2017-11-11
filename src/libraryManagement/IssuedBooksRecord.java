package libraryManagement;

import java.util.Date;

public class IssuedBooksRecord {
    private int id;
    private String userId;
    private int bookId;
    private String issuedDate;

    public IssuedBooksRecord() {

    }
    public IssuedBooksRecord(String userId,int bookId, String issuedDate) {
        this.userId = userId;
        this.bookId = bookId;
        this.issuedDate = issuedDate;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
