import java.util.ArrayList;

public class SuperClass {
	public ArrayList<Position> list = new ArrayList<>();	// 그림 그릴 위치 표시
	double x, y, x1, y1, x2, y2;
	
	public SuperClass(int a1, int b1, int a2, int b2) {
		x = x1 = a1;
		y = y1 = b1;
		x2 = a2;
		y2 = b2;
	}
}
