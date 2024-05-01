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
	private PreparedStatement pstmt;
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

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BufferedImage[] outputImage(GalleryVO GalleryVO) throws Exception {

		Class.forName(DRIVER);
		conn = DriverManager.getConnection(URL, USER, PASSWORD);
		String sql = "SELECT content FROM GALLERY g"; //쿼리 문 수정 필요.
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

	public int getmino() { // DB 파일 숫자 받아오는 메소드 유저 id 와 묶을 예정. 쿼리문 수정 필요.
		int result = 0;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps1 = conn.prepareStatement("Select MAX(ino) from Gallery");
			rs = ps1.executeQuery();
			rs.next();
			result = rs.getInt(1);
		} catch (Exception e) {
			e.printStackTrace();

		}
		return result;
	}

}