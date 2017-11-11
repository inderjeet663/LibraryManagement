package libraryManagement;

public class Book {
    private int id;
    private String isbn;
    private String subject;
    private String name;
    private String author;
    private String publisher;
    private int edition;
    private int pages;
    private String addDate;
    private int shelf;
    private int copies;
    private String language;
    private String description;
    private byte[] photo;
    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }


public Book() {}
    public Book(String isbn, String subject, String name, String author, String publisher, int edition, int pages, String addDate, int shelf, int copies, String language, String description ,byte[] photo){
        this.isbn = isbn;
        this.subject = subject;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.edition = edition;
        this.pages = pages;
        this.addDate = addDate;
        this.shelf = shelf;
        this.copies = copies;
        this.language = language;
        this.description = description;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getAddDate() {
        return addDate;
    }

    public void setAddDate(String addDate) {
        this.addDate = addDate;
    }

    public int getShelf() {
        return shelf;
    }

    public void setShelf(int shelf) {
        this.shelf = shelf;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
