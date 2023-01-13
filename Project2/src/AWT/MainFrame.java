package AWT;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JFrame;

import AWT.Login.LoginFrame;


public class MainFrame extends JFrame implements ActionListener {
	private JFrame f;
	private JButton btn1, btn2, btn3,btn4;
	StaffMain stfm = new StaffMain();
	MainRecord mar = new MainRecord();
	static String userid;

	public MainFrame() {
	}
	
	public void userMainFrame(String id) {
		this.userid = id;
		
		f = new JFrame("Main From");
		f.setSize(490, 200);
		f.setLayout(null);
		f.setResizable(false);

		btn1 = new JButton("출/퇴근기록");
		btn1.setBounds(15, 100, 100, 50);

		btn2 = new JButton("내 정보");
		btn2.setBounds(130, 100, 100, 50);
		
		btn3 = new JButton("홈페이지");
		btn3.setBounds(245, 100, 100, 50);

		btn4 = new JButton("로그아웃");
		btn4.setBounds(355, 100, 100, 50);
		

		f.add(btn1);
		f.add(btn2);
		f.add(btn3);
		f.add(btn4);
		f.setVisible(true);
		f.setLocationRelativeTo(null);
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btn4.addActionListener(this);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn2) {
			stfm.staffm();
		}
		if (e.getSource() == btn1) {
			mar.mard();
		}
		
		if(e.getSource() == btn3) {
			String urlLink = "https://github.com/asd99708";
			try {
				Desktop.getDesktop().browse(new URI(urlLink));
			} catch (IOException e1) {
				e1.printStackTrace();
			} catch (URISyntaxException e2) {
				e2.printStackTrace();
			}
		}
		
		if (e.getSource() == btn4) {
			f.dispose();
			new LoginFrame();
		}

	}
}
