package com.board.control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BoardDAOImpl implements BoardDAO {
	Connection conn;
	PreparedStatement pstmt;
	ResultSet res;

	public static final String dirver = "oracle.jdbc.OracleDriver";
	public static final String url = "jdbc:oracle:thin:@localhost:1521/xe";
	public static final String user = "green";
	public static final String password = "1234";

	public BoardDAOImpl() {
		try {
			Class.forName(dirver);
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public int insert(boardVO vo) {
		// TODO Auto-generated method stub
		try {
			conn = DriverManager.getConnection(url, user, password);
			String sql = "INSERT INTO boardtest (num, title, content, writer, reg_date) VALUES (SEQ_BOARD_NUM.NEXTVAL, ?, ?, ?, SYSDATE)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getName());

			pstmt.executeUpdate();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public int update(boardVO vo) {
		try {
			conn = DriverManager.getConnection(url, user, password);
			String sql = "update boardtest set title = ?, content = ?,reg_date = SYSDATE WHERE NUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getNum());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int delete(boardVO vo) {
		// TODO Auto-generated method stub
		try {
			conn = DriverManager.getConnection(url, user, password);
			String sql = "delete from boardtest where num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getNum());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public boardVO search(boardVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<boardVO> select() {
		// TODO Auto-generated method stub
		List<boardVO> list = new ArrayList<boardVO>();
		try {
			conn = DriverManager.getConnection(url, user, password);
			String sql = "select num, title, writer, reg_date from boardtest order by num desc";
			pstmt = conn.prepareStatement(sql);
			res = pstmt.executeQuery();
			while (res.next()) {
				boardVO vo = new boardVO();
				vo.setNum(res.getInt("num"));
				vo.setTitle(res.getString("title"));
				vo.setName(res.getString("writer"));
				vo.setRegDate(res.getDate("reg_date"));

				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				res.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			pstmt.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void search(String search, String searchString) {
		// TODO Auto-generated method stub
		List<boardVO> list = new ArrayList<boardVO>();
		try {
			conn = DriverManager.getConnection(url, user, password);
			String sql = "select num, title, content, reg_date, writer from boardtest where" + search + "like %";
			pstmt = conn.prepareStatement(sql);
			res = pstmt.executeQuery();
			while (res.next()) {
				boardVO vo = new boardVO();
				vo.setNum(res.getInt("num"));
				vo.setTitle(res.getString("title"));
				vo.setName(res.getString("writer"));
				vo.setRegDate(res.getDate("reg_date"));

				list.add(vo);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				res.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		try {
			pstmt.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

	@Override
	public int getCount() {
		int count = 0;

		try {
			conn = DriverManager.getConnection(url, user, password);
			String sql = "SELECT * FROM boardtest";
			res = pstmt.executeQuery(sql);

			if (res.next()) {
				count = res.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}

	public int selectBoard(boardVO vo) {
		try {
			conn = DriverManager.getConnection(url, user, password);
			String sql = "SELECT writer, title, content from boardtest where num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getNum());
			ResultSet rs = pstmt.executeQuery();

			   if (rs.next()) { // 결과가 있다면
		            // 각 열에서 데이터를 가져옴
		            String writer = rs.getString("writer");
		            String title = rs.getString("title");
		            String content = rs.getString("content");

		            // 가져온 데이터를 vo에 설정
		            vo.setName(writer);
		            vo.setTitle(title);
		            vo.setContent(content);
		        }

			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}try {
			conn.close();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return 0;
	}
}
