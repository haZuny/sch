import javax.swing.*;


import java.util.ArrayList;

// 메인 클래스
public class DrawLine extends JFrame{
	
	// 윈도우 사이즈
	static int winX = 500;
	static int winY = 500;
	static int gab = 5;
	
	public ArrayList<Position> list = new ArrayList<>();	// 그림 그릴 위치 표시
	
	
	// 생성자
	public DrawLine(String title) {
		setSize(winX,winY);
		setVisible(true);
		setTitle(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
	}

		
	// Main
	public static void main(String[] args) {
		
		// 그릴 점 두개
		Position p1 = new Position(20,20);
		Position p2 = new Position(60,20);
		
;		
		DrawLine frame = new DrawLine("BLA");
		Board pan = new Board(frame.winX, frame.winY, gab);
		pan.myList = new ArrayList<Position>();
		frame.add(pan);
				
		BLA bla = new BLA(p1.x, p1.y, p2.x, p2.y);
		ArrayList<Position> blaList = new ArrayList<>();
		blaList.addAll(bla.start());
		pan.myList.addAll(blaList);
		pan.repaint();
		
		
		// move
		Transform trans = new Transform(blaList);
//		pan.myList.addAll(trans.translation(5, 30));
		pan.repaint();
		
		// scale
		Transform scale = new Transform(trans.translation(0, 10));
		pan.myList.addAll(scale.scale(0.5, 0.5, blaList.get(blaList.size()/2)));
		pan.repaint();
		
		// rotate
		pan.myList.addAll(trans.rotation(45, blaList.get(blaList.size()/2)));
		pan.repaint();
		

	}
	
	

}
