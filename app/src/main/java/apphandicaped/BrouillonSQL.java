package apphandicaped;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

class MysqlCon {
    public static void main(String args[]) {
        try {
            // Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_022?enabledTLSProtocols=TLSv1.2", "projet_gei_022", "iequ6Ais");
            // Connection con = DriverManager.getConnection(
            //         "jdbc:mysql://srv-bdens.insa-toulouse.fr:3306/projet_gei_022?enabledTLSProtocols=TLSv1.2", "projet_gei_022", "iequ6Ais");
            System.out.println("****************connected**********");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from testTable1");
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}