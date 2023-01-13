package AWT.AdminFrame.Staff;

import java.awt.Button;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import AWT.AdminFrame.Create.CreateFrame;
import AWT.AdminFrame.Create.CreateFrameDAO;
import AWT.AdminFrame.Create.CreateFrameVo;

public class StaffFrame extends JFrame implements ActionListener {
	String id;
	private JFrame f;
	private Button btn1, btn2, btn3, btn4, btn5;
	private Label la1;
	private JTable jta1;
	JTextField tf1;
	private Font font1 = new Font("굴림", Font.BOLD, 15);
	private Font font2 = new Font("굴림", Font.PLAIN, 15);
	JScrollPane pane;

	CreateFrame cf = new CreateFrame();
	CreateFrame2 cf2 = new CreateFrame2();
	CreateFrameDAO dao = new CreateFrameDAO();
	CreateFrameVo vo = new CreateFrameVo();

	DefaultTableModel model;
	Vector v, col;

	public StaffFrame() {
	}

	public void stafffr() {
		f = new JFrame("Staff Window");

		f.setSize(640, 600);
		f.setLayout(null);
		f.setResizable(false);

		btn1 = new Button("생성");
		btn1.setFont(font2);
		btn1.setBounds(510, 70, 95, 50);

		btn2 = new Button("삭제");
		btn2.setFont(font2);
		btn2.setBounds(510, 140, 95, 50);

		btn3 = new Button("뒤로가기");
		btn3.setFont(font2);
		btn3.setBounds(510, 210, 95, 50);

		btn4 = new Button("수정");
		btn4.setFont(font2);
		btn4.setBounds(510, 20, 95, 30);

		btn5 = new Button("새로고침");
		btn5.setFont(font2);
		btn5.setBounds(510, 300, 95, 50);

		tf1 = new JTextField();
		tf1.setBounds(70, 20, 420, 30);

		la1 = new Label("ID :");
		la1.setFont(font1);
		la1.setBounds(20, 20, 45, 30);

		v = dao.get_userlist();
		col = getColumn();
		model = new DefaultTableModel(v, col);
		jta1 = new JTable(model);
		pane = new JScrollPane(jta1);
		pane.setBounds(20, 70, 470, 470);
		jta1.setEnabled(false);
		pane.setEnabled(false);
//		v = null;

		f.add(btn1);
		f.add(btn2);
		f.add(btn3);
		f.add(btn4);
		f.add(btn5);

		f.add(la1);
		f.add(tf1);
		f.add(pane);

		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btn4.addActionListener(this);
		btn1.addActionListener(this);
		btn5.addActionListener(this);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn3) { // 취소
			f.dispose();
		}
		if (e.getSource() == btn1) { // 생성
			f.dispose();
			cf.create();

		}
		if (e.getSource() == btn4) { // 수정
			id = tf1.getText();
			if(id == null) {
				JOptionPane.showMessageDialog(null, "ID를 입력해주세요.", "삭제 오류", 1);
			}else {
				f.dispose();
				cf2.create(id);
			}

		}

		if (e.getSource() == btn5) {
			System.out.println(jta1.getRowCount());
		}
		if (e.getSource() == btn2) { // 삭제
			id = tf1.getText();
			if (id.isEmpty()== true) {
				JOptionPane.showMessageDialog(null, "ID를 입력해주세요.", "ID확인 필요", 1);
			} else {
				for (int i = 0; i < jta1.getRowCount(); i++) {
					if(jta1.getValueAt(i , 0).equals(id)) {
				model.removeRow(i);
				dao.delectUser(id);
					}
				}

			}
		}

	}

	public Vector getColumn() {
		Vector col = new Vector();

		col.add("ID");
		col.add("이름");
		col.add("생년월일");
		return col;
	}

	public static void main(String[] args) {
		StaffFrame st = new StaffFrame();
		st.stafffr();
	}
}