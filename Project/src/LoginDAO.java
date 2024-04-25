import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.naming.Context;
import javax.sql.DataSource;

public class LoginDAO {
	private PreparedStatement pstmt;
	
	Connection con;
	
	public void getCon() {
		
		try {
			Context initctx = new InitialContext();
			Context envctx = (Context) initctx.lookup("java:comp/env");
			DataSource dsc = (DataSource) envctx.lookup("jdbc/pool");
			con = dsc.getConnection();
		}	catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
