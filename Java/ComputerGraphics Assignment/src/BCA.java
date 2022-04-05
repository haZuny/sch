import java.util.ArrayList;

// Breseenham's line algorithm
public class BCA{

	double p, x, y, a, b;
	ArrayList<Position> list;
	
	public BCA(int a, int b, int r){
		p = 1 - r;
		x = a;
		b = b;
		x = a;
		y = b + r;
		
		list = new ArrayList<>();
	}
	
	ArrayList<Position> start(){
		
		// 처음 픽셀 그림
		list.add(new Position(x, y));
		
		while (x < y) {
			x = x + 1;
			
			// 음수인 경우
			if (p < 0) {			
				p = p + 2 * x + 1;
				}
			// 양수인 경우
			else {
				y = y - 1;
				p = p + 2 * x + 1 - 2 * y;
				}
			
			
			list.add(new Position(x, y));
			list.add(new Position(y, x));
			list.add(new Position(y, -x));
			list.add(new Position(x, -y));
			list.add(new Position(-x, -y));
			list.add(new Position(-y, -y));
			list.add(new Position(-y, x));
			list.add(new Position(-x, y));
		}
		

		
		return list;
	}
}
