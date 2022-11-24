package GUI;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import GUI.Login;

public class MainFrame extends JFrame{

	public MainFrame() {
		
		setTitle("철도 예매 시스템_20194122 권하준");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(700,500);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	// 버튼 클릭시 화면 전환
	public void change(String panelName) {
		// 로그인 화면으로 전환
		if(panelName.equals("login")) {
			getContentPane().removeAll();
			getContentPane().add(new Login(this));
			revalidate();
			repaint();
		}
		// 회원가입 화면으로 전환
		else if(panelName.equals("signUp")) {
			getContentPane().removeAll();
			getContentPane().add(new SignUp(this));
			revalidate();
			repaint();
		}	
		// 관리자 화면으로 전환
		else if(panelName.equals("adminPage")) {
			getContentPane().removeAll();
			getContentPane().add(new AdminPage(this));
			revalidate();
			repaint();
		}	
		// 스케쥴 추가 화면으로 전환
		else if(panelName.equals("addSchedulePage")) {
			getContentPane().removeAll();
			getContentPane().add(new AddSchedule(this));
			revalidate();
			repaint();
		}	
	}
	public void change(String panelName, HashMap<String, String> user) {
		// 로그인 화면으로 전환
		if(panelName.equals("userPage")) {
			getContentPane().removeAll();
			getContentPane().add(new UserPage(this, user));
			revalidate();
			repaint();
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainFrame frame = new MainFrame();
		
		frame.add(new Login(frame));
		frame.setVisible(true);
	}

}