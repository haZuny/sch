package ChatServer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import ChatClient.Message;

public class Server {
	DatagramSocket socket;
	DatagramPacket packet;
	final int portReceive = 5000; // 수신용

	// 생성자_클래스 필드 초기화
	public Server() throws IOException {
		// 소켓 생성
		socket = new DatagramSocket(portReceive);
	}

	// 수신 및 송신_클라이언트에게 메세지 받으면 전달
	public void receive() throws IOException, ClassNotFoundException {
		while (true) {
			
			// 수신
			byte[] buf = new byte[2048];
			packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);

			// 역직렬화
			InputStream is = new ByteArrayInputStream(buf);
			ObjectInputStream ois = new ObjectInputStream(is);
			Message msg = (Message) ois.readObject();

			// 송신
			DatagramPacket packet;
			for (int port : msg.portToSend) {
				System.out.println(port);
				System.out.println(msg.userID + " send message \"" + new String(msg.contentBuf) + "\"");
				
				packet = new DatagramPacket(buf, buf.length, InetAddress.getByName("127.0.0.1"), port);
				socket.send(packet);
			}
		}
	}

	// 메인_서버 객체 생성 후 실행
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Server server = new Server();
		server.receive();
	}
}