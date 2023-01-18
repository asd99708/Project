package AWT.AdminFrame.Staff;

import java.awt.Choice;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import AWT.Admin.Address.Address;
import AWT.AdminFrame.Create.CreateFrameDAO;
import AWT.AdminFrame.Create.CreateFrameVo;

public class CreateFrame2 extends JFrame implements ActionListener, MouseListener {
	private JFrame f;
	private JButton btn1;
	private JLabel la1, la2, la3, la4, la5, la6, la7;
	private TextField tf1 = new TextField(30), tf2 = new TextField(30), tf3 = new TextField(30),
			tf4 = new TextField(30), tf5 = new TextField(30);
	public static TextField tf6 = new TextField(30);

	private Choice year = new Choice();
	private Choice month = new Choice();
	private Choice day = new Choice();

	private String stringyear, stringmonth, stringday;

	LocalDate now = LocalDate.now();
	CreateFrameDAO dao = new CreateFrameDAO();
	CreateFrameVo vo = new CreateFrameVo();
	Vector v, col;
	Address adr = new Address();

	public CreateFrame2() {
	}

	public void create(String id) {
		f = new JFrame("Create Frame");

		f.setSize(400, 380);
		f.setLayout(null);
		f.setResizable(false);
		f.setBackground(Color.white);

		la1 = new JLabel("Account : ");
		la1.setBounds(0, 40, 90, 20);
		tf1.setBounds(100, 40, 200, 20);
		tf1.setFocusable(false);
		la1.setHorizontalAlignment(JLabel.RIGHT);

		la2 = new JLabel("PassWord : ");
		la2.setBounds(0, 80, 90, 20);
		tf2.setBounds(100, 80, 200, 20);
		la2.setHorizontalAlignment(JLabel.RIGHT);

		la3 = new JLabel("이름 : ");
		la3.setBounds(0, 120, 90, 20);
		tf3.setBounds(100, 120, 200, 20);
		la3.setHorizontalAlignment(JLabel.RIGHT);

		la7 = new JLabel("생년월일 : ");
		la7.setBounds(0, 160, 90, 20);
		la7.setHorizontalAlignment(JLabel.RIGHT);
		for (int i = now.getYear(); i >= 1950; i--) { // 년도 범위 지정
			year.add(String.valueOf(i));
		}
		for (int i = 1; i <= 12; i++) { // 월 범위 지정
			month.add(String.valueOf(i));
		}
		for (int i = 1; i <= 31; i++) { // 일 범위 지정
			day.add(String.valueOf(i));
		}

		year.setBounds(100, 160, 60, 50);
		month.setBounds(170, 160, 60, 50);
		day.setBounds(240, 160, 60, 50);

		la4 = new JLabel("전화번호 : ");
		la4.setBounds(0, 200, 90, 20);
		tf4.setBounds(100, 200, 200, 20);
		la4.setHorizontalAlignment(JLabel.RIGHT);

		la5 = new JLabel("Email : ");
		la5.setBounds(0, 240, 90, 20);
		tf5.setBounds(100, 240, 200, 20);
		la5.setHorizontalAlignment(JLabel.RIGHT);

		la6 = new JLabel("주소 : ");
		la6.setBounds(0, 280, 90, 20);
		la6.setHorizontalAlignment(JLabel.RIGHT);
		tf6.setBounds(100, 280, 200, 20);
		tf6.setFocusable(false);

		btn1 = new JButton("수정");
		btn1.setBounds(315, 280, 60, 20);

		f.add(la1); // 아이디
		f.add(tf1);

		f.add(la2); // 비밀번호
		f.add(tf2);

		f.add(la3); // 이름
		f.add(tf3);

		f.add(la4); // 전화번호
		f.add(tf4);

		f.add(la5); // 이메일
		f.add(tf5);

		f.add(la6); // 주소
		f.add(tf6);
		f.add(btn1);

		f.add(la7); // 생년월일
		f.add(year);
		f.add(month);
		f.add(day);

		year.setVisible(true);
		month.setVisible(true);
		day.setVisible(true);
		btn1.addActionListener(this);
		tf6.addMouseListener(this);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		startFrame(id);

	}

	public void startFrame(String id1) {
		ArrayList<CreateFrameVo> arr = new ArrayList<CreateFrameVo>();
		arr = dao.userRead(id1);

		String id = arr.get(0).getId();
		tf1.setText(id);
		String password = arr.get(0).getPassword();
		tf2.setText(password);
		String name = arr.get(0).getName();
		tf3.setText(name);
		String birthdate = arr.get(0).getBirthdate();
		year.select(birthdate.substring(1, 4));
		month.select(birthdate.substring(5, 6));
		day.select(birthdate.substring(7, 8));
		String pnum = arr.get(0).getPnum();
		tf4.setText(pnum);
		String email = arr.get(0).getEmail();
		tf5.setText(email);
		String addr = arr.get(0).getAddr();
		tf6.setText(addr);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn1) {
			
			stringyear = year.getSelectedItem();
			
			if (Integer.parseInt(month.getSelectedItem()) < 10) {
				stringmonth = "0" + month.getSelectedItem();
			} else {
				stringmonth = month.getSelectedItem();
			}
			if (Integer.parseInt(day.getSelectedItem()) < 10) {
				stringday = "0" + day.getSelectedItem();
			} else {
				stringday = day.getSelectedItem();
			}

			String id = tf1.getText();
			String password = tf2.getText();
			String name = tf3.getText();
			String birthdate = (stringyear + stringmonth + stringday);
			String pnum = tf4.getText();
			String email = tf5.getText();
			String addr = tf6.getText();

			Pattern passPattern1 = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$"); // 8자 영문+특문+숫자
			Matcher passMatcher = passPattern1.matcher(password);

			Pattern pnumPattern1 = Pattern.compile("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$"); // 휴대전화 형식
			Matcher pnumMatcher = pnumPattern1.matcher(pnum);

			Pattern emailPattern = Pattern.compile("\\w+@\\w+\\.\\w+(\\.\\w+)?"); // 이메일 형식
			Matcher emailMatcher = emailPattern.matcher(email);
			if (!passMatcher.find()) {
				JOptionPane.showMessageDialog(null, "비밀번호는 영문+특수문자+숫자 8~20자로 구성되어야 합니다", "비밀번호 오류", 1);

			} else {
				if (!pnumMatcher.find()) {
					JOptionPane.showMessageDialog(null, "전화번호를 제대로 입력해주셔야 합니다.", "전화번호 오류", 1);

				} else {
					if (!emailMatcher.find()) {
						JOptionPane.showMessageDialog(null, "이메일을 다시 입력해주세요.", "이메일 오류", 1);
					} else {
						dao.updateData(new CreateFrameVo(id, password, name, birthdate, pnum, email, addr));
						f.dispose();
						StaffFrame st = new StaffFrame();
						st.stafffr();
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

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == tf6) {
			if (e.getClickCount() == 2) {
				adr.findAddress();
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
