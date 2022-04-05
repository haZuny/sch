import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.*;



public class Board extends JPanel{
	
	static int winX = 1000;
	static int winY = 1000;
	ArrayList<Position> myList;

	// �ȼ� ����
	int gap;
	
	// ������
	public Board() {
		gap = 5;
	}
	
	
	// paint ���� ����
	public void paint(Graphics g){		
		// ����Ʈ�� �ִ� �� �׸���
		for(Position loc : myList){
			g.fillRect(loc.x * gap, loc.y * gap, gap, gap);
		}
		
		// ���� �׸���
		for (int i = 0; i < winX / gap; i ++) {
			// y��
			g.drawLine(i * gap, 0, i * gap, winY);
				
			for (int j = 0; j < winY / gap; j ++)
				//x��
				g.drawLine(0,i * gap,winX, i * gap);
		}
	}
	
}
