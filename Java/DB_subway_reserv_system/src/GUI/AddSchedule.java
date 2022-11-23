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
		lb_startTime.setBounds(175, 230, 100, 30);
		add(lb_startTime);

		JLabel lb_train = new JLabel("열차");
		lb_train.setBounds(175, 310, 100, 30);
		add(lb_train);

		// 출발시간_시
		String[] startTimeHourList = { "00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12",
				"13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23" };
		JComboBox comboBox_timeHour = new JComboBox(startTimeHourList);
		comboBox_timeHour.setBounds(325, 230, 75, 30);
		add(comboBox_timeHour);

		JLabel lb_startTimeHour = new JLabel("시");
		lb_startTimeHour.setHorizontalAlignment(SwingConstants.CENTER);
		lb_startTimeHour.setBounds(400, 230, 50, 30);
		add(lb_startTimeHour);

		// 출발시간_분
		String[] startTimeMinuteList = { "00", "10", "20", "30", "40", "50" };
		JComboBox comboBox_timeMinute = new JComboBox(startTimeMinuteList);
		comboBox_timeMinute.setBounds(450, 230, 75, 30);
		add(comboBox_timeMinute);

		lb_startTimeMinute = new JLabel("분");
		lb_startTimeMinute.setHorizontalAlignment(SwingConstants.CENTER);
		lb_startTimeMinute.setBounds(525, 230, 50, 30);
		add(lb_startTimeMinute);

		// 열차 리스트
		String[] trainArr = new String[trainList.size()];
		for (int i = 0; i < trainList.size(); i++) {
			trainArr[i] = trainList.get(i).get("train_num") + ":  " + trainList.get(i).get("train_class");
		}
		JComboBox comboBox_train = new JComboBox(trainArr);
		comboBox_train.setBounds(325, 310, 200, 30);
		add(comboBox_train);

		// 등록 버튼
		JButton btn_register = new JButton("등록");
		btn_register.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				boolean canRegister = true;
				String direction = (String) comboBox_direction.getSelectedItem();
				int timeHour = Integer.parseInt((String) comboBox_timeHour.getSelectedItem());
				int timeMinute = Integer.parseInt((String) comboBox_timeMinute.getSelectedItem());
				int allDayMinute = timeHour * 60 + timeMinute;
				String trainNum = ((String) comboBox_train.getSelectedItem()).split(":")[0];

				// 열차 중복 검사
				for (int i = 0; i < scheduleList.size(); i++) {
					if (scheduleList.get(i).get("train_num").equals(trainNum)) {
						int hour = Integer.parseInt(scheduleList.get(i).get("time").split(":")[0]);
						int minute = Integer.parseInt(scheduleList.get(i).get("time").split(":")[1]);
						int allMinute = hour * 60 + minute;
						// 열차 등급 탐색
						for (int j = 0; j < trainList.size(); j++) {
							if (trainList.get(j).get("train_num").equals(trainNum)) {
								// 무궁화
								if (trainList.get(j).get("train_class").equals("무궁화")) {
									if (allDayMinute <= allMinute
											&& allMinute <= allDayMinute + 20 * (subwayList.size() - 1)) {
										canRegister = false;
									}
								}
								// 새마을
								if (trainList.get(j).get("train_class").equals("새마을")) {
									if (allDayMinute <= allMinute
											&& allMinute <= allDayMinute + 10 * (subwayList.size() - 1)) {
										canRegister = false;
									}
								}
							}
						}
					}
				}

				if (canRegister) {
					// 상행
					if (direction.equals("상행")) {
						// 각 지하철 역에 대해 추가
						for (int i = 0; i < subwayList.size(); i++) {
							if (allDayMinute >= 1440)
								allDayMinute -= 1440;
							int hour = allDayMinute / 60;
							int minute = allDayMinute % 60;

							// 열차 등급 탐색
							for (int j = 0; j < trainList.size(); j++) {
								if (trainList.get(j).get("train_num").equals(trainNum)) {
									// 무궁화
									if (trainList.get(j).get("train_class").equals("무궁화")) {
										DB_Schedule.insertSchedule(subwayList.get(i).get("subway_num"), trainNum,
												direction, Integer.toString(hour) + ":" + Integer.toString(minute));
										allDayMinute += 20;
									}
									// 새마을
									else if (trainList.get(j).get("train_class").equals("새마을")) {
										DB_Schedule.insertSchedule(subwayList.get(i).get("subway_num"), trainNum,
												direction, Integer.toString(hour) + ":" + Integer.toString(minute));
										allDayMinute += 10;
									}
									window.change("adminPage");
								}
							}
						}
					} else {
						// 각 지하철 역에 대해 추가
						for (int i = subwayList.size()-1; i >=0; i--) {
							if (allDayMinute >= 1440)
								allDayMinute -= 1440;
							int hour = allDayMinute / 60;
							int minute = allDayMinute % 60;

							// 열차 등급 탐색
							for (int j = 0; j < trainList.size(); j++) {
								if (trainList.get(j).get("train_num").equals(trainNum)) {
									// 무궁화
									if (trainList.get(j).get("train_class").equals("무궁화")) {
										DB_Schedule.insertSchedule(subwayList.get(i).get("subway_num"), trainNum,
												direction, Integer.toString(hour) + ":" + Integer.toString(minute));
										allDayMinute += 20;
									}
									// 새마을
									else if (trainList.get(j).get("train_class").equals("새마을")) {
										DB_Schedule.insertSchedule(subwayList.get(i).get("subway_num"), trainNum,
												direction, Integer.toString(hour) + ":" + Integer.toString(minute));
										allDayMinute += 10;
									}
									window.change("adminPage");
								}
							}
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "해당 시간에 열차가 이미 운행중입니다.");
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

	}
}
