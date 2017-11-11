package libraryManagement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import sun.misc.OSEnvironment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Library {
    static  Session session = null;
    static Transaction tx = null;
    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Admin related function>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    public static Admin login(int id, String password) {

        try {
            String hql = "FROM Admin a WHERE a.id=" + id + " AND a.password='" + password + "'";
            session =  DataConnection.getDataConnection().getFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery(hql);
            List result = query.list();
            if (result != null) {
                Iterator iterator = result.iterator();
                if (iterator.hasNext()) {
                    tx.commit();
                    return (Admin) iterator.next();
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Some error in login!");
                alert.setTitle("Error!");
                alert.showAndWait();
            }
            return null;
        }finally {
            if(session!=null)
                session.close();
        }
    }

    public static Admin forgotAccount(String mobile) {
        try {
            String hql = "FROM Admin a WHERE a.mobile='" + mobile + "'";
            session  = DataConnection.getDataConnection().getFactory().openSession();
            tx = session.beginTransaction();
            Query query = session.createQuery(hql); List result = query.list();
            if (result != null) {
                Iterator iterator = result.iterator();
                if (iterator.hasNext()) {
                    tx.commit();
                    return (Admin) iterator.next();
                } else {
                    return null;
                }
            } else {
                return null;
            }
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Some error in login!");
                alert.setTitle("Error!");
                alert.showAndWait();
            }
            return null;
        }finally {
            if(session!=null)
                session.close();
        }
    }




    //Getters and setters
   public static ObservableList<Book> getAllBooks() {
       session = DataConnection.getDataConnection().getFactory().openSession();
       tx = session.beginTransaction();
       try {
           String hql = "FROM Book book";
           Query query = session.createQuery(hql);
           List result = query.list();
          tx.commit();
           if (!result.isEmpty()) {
               return FXCollections.observableArrayList(result);
           }
           return null;
       } catch (HibernateException e) {
           if (tx != null) {
              tx.rollback();
               e.printStackTrace();
           }
           return null;
       }finally {
           if(session!=null) {
               session.close();
           }
       }
   }
   public static ObservableList<User> getAllUsers() {
       session = DataConnection.getDataConnection().getFactory().openSession();
       tx = session.beginTransaction();
       try {
           String hql = "FROM User";
           Query query = session.createQuery(hql);
           List result = query.list();
           tx.commit();
           return FXCollections.observableArrayList(result);
       } catch (HibernateException e) {
           if (tx != null) {
               tx.rollback();
               e.printStackTrace();
           }
       }finally {
           if(session!=null)
               session.close();
       }
       return null;
   }
    public ObservableList<Admin> getAllAdmin() {
        session = DataConnection.getDataConnection().getFactory().openSession();
        tx = session.beginTransaction();
        try {
            String hql = "FROM Admin";
            Query query = session.createQuery(hql);
            List result = query.list();
            tx.commit();
           return FXCollections.observableArrayList(result);
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
            }
        }finally {
            if(session!=null)
                session.close();
        }
    return null;
    }
    public static ObservableList<IssuedBooksRecord> getIssuedBooksRecord() {
        session = DataConnection.getDataConnection().getFactory().openSession();
        tx = session.beginTransaction();
        try {
            String hql = "FROM IssuedBooksRecord";
            Query query = session.createQuery(hql);
            List result = query.list();
            tx.commit();
            return FXCollections.observableArrayList(result);
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
                return null;
            }
        }finally {
            if(session!=null)
                session.close();
        }
        return null;
    }
    public static boolean addBook(Book book) {
        session = DataConnection.getDataConnection().getFactory().openSession();
        tx = session.beginTransaction();
        try {
            session.save(book);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
            }
            return false;
        }finally {
            if(session!=null) {
                session.close();
            }
        }
    }
    public static boolean issueBook(Book book ,User user , String date) {
       try {
           session = DataConnection.getDataConnection().getFactory().openSession();
           tx = session.beginTransaction();
           String hql = "FROM User user WHERE user.id=" + user.getUserId();
           Query query = session.createQuery(hql);
           List result = query.list();

           if (result != null) {
               User foundUser = (User) result.get(0);
               if (book.getCopies() > 0) {

                   hql = "UPDATE Book set copies = :c " +
                           "WHERE id = :bId";
                   query = session.createQuery(hql);
                   query.setParameter("c", book.getCopies() - 1);
                   query.setParameter("bId", book.getId());
                   if (query.executeUpdate() > 0) {
                       session.save(new IssuedBooksRecord(user.getUserId(), book.getId(), date));
                       tx.commit();
                       return true;
                   }
               } else {
                   Alert alert = new Alert(Alert.AlertType.WARNING);
                   alert.setHeaderText(null);
                   alert.setTitle("Warning!");
                   alert.setContentText("Book Copies not available");
                   alert.showAndWait();
                   return false;
               }
           }
           return false;
       }catch (HibernateException e) {
           if (tx != null) {
               tx.rollback();
               e.printStackTrace();
           }
           return false;
       }finally {
           if(session!=null) {
               session.close();
           }
       }
    }
    public static boolean submitBook(Book book){
        session = DataConnection.getDataConnection().getFactory().openSession();
        tx = session.beginTransaction();
        try {
            String hql = "UPDATE Book set copies = :c " +
                    "WHERE id = :bId";
            Query query = session.createQuery(hql);
            query.setParameter("c", book.getCopies() + 1);
            query.setParameter("bId", book.getId());

            if (query.executeUpdate() > 0) {
                tx.commit();
                tx = session.beginTransaction();
                System.out.println("Copy incremented");
                hql = "FROM IssuedBooksRecord r WHERE r.bookId="+book.getId();
                query = session.createQuery(hql);
                List list = query.list();
                IssuedBooksRecord record = (IssuedBooksRecord)list.get(0);
                System.out.println(record.getId()+" "+record.getBookId());
                session.delete(record);
                tx.commit();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Book submitted");
                alert.showAndWait();
                return true;
            }
        }catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
            }
            return false;
        }finally {
            if(session!=null) {
                session.close();
            }
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Information");
        alert.setHeaderText(null);
        alert.setContentText("Book not submitted!");
        alert.showAndWait();
        return false;
    }
    //Search book functions
    public static ObservableList<Book> searchBook(String isbn, String name, String subject, String author, String publisher, int edition) {

        ObservableList<Book> allBookList =  Library.getAllBooks();

        if(!allBookList.isEmpty()) {
            ArrayList<Book> foundedBooksList = new ArrayList<>();
            for(Book book : allBookList) {
                if (book.getSubject().equalsIgnoreCase(subject)) {
                    foundedBooksList.add(book);
                }
            }
            if (!foundedBooksList.isEmpty()) {
                if (isbn != null) {
                    for (Book book : foundedBooksList) {
                        if (!(book.getIsbn().equalsIgnoreCase(isbn))) {
                            ArrayList<Book> tempList = new ArrayList<>(foundedBooksList);
                            tempList.remove(tempList.indexOf(book));
                            foundedBooksList = tempList;
                        }
                    }
                }
                if (name != null && (!foundedBooksList.isEmpty())) {
                    for (Book book : foundedBooksList) {
                        if (!(book.getName().equalsIgnoreCase(name))) {
                            ArrayList<Book> tempList = new ArrayList<>(foundedBooksList);
                            tempList.remove(tempList.indexOf(book));
                            foundedBooksList = tempList;
                        }
                    }
                }
                if (author != null && (!foundedBooksList.isEmpty())) {
                    for (Book book : foundedBooksList) {
                        if (!(book.getAuthor().equalsIgnoreCase(author))) {
                            ArrayList<Book> tempList = new ArrayList<>(foundedBooksList);
                            tempList.remove(tempList.indexOf(book));
                            foundedBooksList = tempList;
                        }
                    }
                }
                if (publisher != null && (!foundedBooksList.isEmpty())) {
                    for (Book book : foundedBooksList) {
                        if (!(book.getPublisher().equalsIgnoreCase(publisher))) {
                            ArrayList<Book> tempList = new ArrayList<>(foundedBooksList);
                            tempList.remove(tempList.indexOf(book));
                            foundedBooksList = tempList;
                        }}
                }
                if (edition != 0 && (!foundedBooksList.isEmpty())) {
                    for (Book book : foundedBooksList) {
                        if (book.getEdition() != edition) {
                            ArrayList<Book> tempList = new ArrayList<>(foundedBooksList);
                            tempList.remove(tempList.indexOf(book));
                            foundedBooksList = tempList;   }
                    }
                }
                return FXCollections.observableArrayList(foundedBooksList);
            } }
        return null;
    }

    public static boolean deleteBook(Book book) {
        try {
            session = DataConnection.getDataConnection().getFactory().openSession();
            tx = session.beginTransaction();
            session.delete(book);
            tx.commit();
            return true;
        } catch (HibernateException e) {
        if (tx != null) {
            tx.rollback();
            e.printStackTrace();
        }
        return false;
    }finally {
        if(session!=null) {
            session.close();
        }
    }
    }


    public static ObservableList<Book> getAllIssuedBooks() {
        ObservableList<Book> issuedBooksList = FXCollections.observableArrayList();
        for(IssuedBooksRecord r : Library.getIssuedBooksRecord()) {
            for(Book book : Library.getAllBooks()) {
                if(r.getBookId()==book.getId()) {
                    issuedBooksList.add(book);
                }
            }
        } if(!issuedBooksList.isEmpty()) {
            return issuedBooksList;
        }else {
            return null;
        }

    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<User related Functions>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    public static boolean addNewUser(User user) {
        session = DataConnection.getDataConnection().getFactory().openSession();
        tx = session.beginTransaction();
        try {
            session.save(user);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
            }
            return false;
        }finally {
            if(session!=null)
                session.close();
        }
    }

    public static boolean deleteUser(User user) {
        session = DataConnection.getDataConnection().getFactory().openSession();
        tx = session.beginTransaction();
        try {
            session.delete(user);
            tx.commit();
            return true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();

            }
            return false;
        }finally {
            if(session!=null)
                session.close();
        }
    }

    public static boolean updateUser(User oldUser, User user) {
        session = DataConnection.getDataConnection().getFactory().openSession();
        tx = session.beginTransaction();
        try {
            session.delete(oldUser);
            session.save(user);
            tx.commit();
            AddNewUserStage.oldUser = null;
            return true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
                e.printStackTrace();
            }
            return false;
        }finally {
            if(session!=null)
                session.close();
        }

    }


    //Search user function
    public static ObservableList<User> searchUser(String id, String name) {
        ObservableList<User> registeredUserList = Library.getAllUsers();
        ObservableList<User> foundedUserList = FXCollections.observableArrayList();
        if (!registeredUserList.isEmpty()) {
            for (User user : registeredUserList) {
                if (id != null) {
                    if (user.getUserId().equals(id)) {
                        foundedUserList.add(user);
                    }
                }
                if ((name != null) && (!name.equals(""))) {
                    if (user.getFirstName().equalsIgnoreCase(name)) {
                        foundedUserList.add(user);
                    }
                }
            }
            return foundedUserList;
        }
        return null;
    }
}