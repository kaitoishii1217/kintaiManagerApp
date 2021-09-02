package loginTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {
	
	private static DBManager dbManager = new DBManager();
	
	User user = new User();
	
	private Connection con;
	private PreparedStatement st;
	private ResultSet rs;
	
	public static DBManager getInstance() {
		return dbManager;
	}
	
	public String getSavedPass(User user){
		String savedPass = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/testdb","root","root");
			st = con.prepareStatement("SELECT pass from logintest where userid =?;");
			st.setString(1, user.getUserId());
			rs = st.executeQuery();
			
			while(rs.next()) {
				savedPass = rs.getString("pass");
			}
		
		} catch(SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				if(con!=null) {
					con.close();
				}
				if(st!=null) {
					st.close();
				}
				if(rs!=null) {
					rs.close();
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		return savedPass;
		
	}


}
