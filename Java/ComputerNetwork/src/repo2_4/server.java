package repo2_4;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class server {

	public static void main(String[] args) {
		
		// 사용할 포트 번호
		int port = 6000;
		
		try {
			// 서버 소켓 생성
			ServerSocket server = new ServerSocket(port);
			
			System.out.println("클라이언트 대기중");
			while(true) {
				// 연결 대기
				Socket socket = server.accept();
				
				// 출력 버퍼 생성 후 데이터 전송
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				out.println("Computer Network TCP example 20194122");
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}

}
