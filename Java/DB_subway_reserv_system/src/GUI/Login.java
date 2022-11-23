package GUI;

import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import DB.DB_USER;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JButton;
import GUI.MainFrame;

import GUI.MainFrame;

public class Login extends JPanel {
	GUI.MainFrame window;	// 프레임
	
	private JTextField textField_userName;
	private JPasswordField textField_pw;

	/**
	 * Create the panel.
	 */
	public Login(GUI.MainFrame win) {
		window = win;
		
		setLayout(null);
		
		JLabel lb_userName = new JLabel("회원 이름");
		lb_userName.setBounds(190, 185, 200, 30);
		add(lb_userName);
		
		JLabel lb_pw = new JLabel("비밀번호");
		lb_pw.setBounds(190, 255, 200, 30);
		add(lb_pw);
		
		textField_userName = new JTextField();
		textField_userName.setBounds(190, 215, 200, 30);
		add(textField_userName);
		textField_userName.setColumns(10);
		
		textField_pw = new JPasswordField();
		textField_pw.setBounds(190, 285, 200, 30);
		add(textField_pw);
		textField_pw.setColumns(10);
		
		JLabel lb_title = new JLabel("로그인");
		lb_title.setFont(new Font("HY강B", Font.PLAIN, 39));
		lb_title.setHorizontalAlignment(SwingConstants.CENTER);
		lb_title.setBounds(250, 30, 200, 50);
		add(lb_title);
		
		JButton btn_siginin = new JButton("로그인");
		btn_siginin.addActionListener(new LoginBtnListener());
		btn_siginin.setBounds(410, 215, 100, 30);
		add(btn_siginin);
		
		JButton btn_signup = new JButton("회원가입");
		btn_signup.addActionListener(new SignUpBtnListener());
		btn_signup.setBounds(410, 285, 100, 30);
		add(btn_signup);

	}
	
	// 로그인 버튼 이벤트
		class LoginBtnListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				String userName = textField_userName.getText();
				String password = textField_pw.getText();
				HashMap<String, String> userData= null;
				
				// 아이디 체크
				if(!DB_USER.checkUserNameOverlap(userName)) {
					JOptionPane.showMessageDialog(null, "아이디가 존재하지 않습니다.");
					return;
				}
				// 정보 가져옴
				userData = DB_USER.getUserByUserName(userName);
				// 비밀번호 체크
				if (!userData.get("password").equals(password)) {
					JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.");
					return;					
				}
				// 관리자 페이지로 이동
				if(userData.get("user_class").equals("admin")) {
					// 폼 초기화
					textField_userName.setText("");
					textField_pw.setText("");
					window.change("adminPage");					
				}
			}
		}
	
	// 회원가입 버튼 이벤트
	class SignUpBtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			window.change("signUp");
		}
	}
}
