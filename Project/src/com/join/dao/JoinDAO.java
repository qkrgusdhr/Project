package com.join.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.join.VO.JoinVo;

public class JoinDAO {
	private static final String driver = "oracle.jdbc.driver.OracleDriver";
	private static final String url = "jdbc:oracle:thin:@localhost:1521/xe";
	private static final String user = "green";
	private static final String password = "1234";
	
	private Connection conn;
	private PreparedStatement pstmt;
	
	
	public int insertData(JoinVo joinVo) {
		int result = 0;
		pstmt = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			String sql = "INSERT INTO userinfotest(id, pw)";
			sql += " values(?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,joinVo.getId());
			pstmt.setString(2,joinVo.getPw());
			result = pstmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			closeResources();
		}
		return result;
	}
	
	private void closeResources() {
        try {
            if (pstmt != null) {
                pstmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	public boolean isIdExist(String id) {
		boolean result = false;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			String sql = "SELECT ID FROM USERINFOTEST WHERE ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = true;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			closeResources();
		}
		return result;
	}
}
