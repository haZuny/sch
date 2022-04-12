import javax.swing.*;
import java.util.ArrayList;

// ���� Ŭ����
public class DrawCircle extends JFrame{
	
	// ������ ������
	static int winX = 700;
	static int winY = 700;
	static int gab = 3;

	
	// ������
	public DrawCircle(String title) {
		setSize(winX,winY);
		setVisible(true);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}

		
	// Main
	public static void main(String[] args) {
		
		
	
		 // BCA �˰���
		DrawCircle bcaFrame = new DrawCircle("bla");	// ������ ����
		Board bcaPen = new Board(winX, winY, gab);	// �г� ����
		bcaPen.myList = new ArrayList<Position>();	// ����Ʈ ����
		bcaFrame.add(bcaPen);
		
		BCA bca = new BCA(100, 100, 50);	// ����� �ȼ� Ž��
		bcaPen.myList.addAll(bca.start());
		
		BCA bca2 =  new BCA(40, 40, 10);	// ����� �ȼ� Ž��
		bcaPen.myList.addAll(bca2.start());
		
		BCA bca3 =  new BCA(80, 80, 60);	// ����� �ȼ� Ž��
		bcaPen.myList.addAll(bca3.start());
		
		BCA bca4 =  new BCA(40, 40, 30);	// ����� �ȼ� Ž��
		bcaPen.myList.addAll(bca4.start());
		
		
		bcaPen.repaint();

	}
	
	

}
