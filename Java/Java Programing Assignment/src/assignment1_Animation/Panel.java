package assignment1_Animation;


import java.awt.*;
import javax.swing.*;

//
public class Panel extends JPanel implements Runnable{
	
	Image rocket;
	Thread animator;
	
	int rocketWidth = 80;
	int rocketHeight = 130;
	
	int initial_x = 0;
	int initial_y = RocketAnimation.height - rocketHeight;
	double x;
	double y;
	
	// 생성자
	public Panel(){
		setSize(RocketAnimation.width, RocketAnimation.height);
		setBackground(Color.black);
		
		loadImage("./src/assignment1_Animation/rocket.png");
		x = initial_x;
		y = initial_y;
		
		animator = new Thread(this);
		animator.start();

	}
	
	// 이미지 가져오기
	void loadImage(String name) {
		ImageIcon rockertIcon = new ImageIcon(name);
        rocket = rockertIcon.getImage();
        rocket = rocket.getScaledInstance(rocketWidth, rocketHeight, ABORT);	// 이미지 크기 변경
	}
	
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(rocket, (int)x, (int)y, this);
		}
	
	@Override
	public void run(){
		int windowWidth = RocketAnimation.width;
		int windowHeight = RocketAnimation.height;
		double dx;	// 함수 좌표상의 x위치
		double r = (windowWidth-rocketWidth) / 2;	// 반지름
		double y_buf = 5;	// 로켓의 이동에 잡음을 추가하기 위해 버퍼변수 정의
		
		while(x < RocketAnimation.width) {
			
			dx = x - r;
			
			// 곡선으로 올라가는 공식: y^2 = (r+x)/(r-x), r은 윈도우 가로너비
			x += 1;
			y = windowHeight - Math.sqrt(r*r - dx*dx);
			
			// 잡음 추가
			y_buf += Math.random() * 2 - 1;
			y += y_buf;
			
			// 윈도우 벗어나면 초기 위치로
			if(x > windowWidth - rocketWidth - 50) {
				x = initial_x;
				y = initial_y;
				try {
					animator.sleep(500);
				} catch(InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			
			repaint();
			
			// 애니메이션 프레임 간격 설정
			try {
				animator.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
