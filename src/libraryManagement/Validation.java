package libraryManagement;



public class Validation {
    public static String idValidate(String id) {
        id = id.trim();
        if (id.equals("")) {
            return "Fill Id";
        } else if (id.length() < 4) {
            return "more than 4 digits";
        }
        char[] nameChar = id.toCharArray();
        for (char c : nameChar) {
            if (!Character.isDigit(c)) {
                return "only Digits allowed!";
            }
        } for(User user : Library.getAllUsers()) {
            if(user.getUserId().equals(id)) {
                return "This id already used";
            }
        }
        return "";
    }

    public static String nameValidate(String name) {
        name = name.trim();
        if (name.equals("")) {
            return "Fill Name";
        } else if (name.length() < 3) {
            return "Too short name!";
        } else if (name.length() > 15) {
            return "Too long name";
        }
        char[] nameChar = name.toCharArray();
        for (char c : nameChar) {
            if (!Character.isLetter(c)) {
                return "Digits not allowed!";
            }
        }
        return "";
    }

    public static String mobileValidate(String mobile) {
        mobile = mobile.trim();
        char[] mobileChar = mobile.toCharArray();

        if (mobile.equals("")) {
            return "Fill mobile";
        } else if (!mobile.startsWith("9")) {
            return "Must Start with 9";
        }
        for (char c : mobileChar) {
            if (!Character.isDigit(c)) {
                return "Only Digits allowed!";
            }
        }
        if (mobile.length() > 10 || mobile.length() < 10) {
            return "Enter 10 Digits!";
        }
        return "";
    }

    public static String emailValidate(String email) {
        email = email.trim();
        if (email.equals("")) {
            return "Fill email";
        } else if ((!email.contains("@")) || (!email.contains(".")) || email.lastIndexOf("@") > email.lastIndexOf(".")) {
            return "Wrong email !";
        } else if (email.length() < 5) {
            return "Too short email!";
        } else return "";
    }

    public static String passwordValidate(String password) {
        password = password.trim();
        if (password.equals("")) {
            return "Fill password";
        } else if (password.length() < 3) {
            return "Too short password!";
        } else return "";
    }

    //Book data validation functions

    public static String isbnValidate(String isbn) {


        if(isbn.equals("")) {
            return "Fill ISBN!";
        }isbn = isbn.trim();

        char[] isbnArr = isbn.toCharArray();
        for(char c : isbnArr) {
            if(!Character.isDigit(c)) {
                return "Only Digits allowed!";
            }
        }
        if(isbn.length()<10 || isbn.length()>13) {
            return "Enter 10-13 Digits!";
        }
        return "";
    }
    public static String fieldValidate(String value) {

        if(value.equals("")) {
            return "Please Fill !";
        }value = value.trim();
        char[] valueArr = value.toCharArray();
        for(char c : valueArr) {
            if(Character.isDigit(c)) {
                return "Only Letters";
            }
        } return "";
    }

    public static String addDateValidate(String addDate) {
        addDate = addDate.trim();
        if(addDate.equals("")) {
            return "select  date!";
        }else {
            return "";
        }
    }

}
