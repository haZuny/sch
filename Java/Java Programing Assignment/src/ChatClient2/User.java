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

public class User{
	InetAddress addrServer;
	final String serverIP = "127.0.0.1";
	DatagramSocket socket;
	DatagramPacket packet;
	final int portReceive; // 수신용
	final int portSend = 5000; // 송신용
	
	// 사용자 정보
	String userID ;
	ArrayList<Integer> portToSend;

	public User(String id, int port) throws IOException{
		userID = id;
		portReceive = 7000+port;
		
		addrServer = InetAddress.getByName(serverIP);
		portToSend = new ArrayList<>();
		portToSend.add(portReceive);
		portToSend.add(7000);
		socket = new DatagramSocket(portReceive);
	}

	// 수신
	public void receive() throws IOException, ClassNotFoundException {
		while (true) {
			// 패킷 받음
			byte[] buf = new byte[2048];
			packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);
			
			// 역직렬화
			InputStream is = new ByteArrayInputStream(buf);
			ObjectInputStream ois = new ObjectInputStream(is);
			Message msg = (Message) ois.readObject();
			
			switch(msg.type) {
			case 1: // 텍스트
				PanelChat.textArea.append(msg.userID + " : " + new String(msg.contentBuf) + "\n");
				break;
			}
		}
	}

	// 텍스트 송신
	public void sendText() throws IOException {
		// 소켓 생성
		DatagramSocket socketSend = new DatagramSocket();
		
		// 메세지 객체 생성
		String s = PanelChat.textField.getText();
		Message msg = new Message(1, s.getBytes(), userID, portToSend);
		
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
		socketSend.send(packet);
		socketSend.close();

		// gui 표시
		PanelChat.textField.setText("");
	}
}
