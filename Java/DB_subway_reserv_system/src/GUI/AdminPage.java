package GUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

import DB.DB_Schedule;
import DB.DB_Subway;
import DB.DB_Train;
import DB.DB_USER;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.awt.GridBagConstraints;
import java.awt.CardLayout;

import GUI.MainFrame;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;

import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.ScrollPaneConstants;

import java.util.*;

public class AdminPage extends JPanel {
	MainFrame window;

	ArrayList<HashMap<String, String>> signUpAcceptList;
	ArrayList<HashMap<String, String>> userList;
	ArrayList<HashMap<String, String>> trainList;
	ArrayList<HashMap<String, String>> subwayList;
	ArrayList<HashMap<String, String>> scheduleList;
	ArrayList<HashMap<String, String>> showedScheduleList;

	/**
	 * Create the panel.
	 */
	public AdminPage(MainFrame window) {
		this.window = window;
		// 가입 대기 회원 목록
		signUpAcceptList = DB_USER.getSignUpAcceptUserList();
		userList = DB_USER.getUserList();
		trainList = DB_Train.getTrainList();
		subwayList = DB_Subway.getSubwayList();
		scheduleList = DB_Schedule.getScheduleList();

		setLayout(null);

		JLabel lb_title = new JLabel("관리자 페이지");
		lb_title.setHorizontalAlignment(SwingConstants.CENTER);
		lb_title.setBounds(150, 30, 400, 50);
		lb_title.setFont(new Font("HY견고딕", Font.PLAIN, 39));
		add(lb_title);

		JLabel lb_trainList = new JLabel("열차 목록");
		lb_trainList.setBounds(25, 100, 80, 30);
		add(lb_trainList);

		JLabel lb_schedultList = new JLabel("시간표");
		lb_schedultList.setBounds(25, 280, 200, 30);
		add(lb_schedultList);

		// 기차역 리스트
		String[] subwayArr = new String[subwayList.size()];
		for(int i = 0; i < subwayList.size(); i++) {
			subwayArr[i] = (subwayList.get(i).get("subway_num") + "  " + subwayList.get(i).get("subway_name"));
		}
		JComboBox comboBox_subwayList = new JComboBox(subwayArr);
		comboBox_subwayList.setBounds(25, 430, 200, 30);
		add(comboBox_subwayList);

		JLabel lb_signUpAccept = new JLabel("가입 신청 목록");
		lb_signUpAccept.setBounds(375, 100, 300, 30);
		add(lb_signUpAccept);

		JLabel lb_signUpAccept_1 = new JLabel("회원 목록");
		lb_signUpAccept_1.setBounds(375, 280, 300, 30);
		add(lb_signUpAccept_1);

		// 열차 등급 콤보박스
		String[] trainClassList = { "새마을", "무궁화" };
		JComboBox comboBox_trainClass = new JComboBox(trainClassList);
		comboBox_trainClass.setBounds(145, 100, 100, 30);
		add(comboBox_trainClass);

		// 열차 추가 버튼
		JButton btn_addTrain = new JButton("추가");
		btn_addTrain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int idx = comboBox_trainClass.getSelectedIndex();
				if(idx == 0)
					DB_Train.insertTrain("새마을");
				else
					DB_Train.insertTrain("무궁화");
				window.change("adminPage");
			}
		});
		btn_addTrain.setBounds(245, 100, 80, 30);
		add(btn_addTrain);

		JButton btn_addSchedule = new JButton("추가");
		btn_addSchedule.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				window.change("addSchedulePage");
			}
		});
		btn_addSchedule.setBounds(245, 280, 80, 30);
		add(btn_addSchedule);

		// 로그아웃 버튼
		JButton btn_logout = new JButton("로그아웃");
		btn_logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				window.change("login");
			}
		});
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
		panel_signUpList.setBackground(Color.WHITE);
		panel_signUpList.setPreferredSize(new Dimension(300, 30 * signUpAcceptList.size() + 10));
		scrollPane_signUpList.setViewportView(panel_signUpList);

		JScrollPane scrollPane_trainList = new JScrollPane();
		scrollPane_trainList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_trainList.setToolTipText("");
		scrollPane_trainList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_trainList.setBounds(25, 130, 300, 120);
		add(scrollPane_trainList);

		JPanel panel_trainList = new JPanel();
		panel_trainList.setBackground(Color.WHITE);
		panel_trainList.setPreferredSize(new Dimension(300, 30 * trainList.size() + 40));
		scrollPane_trainList.setViewportView(panel_trainList);

		JScrollPane scrollPane_scheduleList = new JScrollPane();
		scrollPane_scheduleList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_scheduleList.setToolTipText("");
		scrollPane_scheduleList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_scheduleList.setBounds(25, 310, 300, 120);
		add(scrollPane_scheduleList);

		JPanel panel_scheduleList = new JPanel();
		panel_scheduleList.setBackground(Color.WHITE);
		panel_scheduleList.setPreferredSize(new Dimension(300, 30 * trainList.size() + 10));
		scrollPane_scheduleList.setViewportView(panel_scheduleList);
		
		// 스케줄 조회
		JButton btn_search = new JButton("조회");
		btn_search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showedScheduleList = new ArrayList<HashMap<String,String>>();
				panel_scheduleList.removeAll();
				String subwayNum = ((String) comboBox_subwayList.getSelectedItem()).split(" ")[0];
				for(int i = 0; i < scheduleList.size(); i++) {
					if(scheduleList.get(i).get("subway_num").equals(subwayNum))
						showedScheduleList.add(scheduleList.get(i));
				}
				for(int i = 0; i < showedScheduleList.size(); i++) {
					panel_scheduleList.add(new Panel_scheduleListInside(showedScheduleList.get(i)));
				}
				panel_scheduleList.setPreferredSize(new Dimension(250, 30 * showedScheduleList.size() + 40));
				panel_scheduleList.revalidate();
				panel_scheduleList.repaint();
			}
		});
		btn_search.setBounds(245, 430, 80, 30);
		add(btn_search);

		JScrollPane scrollPane_userList = new JScrollPane();
		scrollPane_userList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_userList.setToolTipText("");
		scrollPane_userList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_userList.setBounds(375, 310, 300, 120);
		add(scrollPane_userList);

		JPanel panel_userList = new JPanel();
		panel_userList.setBackground(Color.WHITE);
		panel_userList.setPreferredSize(new Dimension(300, 30 * userList.size() + 10));
		scrollPane_userList.setViewportView(panel_userList);

		// 가입 대기 목록 추가
		for (int i = 0; i < signUpAcceptList.size(); i++) {
			panel_signUpList.add(new Panel_signUpAcceptListInside(signUpAcceptList.get(i)));
		}

		// 회원 목록 추가
		for (int i = 0; i < userList.size(); i++) {
			panel_userList.add(new Panel_usertListInside(userList.get(i)));
		}

		// 열차 목록 추가
		for (int i = 0; i < trainList.size(); i++) {
			panel_trainList.add(new Panel_trainListInside(trainList.get(i)));
		}
		// 스케줄 목록 추가
//		for (int i = 0; i < scheduleList.size(); i++) {
//			panel_scheduleList.add(new Panel_scheduleListInside(scheduleList.get(i)));
//		}
	}

	// 회원가입 승인 리스트 항목
	class Panel_signUpAcceptListInside extends JPanel {
		HashMap<String, String> user;

		public Panel_signUpAcceptListInside(HashMap<String, String> user) {
			this.user = user;

			setLayout(new GridLayout());
			add(new JLabel(user.get("user_num")));
			add(new JLabel(user.get("user_name")));

			// 승인 버튼
			JButton btnAccept = new JButton("승인");
			btnAccept.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					DB_USER.updateUser(user.get("user_num"), "ACCEPT", "TRUE");
					window.change("adminPage");
				}
			});
			add(btnAccept);

			// 제거 버튼
			JButton btnDelete = new JButton("제거");
			btnDelete.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					DB_USER.deleteUser(user.get("user_num"));
					window.change("adminPage");
				}
			});
			add(btnDelete);
		}
	}

	// 회원 목록 리스트 항목
	class Panel_usertListInside extends JPanel {
		HashMap<String, String> user;

		public Panel_usertListInside(HashMap<String, String> user) {
			this.user = user;

			setLayout(new GridLayout());
			setPreferredSize(new Dimension(250, 30));
			add(new JLabel(user.get("user_num")));
			add(new JLabel(user.get("user_name")));

			// 등급 콤보
			String[] userClass = { "SILVER", "GOLD", "VIP" };
			JComboBox<String> comboboxUserClass = new JComboBox(userClass);
			// 디폴트값
			if (user.get("user_class").equals("SILVER"))
				comboboxUserClass.setSelectedIndex(0);
			else if (user.get("user_class").equals("GOLD"))
				comboboxUserClass.setSelectedIndex(1);
			else if (user.get("user_class").equals("VIP"))
				comboboxUserClass.setSelectedIndex(2);

			// 변경 이벤트
			comboboxUserClass.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JComboBox jcb = (JComboBox) e.getSource();
					int idx = jcb.getSelectedIndex();
					DB_USER.updateUser(user.get("user_num"), "USER_CLASS", userClass[idx]);
					window.change("adminPage");
				}
			});
			add(comboboxUserClass);

//			// 제거 버튼
//			JButton btnDelete = new JButton("제거");
//			btnDelete.addActionListener(new ActionListener() {
//				@Override
//				public void actionPerformed(ActionEvent e) {
//					DB_USER.deleteUser(user.get("user_num"));
//					window.change("adminPage");
//				}
//			});
//			add(btnDelete);
		}
	}

	// 열차 목록 리스트 항목
	class Panel_trainListInside extends JPanel {
		HashMap<String, String> train;

		public Panel_trainListInside(HashMap<String, String> train) {
			this.train = train;

			setPreferredSize(new Dimension(250, 30));
			setLayout(new GridLayout());
			add(new JLabel(train.get("train_num")));
			add(new JLabel(train.get("train_class")));
		}
	}
	
	// 시간표 목록 리스트 항목
	class Panel_scheduleListInside extends JPanel{
		HashMap<String, String> schedule;
		
		public Panel_scheduleListInside(HashMap<String, String> schedule){
			this.schedule = schedule;
			String str = "";
			
			setPreferredSize(new Dimension(250, 30));
			setLayout(new GridLayout());
			// 지하철역
			for(int i = 0; i < subwayList.size(); i++) {
				if(subwayList.get(i).get("subway_num").equals(schedule.get("subway_num"))) {
					add(new JLabel(schedule.get("subway_num") + ": " + subwayList.get(i).get("subway_name")));
				}
			}
			// 열차
			for(int i = 0; i < trainList.size(); i++) {
				if(trainList.get(i).get("train_num").equals(schedule.get("train_num"))) {
					add(new JLabel(schedule.get("train_num") + ": " + trainList.get(i).get("train_class")));
				}
			}
			add(new JLabel(schedule.get("direction")));
			add(new JLabel(schedule.get("time")));
		}
	}
}