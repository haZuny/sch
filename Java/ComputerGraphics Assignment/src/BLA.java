import java.util.ArrayList;

// Breseenham's line algorithm
public class BLA extends SuperClass{

	double delX, delY, p0, pMC, pPC, p, xIncrease, yIncrease;
	
	public BLA(int a, int b, int c, int d){
		super(a,b,c,d);
		
		// ����� ���
		delX= Math.abs(x2 - x1);
		delY = Math.abs(y2 - y1);
		
		// ���� p1�� p2���� �����ʿ� ������ ����(xDorminent)
		if (a > c && delX > delY) {
			x = x1 = c;
			y = y1 = d;
			x2 = a;
					y2 = b;
		}
				
		else if (b > d && delX < delY) {
			x = x1 = c;
			y = y1 = d;
			x2 = a;
			y2 = b;
		}
		
		
//		delX= Math.abs(x2 - x1);
//		delY = Math.abs(y2 - y1);
		
		delX= Math.abs(x2 - x1);
		delY = Math.abs(y2 - y1);
		
		
		// x dominant
		if (delX >= delY) {
			p0 = 2*delY - delX;
			pMC = 2 * delY;	// p minus constant
			pPC = 2 * delY - 2 * delX;	// p plus constant
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
			}
			else {
				xIncrease = -1;
			}
		}
		
		// d1 - d2
		p = p0;
	}
	
	ArrayList<Position> start(){
				
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
