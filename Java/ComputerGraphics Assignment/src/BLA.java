import java.util.ArrayList;

// Breseenham's line algorithm
public class BLA {

	// �ȼ� ����
	public ArrayList<Position> list;

	double x, y, x1, y1, x2, y2;

	// �����
	double delX, delY, p0, pMC, pPC, p, xIncrease, yIncrease;

	ArrayList<Position> start(Position p1, Position p2) {
		System.out.println(p1 + "" + p2);

		list = new ArrayList<>();
		
		// �� �� �̻��� null�̸� ����ִ� ����Ʈ ��ȯ
		if (p1 == null || p2 == null)
			return list;
		
		// �ʱ�ȭ
		x = x1 = p1.x;
		y = y1 = p1.y;
		x2 = p2.x;
		y2 = p2.y;
		

		// �� �ٲ㼭 �ָ� ü����
		if (x1 > x2 && y1 > y2) {
			x = x1 = p2.x;
			y = y1 = p2.y;
			x2 = p1.x;
			y2 = p1.y;
		}

		delX = Math.abs(x2 - x1);
		delY = Math.abs(y2 - y1);

		// ���� p1�� p2���� �����ʿ� ������ ����(xDorminent)
		if (p1.x > p2.x && delX > delY) {
			x = x1 = p2.x;
			y = y1 = p2.y;
			x2 = p1.x;
			y2 = p1.y;
		}

		else if (p1.y > p2.y && delX < delY) {
			x = x1 = p2.x;
			y = y1 = p2.y;
			x2 = p1.x;
			y2 = p1.y;
		}

		delX = Math.abs(x2 - x1);
		delY = Math.abs(y2 - y1);

		// x dominant
		if (delX >= delY) {
			p0 = 2 * delY - delX;
			pMC = 2 * delY; // p minus constant
			pPC = 2 * delY - 2 * delX; // p plus constant
			// ���� ����
			if ((x2 - x1) * (y2 - y1) > 0) {
				yIncrease = 1;
			}
			// ���� ����
			else {
				yIncrease = -1;
			}
		}

		// y dominant
		else {
			p0 = 2 * delX - delY;
			pMC = 2 * delX;
			pPC = 2 * delX - 2 * delY;
			// ���� ����
			if ((x2 - x1) * (y2 - y1) > 0) {
				xIncrease = 1;
			} else {
				xIncrease = -1;
			}
		}

		// d1 - d2
		p = p0;

		// ó�� �ȼ� �׸�
		list.add(new Position(x1, y1));

		// x dominant
		if (delX >= delY) {
			for (int i = 0; i < delX; i++) {
				// ������ ���
				if (p < 0) {
					list.add(new Position(x = (x + 1), y));
					p += pMC;
				}
				// ����� ���
				else {
					list.add(new Position(x = (x + 1), y = (y + yIncrease)));
					p += pPC;
				}
			}
		}

		// y dominant
		else {
			for (int i = 0; i < delY; i++) {
				// ������ ���
				if (p < 0) {
					list.add(new Position(x, y = (y + 1)));
					p += pMC;
				}
				// ����� ���
				else {
					list.add(new Position(x = (x + xIncrease), y = (y + 1)));
					p += pPC;
				}
			}
		}

		return list;
	}
}
