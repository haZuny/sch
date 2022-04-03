package assignment1_Animation;

import javax.swing.*;

public class RocketAnimation extends JFrame {
	
	Panel panel = new Panel();
	static int width = 800;
	static int height = 500;
	
	// 생성자
	public RocketAnimation() {
		
		setSize(width, height);
		setTitle("Rocket Animation");
		setVisible(true);
		setResizable(false);
		setLocationRelativeTo(null); 	//매개변수가 null이면 정중앙 위치
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		add(panel);
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RocketAnimation frame = new RocketAnimation();
		
		

	}

}
