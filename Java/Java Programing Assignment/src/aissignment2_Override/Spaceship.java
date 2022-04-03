package aissignment2_Override;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Spaceship extends Object{
	
	// �̻��� ���� ������ ArrayList
    ArrayList<Missile> mLs = new ArrayList<>();
    
    // �̻��� �ѹ��� �߻��ϰ��� bool��
    public boolean fired;

    // ������, �ʱ�ȭ
    public Spaceship() {
    	
    	super();
    	x = 500;
    	y = 500;
    	speed = 5;
        ImageIcon ii = new ImageIcon("./src/aissignment2_Override/ship.png");
        image = ii.getImage();
        
        fired = false;
    }

    // �̵�
    public void move_left() {
        dx = -1 * speed;
    }
    public void move_right() {
        dx = speed;
    }
    // ����_Ű���� ���� ȣ��
    public void stop() {
    	dx = 0;
    }
    // �߻�
    public void fire() {
    	mLs.add(new Missile(x, y));
    }
    
    
    // �̻��� ����Ʈ ������Ʈ
    public void updateMLs() {
    	// ������ ����Ʈ ���� ����
    	ArrayList<Missile> delLs = new ArrayList<>();
    	
    	// ��ǥ�� ����� �̻����� ������ ����
        for(Missile m : mLs) {
        	if(m.y < 0) {
        		delLs.add(m);
        	}
        }
        
        // ����Ʈ ��� ����
        for (Missile m : delLs) {
        	mLs.remove(m);
        }
    }


}
