import java.util.ArrayList;
import java.util.Arrays;

public abstract class Polygon {
	ArrayList<Position> fixelList;
	ArrayList<Position> clippedFixelList;
	ArrayList<Position> transList;
	LineClipping clipping;

	abstract void transMove(Position pa, Position pb);

	abstract void transScale(String sizeX, String sizeY, Position pa);

	abstract void transRotate(String angle, Position pa);

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

	public Point2(Position p) {
		p1 = p;
		trans = new Transform(new ArrayList<>(Arrays.asList(p1)));
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
		try {
			transList = trans.scale(Double.parseDouble(sizeX), Double.parseDouble(sizeY), pa);
			fixelList.clear();
			fixelList.add(transList.get(0));
			p1 = transList.get(0);
		} catch (NumberFormatException ee) {
			Board.clickedPos.clear();
		}
	}

	@Override
	void transRotate(String angle, Position pa) {
		try {
			transList = trans.rotation(Integer.parseInt(angle), pa);
			fixelList.clear();
			fixelList.add(transList.get(0));
			p1 = transList.get(0);
		} catch (NumberFormatException ee) {
			Board.clickedPos.clear();
		}
	}

	@Override
	void clipClip(Position pa, Position pb) {
//		int xMin = (pa.x < pb.x) ? pa.x : pb.x;
//		int xMax = (pa.x > pb.x) ? pa.x : pb.x;
//		int yMin = (pa.y < pb.y) ? pa.y : pb.y;
//		int yMax = (pa.y > pb.y) ? pa.y : pb.y;
//		clippedFixelList.clear();
//
//		if (p1.x >= xMin && p1.x <= xMax) {
//			if (p1.y >= yMin && p1.y <= yMax)
//				clippedFixelList.add(p1);
//		}

	}
}

// 선
class Line extends Polygon {
	Position p1;
	Position p2;
	Transform trans;
	BLA bla;

	public Line(Position pa, Position pb) {
		p1 = pa;
		p2 = pb;
		trans = new Transform(new ArrayList<>(Arrays.asList(p1, p2)));
		bla = new BLA();
		// draw
		fixelList.clear();
		fixelList.addAll(bla.start(p1, p2));
	}

	@Override
	void transMove(Position pa, Position pb) {
		transList = trans.translation(pb.x - pa.x, pb.y - pa.y);
		fixelList.clear();
		p1 = transList.get(0);
		p2 = transList.get(1);
		fixelList.addAll(bla.start(p1, p2));
	}

	@Override
	void transScale(String sizeX, String sizeY, Position pa) {
		try {
			transList = trans.scale(Double.parseDouble(sizeX), Double.parseDouble(sizeY), pa);
			fixelList.clear();
			p1 = transList.get(0);
			p2 = transList.get(1);
			fixelList.addAll(bla.start(p1, p2));
		} catch (NumberFormatException ee) {
			Board.clickedPos.clear();
		}
	}

	@Override
	void transRotate(String angle, Position pa) {
		try {
			transList = trans.rotation(Integer.parseInt(angle), pa);
			fixelList.clear();
			p1 = transList.get(0);
			p2 = transList.get(1);
			fixelList.addAll(bla.start(p1, p2));
		} catch (NumberFormatException ee) {
			Board.clickedPos.clear();
		}
	}

	@Override
	void clipClip(Position pa, Position pb) {
		int xMin = (pa.x < pb.x) ? pa.x : pb.x;
		int xMax = (pa.x > pb.x) ? pa.x : pb.x;
		int yMin = (pa.y < pb.y) ? pa.y : pb.y;
		int yMax = (pa.y > pb.y) ? pa.y : pb.y;
		clippedFixelList.clear();

		transList = clipping.start(p1, p2, pa, pb);
		clippedFixelList.addAll(bla.start(transList.get(0), transList.get(1)));
	}
}

//원
class Circle extends Polygon {
	Position p1;
	int rad;
	Transform trans;
	BCA bca;

	public Circle(Position p, int radious) {
		p1 = p;
		rad = radious;
		trans = new Transform(new ArrayList<Position>(Arrays.asList(p1, new Position(p1.x + rad, p1.y))));
		bca = new BCA();
		// draw
		fixelList.clear();
		fixelList.addAll(bca.start(p1, rad));

	}

	@Override
	void transMove(Position pa, Position pb) {
		transList = trans.translation(pb.x - pa.x, pb.y - pa.y);
		p1 = transList.get(0);
		rad = (int) Math.abs(Math.sqrt(Math.pow(transList.get(1).x - transList.get(0).x, 2)
				+ Math.pow(transList.get(1).y - transList.get(0).y, 2)));
		fixelList.clear();
		fixelList.addAll(bca.start(p1, rad));
	}

	@Override
	void transScale(String sizeX, String sizeY, Position pa) {
		try {
			transList = trans.scale(Double.parseDouble(sizeX), Double.parseDouble(sizeY), pa);
			p1 = transList.get(0);
			rad = (int) Math.abs(Math.sqrt(Math.pow(transList.get(1).x - transList.get(0).x, 2)
					+ Math.pow(transList.get(1).y - transList.get(0).y, 2)));
			fixelList.clear();
			fixelList.addAll(bca.start(p1, rad));
		} catch (NumberFormatException ee) {
			Board.clickedPos.clear();
		}
	}

	@Override
	void transRotate(String angle, Position pa) {
		try {
			transList = trans.rotation(Integer.parseInt(angle), pa);
			p1 = transList.get(0);
			rad = (int) Math.abs(Math.sqrt(Math.pow(transList.get(1).x - transList.get(0).x, 2)
					+ Math.pow(transList.get(1).y - transList.get(0).y, 2)));
			fixelList.clear();
			fixelList.addAll(bca.start(p1, rad));
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
	BLA bla;

	public Triangle(Position pa, Position pb, Position pc) {
		p1 = pa;
		p2 = pb;
		p3 = pc;
		trans = new Transform(new ArrayList<>(Arrays.asList(p1, p2, p3)));
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
		p1 = transList.get(0);
		p2 = transList.get(1);
		p3 = transList.get(2);
		fixelList.addAll(bla.start(p1, p2));
		fixelList.addAll(bla.start(p2, p3));
		fixelList.addAll(bla.start(p1, p3));
	}

	@Override
	void transScale(String sizeX, String sizeY, Position pa) {
		try {
			transList = trans.scale(Double.parseDouble(sizeX), Double.parseDouble(sizeY), pa);
			fixelList.clear();
			p1 = transList.get(0);
			p2 = transList.get(1);
			p3 = transList.get(2);
			fixelList.addAll(bla.start(p1, p2));
			fixelList.addAll(bla.start(p2, p3));
			fixelList.addAll(bla.start(p1, p3));
		} catch (NumberFormatException ee) {
			Board.clickedPos.clear();
		}
	}

	@Override
	void transRotate(String angle, Position pa) {
		try {
			transList = trans.rotation(Integer.parseInt(angle), pa);
			fixelList.clear();
			p1 = transList.get(0);
			p2 = transList.get(1);
			p3 = transList.get(2);
			fixelList.addAll(bla.start(p1, p2));
			fixelList.addAll(bla.start(p2, p3));
			fixelList.addAll(bla.start(p1, p3));
		} catch (NumberFormatException ee) {
			Board.clickedPos.clear();
		}
	}

	@Override
	void clipClip(Position pa, Position pb) {
		int xMin = (pa.x < pb.x) ? pa.x : pb.x;
		int xMax = (pa.x > pb.x) ? pa.x : pb.x;
		int yMin = (pa.y < pb.y) ? pa.y : pb.y;
		int yMax = (pa.y > pb.y) ? pa.y : pb.y;
		clippedFixelList.clear();

		transList = clipping.start(p1, p2, pa, pb);
		clippedFixelList.addAll(bla.start(transList.get(0), transList.get(1)));
		transList = clipping.start(p2, p3, pa, pb);
		clippedFixelList.addAll(bla.start(transList.get(0), transList.get(1)));
		transList = clipping.start(p1, p3, pa, pb);
		clippedFixelList.addAll(bla.start(transList.get(0), transList.get(1)));
	}
}

//사각형
class Quad extends Polygon {
	Position p1;
	Position p2;
	Position p3;
	Position p4;
	Transform trans;
	BLA bla;

	public Quad(Position pa, Position pb, Position pc, Position pd) {
		p1 = pa;
		p2 = pb;
		p3 = pc;
		p4 = pd;
		trans = new Transform(new ArrayList<>(Arrays.asList(p1, p2, p3, p4)));
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
		p1 = transList.get(0);
		p2 = transList.get(1);
		p3 = transList.get(2);
		p4 = transList.get(3);
		fixelList.addAll(bla.start(p1, p2));
		fixelList.addAll(bla.start(p3, p2));
		fixelList.addAll(bla.start(p4, p3));
		fixelList.addAll(bla.start(p1, p4));
	}

	@Override
	void transRotate(String angle, Position pa) {
		try {
			transList = trans.rotation(Integer.parseInt(angle), pa);
			fixelList.clear();
			p1 = transList.get(0);
			p2 = transList.get(1);
			p3 = transList.get(2);
			p4 = transList.get(3);
			fixelList.addAll(bla.start(p1, p2));
			fixelList.addAll(bla.start(p3, p2));
			fixelList.addAll(bla.start(p4, p3));
			fixelList.addAll(bla.start(p1, p4));
		} catch (NumberFormatException ee) {
			Board.clickedPos.clear();
		}
	}

	@Override
	void transScale(String sizeX, String sizeY, Position pa) {
		try {
			transList = trans.scale(Double.parseDouble(sizeX), Double.parseDouble(sizeY), pa);
			fixelList.clear();
			p1 = transList.get(0);
			p2 = transList.get(1);
			p3 = transList.get(2);
			p4 = transList.get(3);
			fixelList.addAll(bla.start(p1, p2));
			fixelList.addAll(bla.start(p3, p2));
			fixelList.addAll(bla.start(p4, p3));
			fixelList.addAll(bla.start(p1, p4));
		} catch (NumberFormatException ee) {
			Board.clickedPos.clear();
		}

	}

	@Override
	void clipClip(Position pa, Position pb) {
		int xMin = (pa.x < pb.x) ? pa.x : pb.x;
		int xMax = (pa.x > pb.x) ? pa.x : pb.x;
		int yMin = (pa.y < pb.y) ? pa.y : pb.y;
		int yMax = (pa.y > pb.y) ? pa.y : pb.y;
		clippedFixelList.clear();

		transList = clipping.start(p1, p2, pa, pb);
		clippedFixelList.addAll(bla.start(transList.get(0), transList.get(1)));
		transList = clipping.start(p2, p3, pa, pb);
		clippedFixelList.addAll(bla.start(transList.get(0), transList.get(1)));
		transList = clipping.start(p3, p4, pa, pb);
		clippedFixelList.addAll(bla.start(transList.get(0), transList.get(1)));
		transList = clipping.start(p1, p4, pa, pb);
		clippedFixelList.addAll(bla.start(transList.get(0), transList.get(1)));
	}
}