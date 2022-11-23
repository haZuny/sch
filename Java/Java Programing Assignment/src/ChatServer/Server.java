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
	final int portReceive = 5000; // ���ſ�

	// ������_Ŭ���� �ʵ� �ʱ�ȭ
	public Server() throws IOException {
		// ���� ����
		socket = new DatagramSocket(portReceive);
	}

	// ���� �� �۽�_Ŭ���̾�Ʈ���� �޼��� ������ ����
	public void receive() throws IOException, ClassNotFoundException {
		while (true) {
			
			// ����
			byte[] buf = new byte[2048];
			packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);

			// ������ȭ
			InputStream is = new ByteArrayInputStream(buf);
			ObjectInputStream ois = new ObjectInputStream(is);
			Message msg = (Message) ois.readObject();

			// �۽�
			DatagramPacket packet;
			for (int port : msg.portToSend) {
				System.out.println(port);
				System.out.println(msg.userID + " send message \"" + new String(msg.contentBuf) + "\"");
				
				packet = new DatagramPacket(buf, buf.length, InetAddress.getByName("127.0.0.1"), port);
				socket.send(packet);
			}
		}
	}

	// ����_���� ��ü ���� �� ����
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		Server server = new Server();
		server.receive();
	}
}