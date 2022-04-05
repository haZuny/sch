import java.util.ArrayList;

// Breseenham's line algorithm
public class BCA{

	double p, x, y, px, py;
	ArrayList<Position> list;
	
	public BCA(int a, int b, int r){
		// 원의 중심
		px = a;
		py = b;
		
		// 그리는 좌표
		x = 0;
		y = r;
		
		list = new ArrayList<>();
		
		// p0 설정
		p = 5/4 - r;

		// 초기 위치 add
		list.add(new Position(px, py + y));
		list.add(new Position(px, py - y));
		list.add(new Position(px + y, py));
		list.add(new Position(px - y, py));
	}
	
	ArrayList<Position> start(){
		
		// 처음 픽셀 그림	
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
			
			// 출력값 add
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
