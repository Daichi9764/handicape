package apphandicaped.controller;

import apphandicaped.Database.InterfaceMySQL;
import apphandicaped.Database.User;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * Controller
 */
public class Controller implements Closeable {

    Connection conn;
    User connectedUser;
    private static Controller instance;

    public enum loginResult {
        NotExist,
        BadPassword,
        Connected,
        UnknownSituation
    }

    public enum RegisterResult {
        Registered,
        AlreadyExist,
        wrongpassword
    }

    private Controller() {
        super();
    }

    // Singleton instance creation method
    public static synchronized Controller getInstance() {
        if (instance == null) {
            instance = new Controller();
        }
        return instance;
    }

    // Open the connection
    public void openConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = InterfaceMySQL.Connect();
        }
    }

    @Override
    public void close() throws IOException {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IOException();
        }
    }

    public loginResult CheckTokens(String firstname, String lastname, char[] password) throws SQLException {
        openConnection(); // Ensure connection is open
        User user = InterfaceMySQL.getUserByAttributes(conn, firstname, lastname);
        String sPassword = new String(password);
        System.out.println("User : " + user);
        System.out.println("given password" + sPassword);
        if (user == null) {
            return loginResult.NotExist;
        }
        if (!user.getUserPassword().equals(sPassword)) {
            return loginResult.BadPassword;
        }
        connectedUser = user;
        return loginResult.Connected;
    }

    public RegisterResult Register(String firstname, String lastname, char[] password, char[] check_password, String UserMail, String UserStatus)
            throws SQLException {
        openConnection(); // Ensure connection is open
        User user = InterfaceMySQL.getUserByAttributes(conn, firstname, lastname);
        if (user != null) {
            return RegisterResult.AlreadyExist;
        } else {
            if (Arrays.equals(password, check_password)) {
                InterfaceMySQL.addUser(conn, new User(firstname, lastname, new String(password), UserMail, UserStatus));
                return RegisterResult.Registered;
            } else {
                return RegisterResult.wrongpassword;
            }
        }
    }
}

