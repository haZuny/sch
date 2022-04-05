import javax.swing.*;
import java.util.ArrayList;

// 메인 클래스
public class DrawCircle extends JFrame{
	
	int winX = Board.winX;
	int winY = Board.winY;

	
	// 생성자
	public DrawCircle(String title) {
		setSize(winX,winY);
		setVisible(true);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}

		
	// Main
	public static void main(String[] args) {
		
		
	
		 // BCA 알고리즘
		DrawCircle bcaFrame = new DrawCircle("bla");	// 프레임 생성
		Board bcaPen = new Board();	// 패널 생성
		bcaPen.myList = new ArrayList<Position>();	// 리스트 생성
		bcaFrame.add(bcaPen);
		
		BCA bca = new BCA(100, 100, 50);	// 출력할 픽셀 탐색
		bcaPen.myList.addAll(bca.start());
		
		BCA bca2 =  new BCA(40, 40, 10);	// 출력할 픽셀 탐색
		bcaPen.myList.addAll(bca2.start());
		
		BCA bca3 =  new BCA(80, 80, 60);	// 출력할 픽셀 탐색
		bcaPen.myList.addAll(bca3.start());
		
		BCA bca4 =  new BCA(40, 40, 30);	// 출력할 픽셀 탐색
		bcaPen.myList.addAll(bca4.start());
		
		
		bcaPen.repaint();

	}
	
	

}
