import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.*;



public class Board extends JPanel{
	
	static int winX = 1000;
	static int winY = 1000;
	ArrayList<Position> myList;

	// 픽셀 간격
	int gap;
	
	// 생성자
	public Board() {
		gap = 5;
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
