//import javax.swing.*;
//
//import java.awt.BorderLayout;
//import java.awt.Color;
//import java.util.ArrayList;
//
//// 메인 클래스
//public class DrawTriangle extends JFrame {
//
//	// 윈도우 사이즈
//	static int winX = 700;
//	static int winY = 700;
//	static int gab = 5;
//
//	// 생성자
//	public DrawTriangle(String title) {
//		setSize(winX + 100, winY + 100);
//		setVisible(true);
//		setTitle(title);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	}
//
//	// Main
//	public static void main(String[] args) {
//
//		// 윈도우 프레임, 페널 생성
//		DrawTriangle transFrame = new DrawTriangle("Triangle Transform");
//		Board transPen = new Board(winX, winY, gab);
//		// 픽셀 버퍼 생성
//		transPen.myList = new ArrayList<Position>();
//		transFrame.add(transPen);
//
//		// 삼각형 꼭지점
//		Position point1 = new Position(50, 30);
//		Position point2 = new Position(100, 100);
//		Position point3 = new Position(30, 100);
//
//		// 윈도우 영역
//		Position winPoint1 = new Position(30, 30);
//		Position winPoint2 = new Position(100, 100);
//
//		// 연산 객체
//		BLA bla = new BLA(); // BLA
//		LineClipping clipping = new LineClipping(); // CLIPPING
//		ArrayList<Position> clippintLineList;
//		
//		// 사각형 그리기
//		transPen.myList.addAll(bla.start(winPoint1, new Position(winPoint1.x, winPoint2.y)));
//		transPen.myList.addAll(bla.start(winPoint1, new Position(winPoint2.x, winPoint1.y)));
//		transPen.myList.addAll(bla.start(winPoint2, new Position(winPoint1.x, winPoint2.y)));
//		transPen.myList.addAll(bla.start(winPoint2, new Position(winPoint2.x, winPoint1.y)));
//
////		// 삼각형 그리기
////		ArrayList<Position> triangleList = new ArrayList<>();
////		triangleList.addAll(bla.start(point1, point2));
////		triangleList.addAll(bla.start(point3, point2));
////		triangleList.addAll(bla.start(point1, point3));
////		
////		transPen.myList.addAll(triangleList);
//
//		// 삼각형 클립핑
//		ArrayList<Position> triangleList = new ArrayList<>();
//
//		clippintLineList = clipping.start(point1, point2, winPoint1, winPoint2);
//		triangleList.addAll(bla.start(clippintLineList.get(0), clippintLineList.get(1)));
//
//		clippintLineList = clipping.start(point2, point3, winPoint1, winPoint2);
//		triangleList.addAll(bla.start(clippintLineList.get(0), clippintLineList.get(1)));
//
//		clippintLineList = clipping.start(point1, point3, winPoint1, winPoint2);
//		triangleList.addAll(bla.start(clippintLineList.get(0), clippintLineList.get(1)));
//
//		transPen.myList.addAll(triangleList);
//		transPen.repaint();
//
//		// 삼각형 세 꼭지점 리스트
//		ArrayList<Position> triList = new ArrayList<>();
//		triList.add(point1);
//		triList.add(point2);
//		triList.add(point3);
//
//		// 변환 객체
//		Transform trans = new Transform(triList);
//
////		// 확대
////		ArrayList<Position> scaleList = new ArrayList<>(); 
////		scaleList.addAll(trans.scale(1.5, 1.5, new Position(Math.floor((30 + 50 + 100) / 3), Math.floor((30 + 100 + 100) / 3))));
////		
////		transPen.myList.addAll(bla.start(scaleList.get(0), scaleList.get(1)));
////		transPen.myList.addAll(bla.start(scaleList.get(2), scaleList.get(1)));
////		transPen.myList.addAll(bla.start(scaleList.get(0), scaleList.get(2)));
//
//		// 클리핑 확대
//		ArrayList<Position> scaleList2 = new ArrayList<>();
//		scaleList2.addAll(
//				trans.scale(1.5, 1.5, new Position(Math.floor((30 + 50 + 100) / 3), Math.floor((30 + 100 + 100) / 3))));
//
//		clippintLineList = clipping.start(scaleList2.get(0), scaleList2.get(1), winPoint1, winPoint2);
//		transPen.myList.addAll(bla.start(clippintLineList.get(0), clippintLineList.get(1)));
//
//		clippintLineList = clipping.start(scaleList2.get(1), scaleList2.get(2), winPoint1, winPoint2);
//		transPen.myList.addAll(bla.start(clippintLineList.get(0), clippintLineList.get(1)));
//
//		clippintLineList = clipping.start(scaleList2.get(2), scaleList2.get(0), winPoint1, winPoint2);
//		transPen.myList.addAll(bla.start(clippintLineList.get(0), clippintLineList.get(1)));
//
////		// 회전
////		ArrayList<Position> rotateList = new ArrayList<>();
////		rotateList.addAll(trans.rotation(180, new Position(Math.floor((30 + 50 + 100) / 3), Math.floor((30 + 100 + 100) / 3))));
////		
////		transPen.myList.addAll(bla.start(rotateList.get(0), rotateList.get(1)));
////		transPen.myList.addAll(bla.start(rotateList.get(0), rotateList.get(2)));
////		transPen.myList.addAll(bla.start(rotateList.get(2), rotateList.get(1)));
//
//		// 회전 클립핑
//		ArrayList<Position> rotateList2 = new ArrayList<>();
//		rotateList2.addAll(
//				trans.rotation(180, new Position(Math.floor((30 + 50 + 100) / 3), Math.floor((30 + 100 + 100) / 3))));
//
//		clippintLineList = clipping.start(rotateList2.get(0), rotateList2.get(1), winPoint1, winPoint2);
//		transPen.myList.addAll(bla.start(clippintLineList.get(0), clippintLineList.get(1)));
//		clippintLineList = clipping.start(rotateList2.get(2), rotateList2.get(1), winPoint1, winPoint2);
//		transPen.myList.addAll(bla.start(clippintLineList.get(0), clippintLineList.get(1)));
//		clippintLineList = clipping.start(rotateList2.get(0), rotateList2.get(2), winPoint1, winPoint2);
//		transPen.myList.addAll(bla.start(clippintLineList.get(0), clippintLineList.get(1)));
//	}
//
//}
