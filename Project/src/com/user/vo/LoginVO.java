package com.user.vo;

public class LoginVO {
	private String id;
	private String pwd;

	public LoginVO(String id, String pwd) {
		 this.id = id;
		 this.pwd = pwd;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pwd;
	}

	public void setPw(String pwd) {
		this.pwd = pwd;
	}

}
