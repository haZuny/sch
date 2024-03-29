package GUI;

import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import GUI.MainFrame;
import DB.DB_Schedule;
import DB.DB_Subway;
import DB.DB_Train;
import DB.DB_USER;
import java.util.*;
import java.util.regex.Pattern;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;

public class AddSchedule extends JPanel {
	GUI.MainFrame window; // 프레임
	private JLabel lb_startTimeMinute;

	ArrayList<HashMap<String, String>> trainList; // 열차 리스트
	ArrayList<HashMap<String, String>> scheduleList;
	ArrayList<HashMap<String, String>> subwayList;

	/**
	 * Create the panel.
	 */
	public AddSchedule(GUI.MainFrame win) {
		window = win;

		trainList = DB_Train.getTrainList();
		scheduleList = DB_Schedule.getScheduleList();
		subwayList = DB_Subway.getSubwayList();

		setLayout(null);

		JLabel lb_title = new JLabel("시간표 등록");
		lb_title.setFont(new Font("HY강B", Font.PLAIN, 39));
		lb_title.setHorizontalAlignment(SwingConstants.CENTER);
		lb_title.setBounds(200, 30, 300, 50);
		add(lb_title);

		JLabel lb_direction = new JLabel("방향");
		lb_direction.setBounds(175, 150, 100, 30);
		add(lb_direction);

		// 방향
		String[] directionList = { "상행", "하행" };
		JComboBox comboBox_direction = new JComboBox(directionList);
		comboBox_direction.setBounds(325, 150, 200, 30);
		add(comboBox_direction);

		JLabel lb_startTime = new JLabel("출발 시간");
		lb_startTime.setBounds(175, 270, 100, 30);
		add(lb_startTime);

		JLabel lb_train = new JLabel("열차");
		lb_train.setBounds(175, 330, 100, 30);
		add(lb_train);

		// 출발시간_시
		String[] startTimeHourList = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
				"13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" };
		JComboBox comboBox_timeHour = new JComboBox(startTimeHourList);
		comboBox_timeHour.setBounds(325, 270, 75, 30);
		add(comboBox_timeHour);

		JLabel lb_startTimeHour = new JLabel("시");
		lb_startTimeHour.setHorizontalAlignment(SwingConstants.CENTER);
		lb_startTimeHour.setBounds(400, 270, 50, 30);
		add(lb_startTimeHour);

		// 출발시간_분
		String[] startTimeMinuteList = { "00", "10", "20", "30", "40", "50" };
		JComboBox comboBox_timeMinute = new JComboBox(startTimeMinuteList);
		comboBox_timeMinute.setBounds(450, 270, 75, 30);
		add(comboBox_timeMinute);

		lb_startTimeMinute = new JLabel("분");
		lb_startTimeMinute.setHorizontalAlignment(SwingConstants.CENTER);
		lb_startTimeMinute.setBounds(525, 270, 50, 30);
		add(lb_startTimeMinute);

		// 열차 리스트
		String[] trainArr = new String[trainList.size()];
		for (int i = 0; i < trainList.size(); i++) {
			trainArr[i] = trainList.get(i).get("train_num") + ":  " + trainList.get(i).get("train_class");
		}
		JComboBox comboBox_train = new JComboBox(trainArr);
		comboBox_train.setBounds(325, 330, 200, 30);
		add(comboBox_train);

		// 날짜 년 콤보박스
		String[] yearList = { "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031" };
		JComboBox comboBox_dateYear = new JComboBox(yearList);
		comboBox_dateYear.setBounds(300, 210, 75, 30);
		add(comboBox_dateYear);

		// 날짜 월 콤보박스
		String[] monthList = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", };
		JComboBox comboBox_dateMonth = new JComboBox(monthList);
		comboBox_dateMonth.setBounds(400, 210, 50, 30);
		add(comboBox_dateMonth);

		JLabel lb_dateMonth = new JLabel("월");
		lb_dateMonth.setHorizontalAlignment(SwingConstants.CENTER);
		lb_dateMonth.setBounds(450, 210, 25, 30);
		add(lb_dateMonth);

		// 날짜 일 콤보박스
		String[] dayList = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16",
				"17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" };
		JComboBox comboBox_dateDay = new JComboBox(dayList);
		comboBox_dateDay.setBounds(475, 210, 50, 30);
		add(comboBox_dateDay);

		// 등록 버튼
		JButton btn_register = new JButton("등록");
		btn_register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				// 추가 가능 여부 플래그
				boolean canRegister = true;

				// 시간
				int timeHour = Integer.parseInt((String) comboBox_timeHour.getSelectedItem());
				int timeMinute = Integer.parseInt((String) comboBox_timeMinute.getSelectedItem());
				int allTimeMinute = timeHour * 60 + timeMinute;

				// 날짜
				int dateYear = Integer.parseInt((String) comboBox_dateYear.getSelectedItem());
				int dateMonth = Integer.parseInt((String) comboBox_dateMonth.getSelectedItem());
				int dateDay = Integer.parseInt((String) comboBox_dateDay.getSelectedItem());
				int allTimeDay = dateYear * 12 * 30 + dateMonth * 30 + dateDay;

				// 스케줄 추가 정보
				String direction = (String) comboBox_direction.getSelectedItem();
				String trainNum = ((String) comboBox_train.getSelectedItem()).split(":")[0];
				String trainClass = ((String) comboBox_train.getSelectedItem()).split(":  ")[1];

				// 상행
				if (direction.equals("상행")) {
					// 각 지하철 역에 대해 열차 중복 여부 검사
					for (int i = 0; i < subwayList.size(); i++) {
						// 시간 초과 계산
						if (allTimeMinute >= 1440) {
							allTimeDay++;
							allTimeMinute -= 1440;
						}

						// 날짜와 열차가 같은 스케줄 찾은 후 시간 비교
						for (int j = 0; j < scheduleList.size(); j++) {
							// 스케줄 객체
							HashMap<String, String> schedule = scheduleList.get(j);

							int year = Integer.parseInt(schedule.get("date").split("\\.")[0]);
							int month = Integer.parseInt(schedule.get("date").split("\\.")[1]);
							int day = Integer.parseInt(schedule.get("date").split("\\.")[2]);
							int allDay = year * 12 * 30 + month * 30 + day;

							// 스케줄 시간 정보
							int hour = Integer.parseInt(schedule.get("time").split(":")[0]);
							int minute = Integer.parseInt(schedule.get("time").split(":")[1]);
							int allMinute = hour * 60 + minute;

							// 날짜와 시간이 동일한 스케줄 존재하면 중복 체크
							if (allTimeDay == allDay && allTimeMinute == allMinute) {
								canRegister = false;
							}
						}
					}

					// 중복 없을 경우 추가
					if (canRegister) {
						// 시간 초기화
						allTimeMinute = timeHour * 60 + timeMinute;
						allTimeDay = dateYear * 12 * 30 + dateMonth * 30 + dateDay;

						// 각 역에 대하여 추가
						for (int i = 0; i < subwayList.size(); i++) {
							// 시간 초과 계산
							if (allTimeMinute >= 1440) {
								allTimeDay += 1;
								allTimeMinute -= 1440;
							}
							int year = allTimeDay / (30 * 12);
							int month = allTimeDay % (30 * 12) / 30;
							int day = allTimeDay % (30 * 12) % 30;
							int hour = allTimeMinute / 60;
							int minute = allTimeMinute % 60;

							// 스케줄 추가
							DB_Schedule.insertSchedule(subwayList.get(i).get("subway_num"), trainNum, direction,
									Integer.toString(hour) + ":" + Integer.toString(minute), Integer.toString(year)
											+ "." + Integer.toString(month) + "." + Integer.toString(day));
							// 시간 추가
							if (trainClass.equals("무궁화")) {
								allTimeMinute += 20;
							} else if (trainClass.equals("새마을")) {
								allTimeMinute += 10;
							}
						}
						window.change("adminPage");
					} else {
						JOptionPane.showMessageDialog(null, "해당 시간에 시간표을 추가할 수 없습니다.");
					}
				}
				// 하행
				if (direction.equals("하행")) {
					// 각 지하철 역에 대해 열차 중복 여부 검사
					for (int i = subwayList.size() - 1; i >=0; i++) {
						// 시간 초과 계산
						if (allTimeMinute >= 1440) {
							allTimeDay++;
							allTimeMinute -= 1440;
						}

						// 날짜와 열차가 같은 스케줄 찾은 후 시간 비교
						for (int j = 0; j < scheduleList.size(); j++) {
							// 스케줄 객체
							HashMap<String, String> schedule = scheduleList.get(j);

							// 스케줄 날짜 정보
							int year = Integer.parseInt(schedule.get("date").split("\\.")[0]);
							int month = Integer.parseInt(schedule.get("date").split("\\.")[1]);
							int day = Integer.parseInt(schedule.get("date").split("\\.")[2]);
							int allDay = year * 12 * 30 + month * 30 + day;

							// 스케줄 시간 정보
							int hour = Integer.parseInt(schedule.get("time").split(":")[0]);
							int minute = Integer.parseInt(schedule.get("time").split(":")[1]);
							int allMinute = hour * 60 + minute;

							// 날짜와 시간이 동일한 스케줄 존재하면 중복 체크
							if (allTimeDay == allDay && allTimeMinute == allMinute) {
								canRegister = false;
							}
						}
					}

					// 중복 없을 경우 추가
					if (canRegister) {
						// 시간 초기화
						allTimeMinute = timeHour * 60 + timeMinute;
						allTimeDay = dateYear * 12 * 30 + dateMonth * 30 + dateDay;

						// 각 역에 대하여 추가
						for (int i = subwayList.size() - 1; i >=0; i++) {
							// 시간 초과 계산
							if (allTimeMinute >= 1440) {
								allTimeDay += 1;
								allTimeMinute -= 1440;
							}
							int year = allTimeDay / (30 * 12);
							int month = allTimeDay % (30 * 12) / 30;
							int day = allTimeDay % (30 * 12) % 30;
							int hour = allTimeMinute / 60;
							int minute = allTimeMinute % 60;

							// 스케줄 추가
							DB_Schedule.insertSchedule(subwayList.get(i).get("subway_num"), trainNum, direction,
									Integer.toString(hour) + ":" + Integer.toString(minute), Integer.toString(year)
											+ "." + Integer.toString(month) + "." + Integer.toString(day));
							// 시간 추가
							if (trainClass.equals("무궁화")) {
								allTimeMinute += 20;
							} else if (trainClass.equals("새마을")) {
								allTimeMinute += 10;
							}
						}
						window.change("adminPage");
					} else {
						JOptionPane.showMessageDialog(null, "해당 시간에 시간표을 추가할 수 없습니다.");
					}
				}
			}
		});
		btn_register.setBounds(225, 390, 100, 30);

		add(btn_register);

		// 취소 버튼
		JButton btn_back = new JButton("취소");
		btn_back.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				win.change("adminPage");
			}
		});
		btn_back.setBounds(375, 390, 100, 30);
		add(btn_back);

		JLabel lb_date = new JLabel("날짜");
		lb_date.setBounds(175, 210, 100, 30);
		add(lb_date);

		JLabel lb_dateDay = new JLabel("일");
		lb_dateDay.setHorizontalAlignment(SwingConstants.CENTER);
		lb_dateDay.setBounds(525, 210, 25, 30);
		add(lb_dateDay);

		JLabel lb_dateYear = new JLabel("년");
		lb_dateYear.setHorizontalAlignment(SwingConstants.CENTER);
		lb_dateYear.setBounds(375, 210, 25, 30);
		add(lb_dateYear);

	}
}
