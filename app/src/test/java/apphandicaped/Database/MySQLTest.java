package apphandicaped.Database;

    
    import org.junit.Test;
    import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
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
    public void Injection() throws SQLException {
       InterfaceMySQL.main(null); 
       Connection conn = InterfaceMySQL.Connect();
       User user = new User("NULL", "messi; SELECT * FROM projet_gei_022.Users");
       InterfaceMySQL.addUser(conn, user);

       conn.close();

    }

}
