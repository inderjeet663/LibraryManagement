package libraryManagement;

import javax.swing.text.DateFormatter;
import java.sql.Blob;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User {
//    private int id;


    private String userId;
    private String firstName;
    private String lastName;
    private String DOB;
    private String mobile;
    private String email;
    private String password;
    private byte[] photo;


    public User() {

    }

    public User(String userId, String firstName, String lastName, String DOB, String mobile, String email, String password, byte[] photo) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.DOB = DOB;
        this.mobile = mobile;
        this.email = email;
        this.password = password;
        this.photo = photo;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
