import javax.swing.*;
import java.util.ArrayList;

// 메인 클래스
public class DrawLine extends JFrame{
	
	// 윈도우 사이즈
	static int winX = 500;
	static int winY = 500;
	static int gab = 10;
	
	public ArrayList<Position> list = new ArrayList<>();	// 그림 그릴 위치 표시
	
	
	// 생성자
	public DrawLine(String title) {
		setSize(winX,winY);
		setVisible(true);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}

		
	// Main
	public static void main(String[] args) {
		
		// 그릴 점 두개
		Position p1 = new Position(0,0);
		Position p2 = new Position(50,15);
		
;		
		DrawLine frame = new DrawLine("둘다");
		Board pan = new Board(frame.winX, frame.winY, gab);
		pan.myList = new ArrayList<Position>();
		frame.add(pan);
		
		DDA dda = new DDA(p1.x, p1.y, p2.x, p2.y);
		BLA bla = new BLA(p1.x, p1.y+10, p2.x, p2.y+10);
		pan.myList.addAll(dda.start());
		pan.myList.addAll(bla.start());
		pan.repaint();
		
		
//		
//		// dda 알고리즘
//		DrawLine ddaFrame = new DrawLine("dda");	// 프레임 생성
//		Board ddaPan = new Board();	// 패널 생성
//		ddaFrame.add(ddaPan);
//		DDA dda = new DDA(p1.x, p1.y, p2.x, p2.y);	// 출력할 픽셀 탐색
//		ddaPan.myList = dda.start();
//		ddaPan.repaint();
//		
//		// bla 알고리즘
//		DrawLine blaFrame = new DrawLine("bla");	// 프레임 생성
//		Board blaPan = new Board();	// 패널 생성
//		blaFrame.add(blaPan);
//		BLA bla = new BLA(p1.x, p1.y, p2.x, p2.y);	// 출력할 픽셀 탐색
//		blaPan.myList = bla.start();
//		blaPan.repaint();

	}
	
	

}
