package ChatClient;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class User{
	DatagramSocket socket;
	DatagramPacket packet;
	final int portReceive = 5001;	// 수신용
	final int portSend = 5000;	// 송신용
	InetAddress addrServer;
	
	public User() throws IOException{
		socket = new DatagramSocket(portReceive);
		addrServer = InetAddress.getByName("220.69.208.121");
	}
	
	// 수신
	public void receive() throws IOException {
		while(true) {
			byte[] buf = new byte[256];
			packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);
			PanelChat.textArea.append("받음 : " + new String(buf) + "\n");
		}
	}
	
	// 송신
	public void send() throws IOException{
	String s = PanelChat.textField.getText();	
		
	byte[] buffer = s.getBytes();
	DatagramPacket packet;
	packet = new DatagramPacket(buffer, buffer.length, addrServer, portSend);
	
	socket.send(packet);

	PanelChat.textField.setText("");
	PanelChat.textArea.append("보냄 : " + s + "\n");
	}
}
