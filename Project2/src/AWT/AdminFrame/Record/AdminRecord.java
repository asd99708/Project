package AWT.AdminFrame.Record;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import AWT.AdminFrame.Create.CreateFrameDAO;

public class AdminRecord extends JFrame implements ActionListener {
	private JFrame f;
	private JScrollPane jpanel;
	private JButton btn1, btn2, btn3, btn4;
	private Vector v, col;
	private JLabel la1;
	private JTextField jtf1 = new JTextField(30);
	private JTable jta1;
	DefaultTableModel dt;

	CreateFrameDAO dao = new CreateFrameDAO();

	public void mard() {
		f = new JFrame("출근기록");
		f.setSize(640, 600);
		f.setLayout(null);

		la1 = new JLabel("ID : ");
		la1.setBounds(20, 20, 80, 30);
		jtf1.setBounds(50, 20, 450, 30);
		btn3 = new JButton("검색");
		btn3.setBounds(520, 20, 80, 30);

		btn1 = new JButton("뒤로");
		btn1.setBounds(520, 60, 80, 40);

		btn2 = new JButton("새로고침");
		btn2.setBounds(520, 120, 80, 40);

		btn4 = new JButton("삭제");
		btn4.setBounds(520, 180, 80, 40);

		col = getColumn();
		dt = new DefaultTableModel(col, 0) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		jta1 = new JTable(dt);
		jpanel = new JScrollPane(jta1);

		dao.userSelectAll(dt);

		jpanel.setBounds(20, 60, 480, 480);

		f.add(la1);
		f.add(jtf1);
		f.add(btn1);
		f.add(btn2);
		f.add(btn3);
		f.add(btn4);
		f.add(jpanel);
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btn4.addActionListener(this);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn1) {
			f.dispose();
		}

		if (e.getSource() == btn2) {
			dao.userSelectAll(dt);
		}

		if (e.getSource() == btn3) {
			String id = jtf1.getText();
			dao.getUserSearch(dt, id);
		}
		if (e.getSource() == btn4) {
			String id = jtf1.getText();
			dao.dellist(id);
			dao.userSelectAll(dt);
		}
	}

	public Vector getColumn() {
		Vector col = new Vector();
		col.add("ID");
		col.add("출/퇴근 시간");
		col.add("출/퇴근상태");
		return col;
	}

	public static void main(String[] args) {
		AdminRecord adr = new AdminRecord();
		adr.mard();
	}

}
