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
<<<<<<< HEAD
	
	//회원가입
	public boolean insertData(JoinVo joinVo) {
		boolean result = false;
=======

	public int insertData(JoinVo joinVo) {
		int result = 0;
>>>>>>> branch 'master' of https://github.com/qkrgusdhr/Project.git
		pstmt = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			String sql = "INSERT INTO member(id, pwd, name, tel, dogname, dogsex, dogbirth)";
			sql += " values(?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, joinVo.getId());
			pstmt.setString(2, joinVo.getPwd());
			pstmt.setString(3, joinVo.getName());
			pstmt.setString(4, joinVo.getTel());
			pstmt.setString(5, joinVo.getDogname());
			pstmt.setString(6, joinVo.getDogsex());
			pstmt.setString(7, joinVo.getDogbirth());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return result;
	}

	private void closeResources() {
<<<<<<< HEAD
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
	//마이페이지
	public boolean changeData(JoinVo joinVo) {
		boolean result = false;
		pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			String sql = "SELECT name, dogname, dogbirth, dogsex, tel FROM MEMBER WHERE id = ?";
			;
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,joinVo.getId());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String name = rs.getString("name");
				String dogname = rs.getString("dogname");
				String dogbirth = rs.getString("dogbirth");
				String dogsex = rs.getString("dogsex");
				String tel = rs.getString("tel");
				
				joinVo.setName(name);
				joinVo.setDogname(dogname);
				joinVo.setDogbirth(dogbirth);
				joinVo.setDogsex(dogsex);
				joinVo.setTel(tel);
			}
			result = true; 
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			closeResources();
		}
		return result;
	}
	
	//회원탈퇴
	public boolean deleteId(String id) {
		boolean result = false;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			String sql = "DELETE FROM member WHERE ID = ?";
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
	
	//비밀번호 찾기
	public boolean foundPassword(String id, String dogname) {
		boolean result = false;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			String sql = "SELECT PWD FROM member WHERE ID = '?' AND DOGNAME = '?'";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, dogname);
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


	
	//아이디 중복
=======
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

>>>>>>> branch 'master' of https://github.com/qkrgusdhr/Project.git
	public boolean isIdExist(String id) {
		boolean result = false;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			String sql = "SELECT ID FROM member WHERE ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return result;
	}

<<<<<<< HEAD
	

}
=======
	public boolean changeData(JoinVo joinVo) {
		boolean result = false;
		pstmt = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			String sql = "UPDATE MEMBER SET pwd = ?, name = ?, tel = ?, dogname = ?, dogsex = ?, dogbirth = ? WHERE ID = ?";
			;

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, joinVo.getPwd());
			pstmt.setString(2, joinVo.getName());
			pstmt.setString(3, joinVo.getTel());
			pstmt.setString(4, joinVo.getDogname());
			pstmt.setString(5, joinVo.getDogsex());
			pstmt.setString(6, joinVo.getDogbirth());
			pstmt.setString(7, joinVo.getId());
			pstmt.executeUpdate();

			result = true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return result;
	}
	public String SearchPassword(JoinVo joinVo) {
		String pwd = null;
		ResultSet rs = null;
		pstmt = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
			String sql = "SELECT PWD FROM MEMBER WHERE ID = ? AND DOGNAME = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, joinVo.getId());
			pstmt.setString(2, joinVo.getDogname());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pwd = rs.getString("PWD");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return pwd;
	}

}
>>>>>>> branch 'master' of https://github.com/qkrgusdhr/Project.git
