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
	public int insert(BoardVO vo) {
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
	public int update(BoardVO vo) {
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
	public int delete(BoardVO vo) {
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
	public BoardVO search(BoardVO vo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BoardVO> select() {
		// TODO Auto-generated method stub
		List<BoardVO> list = new ArrayList<BoardVO>();
		try {
			conn = DriverManager.getConnection(url, user, password);
			String sql = "select num, title, writer, reg_date, likecnt from boardtest order by num desc";
			pstmt = conn.prepareStatement(sql);
			res = pstmt.executeQuery();
			while (res.next()) {
				BoardVO vo = new BoardVO();
				vo.setNum(res.getInt("num"));
				vo.setTitle(res.getString("title"));
				vo.setName(res.getString("writer"));
				vo.setRegDate(res.getDate("reg_date"));
				vo.setLikeCnt(res.getInt("likecnt"));
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
	public List<BoardVO> search(String search, String searchString) {
		// TODO Auto-generated method stub
		List<BoardVO> list = new ArrayList<BoardVO>();
		try {
			conn = DriverManager.getConnection(url, user, password);
			String sql =  "SELECT num, title, content, reg_date, writer FROM boardtest WHERE " + search + " LIKE ?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + searchString + "%");
			res = pstmt.executeQuery();
			while (res.next()) {
				BoardVO vo = new BoardVO();
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
		return list;
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

	public int selectBoard(BoardVO vo) {
		try {
			conn = DriverManager.getConnection(url, user, password);
			String sql = "SELECT L.id, B.TITLE, B.CONTENT, B.LIKECNT FROM boardTest B JOIN LOGIN L ON B.writer = L.id WHERE B.num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getNum());
			ResultSet rs = pstmt.executeQuery();

			   while (rs.next()) { // 결과가 있다면
		            // 각 열에서 데이터를 가져옴
		            String writer = rs.getString("id");
		            String title = rs.getString("title");
		            String content = rs.getString("content");
		            int LikeCnt = rs.getInt("likecnt");
		            // 가져온 데이터를 vo에 설정
		            vo.setName(writer);
		            vo.setTitle(title);
		            vo.setContent(content);
		            vo.setLikeCnt(LikeCnt);
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
	
	public String SearchUser(BoardVO vo) {
		String writer = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
			String sql = "SELECT L.id FROM boardTest B JOIN LOGIN L ON B.writer = L.id WHERE B.num = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getNum());
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				writer = rs.getString("id");
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return writer;
	}

	@Override
	public int LikeInCrease(BoardVO vo) {
		// TODO Auto-generated method stub
		try {
			conn = DriverManager.getConnection(url, user, password);
			String sql = "UPDATE BOARDTEST SET LIKECNT = LIKECNT + 1 WHERE NUM = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getNum());
			pstmt.executeUpdate();
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return 0; 
	}
	
	
}
