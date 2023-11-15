package apphandicaped.Database;

import java.sql.*;
import java.util.ArrayList;
import apphandicaped.*;


// Notice, do not import com.mysql.cj.jdbc.*
// or you will have problems!

public class InterfaceMySQL {

    
    public static Connection Connect() throws SQLException {
        // Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_022", "projet_gei_022", "iequ6Ais");
        System.out.println("connection successful");
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Show tables");
        while(rs.next()) {
            System.out.print(rs.getString(1));
            System.out.println();
        }
        return conn;   
    }
    public static void displayUserTable(Connection conn) throws SQLException{
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * from Users");
        while(rs.next()) {
            System.out.print(rs.getString(1));
            System.out.println();
        }

    }
    public static void addUser(Connection con, User user) throws SQLException{
        
        String SQLCommand ="INSERT INTO User(FirstName,LastName) VALUES " +  String.format("(%s, %s)", user.getFirstName(), user.getLastName());
        Statement stm = con.createStatement();
        stm.executeUpdate(SQLCommand);

    }
    


    public static void main(String argv[]) throws SQLException{
        Connection conn = Connect();
        User user = new User("CR", "7");
        addUser(conn, user);
        displayUserTable(conn);
        
        

    }
}
