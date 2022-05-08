package JavaMonitor;

public class MyProgram {

	public static void main(String[] args) {
		Game game = new Game();
		Player1 p1 = new Player1(game, "Hajun");
		Player2 p2 = new Player2(game, "Junha");

		p1.start();
		p2.start();
	}

}

// �����ձ�
class Game {
	String[] words;
	String keyword;
	boolean isEnd;
	char lastChar;

	public Game() {
		words = new String[26];
		words[0] = "arc";
		words[1] = "bad";
		words[2] = "cat";
		words[3] = "dagon";
		words[4] = "eat";
		words[5] = "fox";
		words[6] = "gearbox";
		words[7] = "hobby";
		words[8] = "image";
		words[9] = "japan";
		words[10] = "korea";
		words[11] = "leaf";
		words[12] = "mash";
		words[13] = "noise";
		words[14] = "open";
		words[15] = "page";
		words[16] = "question";
		words[17] = "remove";
		words[18] = "string";
		words[19] = "tab";
		words[20] = "ubuntu";
		words[21] = "vacation";
		words[22] = "wait";
		words[23] = "x-ray";
		words[24] = "year";
		words[25] = "zero";

		isEnd = false;

		// ù �ܾ� �������� ����
		if (keyword == null) {
			int idx = (int) (Math.random() * 26);
			keyword = words[idx];
			words[idx] = null;
		}
	}

	// ����
	synchronized public void myTurn(String name) {

		while (true) {
			lastChar = keyword.charAt(keyword.length() - 1);

			// ���� �� ó��
			if (isEnd) {
				notify();
				break;
			}
			// ���� �� �� �ܾ� ������ ���� ����
			if (words[(int) lastChar - 'a'] == null) {
				System.out.println(name + " �й�");
				System.out.println();
				isEnd = true;
				notify();
				break;
			}

			System.out.print(name + "����: " + keyword + " --> ");

			// �����ڷ��� ���� �ٲ�
			keyword = words[(int) lastChar - 'a'];
			words[(int) lastChar - 'a'] = null;
			System.out.println(keyword);
			System.out.println();

			notify();
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class Player1 extends Thread{
	Game g;
	String name;
	Player1(Game game, String name){
		g = game;
		this.name = name;
	}
	public void run(){
		g.myTurn(name);
	}
}

class Player2 extends Thread{
	Game g;
	String name;
	Player2(Game game, String name){
		g = game;
		this.name = name;
	}
	public void run(){
		g.myTurn(name);
	}
}