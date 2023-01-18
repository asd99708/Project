package AWT.Login;

import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import AWT.MainFrame;
import AWT.AdminFrame.AdminFrame;

public class LoginFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JFrame f;
	private Label id;
	private Label pass;
	private JButton btn;
	private TextField tid = new TextField(30);
	private TextField tpass = new TextField(30);
	private TextField t = new TextField(40);
	AdminFrame admin = new AdminFrame();
	MainFrame mainf = new MainFrame();
	public static String userId;

	public LoginFrame() {
		f = new JFrame("Login From");
		f.setSize(400, 235);
		f.setLayout(null);
		f.setResizable(false);

		id = new Label("ID", Label.CENTER);
		id.setBounds(57, 50, 30, 10);
		tid.setBounds(95, 46, 200, 20);

		btn = new JButton("Login");
		btn.setBounds(310, 50, 60, 40);

		pass = new Label("Password", Label.LEFT);
		pass.setBounds(20, 79, 60, 10);
		tpass.setBounds(95, 75, 200, 20);
		tpass.setEchoChar('*');

		t.setBounds(20, 120, 350, 60);
		t.setEditable(false);
		t.setFocusable(false);

		f.add(id);
		f.add(tid);
		f.add(btn);
		f.add(pass);
		f.add(tpass);
		f.add(t);

		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		btn.addActionListener(this);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn) {
			LoginFrameDAO dao = new LoginFrameDAO();
			int daoidcheck = dao.logincheck(tid.getText());
			int daoid = dao.login(tid.getText(), tpass.getText());
			
			if (daoidcheck == 1) {
				if (daoid == 1) {
					t.setText("어드민 계정 로그인 성공");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					f.dispose();
					userId = tid.getText();
					admin.adminfr(tid.getText());

				} else {
					t.setText("로그인 실패");
				}
			} else {
				if (daoid == 1) {
					t.setText("일반 계정 로그인 성공");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					f.dispose();					
					userId = tid.getText();
					mainf.userMainFrame(tid.getText());

				} else {
					t.setText("로그인 실패");
				}
			}

		}
	}

	public static void main(String[] args) {
		new LoginFrame();
	}

}
