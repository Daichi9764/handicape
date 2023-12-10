package apphandicaped.Database;

import java.sql.*;
import java.util.ArrayList;

import com.mysql.cj.protocol.Resultset;

import apphandicaped.*;
import apphandicaped.Database.Request.RequestStatus;


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
    public static int addUser(Connection con, User user) throws SQLException{
        
        String SQLCommand ="INSERT INTO Users (FirstName,LastName,UserMail,UserPassword,UserStatus,UserCreationDate,Connected) VALUES " +  String.format("('%s','%s','%s','%s','%s','%s','%s')", user.getFirstName(), user.getLastName(), user.getUserMail(), user.getUserPassword(), user.getUserStatus(),user.getUserCreateDate(),String.valueOf(0));
        Statement stm = con.createStatement();
        System.out.println(SQLCommand);
        stm.executeUpdate(SQLCommand);
        ResultSet rs = stm.executeQuery("Select last_insert_id()");
        if (rs.next()){
            return rs.getInt(1);
        } else {
            return -1;
        }


    }

    public static User getUserByID(int id) throws SQLException{
        User user ;
        Connection con = Connect();
        String SQLCommand = "SELECT FirstName,LastName,UserPassword FROM Users WHERE " + String.format("UserID = %d", id);
        Statement stm = con.prepareStatement(SQLCommand,Statement.RETURN_GENERATED_KEYS);
        System.out.println(SQLCommand);
        ResultSet rs = stm.executeQuery(SQLCommand);
        if (rs.next()){
            System.out.println(rs.getString(1));
            System.out.println(rs.getString(2));
            System.out.println(rs.getString(3));
            user = new User(rs.getString(1), rs.getString(2), rs.getString(3));
            return user;
        }
        con.close();
        return null;
    }

    public static String getStatusbyname(String FirstName) throws SQLException{
        Connection conn = Connect();
        String SQLCommand = "SELECT * FROM Users WHERE " + String.format("FirstName = '%s'", FirstName);
        Statement stm = conn.prepareStatement(SQLCommand);
        ResultSet rs = stm.executeQuery(SQLCommand);
        if(rs.next()){
            return rs.getString("UserStatus");
        }
        conn.close();
        return null;
    }   

    public static User getUserByAttributes(Connection con, String FirstName, String LastName) throws SQLException{
        User user ;
        String SQLCommand = "SELECT * FROM Users WHERE " + String.format("BINARY LastName = '%s' AND BINARY FirstName = '%s'", LastName, FirstName);
        Statement stm = con.prepareStatement(SQLCommand,Statement.RETURN_GENERATED_KEYS);
        System.out.println(SQLCommand);
        ResultSet rs = stm.executeQuery(SQLCommand);
        if (rs.next()){
            user = new User(rs.getString(2), rs.getString(3), rs.getString(5));
            System.out.println(user);
            return user;
        }
        return null;
    }

    public static void delUser(Connection con , int id) throws SQLException {
        String SQLCommand = "DELETE FROM Users WHERE UserID = " + String.valueOf(id);
        Statement stm = con.createStatement();
        System.out.println(SQLCommand);
        int result = stm.executeUpdate(SQLCommand);
        if (result == 1 ){
            System.out.println("delete succesfull");
        } else {
            System.out.println("delete failed");

        }
    }

    public static void addRequest(RequestStatus rqst , int NeedyId, String description) throws SQLException{
        Connection con = Connect();
        long millis=System.currentTimeMillis();  
        java.sql.Date now = new java.sql.Date(millis);
        String SQLCommand = "INSERT INTO Requests (OriginID,RequestStatus,Description,RequestDate) VALUES " +  String.format("('%s', '%s','%s','%s')",  String.valueOf(NeedyId), rqst.toString(),description,now.toString());
        Statement stm = con.createStatement();
        System.out.println(SQLCommand);
        stm.executeUpdate(SQLCommand);
        con.close();
    }

    public static void requestAcceptedbyWorker(int RequestID) throws SQLException{
        Connection conn = Connect();
        String SQLCommand = "UPDATE Requests SET RequestStatus = 'INPROGRESS' WHERE RequestsID = " + String.valueOf(RequestID);
        System.out.println(SQLCommand);
        Statement stm = conn.createStatement();
        stm.executeUpdate(SQLCommand);
        System.out.println("Mission acceptee");
        conn.close();
    }

    public static void requestAcceptedbyVolunteer(int HelperID, int RequestID) throws SQLException{
        Connection conn = Connect();
        String SQLCommand = "UPDATE Requests SET DestinationID = " + String.valueOf(HelperID) + ", RequestStatus = 'ACCEPTED' WHERE RequestsID = " + String.valueOf(RequestID);
        System.out.println(SQLCommand);
        Statement stm = conn.createStatement();
        stm.executeUpdate(SQLCommand);
        System.out.println("Mission acceptee");
        conn.close();
    }

    public static void requestFinished(int RequestID) throws SQLException{
        Connection conn = Connect();
        String SQLCommand = "UPDATE Requests SET RequestStatus = 'COMPLETED' WHERE RequestsID = " + String.valueOf(RequestID);
        System.out.println(SQLCommand);
        Statement stm = conn.createStatement();
        stm.executeUpdate(SQLCommand);
        conn.close();
    }

    public static void addCommentaire(int RequestID,String commentaire) throws SQLException{
        Connection conn = Connect();
        String SQLCommand = "UPDATE Requests SET Commentaire = " + String.format("'%s'",commentaire) + ", RequestStatus = 'REJECTED' WHERE RequestsID = " + String.valueOf(RequestID);
        System.out.println(SQLCommand);
        Statement stm = conn.createStatement();
        stm.executeUpdate(SQLCommand);
        System.out.println("Mission emis");
        conn.close();
    }

    public static ArrayList<Integer> getUserIDs(User user) throws SQLException {
        Connection con = Connect(); 
        //a mettre Statement.RETURN_GENERATED_KEYS
        ArrayList<Integer> IDs = new ArrayList<Integer>(0);
        String SQLCommand = "SELECT UserID FROM Users WHERE " + String.format("LastName = '%s' AND FirstName = '%s'", user.getLastName(), user.getFirstName());
        Statement stm = con.prepareStatement(SQLCommand,Statement.RETURN_GENERATED_KEYS);
        System.out.println(SQLCommand);
        ResultSet rs = stm.executeQuery(SQLCommand);
        // ResultSet rs = stm.getGeneratedKeys();
        while (rs.next()){
            IDs.add(rs.getInt(1));
        }
        con.close();
        return IDs;
    }
    


    public static void main(String argv[]) throws SQLException{
        
        Connection conn = InterfaceMySQL.Connect();
        Request rqst = new Request(RequestStatus.PENDING);
        System.out.println("Rsqt : " + rqst.Rstatus);
        User user = new User("Kylian", "Mbappe");
        int id = InterfaceMySQL.addUser(conn, user);
    
        System.out.println("Request add Done");
        conn.close();
    
        
    }
}
