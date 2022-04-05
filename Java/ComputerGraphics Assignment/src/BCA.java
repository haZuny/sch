import java.util.ArrayList;

// Breseenham's line algorithm
public class BCA{

	double p, x, y, px, py;
	ArrayList<Position> list;
	
	public BCA(int a, int b, int r){
		// ���� �߽�
		px = a;
		py = b;
		
		// �׸��� ��ǥ
		x = 0;
		y = r;
		
		list = new ArrayList<>();
		
		// p0 ����
		p = 5/4 - r;

		// �ʱ� ��ġ add
		list.add(new Position(px, py + y));
		list.add(new Position(px, py - y));
		list.add(new Position(px + y, py));
		list.add(new Position(px - y, py));
	}
	
	ArrayList<Position> start(){
		
		// ó�� �ȼ� �׸�	
		while (x < y) {
			x = x + 1;
			
			// ������ ���
			if (p < 0) {			
				p = p + 2 * x + 1;
			}
			// ����� ���
			else {
				y = y - 1;
				p = p + 2 * x + 1 - 2 * y;
			}
			
			// ��°� add
			list.add(new Position(px + x, py +y));
			list.add(new Position(px + y, py + x));
			list.add(new Position(px + y, py - x));
			list.add(new Position(px + x, py - y));
			list.add(new Position(px - x, py - y));
			list.add(new Position(px - y, py - x));
			list.add(new Position(px - y, py + x));
			list.add(new Position(px - x, py + y));
		}
		

		
		return list;
	}
}
