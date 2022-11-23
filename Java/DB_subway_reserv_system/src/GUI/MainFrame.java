package GUI;
import javax.swing.*;
import GUI.Login;

public class MainFrame extends JFrame{
	JPanel login;
	JPanel signUp;
	JPanel adminPage;

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
			getContentPane().add(login);
			revalidate();
			repaint();
		}
		// 회원가입 화면으로 전환
		else if(panelName.equals("signUp")) {
			getContentPane().removeAll();
			getContentPane().add(signUp);
			revalidate();
			repaint();
		}	
		// 관리자 화면으로 전환
		else if(panelName.equals("adminPage")) {
			getContentPane().removeAll();
			getContentPane().add(adminPage);
			revalidate();
			repaint();
		}	
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainFrame frame = new MainFrame();
		
		frame.login = new Login(frame);
		frame.signUp = new SignUp(frame);
		frame.adminPage = new AdminPage(frame);
		
		frame.add(frame.login);
		frame.setVisible(true);
	}

}
