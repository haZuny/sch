package ChatClient2;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.xml.crypto.Data;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.io.IOException;
import java.sql.SQLException;

public class PanelSignIn extends JPanel {
	private JTextField textField_ID;
	private JPasswordField passwordField_PW;

	/**
	 * Create the panel.
	 */
	public PanelSignIn() {
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 600, 400);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PW");
		lblNewLabel.setBounds(225, 190, 20, 15);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(225, 140, 20, 15);
		panel.add(lblNewLabel_1);
		
		// id
		textField_ID = new JTextField();
		textField_ID.setBounds(245, 138, 130, 20);
		panel.add(textField_ID);
		textField_ID.setColumns(10);
		
		// pw
		passwordField_PW = new JPasswordField();
		passwordField_PW.setBounds(245, 188, 130, 20);
		panel.add(passwordField_PW);
		
		// 로그인 버튼
		JButton btnButton_signin = new JButton("로그인");
		btnButton_signin.setFont(new Font("굴림", Font.BOLD, 13));
		btnButton_signin.setBounds(225, 240, 75, 30);
		panel.add(btnButton_signin);
		btnButton_signin.addActionListener(e->{
			String id = textField_ID.getText();
			String pw = passwordField_PW.getText();
			
			try {
				int port = DataBase.signIn(id, pw);
				// 로그인 실패
				if(port == -1) {
					textField_ID.setText("");
					passwordField_PW.setText("");
				}
				else {
					Frame.changeMonitor(Frame.monitorChat);
					Frame.generateUser(id, port);					
				}
			} catch (SQLException | ClassNotFoundException | IOException e1) {
				e1.printStackTrace();
			}
		});
		
		// 가입 버튼
		JButton btnButton_signup = new JButton("\uAC00\uC785");
		btnButton_signup.setFont(new Font("굴림", Font.BOLD, 13));
		btnButton_signup.setBounds(300, 240, 75, 30);
		panel.add(btnButton_signup);
		btnButton_signup.addActionListener(e->Frame.changeMonitor(2));

	}
}
