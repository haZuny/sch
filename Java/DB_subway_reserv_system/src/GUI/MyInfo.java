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
import javax.swing.JOptionPane;
import javax.swing.ScrollPaneConstants;

import java.util.*;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class MyInfo extends JPanel {
	MainFrame window;
	HashMap<String, String> user;

	ArrayList<HashMap<String, String>> trainList;
	ArrayList<HashMap<String, String>> subwayList;
	ArrayList<HashMap<String, String>> scheduleList;
	ArrayList<HashMap<String, String>> showedScheduleList;
	private JTextField textField_userName;
	private JTextField textField_phoneNum;
	private JTextField textField_cardNum;
	private JPasswordField passwordField_password;

	/**
	 * Create the panel.
	 */
	public MyInfo(MainFrame window, HashMap<String, String> user) {
		this.window = window;
		this.user = user;
		
		trainList = DB_Train.getTrainList();
		subwayList = DB_Subway.getSubwayList();
		scheduleList = DB_Schedule.getScheduleList();

		setLayout(null);

		JLabel lb_title = new JLabel("내 정보");
		lb_title.setHorizontalAlignment(SwingConstants.CENTER);
		lb_title.setBounds(150, 30, 400, 50);
		lb_title.setFont(new Font("HY견고딕", Font.PLAIN, 39));
		add(lb_title);

		// 기차역 리스트
		String[] subwayArr = new String[subwayList.size()];
		for(int i = 0; i < subwayList.size(); i++) {
			subwayArr[i] = (subwayList.get(i).get("subway_num") + "  " + subwayList.get(i).get("subway_name"));
		}
		
		JLabel lb_userName = new JLabel("회원 이름");
		lb_userName.setHorizontalAlignment(SwingConstants.LEFT);
		lb_userName.setBounds(30, 200, 100, 30);
		add(lb_userName);
		
		// 회원 이름
		textField_userName = new JTextField();
		textField_userName.setText(user.get("user_name"));
		textField_userName.setBounds(130, 200, 200, 30);
		add(textField_userName);
		textField_userName.setColumns(10);
		
		JLabel lb_phoneNum = new JLabel("휴대전화");
		lb_phoneNum.setHorizontalAlignment(SwingConstants.LEFT);
		lb_phoneNum.setBounds(370, 200, 100, 30);
		add(lb_phoneNum);
		
		// 휴대전화
		textField_phoneNum = new JTextField();
		textField_phoneNum.setText(user.get("phone_number"));
		textField_phoneNum.setColumns(10);
		textField_phoneNum.setBounds(470, 200, 200, 30);
		add(textField_phoneNum);
		
		JLabel lb_password = new JLabel("비밀번호");
		lb_password.setHorizontalAlignment(SwingConstants.LEFT);
		lb_password.setBounds(30, 300, 100, 30);
		add(lb_password);
		
		// 카드번호
		textField_cardNum = new JTextField();
		textField_cardNum.setText(user.get("card_number"));
		textField_cardNum.setColumns(10);
		textField_cardNum.setBounds(470, 300, 200, 30);
		add(textField_cardNum);
		
		JLabel lb_cardNum = new JLabel("카드 번호");
		lb_cardNum.setHorizontalAlignment(SwingConstants.LEFT);
		lb_cardNum.setBounds(370, 300, 100, 30);
		add(lb_cardNum);
		
		// 등급
		JLabel lb_userClass = new JLabel("등급: " + user.get("user_class"));
		lb_userClass.setFont(new Font("한컴 고딕", Font.BOLD | Font.ITALIC, 24));
		lb_userClass.setHorizontalAlignment(SwingConstants.CENTER);
		lb_userClass.setBounds(200, 100, 300, 50);
		add(lb_userClass);
		
		// 수정 버튼
		JButton btn_edit = new JButton("수정");
		btn_edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// TextField에서 값 얻기
				String userName = textField_userName.getText();
				String password = passwordField_password.getText();
				String phoneNumber = textField_phoneNum.getText();
				String cardNumber = textField_cardNum.getText();
				
				// userName 중복 확인
				boolean checkUserName = DB_USER.checkUserNameOverlap(userName);
				
				// 공백 체크
				if ("".equals(userName) || "".equals(password) || "".equals(phoneNumber) || "".equals(cardNumber)) {				
					JOptionPane.showMessageDialog(null, "모든 항목을 입력해주세요.");
				}
				// 아이디 중복 체크
				else if (checkUserName && (!userName.equals(user.get("user_name")))) {
					JOptionPane.showMessageDialog(null, "회원이름이 중복되었습니다.");
				}
				// 휴대전화 정규식 체크
				else if (!Pattern.matches("010-\\d\\d\\d\\d-\\d\\d\\d\\d", phoneNumber)) {
					JOptionPane.showMessageDialog(null, "휴대전화를 올바르게 입력해주세요.");
				}
				// 카드번호 정규식 체크
				else if (!Pattern.matches("\\d\\d\\d\\d-\\d\\d\\d\\d-\\d\\d\\d\\d-\\d\\d\\d\\d", cardNumber)) {
					JOptionPane.showMessageDialog(null, "카드번호를 올바르게 입력해주세요.");
				}
				else {				
					// DB 업데이트
					DB_USER.updateUser(user.get("user_num"), "USER_NAME", userName);
					DB_USER.updateUser(user.get("user_num"), "PASS_WORD", password);
					DB_USER.updateUser(user.get("user_num"), "PHONE_NUMBER", phoneNumber);
					DB_USER.updateUser(user.get("user_num"), "CARD_NUMBER", cardNumber);
					
					
					
					// 로그인 화면으로 이동
					window.change("userPage", DB_USER.getUserByUserName(userName));
				}
				
			}
		});btn_edit.setBounds(185,400,150,30);

	add(btn_edit);
		
		// 뒤로 가기 버튼
		JButton btn_back = new JButton("뒤로");
		btn_back.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				window.change("userPage", user);
			}
		});
		btn_back.setBounds(365, 400, 150, 30);
		add(btn_back);
		
		// 비밀번호
		passwordField_password = new JPasswordField();
		passwordField_password.setText(user.get("password"));
		passwordField_password.setBounds(130, 300, 200, 30);
		add(passwordField_password);
	}

	// 시간표 목록 리스트 항목
	class Panel_scheduleListInside extends JPanel {
		HashMap<String, String> schedule;

		public Panel_scheduleListInside(HashMap<String, String> schedule) {
			this.schedule = schedule;
			String str = "";

			setPreferredSize(new Dimension(250, 30));
			setLayout(new GridLayout());
			// 지하철역
			for (int i = 0; i < subwayList.size(); i++) {
				if (subwayList.get(i).get("subway_num").equals(schedule.get("subway_num"))) {
					add(new JLabel(schedule.get("subway_num") + ": " + subwayList.get(i).get("subway_name")));
				}
			}
			// 열차
			for (int i = 0; i < trainList.size(); i++) {
				if (trainList.get(i).get("train_num").equals(schedule.get("train_num"))) {
					add(new JLabel(schedule.get("train_num") + ": " + trainList.get(i).get("train_class")));
				}
			}
			add(new JLabel(schedule.get("direction")));
			add(new JLabel(schedule.get("time")));
		}
	}
}