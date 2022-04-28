import java.util.ArrayList;

public class LineClipping {
	
	// ��ġ ��Ʈ
	static int ABOVE = 8;	// 1000, bit1
	static int BELOW = 4;	// 0100, bit2
	static int RIGHT = 2;	// 0010, bit3
	static int LEFT = 1;	// 0001, bit4

	int x1, y1, x2, y2;
	int xMin, xMax, yMin, yMax;
	
	// �� ���� ��ġ ��Ʈ
	int p1_bit;
	int p2_bit;
	int and_bit;	// and����
	
	ArrayList<Position> list;
	
	
	public ArrayList<Position> start(Position p1, Position p2, Position wp1, Position wp2) {
		list = new ArrayList<>();
		
		// ��ġ ��Ʈ �ʱ�ȭ
		p1_bit = 0;
		p2_bit = 0;
		
		// �� ��ġ ����
		x1 = p1.x;
		y1 = p1.y;
		x2 = p2.x;
		y2 = p2.y;
		
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
		if(p1_bit == 0 && p2_bit == 0) {
			list.add(p1);
			list.add(p2);
		}
		// �� �� ��� ���� ���� ��� - �׸��� ����
		else if(and_bit != 0) {
			
		}
		// �� ���� ��� - Line Clipping
		else {
			Position vertical;
			Position horizonnal;
			
		}
		
		
		return list;
	}
}
