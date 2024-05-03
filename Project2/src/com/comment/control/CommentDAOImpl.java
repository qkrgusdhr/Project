package com.comment.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentDAOImpl implements CommentDAO{
	Connection conn;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public static final String dirver = "oracle.jdbc.OracleDriver";
	public static final String url = "jdbc:oracle:thin:@localhost:1521/xe";
	public static final String user = "green";
	public static final String password = "1234";
	
	public CommentDAOImpl() {
		try {
			Class.forName(dirver);
		}catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public int insert(CommentVO vo) {
		
		try {
			conn = DriverManager.getConnection(url, user, password);
			String sql = "INSERT INTO BOARDCOMMENT (NUM, POSTNUM, WRITER, CONTENT, REG_DATE) VALUES (SEQ_COMMENT_NUM.NEXTVAL,?,?,?,SYSDATE)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, vo.getPostNum());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getContent());
			
			pstmt.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
		
	}
	
	public List<CommentVO> select(int PostNum){
		List<CommentVO> list = new ArrayList<CommentVO>();
		try {
			conn = DriverManager.getConnection(url, user, password);
			String sql = "SELECT * FROM BOARDCOMMENT WHERE POSTNUM = ? order by reg_date desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, PostNum);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CommentVO vo = new CommentVO();
				vo.setNum(rs.getInt("NUM"));
				vo.setWriter(rs.getString("WRITER"));
				vo.setContent(rs.getString("CONTENT"));
				vo.setReg_date(rs.getDate("REG_DATE"));
				
				list.add(vo);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}try {
			pstmt.close();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		return list;
		
	}

	@Override
	public int delete(CommentVO vo) {
		// TODO Auto-generated method stub
		try {
			conn = DriverManager.getConnection(url, user, password);
			String sql = "DELETE FROM BOARDCOMMENT WHERE POSTNUM =? AND NUM = ? AND WRITER = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getPostNum());
			pstmt.setInt(2, vo.getNum());
			pstmt.setString(3, vo.getWriter());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	
	}

	
	
	

	

