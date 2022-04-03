package aissignment2_Override;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Spaceship extends Object{
	
	// 미사일 정보 저장할 ArrayList
    ArrayList<Missile> mLs = new ArrayList<>();
    
    // 미사일 한번만 발사하게할 bool값
    public boolean fired;

    // 생성자, 초기화
    public Spaceship() {
    	
    	super();
    	x = 500;
    	y = 500;
    	speed = 5;
        ImageIcon ii = new ImageIcon("./src/aissignment2_Override/ship.png");
        image = ii.getImage();
        
        fired = false;
    }

    // 이동
    public void move_left() {
        dx = -1 * speed;
    }
    public void move_right() {
        dx = speed;
    }
    // 정지_키보드 땔때 호출
    public void stop() {
    	dx = 0;
    }
    // 발사
    public void fire() {
    	mLs.add(new Missile(x, y));
    }
    
    
    // 미사일 리스트 업데이트
    public void updateMLs() {
    	// 제거할 리스트 정보 저장
    	ArrayList<Missile> delLs = new ArrayList<>();
    	
    	// 좌표를 벗어나는 미사일이 있으면 제거
        for(Missile m : mLs) {
        	if(m.y < 0) {
        		delLs.add(m);
        	}
        }
        
        // 리스트 노드 제거
        for (Missile m : delLs) {
        	mLs.remove(m);
        }
    }


}
