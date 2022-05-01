import javax.swing.*;


import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

// 메인 클래스
public class DrawTriangle extends JFrame{
	
	// 윈도우 사이즈
	static int winX = 700;
	static int winY = 700;
	static int gab = 5;

	
	// 생성자
	public DrawTriangle(String title) {
		setSize(winX + 100, winY + 100);
		setVisible(true);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

		
	// Main
	public static void main(String[] args) {
			
		 // 윈도우 프레임, 페널 생성
		DrawTriangle transFrame = new DrawTriangle("Triangle Transform");
		Board transPen = new Board(winX, winY, gab);
		// 픽셀 버퍼 생성
		transPen.myList = new ArrayList<Position>();
		transFrame.add(transPen);
		
		// 삼각형 꼭지점
		Position point1 = new Position(50,  30);
		Position point2 = new Position(100, 100);
		Position point3 = new Position(30,  100);
		
		// 윈도우 영역
		Position winPoint1 = new Position(40, 40);
		Position winPoint2 = new Position(80, 120);
		
		// Clipping 객체
		LineClipping clipping = new LineClipping();
		
		
//		// 삼각형 그리기
//		ArrayList<Position> triangleList = new ArrayList<>();
//		BLA tri1 = new BLA(point1.x, point1.y, point2.x, point2.y);
//		triangleList.addAll(tri1.start());
//		
//		BLA tri2 = new BLA(point2.x, point2.y, point3.x, point3.y);
//		triangleList.addAll(tri2.start());
//		
//		BLA tri3 = new BLA(point1.x, point1.y, point3.x, point3.y);
//		triangleList.addAll(tri3.start());
		
		// 일정구역 삼각형 그리기
		ArrayList<Position> triangleList = new ArrayList<>();
		ArrayList<Position> lineList;
		
		lineList = clipping.start(point1, point2, winPoint1, winPoint2);
		BLA tri1 = new BLA(lineList.get(1).x, lineList.get(1).y, lineList.get(0).x, lineList.get(0).y);
		triangleList.addAll(tri1.start());
		
		lineList = clipping.start(point2, point3, winPoint1, winPoint2);
		BLA tri2 = new BLA(lineList.get(0).x, lineList.get(0).y, lineList.get(1).x, lineList.get(1).y);
		triangleList.addAll(tri2.start());
		
		lineList = clipping.start(point1, point3, winPoint1, winPoint2);
		BLA tri3 = new BLA(lineList.get(0).x, lineList.get(0).y, lineList.get(1).x, lineList.get(1).y);
		triangleList.addAll(tri3.start());
		
		
		transPen.myList.addAll(triangleList);
		transPen.repaint();
		
		
		// 삼각형 세 꼭지점
		ArrayList<Position> triList = new ArrayList<>();
		triList.add(point1);
		triList.add(point2);
		triList.add(point3);
		
		// 변환 객체
		Transform trans = new Transform(triList);
		
//		// 확대
//		ArrayList<Position> scaleList = new ArrayList<>(); 
//		scaleList.addAll(trans.scale(1.5, 1.5, new Position(Math.floor((30 + 50 + 100) / 3), Math.floor((30 + 100 + 100) / 3))));
//		
//		BLA scaleBLA1 = new BLA(scaleList.get(0).x, scaleList.get(0).y, scaleList.get(1).x, scaleList.get(1).y);
//		transPen.myList.addAll(scaleBLA1.start());
//		
//		BLA scaleBLA2 = new BLA(scaleList.get(1).x, scaleList.get(1).y, scaleList.get(2).x, scaleList.get(2).y);
//		transPen.myList.addAll(scaleBLA2.start());
//		
//		BLA scaleBLA3 = new BLA(scaleList.get(2).x, scaleList.get(2).y, scaleList.get(0).x, scaleList.get(0).y);
//		transPen.myList.addAll(scaleBLA3.start());
//		
//		
//		// 회전
//		ArrayList<Position> rotateList = new ArrayList<>();
//		rotateList.addAll(trans.rotation(180, new Position(Math.floor((30 + 50 + 100) / 3), Math.floor((30 + 100 + 100) / 3))));
//		
//		BLA rotateBLA1 = new BLA(rotateList.get(0).x, rotateList.get(0).y, rotateList.get(1).x, rotateList.get(1).y);
//		transPen.myList.addAll(rotateBLA1.start());	
//		BLA rotateBLA2 = new BLA(rotateList.get(2).x, rotateList.get(2).y, rotateList.get(1).x, rotateList.get(1).y);
//		transPen.myList.addAll(rotateBLA2.start());
//		BLA rotateBLA3 = new BLA(rotateList.get(2).x, rotateList.get(2).y, rotateList.get(0).x, rotateList.get(0).y);
//		transPen.myList.addAll(rotateBLA3.start());
			}
	
}
