package loginTest;

import java.io.Serializable;

public class User implements Serializable {
	
	private String userId;
	private String pass;
	
	public User() {}
	public User(String userId,String pass) {
		this.userId = userId;
		this.pass = pass;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public String getPass() {
		return pass;
	}

}
