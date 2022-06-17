package ChatClient;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;

public class PanelChat extends JPanel{
	public static JTextField textField;
	public static JTextArea textArea;

	/**
	 * Create the panel.
	 */
	public PanelChat() {
		setLayout(null);
		
		// 대화 기록
		textArea = new JTextArea();
		textArea.setEditable(false);
		textArea.setBounds(0, 0, 600, 350);
		add(textArea);
		
		// 보낼 텍스트
		textField = new JTextField();
		textField.setBounds(0, 375, 500, 25);
		add(textField);
		textField.setColumns(10);
		textField.addActionListener(new sendMsg());
		
		// 전송 버튼
		JButton btnSend = new JButton("\uBCF4\uB0B4\uAE30");
		btnSend.setBounds(500, 375, 100, 25);
		add(btnSend);
		btnSend.addActionListener(new sendMsg());

	}
	
	// 메세지 전달 동작 정의
	public class sendMsg implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			
			try {
				Frame.user.sendText();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
