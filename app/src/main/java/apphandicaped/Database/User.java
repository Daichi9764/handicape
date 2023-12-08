package apphandicaped.Database;

import java.util.*;

/**
 * User
 */
public class User {


    int UserID;
    String FirstName, LastName, UserName, UserPassword, UserMail;
    String UserStatus;
    Date Birthday, UserCreateDate;
    Boolean Connected;
    public User(String firstName, String lastName) {
        this.FirstName = firstName;
        this.LastName = lastName;
    }
    public User(String firstName, String lastName, String Password) {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.UserPassword = Password;
    }
    @Override
    public String toString() {
        return String.format("%s %s %s pswd : %s ,%s, %s ", FirstName, LastName, UserName, UserPassword, UserMail, UserStatus);
    }
    

    public int getUserID() {
        return UserID;
    }
    public Date getBirthday() {
        return Birthday;
    }
    public String getLastName() {
        return LastName;
    }
    public String getUserName() {
        return UserName;
    }
    public Boolean getConnected() {
        return Connected;
    }
    public String getFirstName() {
        return FirstName;
    }
    public String getUserStatus() {
        return UserStatus;
    }
    public String getUserPassword() {
        return UserPassword;
    }
    public Date getUserCreateDate() {
        return UserCreateDate;
    }
    
}
