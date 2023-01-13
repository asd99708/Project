package AWT.Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginFrameDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521/xe";
	String user = "c##green";
	String password = "green1234";
	
	Connection conn = null;
    PreparedStatement pstmt = null; //명령
    ResultSet rs = null;       //결과
	
	public Connection getConn(){
	      Connection con = null;
	      try {
	         Class.forName(driver); //1. 드라이버 로딩
	         con = DriverManager.getConnection(url,user,password); //2. 드라이버 연결
	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      return con;
	   }
	
	public int login(String userID, String userPassword) {
	      String SQL = "SELECT password FROM userlist WHERE id = ?";
	      try {
	         conn = getConn();
	         pstmt = conn.prepareStatement(SQL);
	         pstmt.setString(1, userID);
	         rs = pstmt.executeQuery();
	         if(rs.next()) { 
	            if(rs.getString(1).contentEquals(userPassword)) {
	               return 1; // 로그인 성공시 1 반환
	            }
	            else {
	               return 0; 
	            }
	         }
	         return -1;
	      } catch (Exception e) {
	         e.printStackTrace();
	  
	      }
	      return -2;
	   }
	
	public int logincheck(String userID) {
		String SQL = "select RATING FROM ADMINUSERLIST where id = ?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, userID);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).contentEquals("Admin")) {
					return 1;
				}else {
					return 0;
				}
			}
			return -1;
		} catch(Exception e ) {
			e.printStackTrace();
		}
		return -2;
	}
}
