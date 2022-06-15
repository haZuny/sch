package ChatServer;

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

public class Server{
		DatagramSocket socket;
		DatagramPacket packet;
		final int portReceive = 5000;	// 수신용
		final int portSend = 5001;	// 송신용
		InetAddress addrServer;
		
		public Server() throws IOException{
			socket = new DatagramSocket(portReceive);
			addrServer = InetAddress.getByName("220.69.208.121");
		}
		
		// 수신
		public void receive() throws IOException {
			while(true) {
				byte[] buf = new byte[256];
				packet = new DatagramPacket(buf, buf.length);
				socket.receive(packet);
				System.out.println("서버_받음: " + new String(buf));
				
				DatagramPacket packet;
				packet = new DatagramPacket(buf, buf.length, addrServer, portSend);
				
				socket.send(packet);
			}
		}
	
	
	public static void main(String[] args) throws IOException{
		Server server = new Server();
		server.receive();
	}
}