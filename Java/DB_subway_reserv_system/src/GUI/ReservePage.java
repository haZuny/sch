//package GUI;
//
//import javax.swing.JPanel;
//import javax.swing.JLabel;
//import java.awt.Font;
//import javax.swing.SwingConstants;
//
//import DB.DB_Schedule;
//import DB.DB_Subway;
//import DB.DB_Train;
//import DB.DB_USER;
//
//import java.awt.Color;
//import java.awt.Dimension;
//
//import javax.swing.JComboBox;
//import javax.swing.JButton;
//import javax.swing.JTabbedPane;
//import java.awt.GridBagLayout;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.lang.reflect.Array;
//import java.awt.GridBagConstraints;
//import java.awt.CardLayout;
//
//import GUI.MainFrame;
//import GUI.AdminPage.Panel_scheduleListInside;
//
//import java.awt.FlowLayout;
//import java.awt.GridLayout;
//import java.awt.LayoutManager;
//
//import javax.swing.JScrollBar;
//import javax.swing.JScrollPane;
//import javax.swing.JList;
//import javax.swing.ScrollPaneConstants;
//
//import java.util.*;
//
//public class ReservePage extends JPanel {
//	MainFrame window;
//	HashMap<String, String> user;
//
//	String start;
//	String end;
//
//	ArrayList<HashMap<String, String>> trainList;
//	ArrayList<HashMap<String, String>> subwayList;
//	ArrayList<HashMap<String, String>> scheduleList;
//	ArrayList<HashMap<String, String>> showedScheduleList;
//
//	/**
//	 * Create the panel.
//	 */
//	public ReservePage(MainFrame window, HashMap<String, String> user, String dayday, String start, String end) {
//		this.window = window;
//		this.user = user;
//		
//		this.start = start;
//		this.end = end;
//
//		// 선택 정보
//		int year = Integer.parseInt(dayday.split("\\.")[0]);
//		int month = Integer.parseInt(dayday.split("\\.")[1]);
//		int day = Integer.parseInt(dayday.split("\\.")[2]);
//
//		trainList = DB_Train.getTrainList();
//		subwayList = DB_Subway.getSubwayList();
//		scheduleList = DB_Schedule.getScheduleList();
//
//		setLayout(null);
//
//		// 타이틀
//		JLabel lb_title = new JLabel("예약 페이지");
//		lb_title.setHorizontalAlignment(SwingConstants.CENTER);
//		lb_title.setBounds(150, 30, 400, 50);
//		lb_title.setFont(new Font("HY견고딕", Font.PLAIN, 39));
//		add(lb_title);
//
//		JLabel lb_trainList = new JLabel("열차");
//		lb_trainList.setHorizontalAlignment(SwingConstants.CENTER);
//		lb_trainList.setBounds(50, 200, 135, 30);
//		add(lb_trainList);
//
//		JLabel lb_carList = new JLabel("차량");
//		lb_carList.setHorizontalAlignment(SwingConstants.CENTER);
//		lb_carList.setBounds(50, 300, 135, 30);
//		add(lb_carList);
//
//		JButton btn_search = new JButton("조회");
//		btn_search.setBounds(190, 350, 135, 30);
//		add(btn_search);
//
//		// 좌석 정보
//		JPanel panel_seatList = new JPanel();
//		panel_seatList.setBounds(350, 250, 270, 130);
//		add(panel_seatList);
//		panel_seatList.setLayout(new GridLayout(1, 1, 0, 0));
//
//		JLabel lb_money = new JLabel("금액: ");
//		lb_money.setBounds(50, 400, 135, 30);
//		add(lb_money);
//
//		JLabel lb_pay = new JLabel("결제수단");
//		lb_pay.setBounds(50, 450, 135, 30);
//		add(lb_pay);
//
//		JComboBox comboBox_pay = new JComboBox();
//		comboBox_pay.setBounds(190, 450, 135, 30);
//		add(comboBox_pay);
//
//		JButton btn_reserve = new JButton("예약");
//		btn_reserve.setBounds(350, 450, 120, 30);
//		add(btn_reserve);
//
//		// 취소 버튼
//		JButton btn_back = new JButton("취소");
//		btn_back.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				window.change("userPage", user);
//			}
//		});
//		btn_back.setBounds(505, 450, 120, 30);
//		add(btn_back);
//
//		// 금액 레이블
//		JLabel lb_moneyRear = new JLabel("");
//		lb_moneyRear.setHorizontalAlignment(SwingConstants.CENTER);
//		lb_moneyRear.setBounds(190, 400, 135, 30);
//		add(lb_moneyRear);
//
//		JButton btn_selectSeat = new JButton("좌석 선택");
//		btn_selectSeat.setBounds(350, 400, 275, 30);
//		add(btn_selectSeat);
//
//		JLabel lb_day_title = new JLabel("날짜");
//		lb_day_title.setHorizontalAlignment(SwingConstants.CENTER);
//		lb_day_title.setBounds(50, 100, 135, 30);
//		add(lb_day_title);
//
//		// 날짜 년 콤보박스
//		String[] yearList = { "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031" };
//		JComboBox comboBox_day_year = new JComboBox(yearList);
//		for (int i = 0; i < yearList.length; i++) {
//			if (Integer.toString(year).equals(yearList[i])) {
//				comboBox_day_year.setSelectedIndex(i);
//			}
//		}
//		comboBox_day_year.setBounds(190, 100, 100, 30);
//		add(comboBox_day_year);
//
//		JLabel lb_year = new JLabel("년");
//		lb_year.setHorizontalAlignment(SwingConstants.CENTER);
//		lb_year.setBounds(290, 100, 50, 30);
//		add(lb_year);
//
//		// 날짜 월 콤보박스
//		String[] monthList = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", };
//		JComboBox comboBox_day_month = new JComboBox(monthList);
//		for (int i = 0; i < monthList.length; i++) {
//			if (Integer.toString(year).equals(monthList[i])) {
//				comboBox_day_month.setSelectedIndex(i);
//			}
//		}
//		comboBox_day_month.setBounds(340, 100, 60, 30);
//		add(comboBox_day_month);
//
//		JLabel lb_month = new JLabel("월");
//		lb_month.setHorizontalAlignment(SwingConstants.CENTER);
//		lb_month.setBounds(400, 100, 50, 30);
//		add(lb_month);
//
//		// 날짜 일 콤보박스
//		String[] dayList = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
//				"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" };
//		JComboBox comboBox_day_day = new JComboBox(dayList);
//		for (int i = 0; i < dayList.length; i++) {
//			if (Integer.toString(year).equals(dayList[i])) {
//				comboBox_day_day.setSelectedIndex(i);
//			}
//		}
//		comboBox_day_day.setBounds(450, 100, 60, 30);
//		add(comboBox_day_day);
//
//		JLabel lb_day = new JLabel("일");
//		lb_day.setHorizontalAlignment(SwingConstants.CENTER);
//		lb_day.setBounds(510, 100, 50, 30);
//		add(lb_day);
//
//		JLabel lb_start = new JLabel("출발");
//		lb_start.setHorizontalAlignment(SwingConstants.CENTER);
//		lb_start.setBounds(50, 150, 135, 30);
//		add(lb_start);
//
//		// 출발지 리스트
//		String[] subwayComboList = new String[subwayList.size()];
//		for (int i = 0; i < subwayList.size(); i++) {
//			subwayComboList[i] = subwayList.get(i).get("subway_num") + ".  " + subwayList.get(i).get("subway_name");
//		}
//		JComboBox comboBox_start = new JComboBox(subwayComboList);
//		comboBox_start.setBounds(190, 150, 100, 30);
//		add(comboBox_start);
//
//		JLabel lb_end = new JLabel("도착");
//		lb_end.setHorizontalAlignment(SwingConstants.CENTER);
//		lb_end.setBounds(290, 150, 60, 30);
//		add(lb_end);
//
//		JComboBox comboBox_end = new JComboBox(subwayComboList);
//		comboBox_end.setBounds(350, 150, 100, 30);
//		add(comboBox_end);
//
//		// 날짜, 출발지 목적지 선택
//		JButton btn_selectDay = new JButton("선택");
//		btn_selectDay.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				// 선택 날짜 문자열로
//				String selectedDay = (String) comboBox_day_year.getSelectedItem() + "."
//						+ (String) comboBox_day_month.getSelectedItem() + "."
//						+ (String) comboBox_day_day.getSelectedItem();
//				window.change("reservePage", user, selectedDay, trainList.get(0));
//
//			}
//		});
//		btn_selectDay.setBounds(460, 150, 100, 30);
//		add(btn_selectDay);
//
//		JComboBox comboBox_car = new JComboBox();
//		comboBox_car.setBounds(190, 300, 135, 30);
//		add(comboBox_car);
//
//		// 열차 리스트
//		String[] trainArr = new String[scheduleList.size() / subwayList.size()];
//		// 각 열차에 대하여 해당 날짜 스케줄에 열차가 존재하는지 확인
//		for (int i = 0; i < trainList.size(); i++) {
//			String trainNum = trainList.get(i).get("train_num");
//			for (int j = 0; j < scheduleList.size(); j++) {
//				// 해당 날짜에 운행하는 기차 스케줄이 있으면 추가
//				if (scheduleList.get(j).get("train_num").equals(trainNum)
//						&& scheduleList.get(j).get("date").equals(dayday)) {
//					trainArr[i] = trainNum + "번 열차";
//				}
//
//			}
//		}
//		JComboBox comboBox_train = new JComboBox(trainArr);
//		comboBox_train.setBounds(190, 200, 370, 30);
//		add(comboBox_train);
//
//		// 열차 선택
//		JButton btn_selectTrain = new JButton("열차 선택");
//		btn_selectTrain.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				// 선택 날짜 문자열로
//				String selectedDay = (String) comboBox_day_year.getSelectedItem() + "."
//						+ (String) comboBox_day_month.getSelectedItem() + "."
//						+ (String) comboBox_day_day.getSelectedItem();
//				// 선택 날짜 첫번째 기차
//
//				window.change("reservePage", user, selectedDay, trainList.get(comboBox_train.getSelectedIndex()));
//			}
//		});
//		btn_selectTrain.setBounds(190, 250, 135, 30);
//		add(btn_selectTrain);
//
//	}
//}