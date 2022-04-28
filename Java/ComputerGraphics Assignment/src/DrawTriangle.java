import javax.swing.*;


import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

// ���� Ŭ����
public class DrawTriangle extends JFrame{
	
	// ������ ������
	static int winX = 700;
	static int winY = 700;
	static int gab = 5;

	
	// ������
	public DrawTriangle(String title) {
		setSize(winX + 100, winY + 100);
		setVisible(true);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

		
	// Main
	public static void main(String[] args) {
			
		 // BCA �˰���
		DrawTriangle transFrame = new DrawTriangle("Triangle Transform");	// ������ ����
		Board transPen = new Board(winX, winY, gab);	// �г� ����
		
		transPen.myList = new ArrayList<Position>();	// ����Ʈ ����
		transFrame.add(transPen);
		
		Position point1 = new Position(50,  30);
		Position point2 = new Position(100, 100);
		Position point3 = new Position(30,  100);
		
		// �ﰢ�� �׸���
		ArrayList<Position> triangleList = new ArrayList<>();
		BLA tri1 = new BLA(point1.x, point1.y, point2.x, point2.y);
		triangleList.addAll(tri1.start());
		
		BLA tri2 = new BLA(point2.x, point2.y, point3.x, point3.y);
		triangleList.addAll(tri2.start());
		
		BLA tri3 = new BLA(point1.x, point1.y, point3.x, point3.y);
		triangleList.addAll(tri3.start());
		
		transPen.myList.addAll(triangleList);
		transPen.repaint();
		
		
		ArrayList<Position> triList = new ArrayList<>();
		triList.add(point1);
		triList.add(point2);
		triList.add(point3);
		Transform trans = new Transform(triList);
		
		
		ArrayList<Position> scaleList = new ArrayList<>(); 
		// Ȯ��
		scaleList.addAll(trans.scale(1.5, 1.5, new Position(Math.floor((30 + 50 + 100) / 3), Math.floor((30 + 100 + 100) / 3))));
		
		BLA scaleBLA1 = new BLA(scaleList.get(0).x, scaleList.get(0).y, scaleList.get(1).x, scaleList.get(1).y);
		transPen.myList.addAll(scaleBLA1.start());
		
		BLA scaleBLA2 = new BLA(scaleList.get(1).x, scaleList.get(1).y, scaleList.get(2).x, scaleList.get(2).y);
		transPen.myList.addAll(scaleBLA2.start());
		
		BLA scaleBLA3 = new BLA(scaleList.get(2).x, scaleList.get(2).y, scaleList.get(0).x, scaleList.get(0).y);
		transPen.myList.addAll(scaleBLA3.start());
		
		
		ArrayList<Position> rotateList = new ArrayList<>();
		// ȸ��
		rotateList.addAll(trans.rotation(45, new Position(Math.floor((30 + 50 + 100) / 3), Math.floor((30 + 100 + 100) / 3))));
		System.out.println(rotateList);
		
		BLA rotateBLA1 = new BLA(rotateList.get(0).x, rotateList.get(0).y, rotateList.get(1).x, rotateList.get(1).y);
		transPen.myList.addAll(rotateBLA1.start());
		
		BLA rotateBLA2 = new BLA(rotateList.get(2).x, rotateList.get(2).y, rotateList.get(1).x, rotateList.get(1).y);
		transPen.myList.addAll(rotateBLA2.start());
		
		BLA rotateBLA3 = new BLA(rotateList.get(2).x, rotateList.get(2).y, rotateList.get(0).x, rotateList.get(0).y);
		transPen.myList.addAll(rotateBLA3.start());
	}
	
	

}
