import javax.swing.JFrame;

public class DrawingProgram extends JFrame{

	// 윈도우 사이즈
	static int 
	static int winX = 500;
	static int winY = 500;
	static int gab = 5;

	// 생성자
	public DrawingProgram(String title) {
			setSize(winX + 100, winY + 100);
			setVisible(true);
			setTitle(title);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
