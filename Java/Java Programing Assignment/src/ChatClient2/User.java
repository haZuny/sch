package ChatClient2;

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

public class User {
	final String serverIP = "127.0.0.1";
	
	DatagramSocket socket;
	DatagramPacket packet;
	String id;
	final int portReceive;; // ���ſ�
	final int portSend = 5000; // �۽ſ�
	InetAddress addrServer;
	
	// ����� ��
	String userID = "gkwns5791";
	ArrayList<Integer> portToSend;

	public User(String id, int port) throws IOException {
		this.id = id;
		portReceive = 5000+port;
		
		addrServer = InetAddress.getByName(serverIP);
		portToSend = new ArrayList<>();
		portToSend.add(portReceive);
		portToSend.add(6000);
		socket = new DatagramSocket(portReceive);
	}

	// ����
	public void receive() throws IOException, ClassNotFoundException {
		while (true) {
			// ��Ŷ ����
			byte[] buf = new byte[2048];
			packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);
			
			// ������ȭ
			InputStream is = new ByteArrayInputStream(buf);
			ObjectInputStream ois = new ObjectInputStream(is);
			ChatClient.Message msg = (ChatClient.Message) ois.readObject();
			
			switch(msg.type) {
			case 1: 
				PanelChat.textArea.append(msg.userID + " : " + new String(msg.contentBuf) + "\n");
				break;
			}
		}
	}

	// �ؽ�Ʈ �۽�
	public void sendText() throws IOException {
		// ���� ����
		DatagramSocket socketSend = new DatagramSocket();
		
		// �޼��� ��ü ����
		String s = PanelChat.textField.getText();
		ChatClient.Message msg = new ChatClient.Message(1, s.getBytes(), userID, portToSend);
		
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

		// gui ǥ��
		PanelChat.textField.setText("");
	}
}
