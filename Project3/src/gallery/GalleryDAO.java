package gallery;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;

public class GalleryDAO { // 추후 DB 연동시 쿼리문 전체 수정 필요.
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521/xe";
	private static final String USER = "c##green";
	private static final String PASSWORD = "green1234";

	private Connection conn;
	private ResultSet rs;
	private PreparedStatement pstmt;

	public void insertImage(GalleryVO GalleryVO) { // 이미지 파일 업로드 메소드
		try {
			conDB();
			PreparedStatement ps = conn.prepareStatement("insert into Gallery values(?,?,?,?)");
			ps.setString(1, GalleryVO.getId()); // id 값 받아와 입력. -- 제거 후 유저 테이블이랑 연결.
			ps.setInt(2, GalleryVO.getIno());
			ps.setString(3, GalleryVO.getImagename());
			FileInputStream fis = new FileInputStream(GalleryVO.getIpath());
			ps.setBinaryStream(4, fis, fis.available());
			ps.executeQuery();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}

	public BufferedImage[] outputImage(GalleryVO GalleryVO) {
		BufferedImage[] bi = null;
		try {
			conDB();
			String sql = "SELECT content FROM GALLERY g where id = '" + GalleryVO.getId() + "' ORDER BY INO";
			PreparedStatement pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = pstmt.executeQuery();
			rs.last();
			int rowCount = rs.getRow();
			rs.beforeFirst();
			if (rowCount != 0) {
				bi = new BufferedImage[rowCount];
				Blob[] b = new Blob[rowCount];
				int i = 0;
				while (rs.next()) {
					b[i] = rs.getBlob(1);
					bi[i] = ImageIO.read(b[i].getBinaryStream());
					i++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return bi;
	}

	public int getCount(GalleryVO GalleryVO) { // 해당 유저 총 레코드 수 구하기 유저 id 와 묶을 예정. 쿼리문 수정 필요.
		int result = 0;
		try {
			conDB();
			PreparedStatement ps = conn.prepareStatement("SELECT count(*) FROM GALLERY g WHERE id = (?)");
			ps.setString(1, GalleryVO.getId());
			rs = ps.executeQuery();
			rs.next();
			result = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}

	public int getmaxIno(GalleryVO GalleryVO) {

		int ino = 0;
		try {
			conDB();
			String sql = "SELECT MAX(INO) FROM GALLERY g where id = '" + GalleryVO.getId() + "'";
			PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = ps.executeQuery();
			rs.next();
			ino = rs.getInt("Max(ino)");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return ino;
	}

	public int[] getDbino(GalleryVO GalleryVO) {
		int[] ino = null;
		int rowCount;
		try {
			conDB();
			String sql = "SELECT INO FROM GALLERY g WHERE id = '" + GalleryVO.getId() + "' ORDER BY INO";
			PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = ps.executeQuery();
			rs.last();
			rowCount = rs.getRow();
			rs.beforeFirst();
			ino = new int[rowCount];
			int i = 0;
			while (rs.next()) {
				ino[i] = rs.getInt(1);
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return ino;
	}

	public String getFileName(int ino) {
		String result = null;
		try {
			conDB();
			String sql = "SELECT IMAGENAME FROM GALLERY g WHERE iNO = " + ino;
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next())
				result = rs.getString(1);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}

	public void deleteImage(String Cuser, int ino) {
		try {
			conDB();
			PreparedStatement ps = conn.prepareStatement("delete FROM GALLERY g WHERE id = (?) AND INO = (?)");
			ps.setString(1, Cuser);
			ps.setInt(2, ino);
			rs = ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

	}

	public void deleteAllImage(String currentU) {
		try {
			conDB();
			PreparedStatement ps = conn.prepareStatement("delete FROM GALLERY g WHERE id = (?)");
			ps.setString(1, currentU);
			rs = ps.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

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
			if (rs != null)
				rs.close();
			if (pstmt != null)
				pstmt.close();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}