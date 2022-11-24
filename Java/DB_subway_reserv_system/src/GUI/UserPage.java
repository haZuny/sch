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

public class UserPage extends JPanel {
	MainFrame window;
	HashMap<String, String> user;

	ArrayList<HashMap<String, String>> trainList;
	ArrayList<HashMap<String, String>> subwayList;
	ArrayList<HashMap<String, String>> scheduleList;
	ArrayList<HashMap<String, String>> showedScheduleList;

	/**
	 * Create the panel.
	 */
	public UserPage(MainFrame window, HashMap<String, String> user) {
		this.window = window;
		this.user = user;

		trainList = DB_Train.getTrainList();
		subwayList = DB_Subway.getSubwayList();
		scheduleList = DB_Schedule.getScheduleList();

		setLayout(null);

		JLabel lb_title = new JLabel("회원 페이지");
		lb_title.setHorizontalAlignment(SwingConstants.CENTER);
		lb_title.setBounds(150, 30, 400, 50);
		lb_title.setFont(new Font("HY견고딕", Font.PLAIN, 39));
		add(lb_title);

		JLabel lb_schedultList = new JLabel("시간표");
		lb_schedultList.setHorizontalAlignment(SwingConstants.CENTER);
		lb_schedultList.setBounds(25, 100, 300, 30);
		add(lb_schedultList);

		// 기차역 리스트
		String[] subwayArr = new String[subwayList.size()];
		for(int i = 0; i < subwayList.size(); i++) {
			subwayArr[i] = (subwayList.get(i).get("subway_num") + "  " + subwayList.get(i).get("subway_name"));
		}
		JComboBox comboBox_subwayList = new JComboBox(subwayArr);
		comboBox_subwayList.setBounds(25, 430, 200, 30);
		add(comboBox_subwayList);

		// 로그아웃 버튼
		JButton btn_logout = new JButton("로그아웃");
		btn_logout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				window.change("login");
			}
		});
		btn_logout.setFont(new Font("굴림", Font.PLAIN, 10));
		btn_logout.setBounds(460, 50, 80, 30);
		add(btn_logout);

		JScrollPane scrollPane_scheduleList = new JScrollPane();
		scrollPane_scheduleList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_scheduleList.setToolTipText("");
		scrollPane_scheduleList.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_scheduleList.setBounds(25, 130, 300, 300);
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
		
		JButton btn_myInfo = new JButton("내 정보");
		btn_myInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				window.change("myInfo", user);
			}
		});
		btn_myInfo.setFont(new Font("굴림", Font.PLAIN, 10));
		btn_myInfo.setBounds(550, 50, 80, 30);
		add(btn_myInfo);
		
		JLabel lb_reserveInfo = new JLabel("예약 정보");
		lb_reserveInfo.setHorizontalAlignment(SwingConstants.CENTER);
		lb_reserveInfo.setBounds(375, 100, 300, 30);
		add(lb_reserveInfo);
		
		JScrollPane scrollPane_reserveInfo = new JScrollPane();
		scrollPane_reserveInfo.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_reserveInfo.setToolTipText("");
		scrollPane_reserveInfo.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_reserveInfo.setBounds(375, 130, 300, 300);
		add(scrollPane_reserveInfo);
		
		JPanel panel_reserveInfo = new JPanel();
		panel_reserveInfo.setBackground(Color.WHITE);
		scrollPane_reserveInfo.setViewportView(panel_reserveInfo);
		
		JButton btn_reserve = new JButton("예약하기");
		btn_reserve.setBounds(375, 430, 300, 30);
		add(btn_reserve);
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
			add(new JLabel(schedule.get("date")));
		}
	}
}