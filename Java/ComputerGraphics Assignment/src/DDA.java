import java.util.ArrayList;

public class DDA extends SuperClass{
	
	double dx, dy, steps;
	double xIncrement, yIncrement;
	
	
	// 생성자
	public DDA(int a, int b, int c, int d){
		super(a, b, c, d);
		
		// 선분 크기
		dx = x2 - x1;
		dy = y2 - y1;		

		// 만약 p1이 p2보다 오른쪽에 있으면 변경(xDorminent)
		if (a > c && Math.abs(dx) > Math.abs(dy)) {
			x = x1 = c;
			y = y1 = d;
			x2 = a;
			y2 = b;
		}
		
		else if (b > d && Math.abs(dx) < Math.abs(dy)) {
			x = x1 = c;
			y = y1 = d;
			x2 = a;
			y2 = b;
		}
		
		dx = x2 - x1;
		dy = y2 - y1;	

		
	}
	
	ArrayList<Position> start(){
		
		// 0 <= m <= 1 인경우
		if (Math.abs(dx) > Math.abs(dy))
			steps = dx;	// x기준 탐색
		
		// 1 <= m 인경우
		else
			steps = dy;	// y기준 탐색
		
		// 탐색 단위
		xIncrement = (double)dx / steps;
		yIncrement = (double)dy / steps;
			
		// 탐색, 반올림 하면서 탐색
		list.add(new Position(((int)Math.round(x)), (int)Math.round(y)));
			
		
		for (int i = 0; i < steps; i++) {
			x += xIncrement;
			y += yIncrement;
			list.add(new Position(((int)Math.round(x)), (int)Math.round(y)));
			System.out.println(list.get(list.size()-1).x + ", " + list.get(list.size()-1).y );
			
		}
		
		return list;
	}
}
