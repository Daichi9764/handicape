package apphandicaped.Database;

    
    import org.junit.Test;

import apphandicaped.Database.Request.RequestStatus;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 * MySQLTest
 */
public class MySQLTest {

    @Test 
    public void ReadTabletest() throws SQLException {
       InterfaceMySQL.main(null); 
    }
    @Test 
    public void BigBetise() throws SQLException {
       InterfaceMySQL.main(null); 
       Connection conn = InterfaceMySQL.Connect();
       User user = new User(null, "messi");
       InterfaceMySQL.addUser(conn, user);

       conn.close();

    }
    @Test 
    public void AutreBestise() throws SQLException {
       InterfaceMySQL.main(null); 
       Connection conn = InterfaceMySQL.Connect();
       User user = new User("NULL", "messi");
       InterfaceMySQL.addUser(conn, user);

       conn.close();

    }
    @Test
    public void getIDs() throws SQLException{
        Connection conn = InterfaceMySQL.Connect();
        User user = new User("CR", "7");
        InterfaceMySQL.addUser(conn, user);
        InterfaceMySQL.displayUserTable(conn);

        ArrayList<Integer> IDs = InterfaceMySQL.getUserIDs(conn ,user);
        System.out.println("ids : " + IDs);

        conn.close();

    }
    @Test
    public void delUser() throws SQLException{
        Connection conn = InterfaceMySQL.Connect();

        InterfaceMySQL.delUser(conn, 150);
        InterfaceMySQL.delUser(conn, 82);

        InterfaceMySQL.displayUserTable(conn);
        conn.close();

    }
    @Test
    public void getUser() throws SQLException{
        Connection conn = InterfaceMySQL.Connect();
        User user = new User("momo", "ait", "1234");
        int id = InterfaceMySQL.addUser(conn, user);

        User user_queried = InterfaceMySQL.getUserByID(conn, id);
        conn.close();
        assertEquals(user.getLastName(), user_queried.getLastName());
        assertEquals(user.getFirstName(), user_queried.getFirstName());
        assertEquals(user.getUserPassword(), user_queried.getUserPassword());

    }



    // @Test 
    // public void Injection() throws SQLException {
    //    InterfaceMySQL.main(null); 
    //    Connection conn = InterfaceMySQL.Connect();
    //    User user = new User("NULL", "messi; SELECT * FROM projet_gei_022.Users");
    //    InterfaceMySQL.addUser(conn, user);
    //
    //    conn.close();
    //
    // }
    //
}
