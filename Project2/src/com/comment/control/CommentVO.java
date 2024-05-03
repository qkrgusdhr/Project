package com.comment.control;

import java.util.Date;

public class CommentVO {
	private int PostNum;
	private int num;
	private String Writer;
	private String Content;
	private Date reg_date;
	
	
	public int getPostNum() {
		return PostNum;
	}
	public void setPostNum(int postNum) {
		PostNum = postNum;
	}
	public String getWriter() {
		return Writer;
	}
	public void setWriter(String writer) {
		Writer = writer;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
}
