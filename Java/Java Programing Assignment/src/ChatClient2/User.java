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
	final String serverIP = "210.104.172.95";
	
	DatagramSocket socket;
	DatagramPacket packet;
	final int portReceive = 7000; // ���ſ�
	final int portSend = 5000; // �۽ſ�
	InetAddress addrServer;
	
	// ����� ��
	String userID = "gkwns5791";
	ArrayList<InetAddress> addrToSend;

	public User() throws IOException {
		addrServer = InetAddress.getByName(serverIP);
		addrToSend = new ArrayList<>();
		addrToSend.add(InetAddress.getLocalHost());
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
		ChatClient.Message msg = new ChatClient.Message(1, s.getBytes(), userID, addrToSend);
		
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
