import java.util.ArrayList;

// Breseenham's line algorithm
public class BLA extends SuperClass{

	double delX, delY, p0, pMC, pPC, p;
	
	public BLA(int a, int b, int c, int d){
		super(a,b,c,d);
		// 상수값 계산
		delX= x2 - x1;
		delY = y2 - y1;
		p0 = 2*delY - delX;
		pMC = 2 * delY;	// p minus constant
		pPC = 2 * delY - 2 * delX;	// p plus constant
		
		// d1 - d2
		p = p0;
	}
	
	ArrayList<Position> start(){
		
		// 처음 픽셀 그림
		list.add(new Position(x1, y1));
		
		for (int i = 0; i < delX; i++) {
			// 음수인 경우
			if (p < 0) {
				list.add(new Position(x = (x + 1), y));
				p += pMC;
				}
			// 양수인 경우
			else {
				list.add(new Position(x = (x + 1), y = (y+1)));
				p += pPC;
				}
		}
		
		return list;
		
	}
}
