package aissignment2_Override;

import java.awt.Image;


public class Object {	// 우주선과 미사일이 상속받을 객체 클래스
	
	// 공통 속성
	protected int x, y, dx, dy, speed;
	protected Image image;
	
	// 생성자, 초기화
	public Object() {
		x = 0;
		y = 0;
		dx = 0;
		dy = 0;
		speed = 0;
	}
	
	
	// 이동 메소드, 부드럽게 이동하기 위해 dx, dy 사용
	void move() {
		x += dx;
		y += dy;
	}
	
	
	// 접근자
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
