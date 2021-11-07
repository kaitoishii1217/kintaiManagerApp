package model.entity;

import java.io.Serializable;

public class User implements Serializable {
	
	private String userID;
	private String pass;
	private String name;
	private int adminFlag;
	
	public User() {
		
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}
	
	public String getUserID() {
		return userID;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setAdminFlag(int adminFlag) {
		this.adminFlag = adminFlag;
	}
	
	public int getAdminFlag() {
		return adminFlag;
	}
	
	

}
