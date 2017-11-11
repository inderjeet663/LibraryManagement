package libraryManagement;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DataConnection {
    private SessionFactory factory = null;
    private static final DataConnection dataConnection = new DataConnection();

    //making data connection class singleton
    private DataConnection() {
        this.factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }
    public SessionFactory getFactory() {
        return factory;
    }
    public static DataConnection getDataConnection() {
        return dataConnection;
    }
}
