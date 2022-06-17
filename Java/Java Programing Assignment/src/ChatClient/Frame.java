package ChatClient;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Frame extends JFrame {

	private JPanel contentPane;
	public static User user;

	// ���� �޼ҵ�
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// ������ ����
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		// ����� ��ü ����
		user = new User();
		user.receive();
	}

	/**
	 * Create the frame.
	 */
	public Frame() {
		setTitle(user.userID);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		PanelChat panel = new PanelChat();
		panel.setBounds(0, 0, 600, 400);
		contentPane.add(panel);
	}
}
