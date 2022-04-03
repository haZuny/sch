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
	
	// ������
	public Panel(){
		setSize(RocketAnimation.width, RocketAnimation.height);
		setBackground(Color.black);
		
		loadImage("./src/assignment1_Animation/rocket.png");
		x = initial_x;
		y = initial_y;
		
		animator = new Thread(this);
		animator.start();

	}
	
	// �̹��� ��������
	void loadImage(String name) {
		ImageIcon rockertIcon = new ImageIcon(name);
        rocket = rockertIcon.getImage();
        rocket = rocket.getScaledInstance(rocketWidth, rocketHeight, ABORT);	// �̹��� ũ�� ����
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
		double dx;	// �Լ� ��ǥ���� x��ġ
		double r = (windowWidth-rocketWidth) / 2;	// ������
		double y_buf = 5;	// ������ �̵��� ������ �߰��ϱ� ���� ���ۺ��� ����
		
		while(x < RocketAnimation.width) {
			
			dx = x - r;
			
			// ����� �ö󰡴� ����: y^2 = (r+x)/(r-x), r�� ������ ���γʺ�
			x += 1;
			y = windowHeight - Math.sqrt(r*r - dx*dx);
			
			// ���� �߰�
			y_buf += Math.random() * 2 - 1;
			y += y_buf;
			
			// ������ ����� �ʱ� ��ġ��
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
			
			// �ִϸ��̼� ������ ���� ����
			try {
				animator.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
