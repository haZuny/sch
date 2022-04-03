package aissignment2_Override;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener, KeyListener {

	private Timer timer;
	private final int DELAY = 10;
	private Spaceship ship;

	public Board() {
		addKeyListener(this);
		setFocusable(true);
		setBackground(Color.BLACK);

		ship = new Spaceship();
		timer = new Timer(DELAY, this);
		timer.start();
	}

	// Draw
	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		// 로켓
		g2d.drawImage(ship.getImage(), ship.getX(), ship.getY(), this);
		// 미사일
		for(Missile m:ship.mLs)
			g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
		
		Toolkit.getDefaultToolkit().sync();
		
	}

	// timer에 의해 DELAY간격으로 실행
	@Override
	public void actionPerformed(ActionEvent e) {
		
		ship.move();
		
		// 미사일 리스트 업데이트
		ship.updateMLs();

		for(Missile m:ship.mLs)
			m.move();
		repaint();
	}

	// 이동 구현
	@Override
	public void keyPressed(KeyEvent e) {
		// 왼쪽 이동
		if (e.getKeyCode()==KeyEvent.VK_LEFT)
			ship.move_left();
		// 오른쪽 이동
		if (e.getKeyCode()==KeyEvent.VK_RIGHT)
			ship.move_right();
		// 발사, fired가 false일때만 발사
		if (e.getKeyCode()==KeyEvent.VK_SPACE && !(ship.fired)) {
			ship.fire();
			ship.fired = true;
		}
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// 키보드 때면 dx 0으로 만듦
		if (e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_RIGHT)
			ship.stop();
		// 스페이스 때면 다시 발사 가능
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
			ship.fired = false;
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}