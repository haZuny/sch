package aissignment2_Override;

import java.awt.Image;


public class Object {	// ���ּ��� �̻����� ��ӹ��� ��ü Ŭ����
	
	// ���� �Ӽ�
	protected int x, y, dx, dy, speed;
	protected Image image;
	
	// ������, �ʱ�ȭ
	public Object() {
		x = 0;
		y = 0;
		dx = 0;
		dy = 0;
		speed = 0;
	}
	
	
	// �̵� �޼ҵ�, �ε巴�� �̵��ϱ� ���� dx, dy ���
	void move() {
		x += dx;
		y += dy;
	}
	
	
	// ������
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public Image getImage() {
		return image;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}
	

}
