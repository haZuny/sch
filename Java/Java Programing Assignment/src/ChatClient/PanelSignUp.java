package ChatClient;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.TextField;
import java.sql.SQLException;

public class PanelSignUp extends JPanel {
	private JTextField textField_ID;
	private JPasswordField passwordField_PW;

	/**
	 * Create the panel.
	 */
	public PanelSignUp() {
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
		
		// ID
		textField_ID = new JTextField();
		textField_ID.setBounds(245, 138, 130, 20);
		panel.add(textField_ID);
		textField_ID.setColumns(10);
		
		// PW
		passwordField_PW = new JPasswordField();
		passwordField_PW.setBounds(245, 188, 130, 20);
		panel.add(passwordField_PW);
		
		// 생성 버튼
		JButton btnButton_createID = new JButton("생성");
		btnButton_createID.setFont(new Font("굴림", Font.BOLD, 13));
		btnButton_createID.setBounds(225, 240, 75, 30);
		panel.add(btnButton_createID);
		btnButton_createID.addActionListener(e->{
			
			String id = textField_ID.getText();
			String pw = passwordField_PW.getText();
			textField_ID.setText("");
			passwordField_PW.setText("");
			
			if (id.length() == 0 || pw.length() == 0) {
				textField_ID.setText("아이디와 비밀번호를 입력하세요");
				return;
			}
			
			try {
				int i = DataBase.addUser(id, pw);
				// 실패할 경우
				if (i == -1) {
					textField_ID.setText("아이디와 비밀번호를 입력하세요");
					return;
				}
				// 성공할 경우
				else {
					Frame.changeMonitor(Frame.monitorSignIn);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		
		// 뒤로가기 버튼
		JButton btnButton_back = new JButton("뒤로");
		btnButton_back.setFont(new Font("굴림", Font.BOLD, 13));
		btnButton_back.setBounds(290, 240, 75, 30);
		panel.add(btnButton_back);
		btnButton_back.addActionListener(e->Frame.changeMonitor(1));

	}
}
