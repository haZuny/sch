package ChatClient;

import java.awt.Image;
import java.io.Serializable;
import java.net.InetAddress;

public class Message implements Serializable{
	public static final int typeText = 1;
	public static final int typeImage = 2;
	
	public int type;
	byte[] contentBuf;
	public String userID;
	
	public InetAddress addr;
	
	
	public Message(int type, byte[] content, String user, InetAddress address) {
		this.type = type;
		contentBuf = content;
		userID = user;
		addr = address;
	}

}
