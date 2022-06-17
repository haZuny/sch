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

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class User {
	final String serverIP = "192.168.96.53";
	
	DatagramSocket socket;
	DatagramPacket packet;
	final int portReceive = 6000; // 수신용
	final int portSend = 5000; // 송신용
	InetAddress addrServer;
	
	// 사용자 정
	String userID = "hj3175791";
	InetAddress addrUser;
	InetAddress[] addrToDest;

	public User() throws IOException {
		socket = new DatagramSocket(portReceive);
		addrServer = InetAddress.getByName(serverIP);
		addrUser = InetAddress.getLocalHost();
	}

	// 수신
	public void receive() throws IOException, ClassNotFoundException {
		while (true) {
			// 패킷 받음
			byte[] buf = new byte[1024];
			packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);
			
			// 역직렬화
			InputStream is = new ByteArrayInputStream(buf);
			ObjectInputStream ois = new ObjectInputStream(is);
			Message msg = (Message) ois.readObject();
			
			switch(msg.type) {
			case 1: 
				PanelChat.textArea.append(msg.userID + " : " + new String(msg.contentBuf) + "\n");
				break;
				
			}
		}
	}

	// 텍스트 송신
	public void sendText() throws IOException {
		// 메세지 객체 생성
		String s = PanelChat.textField.getText();
		Message msg = new Message(1, s.getBytes(), userID, addrUser);
		
		// 직렬화
		byte[] buffer;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try(ObjectOutputStream oos = new ObjectOutputStream(baos)){
			oos.writeObject(msg);
			buffer = baos.toByteArray();
		}

		// 패킷 생성 후 전송
		DatagramPacket packet;
		packet = new DatagramPacket(buffer, buffer.length, addrServer, portSend);
		socket.send(packet);

		// gui 표시
		PanelChat.textField.setText("");
	}
}
