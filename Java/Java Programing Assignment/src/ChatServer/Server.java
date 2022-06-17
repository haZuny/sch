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
	final int portSend = 6000; // 송신용

	public Server() throws IOException {
		// 소켓 생성
		socket = new DatagramSocket(portReceive);
	}

	// 수신 및 송신
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
			for (InetAddress addr : msg.addrToSend) {
				System.out.println(addr);
				System.out.println(msg.userID + " send message \"" + new String(msg.contentBuf) + "\"");
				
				packet = new DatagramPacket(buf, buf.length, addr, portSend);
				socket.send(packet);
				
				packet = new DatagramPacket(buf, buf.length, addr, 7000);
				socket.send(packet);
			}
		}
	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Server server = new Server();
		server.receive();
	}
}