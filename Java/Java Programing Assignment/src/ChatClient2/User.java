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
	final int portReceive = 7000; // 수신용
	final int portSend = 5000; // 송신용
	InetAddress addrServer;
	
	// 사용자 정
	String userID = "gkwns5791";
	ArrayList<InetAddress> addrToSend;

	public User() throws IOException {
		addrServer = InetAddress.getByName(serverIP);
		addrToSend = new ArrayList<>();
		addrToSend.add(InetAddress.getLocalHost());
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
			ChatClient.Message msg = (ChatClient.Message) ois.readObject();
			
			switch(msg.type) {
			case 1: 
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
		ChatClient.Message msg = new ChatClient.Message(1, s.getBytes(), userID, addrToSend);
		
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
