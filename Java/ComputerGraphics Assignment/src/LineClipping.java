import java.util.ArrayList;

public class LineClipping {

	// 위치 비트
	static int ABOVE = 8; // 1000, bit1
	static int BELOW = 4; // 0100, bit2
	static int RIGHT = 2; // 0010, bit3
	static int LEFT = 1; // 0001, bit4

	double x1, y1, x2, y2;
	double xMin, xMax, yMin, yMax;

	// 각 점의 위치 비트
	int p1_bit;
	int p2_bit;
	int and_bit; // and연산

	ArrayList<Position> list;

	// 직선 공식 선분, y = mx + yIntercept
	double m; // 기울기
	double yIntercept; // y절편

	// 교점 좌표
	double leftIntersectionX;
	double leftIntersectionY;
	double rightIntersectionX;
	double rightIntersectionY;
	double aboveIntersectionX;
	double aboveIntersectionY;
	double belowIntersectionX;
	double belowIntersectionY;
	
	// Liang & Barsky 상수
	double P1, P2, P3, P4;
	double q1, q2, q3, q4;
	double u1, u2, u3, u4;
	
	Transform trans;

	public ArrayList<Position> start(Position p1, Position p2, Position wp1, Position wp2) {

		list = new ArrayList<>();

		// 위치 비트 초기화
		p1_bit = 0;
		p2_bit = 0;

		// 교점 좌표 초기화
		leftIntersectionX = 0;
		leftIntersectionY = 0;
		rightIntersectionX = 0;
		rightIntersectionY = 0;
		aboveIntersectionX = 0;
		aboveIntersectionY = 0;
		belowIntersectionX = 0;
		belowIntersectionY = 0;

		// 점 위치 받음
		x1 = p1.x;
		y1 = p1.y;
		x2 = p2.x;
		y2 = p2.y;

		// 상수값 구함
		m = (y2 - y1) / (x2 - x1);
		yIntercept = y1 - m * x1;

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
		if (p1_bit == 0 && p2_bit == 0) {
			list.add(p1);
			list.add(p2);
		}
		// 두 점 모두 영역 밖인 경우 - 그리지 않음
		else if (and_bit != 0) {

		}
		// 그 외의 경우 - Line Clipping
		else {
			// 한쪽이 영역 안인 경우
			if (p1_bit == 0)
				list.add(p1);
			if (p2_bit == 0)
				list.add(p2);

			// 각 경계에 대한 좌표 구함
			leftIntersectionX = xMin;
			leftIntersectionY = y1 + m * (xMin - x1);
			rightIntersectionX = xMax;
			rightIntersectionY = y1 + m * (xMax - x1);
			aboveIntersectionX = x1 + (yMin - y1) / m;
			aboveIntersectionY = yMin;
			belowIntersectionX = x1 + (yMax - y1) / m;
			belowIntersectionY = yMax;

			// 구한 교점을 직선 방정식에 대입하고 성립하면 add
			if (leftIntersectionY >= yMin && leftIntersectionY <= yMax)
				list.add(new Position(leftIntersectionX, leftIntersectionY));
			if (rightIntersectionY >= yMin && rightIntersectionY <= yMax)
				list.add(new Position(rightIntersectionX, rightIntersectionY));
			if (aboveIntersectionX >= xMin && aboveIntersectionX <= xMax)
				list.add(new Position(aboveIntersectionX, aboveIntersectionY));
			if (belowIntersectionX >= xMin && belowIntersectionX <= xMax)
				list.add(new Position(belowIntersectionX, belowIntersectionY));
		}

		// 점이 여러개인 경우
		if (list.size() > 2) {
			Position p = null;
			for (Position point : list) {
				double x1 = p1.x < p2.x ? p1.x : p2.x;
				double x2 = p1.x > p2.x ? p1.x : p2.x;
				if (!(point.x > x1 && point.x < x2)) {
					p = point;
				}
			}
			list.remove(p);
		}

		// viewPort
		trans = new Transform(list);
		double scaleSX = (Program.panX / Program.gap) / (xMax - xMin);
		double scaleSY = (Program.panY / Program.gap) / (yMax - yMin);
		list = trans.scale(scaleSX, scaleSY, new Position(xMin, yMin));
		trans = new Transform(list);
		list = trans.translation(-xMin, -yMin);
		
		// 점이 충분하지 않으면 null 삽입
		if (list.size() == 1)
			list.add(null);
		else if (list.size() == 0) {
			list.add(null);
			list.add(null);
		}
		
		return list;
	}
	
	
	// Liang & Barsky 알고리즘
	public ArrayList<Position> start2(Position p1, Position p2, Position wp1, Position wp2) {
		list = new ArrayList<>();

		// 위치 비트 초기화
		p1_bit = 0;
		p2_bit = 0;

		// 교점 좌표 초기화
		leftIntersectionX = 0;
		leftIntersectionY = 0;
		rightIntersectionX = 0;
		rightIntersectionY = 0;
		aboveIntersectionX = 0;
		aboveIntersectionY = 0;
		belowIntersectionX = 0;
		belowIntersectionY = 0;

		// 점 위치 받음
		x1 = p1.x;
		y1 = p1.y;
		x2 = p2.x;
		y2 = p2.y;

		// 상수값 구함
		m = (y2 - y1) / (x2 - x1);
		yIntercept = y1 - m * x1;

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
		
		// p, q, u값 구하기
		P1 = x2 - x1;
		P2 = x1 - x2;
		P3 = y2 - y1;
		P4 = y1 - y2;
		
		q1 = x1 - xMin;
		q2 = xMax - x1;
		q3 = y1 - yMin;
		q4 = yMax - y1;
		
		u1 = q1 / P1;
		u2 = q2 / P2;
		u3 = q3 / P3;
		u4 = q4 / P4;
				
		return list;
	}

}
