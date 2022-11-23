package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import DB.DB_USER;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.CardLayout;

import GUI.MainFrame;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ScrollPaneConstants;

import java.util.*;

public class AdminPage extends JPanel {
	MainFrame window;

	ArrayList<HashMap<String, String>> signUpAcceptList;

	/**
	 * Create the panel.
	 */
	public AdminPage(MainFrame window) {
		this.window = window;
		// 가입 대기 회원 목록
		signUpAcceptList = DB_USER.getSignUpAcceptUserList();

		setLayout(null);

		JLabel lb_title = new JLabel("관리자 페이지");
		lb_title.setHorizontalAlignment(SwingConstants.CENTER);
		lb_title.setBounds(150, 30, 400, 50);
		lb_title.setFont(new Font("HY견고딕", Font.PLAIN, 39));
		add(lb_title);

		JLabel lb_trainList = new JLabel("열차 목록");
		lb_trainList.setBounds(25, 100, 216, 30);
		add(lb_trainList);

		JLabel lb_schedultList = new JLabel("시간표");
		lb_schedultList.setBounds(25, 280, 200, 30);
		add(lb_schedultList);

		JComboBox comboBox_schedule = new JComboBox();
		comboBox_schedule.setBounds(25, 430, 200, 30);
		add(comboBox_schedule);

		JButton btn_search = new JButton("조회");
		btn_search.setBounds(245, 430, 80, 30);
		add(btn_search);

		JLabel lb_signUpAccept = new JLabel("가입 신청 목록");
		lb_signUpAccept.setBounds(375, 100, 300, 30);
		add(lb_signUpAccept);

		JLabel lb_signUpAccept_1 = new JLabel("회원 목록");
		lb_signUpAccept_1.setBounds(375, 280, 300, 30);
		add(lb_signUpAccept_1);

		JButton btn_addTrain = new JButton("추가");
		btn_addTrain.setBounds(245, 100, 80, 30);
		add(btn_addTrain);

		JButton btn_addTrain_1 = new JButton("추가");
		btn_addTrain_1.setBounds(245, 280, 80, 30);
		add(btn_addTrain_1);

		JButton btn_logout = new JButton("로그아웃");
		btn_logout.addActionListener(new LogoutBtnListener());
		btn_logout.setFont(new Font("굴림", Font.PLAIN, 10));
		btn_logout.setBounds(491, 51, 80, 30);
		add(btn_logout);

		JScrollPane scrollPane_signUpList = new JScrollPane();
		scrollPane_signUpList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_signUpList.setToolTipText("");
		scrollPane_signUpList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_signUpList.setBounds(375, 130, 300, 120);
		add(scrollPane_signUpList);

		JPanel panel_signUpList = new JPanel();
		panel_signUpList.setPreferredSize(new Dimension(300, 120));
		scrollPane_signUpList.setViewportView(panel_signUpList);

		JScrollPane scrollPane_trainList = new JScrollPane();
		scrollPane_trainList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_trainList.setToolTipText("");
		scrollPane_trainList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_trainList.setBounds(25, 130, 300, 120);
		add(scrollPane_trainList);

		JPanel panel_trainList = new JPanel();
		panel_trainList.setPreferredSize(new Dimension(300, 120));
		scrollPane_trainList.setViewportView(panel_trainList);

		JScrollPane scrollPane_scheduleList = new JScrollPane();
		scrollPane_scheduleList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_scheduleList.setToolTipText("");
		scrollPane_scheduleList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_scheduleList.setBounds(25, 310, 300, 120);
		add(scrollPane_scheduleList);

		JPanel panel_scheduleList = new JPanel();
		panel_scheduleList.setPreferredSize(new Dimension(300, 120));
		scrollPane_scheduleList.setViewportView(panel_scheduleList);

		JScrollPane scrollPane_userList = new JScrollPane();
		scrollPane_userList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_userList.setToolTipText("");
		scrollPane_userList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_userList.setBounds(375, 310, 300, 120);
		add(scrollPane_userList);

		JPanel panel_userList = new JPanel();
		panel_userList.setPreferredSize(new Dimension(300, 120));
		scrollPane_userList.setViewportView(panel_userList);

		// 가입 대기 목록 추가
		for (int i = 0; i < signUpAcceptList.size(); i++) {
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout());
			panel_signUpList.add(panel);

			JLabel lblNewLabel2 = new JLabel(signUpAcceptList.get(i).get("user_num"));
			panel.add(lblNewLabel2);

			JLabel lblNewLabel = new JLabel(signUpAcceptList.get(i).get("user_name"));
			panel.add(lblNewLabel);

			JButton btnNewButton = new JButton("제거");
			btnNewButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					DB_USER.deleteUser(signUpAcceptList.get(i).get("user_num"));
				}
			});
			panel.add(btnNewButton);

			JButton btnNewButton_1 = new JButton("승낙");
			panel.add(btnNewButton_1);

		}

	}

	// 로그아웃 버튼 이벤트
	class LogoutBtnListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			window.change("login");
		}
	}
}
