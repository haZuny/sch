import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;



public class Board extends JPanel{
	
	// 윈도우 크기
	static int winX;
	static int winY;
	
	// 픽셀 리스트
	ArrayList<Position> figureGroups;

	// 픽셀 간격
	int gap;
	
	// 생성자
	public Board(int windowSizeX, int windowSizeY, int gapSize) {
		winX = windowSizeX + 1;
		winY = windowSizeY + 1;
		
		
		setPreferredSize(new Dimension(winX, winY));
		gap = gapSize;
	}
	
	
	// paint 동작 정의
	public void paint(Graphics g){	
		
		// 격자 그리기
		for (int i = 0; i <= winX / gap; i ++) {
			// y축
			g.drawLine(i * gap, 0, i * gap, winY);
				
			for (int j = 0; j <= winY / gap; j ++)
				//x축
				g.drawLine(0,i * gap,winX, i * gap);
		}
	}
	
}
