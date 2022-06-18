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
		
		// ���� ��ư
		JButton btnButton_createID = new JButton("����");
		btnButton_createID.setFont(new Font("����", Font.BOLD, 13));
		btnButton_createID.setBounds(225, 240, 75, 30);
		panel.add(btnButton_createID);
		btnButton_createID.addActionListener(e->{
			
			String id = textField_ID.getText();
			String pw = passwordField_PW.getText();
			textField_ID.setText("");
			passwordField_PW.setText("");
			
			if (id.length() == 0 || pw.length() == 0) {
				textField_ID.setText("���̵�� ��й�ȣ�� �Է��ϼ���");
				return;
			}
			
			try {
				int i = DataBase.addUser(id, pw);
				// ������ ���
				if (i == -1) {
					textField_ID.setText("���̵�� ��й�ȣ�� �Է��ϼ���");
					return;
				}
				// ������ ���
				else {
					Frame.changeMonitor(Frame.monitorSignIn);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		});
		
		// �ڷΰ��� ��ư
		JButton btnButton_back = new JButton("�ڷ�");
		btnButton_back.setFont(new Font("����", Font.BOLD, 13));
		btnButton_back.setBounds(290, 240, 75, 30);
		panel.add(btnButton_back);
		btnButton_back.addActionListener(e->Frame.changeMonitor(1));

	}
}
