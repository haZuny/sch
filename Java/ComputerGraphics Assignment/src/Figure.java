import java.util.ArrayList;

public abstract class Figure {
	
	// 픽셀 버퍼
	ArrayList<Position> pixelBuf;
	
	// 버퍼 업데이트 메소드
	abstract void draw();
	abstract void clippigDraw(Position win1, Position win2);

}

class Point extends Figure{
	
	Position p1;
	
	// 생성자
	public Point(Position a){
		pixelBuf = new ArrayList<>();
		p1 = a;
	}
	
	@Override
	void draw() {
		pixelBuf.clear();
		pixelBuf.add(p1);
	}
	
	@Override
	void clippigDraw(Position win1, Position win2) {
		// 버퍼 초기화
		pixelBuf.clear();
		// 클리핑 영역 안인지 확인
		if (p1.x < win1.x || p1.x > win2.x)
			return;
		if (p1.y < win1.y || p1.y > win2.y)
			return;
		// 클리핑
		pixelBuf.add(p1);
		
	}
	
	
}
