package apphandicaped.controller;

import apphandicaped.Database.InterfaceMySQL;
import apphandicaped.Database.User;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;

/**
 * Controller
 */
public class Controller implements Closeable {

	Connection conn;

	enum loginResult {
		NotExist,
		BadPassword,
		Connected,
		UnknownSituation
	}

	enum RegisterResult {
		Registered,
		AlreadyExist,
		wrongpassword
	}

	public Controller() throws SQLException {
		super();
		this.conn = InterfaceMySQL.Connect();

	}

	@Override
	public void close() throws IOException {
		try {
			this.conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new IOException();
		}

	}

	public loginResult CheckTokens(String firstname, String lastname, String password) throws SQLException {
		User user = InterfaceMySQL.getUserByAttributes(conn, firstname, lastname);
		if (user == null) {
			return loginResult.NotExist;
		}
		if (user.getUserPassword() != password) {
			return loginResult.BadPassword;
		}
		if (user.getUserPassword() == password) {
			return loginResult.Connected;
		} else {
			return loginResult.UnknownSituation;
		}
	}

	public RegisterResult Register(String firstname, String lastname, String password, String check_password ) throws SQLException{
	    
	    User user = InterfaceMySQL.getUserByAttributes(conn, firstname, lastname);
	    if (user != null){
		return RegisterResult.AlreadyExist;
	    } else {
		if (password == check_password){
		InterfaceMySQL.addUser(conn, new User(firstname, lastname, password));
		return RegisterResult.Registered;
		} else {
		    return RegisterResult.wrongpassword;
		}
	    }


	}
}
