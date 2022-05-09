import java.util.ArrayList;

public class LineClipping {

	// ��ġ ��Ʈ
	static int ABOVE = 8; // 1000, bit1
	static int BELOW = 4; // 0100, bit2
	static int RIGHT = 2; // 0010, bit3
	static int LEFT = 1; // 0001, bit4

	double x1, y1, x2, y2;
	double xMin, xMax, yMin, yMax;

	// �� ���� ��ġ ��Ʈ
	int p1_bit;
	int p2_bit;
	int and_bit; // and����

	ArrayList<Position> list;

	// ���� ���� ����, y = mx + yIntercept
	double m; // ����
	double yIntercept; // y����

	// ���� ��ǥ
	double leftIntersectionX;
	double leftIntersectionY;
	double rightIntersectionX;
	double rightIntersectionY;
	double aboveIntersectionX;
	double aboveIntersectionY;
	double belowIntersectionX;
	double belowIntersectionY;
	
	// Liang & Barsky ���
	double P1, P2, P3, P4;
	double q1, q2, q3, q4;
	double u1, u2, u3, u4;
	double realU1, realU2;
	
	Transform trans;

	public ArrayList<Position> start(Position p1, Position p2, Position wp1, Position wp2) {

		list = new ArrayList<>();

		// ��ġ ��Ʈ �ʱ�ȭ
		p1_bit = 0;
		p2_bit = 0;

		// ���� ��ǥ �ʱ�ȭ
		leftIntersectionX = 0;
		leftIntersectionY = 0;
		rightIntersectionX = 0;
		rightIntersectionY = 0;
		aboveIntersectionX = 0;
		aboveIntersectionY = 0;
		belowIntersectionX = 0;
		belowIntersectionY = 0;

		// �� ��ġ ����
		x1 = p1.x;
		y1 = p1.y;
		x2 = p2.x;
		y2 = p2.y;

		// ����� ����
		m = (y2 - y1) / (x2 - x1);
		yIntercept = y1 - m * x1;

		// ������ ���� ����
		xMin = (wp1.x < wp2.x) ? wp1.x : wp2.x;
		xMax = (wp1.x > wp2.x) ? wp1.x : wp2.x;
		yMin = (wp1.y < wp2.y) ? wp1.y : wp2.y;
		yMax = (wp1.y > wp2.y) ? wp1.y : wp2.y;

		// �� ���� ��ġ �Ǻ�
		p1_bit += (p1.x < xMin) ? LEFT : 0;
		p1_bit += (p1.x > xMax) ? RIGHT : 0;
		p1_bit += (p1.y < yMin) ? ABOVE : 0;
		p1_bit += (p1.y > yMax) ? BELOW : 0;
		p2_bit += (p2.x < xMin) ? LEFT : 0;
		p2_bit += (p2.x > xMax) ? RIGHT : 0;
		p2_bit += (p2.y < yMin) ? ABOVE : 0;
		p2_bit += (p2.y > yMax) ? BELOW : 0;

		// and ����
		and_bit = p1_bit & p2_bit;

		// �� �� ��� ���� ������ ���
		if (p1_bit == 0 && p2_bit == 0) {
			list.add(p1);
			list.add(p2);
		}
		// �� �� ��� ���� ���� ��� - �׸��� ����
		else if (and_bit != 0) {

		}
		// �� ���� ��� - Line Clipping
		else {
			// ������ ���� ���� ���
			if (p1_bit == 0)
				list.add(p1);
			if (p2_bit == 0)
				list.add(p2);

			// �� ��迡 ���� ��ǥ ����
			leftIntersectionX = xMin;
			leftIntersectionY = y1 + m * (xMin - x1);
			rightIntersectionX = xMax;
			rightIntersectionY = y1 + m * (xMax - x1);
			aboveIntersectionX = x1 + (yMin - y1) / m;
			aboveIntersectionY = yMin;
			belowIntersectionX = x1 + (yMax - y1) / m;
			belowIntersectionY = yMax;

			// ���� ������ ���� �����Ŀ� �����ϰ� �����ϸ� add
			if (leftIntersectionY >= yMin && leftIntersectionY <= yMax)
				list.add(new Position(leftIntersectionX, leftIntersectionY));
			if (rightIntersectionY >= yMin && rightIntersectionY <= yMax)
				list.add(new Position(rightIntersectionX, rightIntersectionY));
			if (aboveIntersectionX >= xMin && aboveIntersectionX <= xMax)
				list.add(new Position(aboveIntersectionX, aboveIntersectionY));
			if (belowIntersectionX >= xMin && belowIntersectionX <= xMax)
				list.add(new Position(belowIntersectionX, belowIntersectionY));
		}

		// ���� �������� ���
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
		
		// ���� ������� ������ null ����
		if (list.size() == 1)
			list.add(null);
		else if (list.size() == 0) {
			list.add(null);
			list.add(null);
		}
		
		return list;
	}
	
	
	// Liang & Barsky �˰���
	public ArrayList<Position> start2(Position p1, Position p2, Position wp1, Position wp2) {
		list = new ArrayList<>();

		// ��ġ ��Ʈ �ʱ�ȭ
		p1_bit = 0;
		p2_bit = 0;

		// ���� ��ǥ �ʱ�ȭ
		leftIntersectionX = 0;
		leftIntersectionY = 0;
		rightIntersectionX = 0;
		rightIntersectionY = 0;
		aboveIntersectionX = 0;
		aboveIntersectionY = 0;
		belowIntersectionX = 0;
		belowIntersectionY = 0;

		// �� ��ġ ����
		x1 = p1.x;
		y1 = p1.y;
		x2 = p2.x;
		y2 = p2.y;

		// ����� ����
		m = (y2 - y1) / (x2 - x1);
		yIntercept = y1 - m * x1;

		// ������ ���� ����
		xMin = (wp1.x < wp2.x) ? wp1.x : wp2.x;
		xMax = (wp1.x > wp2.x) ? wp1.x : wp2.x;
		yMin = (wp1.y < wp2.y) ? wp1.y : wp2.y;
		yMax = (wp1.y > wp2.y) ? wp1.y : wp2.y;

		// �� ���� ��ġ �Ǻ�
		p1_bit += (p1.x < xMin) ? LEFT : 0;
		p1_bit += (p1.x > xMax) ? RIGHT : 0;
		p1_bit += (p1.y < yMin) ? ABOVE : 0;
		p1_bit += (p1.y > yMax) ? BELOW : 0;
		p2_bit += (p2.x < xMin) ? LEFT : 0;
		p2_bit += (p2.x > xMax) ? RIGHT : 0;
		p2_bit += (p2.y < yMin) ? ABOVE : 0;
		p2_bit += (p2.y > yMax) ? BELOW : 0;

		// and ����
		and_bit = p1_bit & p2_bit;
		
		// p, q, u�� ���ϱ�
//		P1 = -Math.abs(x1 - x2);
//		P2 = Math.abs(x1 - x2);
//		P3 = -Math.abs(y1 - y2);
//		P4 = Math.abs(x1 - x2);
		P1 = x1 - x2;
		P2 = x2 - x1;
		P3 = y1 - y2;
		P4 = y2 - y1;
		
		q1 = x1 - xMin;
		q2 = xMax - x1;
		q3 = y1 - yMin;
		q4 = yMax - y1;
		
		u1 = q1 / P1;
		u2 = q2 / P2;
		u3 = q3 / P3;
		u4 = q4 / P4;
		
		realU1 = 0;
		realU2 = 0;
		
		// ������ u�� ����
		if (u1 < 0)
			u1 = 10;
		if (u2 < 0)
			u2 = 10;
		if (u3 < 0)
			u3 = 10;
		if (u4 < 0)
			u4 = 10;
		// u1, u2�� ����
		if (u1 < u3)
			realU1 = u1;
		if (u2 < u4) {
			if (realU1 == 0)
				realU1 = u2;
			else
				realU2 = u2;
		}
		if ( u3 < u1) {
			if (realU1 == 0)
				realU1 = u3;
			else
				realU2 = u3;
		}
		if ( u4 < u2) {
			if (realU1 == 0)
				realU1 = u4;
			else
				realU2 = u4;
		}
		System.out.println(realU1 + ", " + realU2);
		
		list.add(new Position(x1 + P2 * realU1, y1 + P4 * realU1));
		list.add(new Position(x1 + P2 * realU2, y1 + P4 * realU2));
				
		return list;
	}

}
