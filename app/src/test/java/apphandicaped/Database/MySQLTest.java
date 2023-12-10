package apphandicaped.Database;

    
    import org.junit.Test;

import apphandicaped.Database.Request.RequestStatus;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * MySQLTest
 */
public class MySQLTest {

private Connection connection;

    @Before
    public void setUp() throws SQLException {
        connection = InterfaceMySQL.Connect();
    }

    @After
    public void tearDown() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Test
    public void testAddUser() throws SQLException {
        User user = new User("Momo", "Ait");
        int userId = InterfaceMySQL.addUser(connection, user);
        assertTrue(userId > 0);
    }

    @Test
    public void testGetUserByID() throws SQLException {
        User user = new User("Momo", "Ait");
        int userId = InterfaceMySQL.addUser(connection, user);

        User retrievedUser = InterfaceMySQL.getUserByID(userId);
        assertNotNull(retrievedUser);
        assertEquals(user.getFirstName(), retrievedUser.getFirstName());
        assertEquals(user.getLastName(), retrievedUser.getLastName());
    }

    @Test
    public void testGetStatusByName() throws SQLException {
        User user = new User("Momo", "Ait");
        InterfaceMySQL.addUser(connection, user);

        String status = InterfaceMySQL.getStatusbyname("Momo");
        assertNotNull(status);
    }

    @Test
    public void testGetUserByAttributes() throws SQLException {
        User user = new User("Momo", "Ait");
        InterfaceMySQL.addUser(connection, user);

        User retrievedUser = InterfaceMySQL.getUserByAttributes(connection, "Momo", "Ait");
        assertNotNull(retrievedUser);
        assertEquals(user.getFirstName(), retrievedUser.getFirstName());
        assertEquals(user.getLastName(), retrievedUser.getLastName());
    }

    @Test
    public void testDelUser() throws SQLException {
        User user = new User("Momo", "Ait");
        int userId = InterfaceMySQL.addUser(connection, user);

        InterfaceMySQL.delUser(connection, userId);
        User retrievedUser = InterfaceMySQL.getUserByID(userId);
        assertNull(retrievedUser);
   }

}
