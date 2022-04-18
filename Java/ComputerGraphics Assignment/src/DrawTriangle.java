import javax.swing.*;
import java.util.ArrayList;

// 메인 클래스
public class DrawTriangle extends JFrame{
	
	// 윈도우 사이즈
	static int winX = 700;
	static int winY = 700;
	static int gab = 5;

	
	// 생성자
	public DrawTriangle(String title) {
		setSize(winX,winY);
		setVisible(true);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}

		
	// Main
	public static void main(String[] args) {
		
		
	
		 // BCA 알고리즘
		DrawTriangle transFrame = new DrawTriangle("Triangle Transform");	// 프레임 생성
		Board transPen = new Board(winX, winY, gab);	// 패널 생성
		transPen.myList = new ArrayList<Position>();	// 리스트 생성
		transFrame.add(transPen);
		
		// 삼각형 그리기
		ArrayList<Position> triangleList = new ArrayList<>();
		BLA tri1 = new BLA(50, 30, 100, 100);
		triangleList.addAll(tri1.start());
		BLA tri2 = new BLA(50, 30, 30, 100);
		triangleList.addAll(tri2.start());
		BLA tri3 = new BLA(30, 100, 100, 100);
		triangleList.addAll(tri3.start());
		
		transPen.myList.addAll(triangleList);
		transPen.repaint();
		
		// 이동
		Transform trans = new Transform(triangleList);
		transPen.myList.addAll(trans.translation(30, 30));
		
		// 축소
		transPen.myList.addAll(trans.scale(0.5, 0.5, new Position(Math.round((30 + 50 + 100) / 3), Math.round((30 + 100 + 100) / 3))));
		
		// 회전
		transPen.myList.addAll(trans.rotation(45, new Position(Math.round((30 + 50 + 100) / 3), Math.round((30 + 100 + 100) / 3))));

	}
	
	

}
