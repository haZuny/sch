import javax.swing.*;

public class DrawingProgram extends JFrame{

	// 윈도우 사이즈
	static int winX = 800;
	static int winY = 600;
	
	// 그림 영역 사이즈
	static int penX = 500;
	static int penY = 500;
	static int gab = 5;
	
	// 컴포넌트
	// 그리기
	JLabel label_titleDraw;
	JLabel label_drawPoint;	// 점
	JLabel label_drawLine;	// 선
	JTextField text_drawLineP1;
	JTextField text_drawLineP2;
	JLabel label_drawCicle;	// 원
	JTextField text_drawCircleP;
	JTextField text_drawCircleR;
	JLabel label_drawTri;	// 삼각형
	JTextField text_drawTriP1;
	JTextField text_drawTriP2;
	JTextField text_drawTriP3;
	JLabel label_drawQuad;	// 사각형
	JTextField text_drawQuadP1;
	JTextField text_drawQuadP2;
	JTextField text_drawQuadP3;
	JTextField text_drawQuadP4;
	// 변환
	JLabel label_titleTrans;
	JLabel label_transMove;	// 이동
	
	
	
	

	// 생성자
	public DrawingProgram(String title) {
			setSize(winX, winY);
			setVisible(true);
			setTitle(title);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

	// 메인 메소드
	public static void main(String[] args) {
		
		DrawingProgram prg = new DrawingProgram("20194122 권하준");
		JPanel winPan = new JPanel();
		winPan.setSize(winX, winY);
		prg.add(winPan);
		
		Board drawPan = new Board(penX, penY, gab);
		winPan.add(drawPan);
		
		drawPan.repaint();

	}

}
