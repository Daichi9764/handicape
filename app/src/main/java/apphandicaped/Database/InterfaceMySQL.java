package apphandicaped.Database;

import java.sql.*;
import java.util.ArrayList;


// Notice, do not import com.mysql.cj.jdbc.*
// or you will have problems!

public class InterfaceMySQL {

    
    public static Connection Connect() throws SQLException {
        // Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_022", "projet_gei_022", "iequ6Ais");
        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("Show tables");
        while(rs.next()) {
            System.out.print(rs.getString(1));
            System.out.println();
        }
        return conn;   
    }


    public static void main(String argv[]) throws SQLException{
        int GlobalID = 0;
        String SQL = "INSERT INTO User(first_name,last_name) " + "VALUES(?,?)";
        ArrayList User = new ArrayList<User>();
        User Test = new User(GlobalID + 1 , "Cristiano", "Ronaldo", "Cr7Corp@gmail.com", "MeilleurQueMessi", "Footballer", new Date(14,11,23), new Date(14,11,23), true);
    
        Connection con = Connect();
        Statement stm = con.createStatement();
    }
}