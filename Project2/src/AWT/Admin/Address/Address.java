package AWT.Admin.Address;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import AWT.AdminFrame.Create.CreateFrame;
import AWT.AdminFrame.Staff.CreateFrame2;

public class Address extends JFrame implements ActionListener,MouseListener {

	private JFrame f;
	private JLabel la1;
	private JTextField tf1;
	private JButton btn1;
	private JTable jta1;
	private JScrollPane pane;
	private Vector v, col;
	private String revalue;
	DefaultTableModel dt;
	AddressDAO dao = new AddressDAO();

	public void findAddress() {
		f = new JFrame("Staff Window");

		f.setSize(400, 600);
		f.setLayout(null);
		f.setResizable(false);

		la1 = new JLabel("주소 : ");
		la1.setBounds(20, 20, 50, 30);

		tf1 = new JTextField();
		tf1.setBounds(60, 20, 240, 30);

		btn1 = new JButton("검색");
		btn1.setBounds(310, 20, 60, 30);

		col = getColumn();
		dt = new DefaultTableModel(col, 0) {
			public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		jta1 = new JTable(dt);
		pane = new JScrollPane(jta1);

		dao.userSelectAll(dt);

		pane.setBounds(20, 70, 345, 470);

		f.add(la1);
		f.add(tf1);
		f.add(btn1);
		f.add(pane);

		btn1.addActionListener(this);
		jta1.addMouseListener(this);
		f.setLocationRelativeTo(null);
		f.setVisible(true);

	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn1) {
			String search = tf1.getText();
			try {
				dao.AddressDb(dt,search);
				dao.getUserSearch(dt);
				if (dt.getRowCount() > 0)
					jta1.setRowSelectionInterval(0, 0);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		}
	}

	public Vector getColumn() {
		Vector col = new Vector();
		col.add("도로명 주소");
		return col;
	}

	public static void main(String[] args) {
		Address ad = new Address();
		ad.findAddress();
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==jta1) {
			if(e.getClickCount()==2) {
				int row = jta1.getSelectedRow();
				int col = jta1.getSelectedColumn();
				Object value = jta1.getValueAt(row, col);
				revalue = (String) value;
				f.dispose();
				CreateFrame.tf6.setText(revalue);
				CreateFrame2.tf6.setText(revalue);
				
			}
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
	}
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	}
	@Override
	public void mouseExited(MouseEvent e) {
	}
}
