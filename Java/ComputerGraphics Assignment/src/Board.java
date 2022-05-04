import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;



public class Board extends JPanel{
	
	// ������ ũ��
	static int winX;
	static int winY;
	
	// �ȼ� ����Ʈ
	ArrayList<Position> figureGroups;

	// �ȼ� ����
	int gap;
	
	// ������
	public Board(int windowSizeX, int windowSizeY, int gapSize) {
		winX = windowSizeX + 1;
		winY = windowSizeY + 1;
		
		
		setPreferredSize(new Dimension(winX, winY));
		gap = gapSize;
	}
	
	
	// paint ���� ����
	public void paint(Graphics g){	
		
		// ���� �׸���
		for (int i = 0; i <= winX / gap; i ++) {
			// y��
			g.drawLine(i * gap, 0, i * gap, winY);
				
			for (int j = 0; j <= winY / gap; j ++)
				//x��
				g.drawLine(0,i * gap,winX, i * gap);
		}
	}
	
}
