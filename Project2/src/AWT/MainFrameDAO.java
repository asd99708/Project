package AWT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import AWT.AdminFrame.Create.CreateFrameVo;

public class MainFrameDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521/xe";
	String user = "c##green";
	String password = "green1234";

	Connection conn = null;
	PreparedStatement pstmt = null; // 명령
	ResultSet rs = null;
	Statement st = null;
	MainFrameVo vo = new MainFrameVo();

	public void getUserCommute(DefaultTableModel dt, String id) {
		String sql = "SELECT * FROM ATTENDANCECHECK where id = '" + id + "' order by ATTENDANCE";

		try {
			conn = getConn();
			st = conn.createStatement();
			rs = st.executeQuery(sql);

			// DefaultTableModel에 있는 기존 데이터 지우기
			for (int i = 0; i < dt.getRowCount();) {
				dt.removeRow(0);
			}

			while (rs.next()) {
				Object data[] = { rs.getString(1),rs.getString(2),rs.getString(3) };
				dt.addRow(data);
			}

		} catch (SQLException e) {
			System.out.println(e + "=> getUserSearch fail");
		} finally {
			dbClose();
		}

	}// getUserCommute()
	
	public void insertData(MainFrameVo vo) {
		try {
			conn = getConn();
			String sql = "INSERT INTO ATTENDANCECHECK(id,COMMUTE) values(?, ?) ";
			// PrparedStatment객체 생성, 인자로 sql문이 주어짐
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.id);
			pstmt.setString(2, vo.commute);

			// executeUpdate : insert, delete, update와 같이 값을 받아오지 않는 쿼리문 실행
			pstmt.executeUpdate();
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("SQL.error" + e.getMessage());
		} finally {
			dbClose();
		}
	}
	

	public Connection getConn() {
		Connection con = null;
		try {
			Class.forName(driver); // 1. 드라이버 로딩
			con = DriverManager.getConnection(url, user, password); // 2. 드라이버 연결

		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public void dbClose() {
		try {
			if (rs != null)
				rs.close();
			if (st != null)
				st.close();
			if (pstmt != null)
				pstmt.close();
		} catch (Exception e) {
			System.out.println(e + "=> dbClose fail");
		}
	}
	
}
