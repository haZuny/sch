import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.*;



public class Board extends JPanel{
	
	// 윈도우 크기
	static int winX;;
	static int winY;;
	
	// 픽셀 리스트
	ArrayList<Position> myList;

	// 픽셀 간격
	int gap;
	
	// 생성자
	public Board(int windowSizeX, int windowSizeY, int gapSize) {
		winX = windowSizeX;
		winY = windowSizeY;
		gap = gapSize;
	}
	
	
	// paint 동작 정의
	public void paint(Graphics g){		
		// 리스트에 있는 값 그리기
		for(Position loc : myList){
			g.fillRect(loc.x * gap, loc.y * gap, gap, gap);
		}
		
		// 격자 그리기
		for (int i = 0; i < winX / gap; i ++) {
			// y축
			g.drawLine(i * gap, 0, i * gap, winY);
				
			for (int j = 0; j < winY / gap; j ++)
				//x축
				g.drawLine(0,i * gap,winX, i * gap);
		}
	}
	
}
