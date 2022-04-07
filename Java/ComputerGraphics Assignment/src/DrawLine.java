import javax.swing.*;
import java.util.ArrayList;

// ���� Ŭ����
public class DrawLine extends JFrame{
	
	// ������ ������
	static int winX = 500;
	static int winY = 500;
	static int gab = 10;
	
	public ArrayList<Position> list = new ArrayList<>();	// �׸� �׸� ��ġ ǥ��
	
	
	// ������
	public DrawLine(String title) {
		setSize(winX,winY);
		setVisible(true);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}

		
	// Main
	public static void main(String[] args) {
		
		// �׸� �� �ΰ�
		Position p1 = new Position(0,0);
		Position p2 = new Position(50,15);
		
;		
		DrawLine frame = new DrawLine("�Ѵ�");
		Board pan = new Board(frame.winX, frame.winY, gab);
		pan.myList = new ArrayList<Position>();
		frame.add(pan);
		
		DDA dda = new DDA(p1.x, p1.y, p2.x, p2.y);
		BLA bla = new BLA(p1.x, p1.y+10, p2.x, p2.y+10);
		pan.myList.addAll(dda.start());
		pan.myList.addAll(bla.start());
		pan.repaint();
		
		
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

	}
	
	

}
