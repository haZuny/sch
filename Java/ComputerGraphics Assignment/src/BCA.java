import java.util.ArrayList;

// Breseenham's line algorithm
public class BCA{

	double p, x, y, px, py;
	ArrayList<Position> list;
	
	ArrayList<Position> start(Position pos, int radious){
		// ���� �߽�
				px = pos.x;
				py = pos.y;
				
				// �׸��� ��ǥ
				x = 0;
				y = radious;
				
				list = new ArrayList<>();
				
				// p0 ����
				p = 5/4 - radious;

				// �ʱ� ��ġ add
				list.add(new Position(px, py + y));
				list.add(new Position(px, py - y));
				list.add(new Position(px + y, py));
				list.add(new Position(px - y, py));
		
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
