package AWT.AdminFrame.Create;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CreateFrameDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521/xe";
	String user = "c##green";
	String password = "green1234";

	Connection conn = null;
	PreparedStatement pstmt = null; // 명령
	ResultSet rs = null;
	Statement st = null;

	public Vector get_userlist() {
		Vector data = new Vector();
		try {
			conn = getConn();
			String sql = "select id,name,birthdate from userlist ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				String id = rs.getString("ID");
				String name = rs.getString("name");
				String birthdate = rs.getString("birthdate");

				Vector<String> row = new Vector<String>();
				row.add(id);
				row.add(name);
				row.add(birthdate);
				data.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			dbClose();
		}

		return data;
	}

	public void userSelectAll(DefaultTableModel model) {
		try {
			conn = getConn();
			st = conn.createStatement();
			rs = st.executeQuery("SELECT * FROM ATTENDANCECHECK order by ATTENDANCE desc");

			// DefaultTableModel에 있는 기존 데이터 지우기
			for (int i = 0; i < model.getRowCount();) {
				model.removeRow(0);
			}

			while (rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(2), rs.getString(3) };

				model.addRow(data); // DefaultTableModel에 레코드 추가
			}

		} catch (SQLException e) {
			System.out.println(e + "=> userSelectAll fail");
		} finally {
			dbClose();
		}
	}// userSelectAll()

	public void delectUser(String id) {
		String sql = "DELETE FROM userlist WHERE ID = ?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id);

			pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "삭제되었습니다.", "삭제", 1);
		} catch (SQLException e) {
			System.out.println("SQL.error" + e.getMessage());
		} finally {
			dbClose();
		}
	}

	public void dellist(String id) {
		String sql = "DELETE FROM attendancecheck WHERE ID = ?";
		try {
			conn = getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("SQL.error" + e.getMessage());
		} finally {
			dbClose();
		}
	}

	public ArrayList<CreateFrameVo> userRead(String id) {
		ArrayList<CreateFrameVo> list = new ArrayList<CreateFrameVo>();
		try {
			conn = getConn();
			String sql = "select * from userlist where ID = '" + id + "'";
			pstmt = conn.prepareStatement(sql);

			// insert, update, delete, select
			rs = pstmt.executeQuery(); // 쿼리 실행
			while (rs.next()) { // 데이터베이스형식과 java 형식이 다름.

				String userID = rs.getString(1);
				String userPassword = rs.getString(2);
				String username = rs.getString(3);
				String birthdate = rs.getString(4);
				String pnum = rs.getString(5);
				String email = rs.getString(6);
				String addr = rs.getString(7);

				list.add(new CreateFrameVo(userID, userPassword, username, birthdate, pnum, email, addr));
			}

		} catch (SQLException e) {
			e.getStackTrace();
		} finally {
			dbClose();
		}
		return list;
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

	public void updateData(CreateFrameVo vo) {
		try {
			String sql = "UPDATE userlist set password = ?,name = ?,birthdate = ?,pnum = ?,email = ?,addr = ? where ID = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.password);
			pstmt.setString(2, vo.name);
			pstmt.setString(3, vo.birthdate);
			pstmt.setString(4, vo.pnum);
			pstmt.setString(5, vo.email);
			pstmt.setString(6, vo.addr);
			pstmt.setString(7, vo.id);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
	}

	public void insertData(CreateFrameVo vo) {
		try {
			conn = getConn();
			String sql = "INSERT INTO userlist(id,password, name, birthdate, pnum,email,addr) values(?, ?, ?, ?, ?, ?,?)";
			// PrparedStatment객체 생성, 인자로 sql문이 주어짐
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.id);
			pstmt.setString(2, vo.password);
			pstmt.setString(3, vo.name);
			pstmt.setString(4, vo.birthdate);
			pstmt.setString(5, vo.pnum);
			pstmt.setString(6, vo.email);
			pstmt.setString(7, vo.addr);

			// executeUpdate : insert, delete, update와 같이 값을 받아오지 않는 쿼리문 실행
			pstmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "회원 가입 완료!", "회원가입", 1);
		} catch (SQLException e) {
//			e.printStackTrace();
			System.out.println("SQL.error" + e.getMessage());

			if (e.getMessage().contains("PRIMARY")) {
				JOptionPane.showMessageDialog(null, "아이디 중복!", "아이디 중복 오류", 1);
			} else {
				JOptionPane.showMessageDialog(null, "정보를 제대로 입력해주세요.", "오류", 1);
			}
		} finally {
			dbClose();
		}
	}

	public void getUserSearch(DefaultTableModel dt, String id) {
		String sql = "SELECT * FROM ATTENDANCECHECK where id = '" + id + "'";

		try {
			conn = getConn();
			st = conn.createStatement();
			rs = st.executeQuery(sql);

			// DefaultTableModel에 있는 기존 데이터 지우기
			for (int i = 0; i < dt.getRowCount();) {
				dt.removeRow(0);
			}

			while (rs.next()) {
				Object data[] = { rs.getString(1), rs.getString(2), rs.getString(3) };

				dt.addRow(data);
			}

		} catch (SQLException e) {
			System.out.println(e + "=> getUserSearch fail");
		} finally {
			dbClose();
		}

	}// getUserSearch()

}
