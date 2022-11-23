package repo2_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 포트번호 생성
		int port = 6000;
		// 루프백 주소
		String local = "127.0.0.1";
		
		try {
			// 클라이언트 소켓 생성
			Socket socket = new Socket(local, port);
			
			// 입력 버퍼 생성후 소켓에서 값을 받음
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String res = input.readLine();
			
			// 결과 출력
			System.out.println(res);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
