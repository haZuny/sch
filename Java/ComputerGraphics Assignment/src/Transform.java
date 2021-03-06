import java.util.ArrayList;

public class Transform {

	ArrayList<Position> list;
	ArrayList<Position> newList;

	ArrayList<int[]> originPos; // 메트릭스
	ArrayList<double[]> transMet;

	int mx, my, mw;

	// 생성자
	public Transform(ArrayList<Position> myList) {

		list = myList;

		// 초기 위치 메트릭스 표현
		originPos = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			originPos.add(new int[3]);
			originPos.get(i)[0] = list.get(i).x;
			originPos.get(i)[1] = list.get(i).y;
			originPos.get(i)[2] = 1;
		}
		
		// 계산 메트릭스 삽입
		transMet = new ArrayList<>();
		transMet.add(new double[3]);
		transMet.add(new double[3]);
		transMet.add(new double[3]);
	}

	public Transform(Position position, Position position2) {
		// TODO Auto-generated constructor stub
	}

	// 이동 메소드
	ArrayList<Position> translation(double d, double e) {
		
		// 이동 메트릭스 초기화
		transMet.get(0)[0] = 1;
		transMet.get(0)[1] = 0;
		transMet.get(0)[2] = 0;
		
		transMet.get(1)[0] = 0;
		transMet.get(1)[1] = 1;
		transMet.get(1)[2] = 0;
		
		transMet.get(2)[0] = d;
		transMet.get(2)[1] = e;
		transMet.get(2)[2] = 1;

		// 행렬 곱 수행
		newList = new ArrayList<>();
		for (int i = 0; i < originPos.size(); i++) {
			mx = (int) Math.floor(originPos.get(i)[0] * transMet.get(0)[0] + originPos.get(i)[1] * transMet.get(1)[0]
					+ originPos.get(i)[2] * transMet.get(2)[0]);
			my = (int) Math.floor(originPos.get(i)[0] * transMet.get(0)[1] + originPos.get(i)[1] * transMet.get(1)[1]
					+ originPos.get(i)[2] * transMet.get(2)[1]);

			newList.add(new Position(mx, my));
		}

		// 정보 업데이트
		originPos.clear();
		for (int i = 0; i < newList.size(); i++) {
			originPos.add(new int[3]);
			originPos.get(i)[0] = newList.get(i).x;
			originPos.get(i)[1] = newList.get(i).y;
			originPos.get(i)[2] = 1;
		}

		return newList;
	}

	// 크기변환 메소드
	ArrayList<Position> scale(double sizeX, double sizeY, Position fixPos) {

		// 이동 메트릭스 초기화
		transMet.get(0)[0] = sizeX;
		transMet.get(0)[1] = 0;
		transMet.get(0)[2] = 0;
		
		transMet.get(1)[0] = 0;
		transMet.get(1)[1] = sizeY;
		transMet.get(1)[2] = 0;

		transMet.get(2)[0] = (1 - sizeX) * fixPos.x;
		transMet.get(2)[1] = (1 - sizeY) * fixPos.y;
		transMet.get(2)[2] = 1;

		// 행렬 곱 수행
		newList = new ArrayList<>();
		for (int i = 0; i < originPos.size(); i++) {
			mx = (int) Math.floor(originPos.get(i)[0] * transMet.get(0)[0] + originPos.get(i)[1] * transMet.get(1)[0]
					+ originPos.get(i)[2] * transMet.get(2)[0]);
			my = (int) Math.floor(originPos.get(i)[0] * transMet.get(0)[1] + originPos.get(i)[1] * transMet.get(1)[1]
					+ originPos.get(i)[2] * transMet.get(2)[1]);

			newList.add(new Position(mx, my));
		}

		// 정보 업데이트
		originPos.clear();
		for (int i = 0; i < newList.size(); i++) {
			originPos.add(new int[3]);
			originPos.get(i)[0] = newList.get(i).x;
			originPos.get(i)[1] = newList.get(i).y;
			originPos.get(i)[2] = 1;
		}
		
		return newList;
	}

	// 회전
	ArrayList<Position> rotation(int angle, Position fixPos) {


		// 각도 라디안 변환
		double radAngle = (angle * Math.PI) / 180;

		// 이동 메트릭스 초기화
		transMet.get(0)[0] = Math.cos(radAngle);
		transMet.get(0)[1] = Math.sin(radAngle);
		transMet.get(0)[2] = 0;

		transMet.get(1)[0] = -1 * Math.sin(radAngle);
		transMet.get(1)[1] = Math.cos(radAngle);
		transMet.get(1)[2] = 0;

		transMet.get(2)[0] = (1 - Math.cos(radAngle)) * fixPos.x + Math.sin(radAngle) * fixPos.y;
		transMet.get(2)[1] = (1 - Math.cos(radAngle)) * fixPos.y - Math.sin(radAngle) * fixPos.x;
		transMet.get(2)[2] = 1;

		// 행렬 곱 수행
		newList = new ArrayList<>();
		for (int i = 0; i < originPos.size(); i++) {
			mx = (int) Math.floor(originPos.get(i)[0] * transMet.get(0)[0] + originPos.get(i)[1] * transMet.get(1)[0]
					+ originPos.get(i)[2] * transMet.get(2)[0]);
			my = (int) Math.floor(originPos.get(i)[0] * transMet.get(0)[1] + originPos.get(i)[1] * transMet.get(1)[1]
					+ originPos.get(i)[2] * transMet.get(2)[1]);

			newList.add(new Position(mx, my));
		}

		// 정보 업데이트
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
