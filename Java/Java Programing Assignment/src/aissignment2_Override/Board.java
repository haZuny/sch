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
		// ����
		g2d.drawImage(ship.getImage(), ship.getX(), ship.getY(), this);
		// �̻���
		for(Missile m:ship.mLs)
			g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
		
		Toolkit.getDefaultToolkit().sync();
		
	}

	// timer�� ���� DELAY�������� ����
	@Override
	public void actionPerformed(ActionEvent e) {
		
		ship.move();
		
		// �̻��� ����Ʈ ������Ʈ
		ship.updateMLs();

		for(Missile m:ship.mLs)
			m.move();
		repaint();
	}

	// �̵� ����
	@Override
	public void keyPressed(KeyEvent e) {
		// ���� �̵�
		if (e.getKeyCode()==KeyEvent.VK_LEFT)
			ship.move_left();
		// ������ �̵�
		if (e.getKeyCode()==KeyEvent.VK_RIGHT)
			ship.move_right();
		// �߻�, fired�� false�϶��� �߻�
		if (e.getKeyCode()==KeyEvent.VK_SPACE && !(ship.fired)) {
			ship.fire();
			ship.fired = true;
		}
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// Ű���� ���� dx 0���� ����
		if (e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_RIGHT)
			ship.stop();
		// �����̽� ���� �ٽ� �߻� ����
		if (e.getKeyCode() == KeyEvent.VK_SPACE)
			ship.fired = false;
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}
}