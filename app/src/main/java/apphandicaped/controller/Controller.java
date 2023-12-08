package apphandicaped.controller;

import apphandicaped.Database.InterfaceMySQL;
import apphandicaped.Database.User;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.util.Arrays;

/**
 * Controller
 */
public class Controller implements Closeable {

	Connection conn;
	User connectedUser;
	private static Controller instance;

	public enum loginResult {
		NotExist,
		BadPassword,
		Connected,
		UnknownSituation
	}

	public enum RegisterResult {
		Registered,
		AlreadyExist,
		wrongpassword
	}

	private Controller() throws SQLException {
		super();
		this.conn = InterfaceMySQL.Connect();
		this.connectedUser = null;
	}

	// Singleton instance creation method
	public static synchronized Controller getInstance() throws SQLException {
		if (instance == null) {
			instance = new Controller();
		}
		return instance;
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

	public loginResult CheckTokens(String firstname, String lastname, char[] password) throws SQLException {
		User user = InterfaceMySQL.getUserByAttributes(conn, firstname, lastname);
		String sPassword = new String(password);
		System.out.println("User : " + user);
		System.out.println("given password" + sPassword);
		if (user == null) {
			return loginResult.NotExist;
		}
		if (!user.getUserPassword().equals(sPassword)) {
			return loginResult.BadPassword;
		}
		if (user.getUserPassword().equals(sPassword)) {
			connectedUser = user;
			return loginResult.Connected;

		} else {
			return loginResult.UnknownSituation;
		}
	}

	public RegisterResult Register(String firstname, String lastname, char[] password, char[] check_password)
			throws SQLException {

		User user = InterfaceMySQL.getUserByAttributes(conn, firstname, lastname);
		if (user != null) {
			return RegisterResult.AlreadyExist;
		} else {
			if (Arrays.equals(password, check_password))  {
				InterfaceMySQL.addUser(conn, new User(firstname, lastname, new String(password)));
				return RegisterResult.Registered;
			} else {
				return RegisterResult.wrongpassword;
			}
		}
	}
}
