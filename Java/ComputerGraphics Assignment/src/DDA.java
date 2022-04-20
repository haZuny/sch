import java.util.ArrayList;

public class DDA extends SuperClass{
	
	double dx, dy, steps;
	double xIncrement, yIncrement;
	
	
	// ������
	public DDA(int a, int b, int c, int d){
		super(a, b, c, d);
		
		// ���� ũ��
		dx = x2 - x1;
		dy = y2 - y1;		

		// ���� p1�� p2���� �����ʿ� ������ ����(xDorminent)
		if (a > c && Math.abs(dx) > Math.abs(dy)) {
			x = x1 = c;
			y = y1 = d;
			x2 = a;
			y2 = b;
		}
		
		else if (b > d && Math.abs(dx) < Math.abs(dy)) {
			x = x1 = c;
			y = y1 = d;
			x2 = a;
			y2 = b;
		}
		
		dx = x2 - x1;
		dy = y2 - y1;	

		
	}
	
	ArrayList<Position> start(){
		
		// 0 <= m <= 1 �ΰ��
		if (Math.abs(dx) > Math.abs(dy))
			steps = dx;	// x���� Ž��
		
		// 1 <= m �ΰ��
		else
			steps = dy;	// y���� Ž��
		
		// Ž�� ����
		xIncrement = (double)dx / steps;
		yIncrement = (double)dy / steps;
			
		// Ž��, �ݿø� �ϸ鼭 Ž��
		list.add(new Position(((int)Math.round(x)), (int)Math.round(y)));
			
		
		for (int i = 0; i < steps; i++) {
			x += xIncrement;
			y += yIncrement;
			list.add(new Position(((int)Math.round(x)), (int)Math.round(y)));
			
		}
		
		return list;
	}
}
