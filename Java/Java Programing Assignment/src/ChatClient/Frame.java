package ChatClient;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ChatClient.PanelSignIn;
import ChatClient.PanelSignUp;

public class Frame extends JFrame {
	
	// ȭ�� ���
	static final int monitorSignIn = 1;
	static final int monitorSignUP = 2;
	static final int monitorMain = 3;
	
	// �г� ����
	static PanelSignIn panel_signIn;
	static PanelSignUp panel_signUp;
	static PanelMain panel_main;

	private JPanel contentPane;
	
	// User ��ü
	public static User user;

	// ���� �޼ҵ�
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// ������ ����
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					System.out.println(frame);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		int a = 1;
		while(user == null) {
			System.out.println("�α��� �����");
		}
		user.receive();
	}

	/**
	 * Create the frame.
	 */
	public Frame() {
		setTitle("ä�� Ŭ���̾�Ʈ");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 440);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// �α���
		panel_signIn = new PanelSignIn();
		panel_signIn.setBounds(0, 0, 600, 400);
		contentPane.add(panel_signIn);
		
		// ����
		panel_signUp = new PanelSignUp();
		panel_signUp.setBounds(0, 0, 600, 400);
		contentPane.add(panel_signUp);
		panel_signUp.setVisible(false);

		// ����
		panel_main = new PanelMain();
		panel_main.setBounds(0, 0, 600, 400);
		contentPane.add(panel_main);
		panel_main.setVisible(false);
	}
	
	// ȭ�� ����
	public static void changeMonitor(int monitor) {
		panel_signIn.setVisible(false);
		panel_signUp.setVisible(false);
		panel_main.setVisible(false);
		
		switch(monitor) {
		case monitorSignIn:
			panel_signIn.setVisible(true);
			break;
		case monitorSignUP:
			panel_signUp.setVisible(true);
			break;
		case monitorMain:
			panel_main.setVisible(true);
			panel_main.lblDd.setText("ID: " + user.userID);
			panel_main.lblPort.setText("Port: " + user.portReceive);
			break;
			
		}
	}
	
	// ����� ����
	public static void generateUser(String id, int port) throws IOException, ClassNotFoundException {
		user = new User(id, port);
		System.out.println(user.portReceive);
		System.out.println(user.portSend);
		System.out.println(user.userID);
		
	}
}
