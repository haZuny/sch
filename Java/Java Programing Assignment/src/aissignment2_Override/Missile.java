package aissignment2_Override;import java.awt.Image;

import javax.swing.ImageIcon;

public class Missile extends Object{

	public Image getImage() {
		return image;
	}

	// ������, �ʱ�ȭ
	public Missile(int x, int y) {
		this.x = x;
		this.y = y;
		dy =-1 * (speed = 10);
		ImageIcon ii = new ImageIcon("./src/aissignment2_Override/missile.png");
		image = ii.getImage();
	}
}