import java.util.ArrayList;

public abstract class Figure {
	
	// �ȼ� ����
	ArrayList<Position> pixelBuf;
	
	// ���� ������Ʈ �޼ҵ�
	abstract void draw();
	abstract void clippigDraw(Position win1, Position win2);

}

class Point extends Figure{
	
	Position p1;
	
	// ������
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
		// ���� �ʱ�ȭ
		pixelBuf.clear();
		// Ŭ���� ���� ������ Ȯ��
		if (p1.x < win1.x || p1.x > win2.x)
			return;
		if (p1.y < win1.y || p1.y > win2.y)
			return;
		// Ŭ����
		pixelBuf.add(p1);
		
	}
	
	
}
