package apphandicaped.Database;

    
    import org.junit.Test;
    import static org.junit.Assert.*;

import java.sql.SQLException;
/**
 * MySQLTest
 */
public class MySQLTest {

    @Test 
    public void ReadTabletest() throws SQLException {
       InterfaceMySQL.main(null); 
    }

}
