package AWT;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import AWT.AdminFrame.Create.CreateFrameVo;

public class MainRecord extends JFrame implements ActionListener {
	private JFrame f;
	private JTextArea ta = new JTextArea();
	private JScrollPane jpanel;
	private JButton btn1,btn2,btn3;
	private Vector v,col;
	private JTable jta1;
	DefaultTableModel dt;

	MainFrameDAO dao = new MainFrameDAO();
	
	public void mard() {
		f = new JFrame("출근기록");
		f.setSize(640,600);
		f.setLayout(null);
		ta.setEditable(false);
		btn1 = new JButton("뒤로");
		btn1.setBounds(520,20,80,40);
		
		btn2 = new JButton("출근");
		btn2.setBounds(520,80,80,40);
		
		btn3 = new JButton("퇴근");
		btn3.setBounds(520,140,80,40);
		
		col = getColumn();
		dt = new DefaultTableModel(col, 0);
		jta1 = new JTable(dt);
		jpanel = new JScrollPane(jta1);
		
		dao.getUserCommute(dt,MainFrame.userid);

		jpanel.setBounds(20,20,480,520);
		
		f.add(btn1);
		f.add(btn2);
		f.add(btn3);
		f.add(jpanel);
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btn1) {
			f.dispose();
		}
		
		if(e.getSource() == btn2) {
			dao.insertData(new MainFrameVo(MainFrame.userid, btn2.getText()));
			dao.getUserCommute(dt,MainFrame.userid);
		}
		
		if(e.getSource() == btn3) {
			dao.insertData(new MainFrameVo(MainFrame.userid, btn3.getText()));
			dao.getUserCommute(dt,MainFrame.userid);
		}
	}
	
	
	
	public Vector getColumn() {
		Vector col = new Vector();
		col.add("ID");
		col.add("출/퇴근 시간");
		col.add("출/퇴근상태");
		return col;
	}

	
}
