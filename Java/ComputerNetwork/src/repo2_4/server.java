package repo2_4;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class server {

	public static void main(String[] args) {
		
		// ����� ��Ʈ ��ȣ
		int port = 6000;
		
		try {
			// ���� ���� ����
			ServerSocket server = new ServerSocket(port);
			
			System.out.println("Ŭ���̾�Ʈ �����");
			while(true) {
				// ���� ���
				Socket socket = server.accept();
				
				// ��� ���� ���� �� ������ ����
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
				out.println("Computer Network TCP example 20194122");
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	}

}
