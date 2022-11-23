package ChatClient;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class User{
	final String serverIP = "127.0.0.1";	//Local Host
	InetAddress addrServer;
	DatagramSocket socket;
	DatagramPacket packet;
	final int portReceive; // ���ſ�(�� ������ ���� �ٸ� ��Ʈ��ȣ)
	final int portSend = 5000; // �۽ſ�(������ ���� ��Ʈ)
	
	// ����� ����
	String userID ;
	ArrayList<Integer> portToSend;	//���� ���� ����Ʈ

	// ������_Ŭ���� �ʵ� �ʱ�ȭ
	public User(String id, int port) throws IOException{
		userID = id;
		portReceive = 7000+port;
		
		addrServer = InetAddress.getByName(serverIP);
		portToSend = new ArrayList<>();
		portToSend.add(portReceive);
		portToSend.add(7000);
		socket = new DatagramSocket(portReceive);
	}

	// ����_�޼����� ��ٸ�
	public void receive() throws IOException, ClassNotFoundException {
		while (true) {
			// ��Ŷ ����
			byte[] buf = new byte[2048];
			packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);
			
			// ������ȭ
			InputStream is = new ByteArrayInputStream(buf);
			ObjectInputStream ois = new ObjectInputStream(is);
			Message msg = (Message) ois.readObject();
			
			switch(msg.type) {
			case 1: // �ؽ�Ʈ
				PanelMain.textArea_MSG.append(msg.userID + " : " + new String(msg.contentBuf) + "\n");
				break;
			}
		}
	}

	// �ؽ�Ʈ �۽�_�޼��� ����
	public void sendText(String txt) throws IOException {
		// ���� ����
		DatagramSocket socketSend = new DatagramSocket();
		
		// �޼��� ��ü ����
		String s = txt;
		Message msg = new Message(1, s.getBytes(), userID, portToSend);
		
		// ����ȭ
		byte[] buffer;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try(ObjectOutputStream oos = new ObjectOutputStream(baos)){
			oos.writeObject(msg);
			buffer = baos.toByteArray();
		}

		// ��Ŷ ���� �� ����
		DatagramPacket packet;
		packet = new DatagramPacket(buffer, buffer.length, addrServer, portSend);
		socketSend.send(packet);
		socketSend.close();
	}
}
