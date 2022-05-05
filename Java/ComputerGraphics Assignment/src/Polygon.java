import java.util.ArrayList;
import java.util.Arrays;


public abstract class Polygon {
	ArrayList<Position> fixelList;
	ArrayList<Position> clippedFixelList;
	LineClipping clipping;

	abstract void transMove(Position pa, Position pb);
	abstract void transScale(String sizeX, String sizeY, Position pa);
	abstract void clipClip(Position pa, Position pb);

	public Polygon() {
		clipping = new LineClipping();
		fixelList = new ArrayList<>();
		clippedFixelList = new ArrayList<>();
	}
}

// 점
class Point2 extends Polygon {
	Position p1;
	Transform trans;
	ArrayList<Position> transList;

	public Point2(Position p) {
		java.util.List<Position> list = Arrays.asList(p1);
		trans = new Transform(new ArrayList<>(list));
		p1 = p;
		// draw
		fixelList.clear();
		fixelList.add(p1);
	}

	@Override
	void transMove(Position pa, Position pb) {
		transList = trans.translation(pb.x - pa.x, pb.y - pa.y);
		fixelList.clear();
		fixelList.add(transList.get(0));	
		p1 = transList.get(0);
	}
	
	@Override
	void transScale(String sizeX, String sizeY, Position pa) {
		
	}

	@Override
	void clipClip(Position pa, Position pb) {
		clippedFixelList.clear();
	}
}

// 선
class Line extends Polygon {
	Position p1;
	Position p2;
	Transform trans;
	ArrayList<Position> transList;
	BLA bla;

	public Line(Position pa, Position pb) {
		p1 = pa;
		p2 = pb;
		java.util.List<Position> list = Arrays.asList(p1, p2);
		trans = new Transform(new ArrayList<>(list));
		bla = new BLA();
		// draw
		fixelList.clear();
		fixelList.addAll(bla.start(p1, p2));
	}
	
	@Override
	void transMove(Position pa, Position pb) {
		transList = trans.translation(pb.x - pa.x, pb.y - pa.y);
		fixelList.clear();
		fixelList.addAll(bla.start(transList.get(0), transList.get(1)));
		p1 = transList.get(0);
		p2 = transList.get(1);
	}
	
	@Override
	void transScale(String sizeX, String sizeY, Position pa) {
		try {
			transList = trans.scale(Double.parseDouble(sizeX), Double.parseDouble(sizeY), pa);
			fixelList.clear();
			fixelList.addAll(bla.start(transList.get(0), transList.get(1)));
			p1 = transList.get(0);
			p2 = transList.get(1);
		} catch (NumberFormatException ee) {
			Board.clickedPos.clear();
		}	
	}


	@Override
	void clipClip(Position pa, Position pb) {
		clippedFixelList.clear();
	}
}

//원
class Circle extends Polygon {
	Position p1;
	int rad;
	Transform trans;
	ArrayList<Position> transList;
	BCA bca;

	public Circle(Position p, int radious) {
		p1 = p;
		rad = radious;
		trans = new Transform(fixelList);
		bca = new BCA();
		// draw
		fixelList.clear();
		fixelList.addAll(bca.start(p1, rad));
	}
	
	@Override
	void transMove(Position pa, Position pb) {
		transList = trans.translation(pb.x - pa.x, pb.y - pa.y);
		fixelList.clear();
		fixelList.addAll(transList);	
		p1 = transList.get(0);
	}
	
	@Override
	void transScale(String sizeX, String sizeY, Position pa) {
		try {
			transList = trans.scale(Double.parseDouble(sizeX), Double.parseDouble(sizeY), pa);
			fixelList.clear();
			fixelList.addAll(transList);
		} catch (NumberFormatException ee) {
			Board.clickedPos.clear();
		}	
	}
	
	@Override
	void clipClip(Position pa, Position pb) {
		clippedFixelList.clear();
	}
}

// 삼각형
class Triangle extends Polygon {
	Position p1;
	Position p2;
	Position p3;
	Transform trans;
	ArrayList<Position> transList;
	BLA bla;

	public Triangle(Position pa, Position pb, Position pc) {
		p1 = pa;
		p2 = pb;
		p3 = pc;
		java.util.List<Position> list = Arrays.asList(p1, p2, p3);
		trans = new Transform(new ArrayList<>(list));
		bla = new BLA();
		// draw
		fixelList.clear();
		fixelList.addAll(bla.start(p1, p2));
		fixelList.addAll(bla.start(p1, p3));
		fixelList.addAll(bla.start(p2, p3));
	}
	
	@Override
	void transMove(Position pa, Position pb) {
		transList = trans.translation(pb.x - pa.x, pb.y - pa.y);
		fixelList.clear();
		fixelList.addAll(bla.start(transList.get(0), transList.get(1)));		
		fixelList.addAll(bla.start(transList.get(0), transList.get(2)));		
		fixelList.addAll(bla.start(transList.get(2), transList.get(1)));
		p1 = transList.get(0);
		p2 = transList.get(1);
		p3 = transList.get(2);
	}
	
	@Override
	void transScale(String sizeX, String sizeY, Position pa) {
		// TODO Auto-generated method stub
		
	}

	@Override
	void clipClip(Position pa, Position pb) {
		clippedFixelList.clear();
	}
}

//사각형
class Quad extends Polygon {
	Position p1;
	Position p2;
	Position p3;
	Position p4;
	Transform trans;
	ArrayList<Position> transList;
	BLA bla;

	public Quad(Position pa, Position pb, Position pc, Position pd) {
		p1 = pa;
		p2 = pb;
		p3 = pc;
		p4 = pd;
		java.util.List<Position> list = Arrays.asList(p1, p2, p3, p4);
		trans = new Transform(new ArrayList<>(list));
		bla = new BLA();
		// draw
		fixelList.clear();
		fixelList.addAll(bla.start(p1, p2));
		fixelList.addAll(bla.start(p2, p3));
		fixelList.addAll(bla.start(p4, p3));
		fixelList.addAll(bla.start(p1, p4));
	}

	@Override
	void transMove(Position pa, Position pb) {
		transList = trans.translation(pb.x - pa.x, pb.y - pa.y);
		fixelList.clear();
		fixelList.addAll(bla.start(transList.get(0), transList.get(1)));		
		fixelList.addAll(bla.start(transList.get(1), transList.get(2)));		
		fixelList.addAll(bla.start(transList.get(2), transList.get(3)));		
		fixelList.addAll(bla.start(transList.get(3), transList.get(0)));	
		p1 = transList.get(0);
		p1 = transList.get(1);
		p2 = transList.get(2);
		p3 = transList.get(3);
	}
	
	@Override
	void transScale(String sizeX, String sizeY, Position pa) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	void clipClip(Position pa, Position pb) {
		clippedFixelList.clear();
	}
}