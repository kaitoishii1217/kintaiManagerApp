package model.DAO;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.entity.User;

public class UserDAO {
	
	private static UserDAO instance = new UserDAO();
	
	private Connection con;
	
	private Statement st;
	
	private UserDAO() {
	}
	
	public static UserDAO getinstance() {
		return instance;
	}
	
	public void getConnection() throws SQLException {
		ConnectionManager cm = ConnectionManager.getinstance();
		con = cm.connect();
	}
	
	public void createSt() throws SQLException {
		st = con.createStatement();
	}
	
	public void discon() {
		try {
			if (con != null) 
				con.close();
			if (st != null)
				st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	public User loginCheck(String userID,String pass) throws NoSuchAlgorithmException, SQLException {
		User loginUser = new User();
		MessageDigest digest = MessageDigest.getInstance("SHA-256");
		byte[] passDigest = digest.digest(pass.getBytes());
		String sha256 = String.format("%064x", new BigInteger(1,passDigest));
		
		String sql = "select * from user where userID = '" + userID + "' and pass = '" + sha256 + "';";
		ResultSet rs = st.executeQuery(sql);
		
		if (rs.next()) {
			if (userID.equals(rs.getString(1))) {
				if (sha256.equals(rs.getString(2))) {
					loginUser.setUserID(rs.getString(1));
					loginUser.setName(rs.getString(3));
					loginUser.setAdminFlag(rs.getInt(4));
				}
			}
		}
		return loginUser;
		
	}
	
	public List<User> selectAllUser() throws SQLException{
		List<User> userList = new ArrayList<User>();
		
		String sql = "select userID,name,adminFlag from user;";
		ResultSet rs = st.executeQuery(sql);
		
		while (rs.next()) {
			User user = new User();
			user.setUserID(rs.getString(1));
			user.setName(rs.getString(2));
			user.setAdminFlag(rs.getInt(3));
			userList.add(user);
		}
		return userList;
	}

}
