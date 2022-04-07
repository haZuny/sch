import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.*;



public class Board extends JPanel{
	
	// ������ ũ��
	static int winX;;
	static int winY;;
	
	// �ȼ� ����Ʈ
	ArrayList<Position> myList;

	// �ȼ� ����
	int gap;
	
	// ������
	public Board(int windowSizeX, int windowSizeY, int gapSize) {
		winX = windowSizeX;
		winY = windowSizeY;
		gap = gapSize;
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
