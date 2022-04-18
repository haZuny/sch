import javax.swing.*;
import java.util.ArrayList;

// ���� Ŭ����
public class DrawTriangle extends JFrame{
	
	// ������ ������
	static int winX = 700;
	static int winY = 700;
	static int gab = 5;

	
	// ������
	public DrawTriangle(String title) {
		setSize(winX,winY);
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
		
		// �ﰢ�� �׸���
		ArrayList<Position> triangleList = new ArrayList<>();
		BLA tri1 = new BLA(50, 30, 100, 100);
		triangleList.addAll(tri1.start());
		BLA tri2 = new BLA(50, 30, 30, 100);
		triangleList.addAll(tri2.start());
		BLA tri3 = new BLA(30, 100, 100, 100);
		triangleList.addAll(tri3.start());
		
		transPen.myList.addAll(triangleList);
		transPen.repaint();
		
		// �̵�
		Transform trans = new Transform(triangleList);
		transPen.myList.addAll(trans.translation(30, 30));
		
		// ���
		transPen.myList.addAll(trans.scale(0.5, 0.5, new Position(Math.round((30 + 50 + 100) / 3), Math.round((30 + 100 + 100) / 3))));
		
		// ȸ��
		transPen.myList.addAll(trans.rotation(45, new Position(Math.round((30 + 50 + 100) / 3), Math.round((30 + 100 + 100) / 3))));

	}
	
	

}
