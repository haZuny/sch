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
	static final int monitorSignIn = 1;
	static final int monitorSignUP = 2;
	static final int monitorChat = 3;
	
	// 패널 변수
	static PanelSignIn panel_signIn;
	static PanelSignUp panel_signUp;
	static PanelChat panel_chat;

	private JPanel contentPane;
	
	// User 객체
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
		while(true) {
			if(user != null) {
				break;
			}
			System.out.println("로그인 대기중");
		}
		user.receive();
	}

	/**
	 * Create the frame.
	 */
	public Frame() {
		setTitle("채팅 클라이언트");
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
	
	// 화면 변경
	public static void changeMonitor(int monitor) {
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
	
	// 사용자 생성
	public static void generateUser(String id, int port) throws IOException, ClassNotFoundException {
		user = new User(id, port);
		System.out.println(user.portReceive);
		System.out.println(user.portSend);
		System.out.println(user.userID);
		
	}
}
