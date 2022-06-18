package ChatClient;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class Panel_friendList extends JPanel {
	String id;
	int port;

	/**
	 * Create the panel.
	 */
	public Panel_friendList(String ID, int port) {
		id = ID;
		this.port = port + 7000;
		
		setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 290, 50);
		add(panel);
		panel.setLayout(null);
		
		JLabel Label_friendData = new JLabel(ID + " (" + (port+7000) + ")");
		Label_friendData.setFont(new Font("±¼¸²", Font.BOLD, 15));
		Label_friendData.setBounds(0, 0, 245, 50);
		panel.add(Label_friendData);
		
		JCheckBox CheckBox_friend = new JCheckBox("");
		CheckBox_friend.setHorizontalAlignment(SwingConstants.CENTER);
		CheckBox_friend.setBounds(245, 0, 25, 50);
		panel.add(CheckBox_friend);
		CheckBox_friend.addItemListener(e->{
			if(e.getStateChange() == 1) {
				Frame.user.portToSend.add(port+7000);
			}
			else {
				Frame.user.portToSend.remove(Integer.valueOf(port+7000));
			}
		});

	}
}
