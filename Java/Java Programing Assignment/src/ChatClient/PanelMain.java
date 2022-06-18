package ChatClient;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.FlowLayout;
import javax.swing.JTable;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.GridBagLayout;
import java.awt.CardLayout;
import java.awt.Dimension;

import javax.swing.BoxLayout;

public class PanelMain extends JPanel {
	private JTextField textField_MSG;

	static JTextArea textArea_MSG;
	JScrollPane scrollPane_MSG;
	JLabel lblDd;
	JLabel lblPort;
	static JPanel panel_friend;

	/**
	 * Create the panel.
	 */
	public PanelMain() {
		setLayout(null);

		JPanel panelLeft = new JPanel();
		panelLeft.setBounds(0, 0, 300, 400);
		add(panelLeft);
		panelLeft.setLayout(null);

		lblDd = new JLabel();
		lblDd.setFont(new Font("굴림", Font.BOLD, 25));
		lblDd.setBounds(0, 0, 200, 50);
		panelLeft.add(lblDd);

		lblPort = new JLabel();
		lblPort.setBounds(200, 25, 100, 25);
		panelLeft.add(lblPort);

		JScrollPane scrollPane_friend = new JScrollPane();
		scrollPane_friend.setBounds(0, 50, 300, 350);
		panelLeft.add(scrollPane_friend);

		panel_friend = new JPanel();
		scrollPane_friend.setViewportView(panel_friend);
		panel_friend.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		panel_friend.setPreferredSize(new Dimension(300, 350));

		JPanel panelRight = new JPanel();
		panelRight.setBounds(300, 0, 300, 400);
		add(panelRight);
		panelRight.setLayout(null);

		// 메세지 스크롤
		scrollPane_MSG = new JScrollPane();
		scrollPane_MSG.setBounds(0, 0, 300, 375);
		panelRight.add(scrollPane_MSG);

		// 메시지 전달 텍스트 영역
		textArea_MSG = new JTextArea();
		textArea_MSG.setEditable(false);
		scrollPane_MSG.setViewportView(textArea_MSG);

		// 메세지 전달 텍스트 필드
		textField_MSG = new JTextField();
		textField_MSG.setBounds(0, 375, 225, 25);
		panelRight.add(textField_MSG);
		textField_MSG.setColumns(10);
		textField_MSG.addActionListener(new sendMsg());

		// 메세지 전달 버튼
		JButton btnButton_MSG = new JButton("\uC804\uC1A1");
		btnButton_MSG.setBounds(225, 375, 75, 25);
		panelRight.add(btnButton_MSG);
		btnButton_MSG.addActionListener(new sendMsg());

	}

	// 메세지 전달 동작 정의
	public class sendMsg implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub

			try {
				Frame.user.sendText(textField_MSG.getText());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			textField_MSG.setText("");
			scrollPane_MSG.getVerticalScrollBar().setValue(scrollPane_MSG.getVerticalScrollBar().getMaximum());
		}
	}

	// 친구 목록 추가
	public static void updateFriends() {
		// 친구 목록 추가
		HashMap<String, Integer> friends = null;
		Panel_friendList fl;
		try {
			friends = DataBase.getFriendsID();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (String id : friends.keySet()) {
			if (id.equals(Frame.user.userID)) {
			} else {
				fl = new Panel_friendList(id, friends.get(id));
				fl.setPreferredSize(new Dimension(270, 50));
				panel_friend.add(fl);
			}
		}
	}
}
