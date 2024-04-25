package com.user.dao;

import java.sql.*;
import java.util.ArrayList;
import com.user.vo.UserVO;

public class UserDAO {
    private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String URL = "jdbc:oracle:thin:@localhost:1521/xe";
    private static final String USER = "green";
    private static final String PASSWORD = "1234";
    
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    
    public ArrayList<UserVO> list(String id, String pw) {
        ArrayList<UserVO> list = new ArrayList<>();
        String sql = "SELECT * FROM TEST WHERE ID = ? AND PW = ?";
        
        try {
            conDB();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pw);
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                String userId = rs.getString("id");
                String userPw = rs.getString("pw");
                UserVO userVO = new UserVO(userId, userPw);
                list.add(userVO);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeDB();
        }
        
        return list;
    }
    
    public void conDB() {
        try {
            Class.forName(DRIVER);
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void closeDB() {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
