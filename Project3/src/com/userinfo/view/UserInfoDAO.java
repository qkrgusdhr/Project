package com.userinfo.view;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.join.VO.JoinVo;

public class UserInfoDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String user = "c##green";
	String password = "GREEN1234";
	
	private Connection conn;
	private PreparedStatement pstmt;
	
	public boolean insertData(JoinVo joinVo) {
		boolean result = false;
		pstmt = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			String sql = "INSERT INTO member(id, pwd, name, tel, dogname, dogsex, dogbirth)";
			sql += " values(?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			closeResources();
		}
		return result;
	}
	}
}
