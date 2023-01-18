package AWT.Admin.Address;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
public class AddressDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521/xe";
	String user = "c##green";
	String password = "green1234";

	Connection conn = null;
	PreparedStatement pstmt = null; // 명령
	ResultSet rs = null;
	Statement st;
	
	
   public void AddressDb(DefaultTableModel model, String search) throws SQLException {
	   conn = getConn();
	   st = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);;
	   
	   AddressGet.addressGet(search);
      ArrayList<AddressVo> addr = AddressGet.addr;
      try {
         st.executeUpdate("drop table Address");
         st.executeUpdate("create table Address(" + "zipNo varchar2(3000), " + "lnmAdres varchar2(3000), "
               + "rnAdres varchar2(3000) " + ")");

         for (int i = 0; i < addr.size(); i++) {
            String zipNo = addr.get(i).zipNo;
            String lnmAdres = addr.get(i).lnmAdres;
            String rnAdres = addr.get(i).rnAdres;
            String cvInsert = "insert into Address values(" + "'" + zipNo + "','" + lnmAdres + "','" + rnAdres 
                  + "'" + ")";
            System.out.println(cvInsert);
            st.executeUpdate(cvInsert);
         }
         addr.removeAll(addr);
      } catch (Exception e) {
         e.printStackTrace();
      } finally {
         try {
            if (st != null) {
               st.close();
            }
            if (conn != null) {
               conn.close();
            }
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
      getUserSearch(model);
   }
   
//   public Vector get_userlist() {
//		Vector data = new Vector();
//		try {
//			conn = getConn();
//			String sql = "select lnmAdres from Address ";
//			pstmt = conn.prepareStatement(sql);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				String lnmAdres = rs.getString("LNMADRES");
//				Vector<String> row = new Vector<String>();
//				row.add(lnmAdres);
//				data.add(row);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//			return null;
//		} finally {
//			dbClose();
//		}
//		
//		return data;
//	}
//   
   public void userSelectAll(DefaultTableModel model) {
		try {
			conn = getConn();
			st = conn.createStatement();
			rs = st.executeQuery("select lnmAdres from address order by lnmAdres desc");

			// DefaultTableModel에 있는 기존 데이터 지우기
			for (int i = 0; i < model.getRowCount();) {
				model.removeRow(0);
			}

			while (rs.next()) {
				Object data[] = { rs.getString(1)};

				model.addRow(data); // DefaultTableModel에 레코드 추가
			}

		} catch (SQLException e) {
			System.out.println(e + "=> userSelectAll fail");
		} finally {
			dbClose();
		}
	}// userSelectAll()
   
   public void getUserSearch(DefaultTableModel dt) {
	    String sql = "SELECT lnmAdres FROM Address";

	    try {
	    	conn = getConn();
	        st = conn.createStatement();
	        rs = st.executeQuery(sql);

	        // DefaultTableModel에 있는 기존 데이터 지우기
	        for (int i = 0; i < dt.getRowCount();) {
	            dt.removeRow(0);
	        }

	        while (rs.next()) {
	            Object data[] = {rs.getString(1)};

	            dt.addRow(data);
	        }

	    } catch (SQLException e) {
	        System.out.println(e + "=> getUserSearch fail");
	    } finally {
	        dbClose();
	    }

	}//getUserSearch()
   
   
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