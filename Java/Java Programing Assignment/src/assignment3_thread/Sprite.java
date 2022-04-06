package assignment3_thread;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

// �ҽ��� �Է��ϰ� Ctrl+Shift+O�� ������.
public class Sprite {
	protected int x; // ���� ��ġ�� x��ǥ
	protected int y; // ���� ��ġ�� y��ǥ
	protected int dx; // �����ð��� �����̴� x���� �Ÿ�
	protected int dy; // �����ð��� �����̴� y���� �Ÿ�
	private Image image; // ��������Ʈ�� ������ �ִ� �̹���

	// ������
	public Sprite(Image image, int x, int y) {
		this.image = image;
		this.x = x;
		this.y = y;
	}

	// ��������Ʈ�� ���� ���̸� ��ȯ�Ѵ�.
	public int getWidth() {
		return image.getWidth(null);
	}

	// ��������Ʈ�� ���� ���̸� ��ȯ�Ѵ�.
	public int getHeight() {
		return image.getHeight(null);
	}

	// ��������Ʈ�� ȭ�鿡 �׸���.
	public void draw(Graphics g) {
		g.drawImage(image, x, y, null);
	}

	// ��������Ʈ�� �����δ�.
	public void move() {
		x += dx;
		y += dy;
	}

	// dx�� �����Ѵ�.
	public void setDx(int dx) {
		this.dx = dx;
	}

	// dy�� �����Ѵ�.
	public void setDy(int dy) {
		this.dy = dy;
	}

	// dx�� ��ȯ�Ѵ�.
	public int getDx() {
		return dx;
	}

	// dy�� ��ȯ�Ѵ�.
	public int getDy() {
		return dy;
	}

	// x�� ��ȯ�Ѵ�.
	public int getX() {
		return x;
	}

	// y�� ��ȯ�Ѵ�.
	public int getY() {
		return y;
	}

	// �ٸ� ��������Ʈ���� �浹 ���θ� ����Ѵ�. �浹�̸� true�� ��ȯ�Ѵ�.
	public boolean checkCollision(Sprite other) {
		Rectangle myRect = new Rectangle();
		Rectangle otherRect = new Rectangle();
		myRect.setBounds(x, y, getWidth(), getHeight());
		otherRect.setBounds(other.getX(), other.getY(), other.getWidth(),
				other.getHeight());

		return myRect.intersects(otherRect);
	}

	// �浹�� ó���Ѵ�.
	public void handleCollision(Sprite other) {

	}
}