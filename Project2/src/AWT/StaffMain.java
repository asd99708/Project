package AWT;

import java.awt.Choice;
import java.awt.Component;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import AWT.AdminFrame.Create.CreateFrameDAO;
import AWT.AdminFrame.Create.CreateFrameVo;
import AWT.AdminFrame.Staff.StaffFrame;

public class StaffMain extends JFrame implements ActionListener {
	private JFrame f;
	private JButton btn1, btn2;
	private JLabel la1, la2, la3, la4, la5, la6, la7, la8;
	private TextField tf1 = new TextField(30), tf2 = new TextField(30), tf3 = new TextField(30),
			tf4 = new TextField(30), tf5 = new TextField(30), tf6 = new TextField(30), tf7 = new TextField(30),
			tf8 = new TextField(30);

	private Choice year = new Choice();
	private Choice month = new Choice();
	private Choice day = new Choice();
	
	private String stringmonth, stringday;
	
	LocalDate now = LocalDate.now();
	CreateFrameDAO dao = new CreateFrameDAO();

	public void staffm() {
		f = new JFrame("내 정보");

		f.setSize(450, 450);
		f.setLayout(null);
		f.setResizable(false);

		la1 = new JLabel("Account : ");
		la1.setBounds(0, 40, 115, 20);
		tf1.setBounds(115, 40, 225, 20);
		tf1.requestFocus();
		la1.setHorizontalAlignment(JLabel.RIGHT);
		tf1.setEditable(false);
		tf1.setFocusable(false);

		la2 = new JLabel("PassWord : ");
		la2.setBounds(0, 80, 115, 20);
		tf2.setBounds(115, 80, 225, 20);
		la2.setHorizontalAlignment(JLabel.RIGHT);
		tf2.setEchoChar('*');

		la8 = new JLabel("PassWord Check : ");
		la8.setBounds(0, 120, 115, 20);
		tf7.setBounds(115, 120, 225, 20);
		la8.setHorizontalAlignment(JLabel.RIGHT);
		tf7.setEchoChar('*');

		la3 = new JLabel("이름 : ");
		la3.setBounds(0, 160, 115, 20);
		tf3.setBounds(115, 160, 225, 20);
		la3.setHorizontalAlignment(JLabel.RIGHT);
		tf3.setEditable(false);
		tf3.setFocusable(false);

		la7 = new JLabel("생년월일 : ");
		la7.setBounds(0, 200, 90, 20);
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
		year.setBounds(100, 200, 60, 50);
		month.setBounds(170, 200, 60, 50);
		day.setBounds(240, 200, 60, 50);

		la4 = new JLabel("전화번호 : ");
		la4.setBounds(0, 240, 90, 20);
		tf4.setBounds(115, 240, 225, 20);
		la4.setHorizontalAlignment(JLabel.RIGHT);

		la5 = new JLabel("Email : ");
		la5.setBounds(0, 280, 90, 20);
		tf5.setBounds(115, 280, 225, 20);
		la5.setHorizontalAlignment(JLabel.RIGHT);

		la6 = new JLabel("주소 : ");
		la6.setBounds(0, 320, 90, 20);
		la6.setHorizontalAlignment(JLabel.RIGHT);
		tf6.setBounds(115, 320, 225, 20);

		btn1 = new JButton("수정");
		btn1.setBounds(350, 360, 60, 20);

		btn2 = new JButton("OK");
		btn2.setBounds(350, 120, 60, 20);

		f.add(la1);
		f.add(tf1);

		f.add(la2);
		f.add(tf2);

		f.add(la3);
		f.add(tf3);

		f.add(la4);
		f.add(tf4);

		f.add(la5);
		f.add(tf5);

		f.add(la6);
		f.add(tf6);

		f.add(la7);
		f.add(year);
		f.add(month);
		f.add(day);

		f.add(la8);
		f.add(tf7);
		f.add(btn2);

		f.add(btn1);

		startFrame(MainFrame.userid);
		
		year.setVisible(true);
		month.setVisible(true);
		day.setVisible(true);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		btn1.addActionListener(this);
		btn2.addActionListener(this);

	}

	public void startFrame(String id1) {
		ArrayList<CreateFrameVo> arr = new ArrayList<CreateFrameVo>();
		arr = dao.userRead(id1);
		
		String id = arr.get(0).getId();
		tf1.setText(id);
		String password = arr.get(0).getPassword();
		tf2.setText(password);
		tf7.setText(password);
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
		if (e.getSource() == btn2) {
			if (tf2.getText().equals("")&& tf7.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "비밀번호를 다시 확인 해주세요.", "PassWord Check", JOptionPane.WARNING_MESSAGE);
			}else if(tf2.getText().equals(tf7.getText())) {
				JOptionPane.showMessageDialog(null, "비밀번호가 일치합니다.", "PassWord Check", JOptionPane.WARNING_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "비밀번호를 다시 확인 해주세요.", "PassWord Check", JOptionPane.WARNING_MESSAGE);
			}
		}
		if(e.getSource() == btn1) {
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
			String birthdate = (year.getSelectedItem() + stringmonth + stringday);
			String pnum = tf4.getText();
			String email = tf5.getText();
			String addr = tf6.getText();

			Pattern passPattern1 = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$"); //8자 영문+특문+숫자
			Matcher passMatcher = passPattern1.matcher(password);
			
			Pattern pnumPattern1 = Pattern.compile("^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$"); // 휴대전화 형식
			Matcher pnumMatcher = pnumPattern1.matcher(pnum);
			
			Pattern emailPattern = Pattern.compile("\\w+@\\w+\\.\\w+(\\.\\w+)?");	// 이메일 형식
			Matcher emailMatcher = emailPattern.matcher(email);
			if (!passMatcher.find()) {
				JOptionPane.showMessageDialog(null, "비밀번호는 영문+특수문자+숫자 8~20자로 구성되어야 합니다", "비밀번호 오류", 1);
				
			} else  {
				if(!pnumMatcher.find()) {
					JOptionPane.showMessageDialog(null, "전화번호를 제대로 입력해주셔야 합니다.","전화번호 오류",1);
					
				} else {
					if(!emailMatcher.find()) {
						JOptionPane.showMessageDialog(null, "이메일을 다시 입력해주세요.","이메일 오류",1);
					} else {
						dao.updateData(new CreateFrameVo(id, password, name, birthdate, pnum, email, addr));
						f.dispose();	
						MainFrame ma = new MainFrame();
						ma.userMainFrame(id);
						JOptionPane.showMessageDialog(null, "수정되었습니다","확인",1);
						
					}
				}
			}
		}
	}

	public static void messageBox(Object obj, String message) {
		JOptionPane.showMessageDialog((Component) obj, message);
	}
	
}
