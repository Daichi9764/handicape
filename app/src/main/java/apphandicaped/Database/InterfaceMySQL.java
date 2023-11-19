package apphandicaped.Database;

import java.sql.*;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

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
            System.out.print( "Id : " + rs.getString(1) + " ");
            System.out.print("FirstName : " + rs.getString(2) + " ");
            System.out.print("LastName : " + rs.getString(3) + " ");
            System.out.println();
        }

    }
    public static void addUser(Connection con, User user) throws SQLException{
        
        String SQLCommand ="INSERT INTO Users (FirstName,LastName) VALUES " +  String.format("('%s', '%s')", user.getFirstName(), user.getLastName());
        Statement stm = con.createStatement();
        System.out.println(SQLCommand);
        stm.executeUpdate(SQLCommand);

    }

    public static void delUser(Connection con , User user, int id) throws SQLException {
        String SQLCommand = "DELETE FROM Users WHERE ID = " + id;
        Statement stm = con.createStatement();
        System.out.println(SQLCommand);
        stm.executeUpdate(SQLCommand);
    }

    public static int getUserIDs(Connection con , User user) throws SQLException {

        //a mettre Statement.RETURN_GENERATED_KEYS
        String SQLCommand = "SELECT UserID FROM Users WHERE " + String.format("LastName = '%s' AND FirstName = '%s'", user.getLastName(), user.getFirstName());
        Statement stm = con.createStatement();
        System.out.println(SQLCommand);
        ResultSet rs = stm.getGeneratedKeys();
        if(rs.next()){
            return rs.getInt(1);
        }
        return 0;
    }
    


    public static void main(String argv[]) throws SQLException{
        Connection conn = Connect();
        User user = new User("CR", "7");
        addUser(conn, user);
        displayUserTable(conn);

        int id = getUserIDs(conn ,user);
        System.out.println(id);

        conn.close();
        
        

    }
}
