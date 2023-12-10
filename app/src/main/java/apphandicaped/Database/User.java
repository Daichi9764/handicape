package apphandicaped.Database;

import java.text.SimpleDateFormat;
import java.util.*;
import java.sql.Date;

/**
 * User
 */
public class User {


    String FirstName, LastName, UserName, UserPassword, UserMail;
    String UserStatus;
    java.sql.Date UserCreateDate;
    Boolean Connected;

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 

    public User(String firstName, String lastName) {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.UserPassword = "";
        this.UserMail = "";
        this.UserStatus = "";
        long millis=System.currentTimeMillis(); 
        this.UserCreateDate = new java.sql.Date(millis); 
    }
    public User(String firstName, String lastName, String Password) {
        this.FirstName = firstName;
        this.LastName = lastName;
        this.UserPassword = Password;
        this.UserMail = "";
        this.UserStatus = "";
        long millis=System.currentTimeMillis(); 
        this.UserCreateDate = new java.sql.Date(millis); 
    }
    public User(String FirstName, String LastName, String UserPassword,String UserMail,String UserStatus){
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.UserPassword = UserPassword;
        this.UserMail = UserMail;
        this.UserStatus = UserStatus;
        long millis=System.currentTimeMillis(); 
        this.UserCreateDate = new java.sql.Date(millis); 
    }


    @Override
    public String toString() {
        return String.format("%s %s %s pswd : %s ,%s, %s ", FirstName, LastName, UserName, UserPassword, UserMail, UserStatus);
    }
    


    public String getLastName() {
        return this.LastName;
    }
    public String getUserName() {
        return this.UserName;
    }
    public Boolean getConnected() {
        return this.Connected;
    }
    public String getFirstName() {
        return this.FirstName;
    }
    public String getUserStatus() {
        return this.UserStatus;
    }
    public String getUserPassword() {
        return this.UserPassword;
    }
    public Date getUserCreateDate() {
        return this.UserCreateDate;
    }
    public String getUserMail() {
        return this.UserMail;
    }
}
