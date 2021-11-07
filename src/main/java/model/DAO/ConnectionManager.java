package model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	
	private static final String url = "jdbc:mysql://localhost/KintaiManager";
	
	private static final String user = "root";
	
	private static final String pass = "root";
	
	private static ConnectionManager instance = new ConnectionManager();
	
	private ConnectionManager() {
	}
	
	public static ConnectionManager getinstance() {
		return instance;
	}
	
	public Connection connect() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return DriverManager.getConnection(url, user, pass);
	}

}
