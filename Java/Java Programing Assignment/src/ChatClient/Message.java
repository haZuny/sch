package ChatClient;

import java.awt.Image;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.ArrayList;

public class Message implements Serializable{
	public static final int typeText = 1;
	public static final int typeImage = 2;
	
	public int type;
	public byte[] contentBuf;
	public String userID;
	
	public ArrayList<Integer> portToSend;
	
	
	public Message(int type, byte[] content, String user, ArrayList<Integer> portsToSend) {
		this.type = type;
		contentBuf = content;
		userID = user;
		portToSend = portsToSend;
	}

}
