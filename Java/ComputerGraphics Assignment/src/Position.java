public class Position {
	// 위치 표현 클래스
		int x;
		int y;
		
		Position(int xa, int ya){
			x = xa;
			y = ya;
		}
		
		Position(double xa, double ya){
			x = (int)xa;
			y = (int)ya;
		}
		
		@Override
		public String toString() {
			// TODO Auto-generated method stub
			System.out.println("(" + x + ", " + y + ") ");
			return super.toString();
		}
	}
