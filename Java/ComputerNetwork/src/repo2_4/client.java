package repo2_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ��Ʈ��ȣ ����
		int port = 6000;
		// ������ �ּ�
		String local = "127.0.0.1";
		
		try {
			// Ŭ���̾�Ʈ ���� ����
			Socket socket = new Socket(local, port);
			
			// �Է� ���� ������ ���Ͽ��� ���� ����
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String res = input.readLine();
			
			// ��� ���
			System.out.println(res);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
