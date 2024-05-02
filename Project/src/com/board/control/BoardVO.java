package com.board.control;

import java.util.Date;


public class BoardVO {
	private int num;
	private String name;
	private String title;
	private String content;
	private Date regDate;
	private String setUserID;
	
	 public Object[] toArray() {
	        return new Object[] { getNum(), getName(), getTitle(), getRegDate() };
	    }
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getSetUserID() {
		return setUserID;
	}

	public void setSetUserID(String setUserID) {
		this.setUserID = setUserID;
	}

	
}
