import javax.swing.*;

public class DrawingProgram extends JFrame{

	// ������ ������
	static int winX = 800;
	static int winY = 600;
	
	// �׸� ���� ������
	static int penX = 500;
	static int penY = 500;
	static int gab = 5;
	
	// ������Ʈ
	// �׸���
	JLabel label_titleDraw;
	JLabel label_drawPoint;	// ��
	JLabel label_drawLine;	// ��
	JTextField text_drawLineP1;
	JTextField text_drawLineP2;
	JLabel label_drawCicle;	// ��
	JTextField text_drawCircleP;
	JTextField text_drawCircleR;
	JLabel label_drawTri;	// �ﰢ��
	JTextField text_drawTriP1;
	JTextField text_drawTriP2;
	JTextField text_drawTriP3;
	JLabel label_drawQuad;	// �簢��
	JTextField text_drawQuadP1;
	JTextField text_drawQuadP2;
	JTextField text_drawQuadP3;
	JTextField text_drawQuadP4;
	// ��ȯ
	JLabel label_titleTrans;
	JLabel label_transMove;	// �̵�
	
	
	
	

	// ������
	public DrawingProgram(String title) {
			setSize(winX, winY);
			setVisible(true);
			setTitle(title);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

	// ���� �޼ҵ�
	public static void main(String[] args) {
		
		DrawingProgram prg = new DrawingProgram("20194122 ������");
		JPanel winPan = new JPanel();
		winPan.setSize(winX, winY);
		prg.add(winPan);
		
		Board drawPan = new Board(penX, penY, gab);
		winPan.add(drawPan);
		
		drawPan.repaint();

	}

}
