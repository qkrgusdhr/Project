package com.user.vo;

public class LoginVO {
	private String id;
	private String pw;
	private String sessionID;
	
	public LoginVO(String id, String pw) {
		 this.id = id;
		 this.pw = pw;
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	

}
