import javax.swing.*;
import java.util.ArrayList;

// ���� Ŭ����
public class DrawCircle extends JFrame{
	
	int winX = Board.winX;
	int winY = Board.winY;
	
	public ArrayList<Position> list = new ArrayList<>();	// �׸� �׸� ��ġ ǥ��
	
	
	// ������
	public DrawCircle(String title) {
		setSize(winX,winY);
		setVisible(true);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}

		
	// Main
	public static void main(String[] args) {
		
//		// �׸� �� �ΰ�
//		Position p1 = new Position(0,0);
//		Position p2 = new Position(50,15);
//		
//		// dda �˰���
//		DrawLine ddaFrame = new DrawLine("dda");	// ������ ����
//		Board ddaPan = new Board();	// �г� ����
//		ddaFrame.add(ddaPan);
//		DDA dda = new DDA(p1.x, p1.y, p2.x, p2.y);	// ����� �ȼ� Ž��
//		ddaPan.myList = dda.start();
//		ddaPan.repaint();
//		
//		// bla �˰���
//		DrawLine blaFrame = new DrawLine("bla");	// ������ ����
//		Board blaPan = new Board();	// �г� ����
//		blaFrame.add(blaPan);
//		BLA bla = new BLA(p1.x, p1.y, p2.x, p2.y);	// ����� �ȼ� Ž��
//		blaPan.myList = bla.start();
//		blaPan.repaint();
		
		 // BCA �˰���
		DrawCircle bcaFrame = new DrawCircle("bla");	// ������ ����
		Board bcaPen = new Board();	// �г� ����
		bcaFrame.add(bcaPen);
		BCA bca = new BCA(25,25,20);	// ����� �ȼ� Ž��
		bcaPen.myList = bca.start();
		bcaPen.repaint();
		System.out.println(bca.list);

	}
	
	

}
