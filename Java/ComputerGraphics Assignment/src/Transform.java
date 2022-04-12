import java.util.ArrayList;

public class Transform {
	
	ArrayList<Position> list;
	ArrayList<Position> newList;
	
	ArrayList<int[]> originPos;	// 메트릭스
	ArrayList<int[]> newPos;
	ArrayList<double[]> transMet;
	
	int mx, my, mw;
	
	// 생성자
	public Transform(ArrayList<Position> myList) {
		
		list = myList;
		transMet = new ArrayList<>();		
		
		// 초기 위치 메트릭스 표현
		originPos = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			originPos.add(new int[3]);
			originPos.get(i)[0] = list.get(i).x;
			originPos.get(i)[1] = list.get(i).y;
			originPos.get(i)[2] = 1;	
		}
		
	}
	
	// 이동 메소드
	ArrayList<Position> translation(int moveX, int moveY) {
		// 메트릭스 초기화
		for(int i = 0; i < transMet.size(); i++) {
			transMet.remove(0);
		}
		
		// 이동 메트릭스 삽입
		transMet.add(new double[3]);
		transMet.get(0)[0] = 1;
		transMet.get(0)[1] = 0;
		transMet.get(0)[2] = 0;
		transMet.add(new double[3]);
		transMet.get(1)[0] = 0;
		transMet.get(1)[1] = 1;
		transMet.get(1)[2] = 0;
		transMet.add(new double[3]);
		transMet.get(2)[0] = moveX;
		transMet.get(2)[1] = moveY;
		transMet.get(2)[2] = 1;
		
		// 행렬 곱 수행
		newList = new ArrayList<>();
		for (int i = 0; i < originPos.size(); i++) {
			mx =  (int) Math.round(originPos.get(i)[0] * transMet.get(0)[0] + 
					originPos.get(i)[1] * transMet.get(1)[0] + 
					originPos.get(i)[2] * transMet.get(2)[0]);
			my = (int)Math.round(originPos.get(i)[0] * transMet.get(0)[1] + 
					originPos.get(i)[1] * transMet.get(1)[1] + 
					originPos.get(i)[2] * transMet.get(2)[1]);
			
			newList.add(new Position(mx, my));	
		}
		return newList;	
	}
	
	
	
	// 크기변환 메소드
	ArrayList<Position> scale(double sizeX, double sizeY, Position fixPos) {
		
		// 메트릭스 초기화
		for(int i = 0; i < transMet.size(); i++) {
			transMet.remove(0);
		}
		
		// 이동 메트릭스 삽입
		transMet.add(new double[3]);
		transMet.get(0)[0] = sizeX;
		transMet.get(0)[1] = 0;
		transMet.get(0)[2] = 0;
		transMet.add(new double[3]);
		transMet.get(1)[0] = 0;
		transMet.get(1)[1] = sizeY;
		transMet.get(1)[2] = 0;
		transMet.add(new double[3]);
		transMet.get(2)[0] = (1-sizeX)*fixPos.x;
		transMet.get(2)[1] = (1-sizeY)*fixPos.y;
		transMet.get(2)[2] = 1;
			
		// 행렬 곱 수행
		newList = new ArrayList<>();
		for (int i = 0; i < originPos.size(); i++) {
			mx =  (int) Math.round(originPos.get(i)[0] * transMet.get(0)[0] + 
					originPos.get(i)[1] * transMet.get(1)[0] + 
					originPos.get(i)[2] * transMet.get(2)[0]);
			my = (int)Math.round(originPos.get(i)[0] * transMet.get(0)[1] + 
					originPos.get(i)[1] * transMet.get(1)[1] + 
					originPos.get(i)[2] * transMet.get(2)[1]);
			
			newList.add(new Position(mx, my));
		}
		System.out.println(newList);
		return newList;	
	}
	
	
	
	// 회전
	ArrayList<Position> rotation(int angle, Position fixPos) {
		
		// 메트릭스 초기화
		for(int i = 0; i < transMet.size(); i++) {
			transMet.remove(0);
		}
		
		// 각도 라디안 변환
		double radAngle = (angle * Math.PI) / 180;
		
		// 이동 메트릭스 삽입
		transMet.add(new double[3]);
		transMet.get(0)[0] = Math.cos(radAngle);
		transMet.get(0)[1] = Math.sin(radAngle);
		transMet.get(0)[2] = 0;
		transMet.add(new double[3]);
		transMet.get(1)[0] = -1 * Math.sin(radAngle);
		transMet.get(1)[1] = Math.cos(radAngle);
		transMet.get(1)[2] = 0;
		transMet.add(new double[3]);
		transMet.get(2)[0] = (1-Math.cos(radAngle)) * fixPos.x + Math.sin(radAngle) * fixPos.y;
		transMet.get(2)[1] = (1-Math.cos(radAngle)) * fixPos.y - Math.sin(radAngle) * fixPos.x;
		transMet.get(2)[2] = 1;
				
		// 행렬 곱 수행
		newList = new ArrayList<>();
		for (int i = 0; i < originPos.size(); i++) {
			mx =  (int) Math.round(originPos.get(i)[0] * transMet.get(0)[0] + 
					originPos.get(i)[1] * transMet.get(1)[0] + 
					originPos.get(i)[2] * transMet.get(2)[0]);
			my = (int)Math.round(originPos.get(i)[0] * transMet.get(0)[1] + 
					originPos.get(i)[1] * transMet.get(1)[1] + 
					originPos.get(i)[2] * transMet.get(2)[1]);
			
			newList.add(new Position(mx, my));
		}
		System.out.println(newList);
		return newList;	
	}
}
