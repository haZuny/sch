package ChatClient2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ChatClient.PanelSignIn;
import ChatClient.PanelSignUp;

public class Frame extends JFrame {
	
	// 화면 상수
	final int monitorSignIn = 1;
	final int monitorSignUP = 2;
	final int monitorChat = 3;
	
	// 패널 변수
	PanelSignIn panel_signIn;
	PanelSignUp panel_signUp;
	PanelChat panel_chat;

	private JPanel contentPane;
	public static User user;

	// 메인 메소드
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// 프레임 생성
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
		
		// 사용자 객체 생성
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
		
		// 로그인
		panel_signIn = new PanelSignIn();
		panel_signIn.setBounds(0, 0, 600, 400);
		contentPane.add(panel_signIn);
		
		// 가입
		panel_signUp = new PanelSignUp();
		panel_signUp.setBounds(0, 0, 600, 400);
		contentPane.add(panel_signUp);
		panel_signUp.setVisible(false);

		// 채팅
		panel_chat = new PanelChat();
		panel_chat.setBounds(0, 0, 600, 400);
		contentPane.add(panel_chat);
		panel_chat.setVisible(false);
	}
	
	
	void changeMonitor(int monitor) {
		panel_signIn.setVisible(false);
		panel_signUp.setVisible(false);
		panel_chat.setVisible(false);
		
		switch(monitor) {
		case monitorSignIn:
			panel_signIn.setVisible(true);
			break;
		case monitorSignUP:
			panel_signUp.setVisible(true);
			break;
		case monitorChat:
			panel_chat.setVisible(true);
			break;
			
		}
	}
}
