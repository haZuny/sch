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
import java.awt.GridBagConstraints;
import java.awt.CardLayout;

public class AdminPage extends JPanel {

	/**
	 * Create the panel.
	 */
	public AdminPage() {
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
		
		JLabel lb_schedultList = new JLabel("시간표");
		lb_schedultList.setBounds(25, 280, 200, 30);
		add(lb_schedultList);
		
		JComboBox comboBox_schedule = new JComboBox();
		comboBox_schedule.setBounds(25, 430, 200, 30);
		add(comboBox_schedule);
		
		JPanel panel_trainList_1 = new JPanel();
		panel_trainList_1.setBackground(Color.WHITE);
		panel_trainList_1.setBounds(25, 310, 300, 120);
		add(panel_trainList_1);
		
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
		GridBagLayout gbl_panel_signUpAccept = new GridBagLayout();
		gbl_panel_signUpAccept.columnWidths = new int[]{0, 0};
		gbl_panel_signUpAccept.rowHeights = new int[]{0, 0};
		gbl_panel_signUpAccept.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_signUpAccept.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel_signUpAccept.setLayout(gbl_panel_signUpAccept);
		
		JPanel panel_signUpAccept_1 = new JPanel();
		panel_signUpAccept_1.setBackground(Color.WHITE);
		panel_signUpAccept_1.setBounds(375, 310, 300, 120);
		add(panel_signUpAccept_1);
		
		JLabel lb_signUpAccept_1 = new JLabel("회원 목록");
		lb_signUpAccept_1.setBounds(375, 280, 300, 30);
		add(lb_signUpAccept_1);
		
		JButton btn_addTrain = new JButton("추가");
		btn_addTrain.setBounds(245, 100, 80, 30);
		add(btn_addTrain);
		
		JButton btn_addTrain_1 = new JButton("추가");
		btn_addTrain_1.setBounds(245, 280, 80, 30);
		add(btn_addTrain_1);

	}
}
