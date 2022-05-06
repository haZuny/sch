import java.util.ArrayList;

public class Transform {

	ArrayList<Position> list;
	ArrayList<Position> newList;

	ArrayList<int[]> originPos; // ��Ʈ����
	ArrayList<double[]> transMet;

	int mx, my, mw;

	// ������
	public Transform(ArrayList<Position> myList) {

		list = myList;

		// �ʱ� ��ġ ��Ʈ���� ǥ��
		originPos = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			originPos.add(new int[3]);
			originPos.get(i)[0] = list.get(i).x;
			originPos.get(i)[1] = list.get(i).y;
			originPos.get(i)[2] = 1;
		}
		
		// ��� ��Ʈ���� ����
		transMet = new ArrayList<>();
		transMet.add(new double[3]);
		transMet.add(new double[3]);
		transMet.add(new double[3]);
	}

	public Transform(Position position, Position position2) {
		// TODO Auto-generated constructor stub
	}

	// �̵� �޼ҵ�
	ArrayList<Position> translation(double d, double e) {
		
		// �̵� ��Ʈ���� �ʱ�ȭ
		transMet.get(0)[0] = 1;
		transMet.get(0)[1] = 0;
		transMet.get(0)[2] = 0;
		
		transMet.get(1)[0] = 0;
		transMet.get(1)[1] = 1;
		transMet.get(1)[2] = 0;
		
		transMet.get(2)[0] = d;
		transMet.get(2)[1] = e;
		transMet.get(2)[2] = 1;

		// ��� �� ����
		newList = new ArrayList<>();
		for (int i = 0; i < originPos.size(); i++) {
			mx = (int) Math.floor(originPos.get(i)[0] * transMet.get(0)[0] + originPos.get(i)[1] * transMet.get(1)[0]
					+ originPos.get(i)[2] * transMet.get(2)[0]);
			my = (int) Math.floor(originPos.get(i)[0] * transMet.get(0)[1] + originPos.get(i)[1] * transMet.get(1)[1]
					+ originPos.get(i)[2] * transMet.get(2)[1]);

			newList.add(new Position(mx, my));
		}

		// ���� ������Ʈ
		originPos.clear();
		for (int i = 0; i < newList.size(); i++) {
			originPos.add(new int[3]);
			originPos.get(i)[0] = newList.get(i).x;
			originPos.get(i)[1] = newList.get(i).y;
			originPos.get(i)[2] = 1;
		}

		return newList;
	}

	// ũ�⺯ȯ �޼ҵ�
	ArrayList<Position> scale(double sizeX, double sizeY, Position fixPos) {

		// �̵� ��Ʈ���� �ʱ�ȭ
		transMet.get(0)[0] = sizeX;
		transMet.get(0)[1] = 0;
		transMet.get(0)[2] = 0;
		
		transMet.get(1)[0] = 0;
		transMet.get(1)[1] = sizeY;
		transMet.get(1)[2] = 0;

		transMet.get(2)[0] = (1 - sizeX) * fixPos.x;
		transMet.get(2)[1] = (1 - sizeY) * fixPos.y;
		transMet.get(2)[2] = 1;

		// ��� �� ����
		newList = new ArrayList<>();
		for (int i = 0; i < originPos.size(); i++) {
			mx = (int) Math.floor(originPos.get(i)[0] * transMet.get(0)[0] + originPos.get(i)[1] * transMet.get(1)[0]
					+ originPos.get(i)[2] * transMet.get(2)[0]);
			my = (int) Math.floor(originPos.get(i)[0] * transMet.get(0)[1] + originPos.get(i)[1] * transMet.get(1)[1]
					+ originPos.get(i)[2] * transMet.get(2)[1]);

			newList.add(new Position(mx, my));
		}

		// ���� ������Ʈ
		originPos.clear();
		for (int i = 0; i < newList.size(); i++) {
			originPos.add(new int[3]);
			originPos.get(i)[0] = newList.get(i).x;
			originPos.get(i)[1] = newList.get(i).y;
			originPos.get(i)[2] = 1;
		}
		
		return newList;
	}

	// ȸ��
	ArrayList<Position> rotation(int angle, Position fixPos) {


		// ���� ���� ��ȯ
		double radAngle = (angle * Math.PI) / 180;

		// �̵� ��Ʈ���� �ʱ�ȭ
		transMet.get(0)[0] = Math.cos(radAngle);
		transMet.get(0)[1] = Math.sin(radAngle);
		transMet.get(0)[2] = 0;

		transMet.get(1)[0] = -1 * Math.sin(radAngle);
		transMet.get(1)[1] = Math.cos(radAngle);
		transMet.get(1)[2] = 0;

		transMet.get(2)[0] = (1 - Math.cos(radAngle)) * fixPos.x + Math.sin(radAngle) * fixPos.y;
		transMet.get(2)[1] = (1 - Math.cos(radAngle)) * fixPos.y - Math.sin(radAngle) * fixPos.x;
		transMet.get(2)[2] = 1;

		// ��� �� ����
		newList = new ArrayList<>();
		for (int i = 0; i < originPos.size(); i++) {
			mx = (int) Math.floor(originPos.get(i)[0] * transMet.get(0)[0] + originPos.get(i)[1] * transMet.get(1)[0]
					+ originPos.get(i)[2] * transMet.get(2)[0]);
			my = (int) Math.floor(originPos.get(i)[0] * transMet.get(0)[1] + originPos.get(i)[1] * transMet.get(1)[1]
					+ originPos.get(i)[2] * transMet.get(2)[1]);

			newList.add(new Position(mx, my));
		}

		// ���� ������Ʈ
		originPos.clear();
		for (int i = 0; i < newList.size(); i++) {
			originPos.add(new int[3]);
			originPos.get(i)[0] = newList.get(i).x;
			originPos.get(i)[1] = newList.get(i).y;
			originPos.get(i)[2] = 1;
		}

		return newList;
	}
}
