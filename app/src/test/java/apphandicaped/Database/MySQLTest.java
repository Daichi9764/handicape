package apphandicaped.Database;

    
    import org.junit.Test;

import apphandicaped.Database.Request.RequestStatus;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.After;

import java.beans.Statement;
import java.sql.*;
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
    @Test
    public void testAddRequest() throws SQLException {
        Connection conn = InterfaceMySQL.Connect();
        InterfaceMySQL.addRequest(RequestStatus.PENDING, 100, "null");
        String SQLCommand = "SELECT RequestsId FROM Requests WHERE RequestStatuS = 'PENDING' AND OriginID = 100 AND Description = 'null';";
        java.sql.Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(SQLCommand);
        assertNotNull(rs.next());

   }
    @Test
    public void testRequestAcceptedbyWorker() throws SQLException {
        Connection conn = InterfaceMySQL.Connect();
        InterfaceMySQL.addRequest(RequestStatus.PENDING, 2, "null");
        String SQLCommand = "SELECT RequestsId FROM Requests WHERE RequestStatuS = 'PENDING' AND OriginID = 2 AND Description = 'null';";
        java.sql.Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(SQLCommand);
        if(rs.next()){
            int requestId = rs.getInt("RequestsId");
            InterfaceMySQL.requestAcceptedbyWorker(requestId);
            String SQLCommand2 = "SELECT RequestStatus FROM Requests WHERE RequestsID = " + String.valueOf(requestId);
            ResultSet rs2 = stm.executeQuery(SQLCommand2);
            if(rs2.next())assertEquals("INPROGRESS",rs2.getString("RequestStatus")); 
        }

   }
    @Test
    public void testRequestAcceptedbyVolunteer() throws SQLException {
        Connection conn = InterfaceMySQL.Connect();
        InterfaceMySQL.addRequest(RequestStatus.PENDING, 3, "null");
        String SQLCommand = "SELECT RequestsId FROM Requests WHERE RequestStatuS = 'PENDING' AND OriginID = 3 AND Description = 'null';";
        java.sql.Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(SQLCommand);
        if(rs.next()){
            int requestId = rs.getInt("RequestsId");
            InterfaceMySQL.requestAcceptedbyVolunteer(10,requestId);
            String SQLCommand2 = "SELECT RequestStatus, DestinationID FROM Requests WHERE RequestsID = " + String.valueOf(requestId);
            ResultSet rs2 = stm.executeQuery(SQLCommand2);
            if(rs2.next()){
                assertEquals("ACCEPTED",rs2.getString("RequestStatus")); 
                assertEquals(10, rs2.getInt("DestinationID"));
            }
        }

   }
    @Test
    public void testRequestFinished() throws SQLException {
        Connection conn = InterfaceMySQL.Connect();
        InterfaceMySQL.addRequest(RequestStatus.PENDING, 4, "null");
        String SQLCommand = "SELECT RequestsId FROM Requests WHERE RequestStatuS = 'PENDING' AND OriginID = 4 AND Description = 'null';";
        java.sql.Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(SQLCommand);
        if(rs.next()){
            int requestId = rs.getInt("RequestsId");
            InterfaceMySQL.requestFinished(requestId);
            String SQLCommand2 = "SELECT RequestStatus FROM Requests WHERE RequestsID = " + String.valueOf(requestId);
            ResultSet rs2 = stm.executeQuery(SQLCommand2);
            if(rs2.next()){
                assertEquals("COMPLETED",rs2.getString("RequestStatus")); 
            }
        }
   }

    @Test
    public void testAddCommentaire() throws SQLException {
        Connection conn = InterfaceMySQL.Connect();
        InterfaceMySQL.addRequest(RequestStatus.PENDING, 4, "null");
        String SQLCommand = "SELECT RequestsId FROM Requests WHERE RequestStatuS = 'PENDING' AND OriginID = 4 AND Description = 'null';";
        java.sql.Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery(SQLCommand);
        if(rs.next()){
            int requestId = rs.getInt("RequestsId");
            InterfaceMySQL.addCommentaire(requestId,"test","COMPLETED");
            String SQLCommand2 = "SELECT RequestStatus,Commentaire FROM Requests WHERE RequestsID = " + String.valueOf(requestId);
            ResultSet rs2 = stm.executeQuery(SQLCommand2);
            if(rs2.next()){
                assertEquals("COMPLETED",rs2.getString("RequestStatus")); 
                assertEquals("test", rs2.getString("Commentaire"));
            }
        }

   }


}
