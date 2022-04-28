import java.util.ArrayList;

public class LineClipping {
	
	// 위치 비트
	static int ABOVE = 8;	// 1000, bit1
	static int BELOW = 4;	// 0100, bit2
	static int RIGHT = 2;	// 0010, bit3
	static int LEFT = 1;	// 0001, bit4

	int x1, y1, x2, y2;
	int xMin, xMax, yMin, yMax;
	
	// 각 점의 위치 비트
	int p1_bit;
	int p2_bit;
	int and_bit;	// and연산
	
	ArrayList<Position> list;
	
	
	public ArrayList<Position> start(Position p1, Position p2, Position wp1, Position wp2) {
		list = new ArrayList<>();
		
		// 위치 비트 초기화
		p1_bit = 0;
		p2_bit = 0;
		
		// 점 위치 받음
		x1 = p1.x;
		y1 = p1.y;
		x2 = p2.x;
		y2 = p2.y;
		
		// 윈도우 영역 받음
		xMin = (wp1.x < wp2.x) ? wp1.x : wp2.x;
		xMax = (wp1.x > wp2.x) ? wp1.x : wp2.x;
		yMin = (wp1.y < wp2.y) ? wp1.y : wp2.y;
		yMax = (wp1.y > wp2.y) ? wp1.y : wp2.y;
		
		// 두 점의 위치 판별
		p1_bit += (p1.x < xMin) ? LEFT : 0;
		p1_bit += (p1.x > xMax) ? RIGHT : 0;
		p1_bit += (p1.y < yMin) ? ABOVE : 0;
		p1_bit += (p1.y > yMax) ? BELOW : 0;
		p2_bit += (p2.x < xMin) ? LEFT : 0;
		p2_bit += (p2.x > xMax) ? RIGHT : 0;
		p2_bit += (p2.y < yMin) ? ABOVE : 0;
		p2_bit += (p2.y > yMax) ? BELOW : 0;
		
		// and 연산
		and_bit = p1_bit & p2_bit;
		
		// 두 점 모두 영역 안쪽인 경우
		if(p1_bit == 0 && p2_bit == 0) {
			list.add(p1);
			list.add(p2);
		}
		// 두 점 모두 영역 밖인 경우 - 그리지 않음
		else if(and_bit != 0) {
			
		}
		// 그 외의 경우 - Line Clipping
		else {
			Position vertical;
			Position horizonnal;
			
		}
		
		
		return list;
	}
}
