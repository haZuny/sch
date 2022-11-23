package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
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

public class AdminPage extends JPanel {
	MainFrame window;

	/**
	 * Create the panel.
	 */
	public AdminPage(MainFrame window) {
		this.window = window;
		
		setLayout(null);
		
		JLabel lb_title = new JLabel("관리자 페이지");
		lb_title.setHorizontalAlignment(SwingConstants.CENTER);
		lb_title.setBounds(150, 30, 400, 50);
		lb_title.setFont(new Font("HY견고딕", Font.PLAIN, 39));
		add(lb_title);
		
		JLabel lb_trainList = new JLabel("열차 목록");
		lb_trainList.setBounds(25, 100, 216, 30);
		add(lb_trainList);
		
		JPanel panel_trainList = new JPanel();
		panel_trainList.setBackground(Color.WHITE);
		panel_trainList.setBounds(25, 130, 300, 120);
		add(panel_trainList);
		panel_trainList.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lb_schedultList = new JLabel("시간표");
		lb_schedultList.setBounds(25, 280, 200, 30);
		add(lb_schedultList);
		
		JComboBox comboBox_schedule = new JComboBox();
		comboBox_schedule.setBounds(25, 430, 200, 30);
		add(comboBox_schedule);
		
		JPanel panel_schedule = new JPanel();
		panel_schedule.setBackground(Color.WHITE);
		panel_schedule.setBounds(25, 310, 300, 120);
		add(panel_schedule);
		panel_schedule.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btn_search = new JButton("조회");
		btn_search.setBounds(245, 430, 80, 30);
		add(btn_search);
		
		JLabel lb_signUpAccept = new JLabel("가입 신청 목록");
		lb_signUpAccept.setBounds(375, 100, 300, 30);
		add(lb_signUpAccept);
		
		JPanel panel_signUpAccept = new JPanel();
		panel_signUpAccept.setBackground(Color.WHITE);
		panel_signUpAccept.setBounds(375, 130, 300, 120);
		add(panel_signUpAccept);
		panel_signUpAccept.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_userList = new JPanel();
		panel_userList.setBackground(Color.WHITE);
		panel_userList.setBounds(375, 310, 300, 120);
		add(panel_userList);
		panel_userList.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
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

	}
	
	
	// 로그아웃 버튼 이벤트
	class LogoutBtnListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			window.change("login");
		}
	}
}
