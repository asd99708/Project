package AWT.AdminFrame;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JFrame;

import AWT.AdminFrame.Record.AdminRecord;
import AWT.AdminFrame.Staff.StaffFrame;

public class AdminFrame extends JFrame implements ActionListener {
	private JFrame f;
	private JButton btn1, btn2, btn3, btn4;
	StaffFrame sffr = new StaffFrame();
	AdminRecord adrd = new AdminRecord();

	public AdminFrame() {
	}

	public void adminfr() {
		f = new JFrame("Admin Window");

		f.setSize(490, 200);
		f.setLayout(null);
		f.setResizable(false);

		btn1 = new JButton("직원관리");
		btn1.setBounds(15, 100, 95, 50);
		;

		btn2 = new JButton("메신저");
		btn2.setBounds(130, 100, 95, 50);

		btn3 = new JButton("홈페이지");
		btn3.setBounds(245, 100, 95, 50);

		btn4 = new JButton("결근내역");
		btn4.setBounds(360, 100, 95, 50);

		f.add(btn1);
		f.add(btn2);
		f.add(btn3);
		f.add(btn4);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		btn1.addActionListener(this);
		btn3.addActionListener(this);
		btn4.addActionListener(this);
		f.setVisible(true);
	}

	public static void main(String[] args) {
		AdminFrame adm = new AdminFrame();
		adm.adminfr();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn1) {
//			f.setVisible(false);
			sffr.stafffr();
		}

		if (e.getSource() == btn3) {
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
			adrd.mard();
		}
	}
}