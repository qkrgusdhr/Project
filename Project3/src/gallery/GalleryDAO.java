package gallery;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.imageio.ImageIO;

public class GalleryDAO {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521/xe";
	private static final String USER = "c##green";
	private static final String PASSWORD = "green1234";

	private Connection conn;
	private ResultSet rs;

	public void insertImage(GalleryVO GalleryVO) { // 이미지 파일 업로드 메소드
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = conn.prepareStatement("insert into Gallery values(?,?,?,?)");
			ps.setString(1, "rictsu"); // id 값 받아와 입력.
			ps.setInt(2, GalleryVO.getIno() + 1);
			ps.setString(3, GalleryVO.getImagename());
			FileInputStream fis = new FileInputStream(GalleryVO.getIpath());
			ps.setBinaryStream(4, fis, fis.available());
			int j = ps.executeUpdate();

			fis.close();
			System.out.println(j + " records affected");
			conn.close();
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BufferedImage[] outputImage(GalleryVO GalleryVO) throws Exception {
		Class.forName(DRIVER);
		conn = DriverManager.getConnection(URL, USER, PASSWORD);
		String sql = "SELECT content FROM GALLERY g where id = " + "'rictsu' " + "ORDER BY INO";// 쿼리 문 수정 필요.
		PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);
		rs = ps.executeQuery();
		rs.last();
		int rowCount = rs.getRow();
		rs.beforeFirst();
		BufferedImage[] bi = new BufferedImage[rowCount];
		Blob[] b = new Blob[rowCount];
		int i = 0;
		while (rs.next()) {
			b[i] = rs.getBlob(1);
			bi[i] = ImageIO.read(b[i].getBinaryStream());
			i++;
		}
		rs.close();
		ps.close();
		conn.close();
		return bi;
	}

	public int getCount() { // 해당 유저 총 레코드 수 구하기 유저 id 와 묶을 예정. 쿼리문 수정 필요.
		int result = 0;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps1 = conn.prepareStatement("SELECT count(*) FROM GALLERY g WHERE id = (?)");
			ps1.setString(1, "rictsu");
			rs = ps1.executeQuery();
			rs.next();
			result = rs.getInt(1);

			rs.close();
			conn.close();
			ps1.close();
		} catch (Exception e) {
			e.printStackTrace();

		}

		return result;
	}

	public int getmaxIno() {

		int ino = 0;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "SELECT MAX(INO) FROM GALLERY g where id = " + "'rictsu' ";
			PreparedStatement ps3 = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = ps3.executeQuery();
			rs.next();
			ino = rs.getInt("Max(ino)");
			rs.close();
			conn.close();
			ps3.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ino;
	}

	public int[] getDbino() {
		int[] ino = null;
		int rowCount;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			String sql = "SELECT INO FROM GALLERY g WHERE id = " + "'rictsu' " + "ORDER BY INO";
			PreparedStatement ps4 = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			rs = ps4.executeQuery();
			rs.last();
			rowCount = rs.getRow();
			rs.beforeFirst();
			ino = new int[rowCount];
			int i = 0;
			while (rs.next()) {
				ino[i] = rs.getInt(1);
				i++;
			}
			rs.close();
			conn.close();
			ps4.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ino;
	}

	public void deleteImage(int pino) {
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps2 = conn.prepareStatement("delete FROM GALLERY g WHERE id = (?) AND INO = (?)");
			ps2.setString(1, "rictsu");
			ps2.setInt(2, pino); // 삭제하고 싶은 이미지 ino 값 구해서 넣어야함.
			rs = ps2.executeQuery();
//			System.out.println("btn " + pino + " deleted!!!"); //INO 값으로 삭제 확인
			conn.close();
			ps2.close();
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}