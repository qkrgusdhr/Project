package com.join.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {	
	private static DBConnect dbconn = new DBConnect();
	// @db서버주소:리스너번호/sid 
	private String url = "jdbc:oracle:thin:@localhost:1521/xe";
	
	private DBConnect() {}
	
	public static DBConnect getInstance() {
		return dbconn;	// 모든 db작업을 이 conn 객체로 실행
	}
	
	public Connection conn() {
		try {
			//드라이버 로드
			Class.forName("oracle.jdbc.OracleDriver");
			
			//세션수립 (로그인) 
			return DriverManager.getConnection(url, "hr", "hr");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}