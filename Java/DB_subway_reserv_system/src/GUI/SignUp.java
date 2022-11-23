package GUI;

import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import GUI.MainFrame;
import DB.DB_USER;
import java.util.*;
import java.util.regex.Pattern;
import javax.swing.JPasswordField;

public class SignUp extends JPanel {
	GUI.MainFrame window;	// 프레임
		
	private JTextField textField_userName;
	private JTextField textField_phoneNum;
	private JTextField textField_cardNum;
	private JPasswordField textField_pw;

	/**
	 * Create the panel.
	 */
	public SignUp(GUI.MainFrame win) {
		window = win;
		
		setLayout(null);
		
		JLabel lb_userName = new JLabel("회원 이름");
		lb_userName.setBounds(190, 115, 200, 30);
		add(lb_userName);
		
		JLabel lb_pw = new JLabel("비밀번호");
		lb_pw.setBounds(190, 185, 200, 30);
		add(lb_pw);
		
		textField_userName = new JTextField();
		textField_userName.setBounds(190, 145, 200, 30);
		add(textField_userName);
		textField_userName.setColumns(10);
		
		JLabel lb_title = new JLabel("회원가입");
		lb_title.setFont(new Font("HY강B", Font.PLAIN, 39));
		lb_title.setHorizontalAlignment(SwingConstants.CENTER);
		lb_title.setBounds(250, 30, 200, 50);
		add(lb_title);
		
		JButton btn_signUp = new JButton("회원가입");
		btn_signUp.addActionListener(new signUpBtnListener());
		btn_signUp.setBounds(410, 215, 100, 30);
		add(btn_signUp);
		
		JButton btn_back = new JButton("뒤로");
		btn_back.addActionListener(new BackBtnListener());
		btn_back.setBounds(410, 285, 100, 30);
		add(btn_back);
		
		textField_phoneNum = new JTextField();
		textField_phoneNum.setColumns(10);
		textField_phoneNum.setBounds(190, 285, 200, 30);
		add(textField_phoneNum);
		
		JLabel lb_phoneNum = new JLabel("휴대전화");
		lb_phoneNum.setBounds(190, 255, 200, 30);
		add(lb_phoneNum);
		
		textField_cardNum = new JTextField();
		textField_cardNum.setColumns(10);
		textField_cardNum.setBounds(190, 355, 200, 30);
		add(textField_cardNum);
		
		JLabel lb_cardNum = new JLabel("카드번호");
		lb_cardNum.setBounds(190, 325, 200, 30);
		add(lb_cardNum);
		
		textField_pw = new JPasswordField();
		textField_pw.setBounds(190, 215, 200, 30);
		add(textField_pw);

	}
	
	
	// 회원가입 버튼 이벤트
	class signUpBtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TextField에서 값 얻기
			String userName = textField_userName.getText();
			String password = textField_pw.getText();
			String phoneNumber = textField_phoneNum.getText();
			String cardNumber = textField_cardNum.getText();
			
			// userName 중복 확인
			boolean checkUserName = DB_USER.checkUserNameOverlap(userName);
			
			// 공백 체크
			if ("".equals(userName) || "".equals(password) || "".equals(phoneNumber) || "".equals(cardNumber)) {				
				JOptionPane.showMessageDialog(null, "모든 항목을 입력해주세요.");
			}
			// 아이디 중복 체크
			else if (checkUserName) {
				JOptionPane.showMessageDialog(null, "회원이름이 중복되었습니다.");
			}
			// 휴대전화 정규식 체크
			else if (!Pattern.matches("010-\\d\\d\\d\\d-\\d\\d\\d\\d", phoneNumber)) {
				JOptionPane.showMessageDialog(null, "휴대전화를 올바르게 입력해주세요.");
			}
			// 카드번호 정규식 체크
			else if (!Pattern.matches("\\d\\d\\d\\d-\\d\\d\\d\\d-\\d\\d\\d\\d-\\d\\d\\d\\d", cardNumber)) {
				JOptionPane.showMessageDialog(null, "카드번호를 올바르게 입력해주세요.");
			}
			else {				
				// DB에 추가
				DB_USER.insertUser(userName, password, phoneNumber, cardNumber);			
				// 폼 초기화
				textField_userName.setText("");
				textField_pw.setText("");
				textField_phoneNum.setText("");
				textField_cardNum.setText("");
				// 로그인 화면으로 이동
				window.change("login");
			}
			
		}
	}
	
	// 뒤로가기 버튼 이벤트
	class BackBtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// 폼 초기화
			textField_userName.setText("");
			textField_pw.setText("");
			textField_phoneNum.setText("");
			textField_cardNum.setText("");
			// 로그인 화면으로 이동
			window.change("login");
		}
	}
}
