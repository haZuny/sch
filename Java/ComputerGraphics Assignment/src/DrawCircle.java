import javax.swing.*;
import java.util.ArrayList;

// 메인 클래스
public class DrawCircle extends JFrame{
	
	int winX = Board.winX;
	int winY = Board.winY;
	
	public ArrayList<Position> list = new ArrayList<>();	// 그림 그릴 위치 표시
	
	
	// 생성자
	public DrawCircle(String title) {
		setSize(winX,winY);
		setVisible(true);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}

		
	// Main
	public static void main(String[] args) {
		
//		// 그릴 점 두개
//		Position p1 = new Position(0,0);
//		Position p2 = new Position(50,15);
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
		
		 // BCA 알고리즘
		DrawCircle bcaFrame = new DrawCircle("bla");	// 프레임 생성
		Board bcaPen = new Board();	// 패널 생성
		bcaFrame.add(bcaPen);
		BCA bca = new BCA(25,25,20);	// 출력할 픽셀 탐색
		bcaPen.myList = bca.start();
		bcaPen.repaint();
		System.out.println(bca.list);

	}
	
	

}
