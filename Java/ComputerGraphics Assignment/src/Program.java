import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.Font;
import java.util.ArrayList;

public class Program extends JFrame {
	// À©µµ¿ì »çÀÌÁî
	static int winX = 800;
	static int winY = 600;

	// ±×¸² ¿µ¿ª »çÀÌÁî
	static int panX = 500;
	static int panY = 500;
	static int gap = 1;
	boolean gridFlag = false;

	// ¼±ÅÃµÈ ·¹ÀÌºí
	static int NULL = 0;
	static int DRAW_POINT = 1;
	static int DRAW_LINE = 2;
	static int DRAW_CIRCLE = 3;
	static int DRAW_TRI = 4;
	static int DRAW_QUAD = 5;
	static int TRANS_MOVE = 6;
	static int TRANS_SCALE = 7;
	static int TRANS_ROTATE = 8;
	static int CLIP_CLIPPING = 9;
	static int selectedFunc = 0;

	// ÄÄÆ÷³ÍÆ®
	private JPanel contentPane;
	static JTextField text_drawPointP1;
	static JTextField text_drawLineP1;
	static JTextField text_drawLineP2;
	static JTextField text_drawCircleP1;
	static JTextField text_drawCircleRadi;
	static JTextField text_drawTriP1;
	static JTextField text_drawTriP2;
	static JTextField text_drawTriP3;
	static JTextField text_drawQuadP1;
	static JTextField text_drawQuadP2;
	static JTextField text_drawQuadP3;
	static JTextField text_drawQuadP4;
	static JTextField text_transMoveP1;
	static JTextField text_transMoveP2;
	static JTextField text_transScaleFP1;
	static JTextField text_transScaleSizeX;
	static JTextField text_transScaleSizeY;
	static JTextField text_transRotateP1;
	static JTextField text_transRotateAngle;
	static JTextField text_clipClipP1;
	static JTextField text_clipClipP2;
	static JLabel label_checkIsClipped;
	private JButton btn_clipClipBack;
	private JButton btn_clear;
	private JButton btn_delete;
	private ButtonGroup radioGroup;

	// ¸ÞÀÎ ¸Þ¼Òµå
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Program frame = new Program();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// »ý¼ºÀÚ
	public Program() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, winX, winY);
		setTitle("20194122 ±ÇÇÏÁØ");

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// ±×¸² ¿µ¿ª
		Board drawingPan = new Board(panX, panY, gap, gridFlag);
		drawingPan.setBounds(35, 35, panX + 1, panY + 1);
		contentPane.add(drawingPan);
		drawingPan.addMouseListener(drawingPan);
		drawingPan.addMouseMotionListener(drawingPan);

		// ±×¸®±â
		JPanel panel_draw = new JPanel();
		panel_draw.setBorder(new LineBorder(Color.DARK_GRAY));
		panel_draw.setBounds(548, 35, 212, 165);
		contentPane.add(panel_draw);
		panel_draw.setLayout(null);

		JLabel label_titleDraw = new JLabel("\uADF8\uB9AC\uAE30");
		label_titleDraw.setHorizontalAlignment(SwingConstants.CENTER);
		label_titleDraw.setBounds(0, 0, 210, 30);
		panel_draw.add(label_titleDraw);

		// Á¡
		JRadioButton radio_drawPoint = new JRadioButton("\uC810");
		radio_drawPoint.addActionListener(e -> {
			selectedFunc = DRAW_POINT;
			Board.clickedPos.clear();
		});
		radio_drawPoint.setBounds(10, 40, 49, 20);
		panel_draw.add(radio_drawPoint);

		// ¼±
		JRadioButton radio_drawLine = new JRadioButton("\uC120");
		radio_drawLine.addActionListener(e -> {
			selectedFunc = DRAW_LINE;
			Board.clickedPos.clear();
		});
		radio_drawLine.setBounds(10, 65, 43, 20);
		panel_draw.add(radio_drawLine);

		// ¿ø
		JRadioButton radio_drawCircle = new JRadioButton("\uC6D0");
		radio_drawCircle.addActionListener(e -> {
			selectedFunc = DRAW_CIRCLE;
			Board.clickedPos.clear();
		});
		radio_drawCircle.setBounds(10, 90, 45, 20);
		panel_draw.add(radio_drawCircle);

		// »ï°¢Çü
		JRadioButton radio_drawTri = new JRadioButton("\uC0BC\uAC01\uD615");
		radio_drawTri.addActionListener(e -> {
			selectedFunc = DRAW_TRI;
			Board.clickedPos.clear();
		});
		radio_drawTri.setBounds(10, 115, 62, 20);
		panel_draw.add(radio_drawTri);

		// »ç°¢Çü
		JRadioButton radio_drawQuad = new JRadioButton("\uC0AC\uAC01\uD615");
		radio_drawQuad.addActionListener(e -> {
			selectedFunc = DRAW_QUAD;
			Board.clickedPos.clear();
		});
		radio_drawQuad.setBounds(10, 140, 62, 20);
		panel_draw.add(radio_drawQuad);

		text_drawPointP1 = new JTextField();
		text_drawPointP1.setFont(new Font("±¼¸²", Font.BOLD, 9));
		text_drawPointP1.setEditable(false);
		text_drawPointP1.setBounds(70, 40, 30, 20);
		panel_draw.add(text_drawPointP1);
		text_drawPointP1.setColumns(10);

		text_drawLineP1 = new JTextField();
		text_drawLineP1.setFont(new Font("±¼¸²", Font.BOLD, 9));
		text_drawLineP1.setEditable(false);
		text_drawLineP1.setColumns(10);
		text_drawLineP1.setBounds(70, 65, 30, 20);
		panel_draw.add(text_drawLineP1);

		text_drawCircleP1 = new JTextField();
		text_drawCircleP1.setFont(new Font("±¼¸²", Font.BOLD, 9));
		text_drawCircleP1.setEditable(false);
		text_drawCircleP1.setColumns(10);
		text_drawCircleP1.setBounds(70, 90, 30, 20);
		panel_draw.add(text_drawCircleP1);

		text_drawTriP1 = new JTextField();
		text_drawTriP1.setFont(new Font("±¼¸²", Font.BOLD, 9));
		text_drawTriP1.setEditable(false);
		text_drawTriP1.setColumns(10);
		text_drawTriP1.setBounds(70, 115, 30, 20);
		panel_draw.add(text_drawTriP1);

		text_drawQuadP1 = new JTextField();
		text_drawQuadP1.setFont(new Font("±¼¸²", Font.BOLD, 9));
		text_drawQuadP1.setEditable(false);
		text_drawQuadP1.setColumns(10);
		text_drawQuadP1.setBounds(70, 140, 30, 20);
		panel_draw.add(text_drawQuadP1);

		text_drawQuadP2 = new JTextField();
		text_drawQuadP2.setFont(new Font("±¼¸²", Font.BOLD, 9));
		text_drawQuadP2.setEditable(false);
		text_drawQuadP2.setColumns(10);
		text_drawQuadP2.setBounds(105, 140, 30, 20);
		panel_draw.add(text_drawQuadP2);

		text_drawQuadP3 = new JTextField();
		text_drawQuadP3.setFont(new Font("±¼¸²", Font.BOLD, 9));
		text_drawQuadP3.setEditable(false);
		text_drawQuadP3.setColumns(10);
		text_drawQuadP3.setBounds(140, 140, 30, 20);
		panel_draw.add(text_drawQuadP3);

		text_drawQuadP4 = new JTextField();
		text_drawQuadP4.setFont(new Font("±¼¸²", Font.BOLD, 9));
		text_drawQuadP4.setEditable(false);
		text_drawQuadP4.setColumns(10);
		text_drawQuadP4.setBounds(175, 140, 30, 20);
		panel_draw.add(text_drawQuadP4);

		text_drawTriP2 = new JTextField();
		text_drawTriP2.setFont(new Font("±¼¸²", Font.BOLD, 9));
		text_drawTriP2.setEditable(false);
		text_drawTriP2.setColumns(10);
		text_drawTriP2.setBounds(105, 115, 30, 20);
		panel_draw.add(text_drawTriP2);

		text_drawTriP3 = new JTextField();
		text_drawTriP3.setFont(new Font("±¼¸²", Font.BOLD, 9));
		text_drawTriP3.setEditable(false);
		text_drawTriP3.setColumns(10);
		text_drawTriP3.setBounds(140, 115, 30, 20);
		panel_draw.add(text_drawTriP3);

		text_drawCircleRadi = new JTextField();
		text_drawCircleRadi.setText("10");
		text_drawCircleRadi.setColumns(10);
		text_drawCircleRadi.setBounds(105, 90, 30, 20);
		panel_draw.add(text_drawCircleRadi);

		text_drawLineP2 = new JTextField();
		text_drawLineP2.setFont(new Font("±¼¸²", Font.BOLD, 9));
		text_drawLineP2.setEditable(false);
		text_drawLineP2.setColumns(10);
		text_drawLineP2.setBounds(105, 65, 30, 20);
		panel_draw.add(text_drawLineP2);

		// º¯È¯
		JPanel panel_trans = new JPanel();
		panel_trans.setLayout(null);
		panel_trans.setBorder(new LineBorder(Color.DARK_GRAY));
		panel_trans.setBounds(548, 215, 212, 116);
		contentPane.add(panel_trans);

		JLabel label_titleTrans = new JLabel("\uBCC0\uD658");
		label_titleTrans.setHorizontalAlignment(SwingConstants.CENTER);
		label_titleTrans.setBounds(0, 0, 210, 30);
		panel_trans.add(label_titleTrans);

		// ÀÌµ¿
		JRadioButton radio_transMove = new JRadioButton("\uC774\uB3D9");
		radio_transMove.addActionListener(e -> {
			selectedFunc = TRANS_MOVE;
			Board.clickedPos.clear();
		});
		radio_transMove.setBounds(10, 40, 59, 20);
		panel_trans.add(radio_transMove);

		// Å©±â º¯È¯
		JRadioButton radio_transSize = new JRadioButton("\uD06C\uAE30");
		radio_transSize.addActionListener(e -> {
			selectedFunc = TRANS_SCALE;
			Board.clickedPos.clear();
		});
		radio_transSize.setBounds(10, 65, 59, 20);
		panel_trans.add(radio_transSize);

		// È¸Àü
		JRadioButton radio_transRotate = new JRadioButton("\uD68C\uC804");
		radio_transRotate.addActionListener(e -> {
			selectedFunc = TRANS_ROTATE;
			Board.clickedPos.clear();
		});
		radio_transRotate.setBounds(10, 90, 57, 20);
		panel_trans.add(radio_transRotate);

		text_transMoveP1 = new JTextField();
		text_transMoveP1.setFont(new Font("±¼¸²", Font.BOLD, 9));
		text_transMoveP1.setEditable(false);
		text_transMoveP1.setColumns(10);
		text_transMoveP1.setBounds(70, 40, 30, 20);
		panel_trans.add(text_transMoveP1);

		text_transScaleFP1 = new JTextField();
		text_transScaleFP1.setFont(new Font("±¼¸²", Font.BOLD, 9));
		text_transScaleFP1.setEditable(false);
		text_transScaleFP1.setColumns(10);
		text_transScaleFP1.setBounds(70, 65, 30, 20);
		panel_trans.add(text_transScaleFP1);

		text_transRotateP1 = new JTextField();
		text_transRotateP1.setFont(new Font("±¼¸²", Font.BOLD, 9));
		text_transRotateP1.setEditable(false);
		text_transRotateP1.setColumns(10);
		text_transRotateP1.setBounds(70, 90, 30, 20);
		panel_trans.add(text_transRotateP1);

		text_transRotateAngle = new JTextField();
		text_transRotateAngle.setToolTipText("");
		text_transRotateAngle.setText("45");
		text_transRotateAngle.setColumns(10);
		text_transRotateAngle.setBounds(105, 90, 30, 20);
		panel_trans.add(text_transRotateAngle);

		text_transScaleSizeX = new JTextField();
		text_transScaleSizeX.setText("1");
		text_transScaleSizeX.setColumns(10);
		text_transScaleSizeX.setBounds(105, 65, 30, 20);
		panel_trans.add(text_transScaleSizeX);

		text_transScaleSizeY = new JTextField();
		text_transScaleSizeY.setText("1");
		text_transScaleSizeY.setColumns(10);
		text_transScaleSizeY.setBounds(140, 65, 30, 20);
		panel_trans.add(text_transScaleSizeY);

		// Å¬¸®ÇÎ
		JPanel panel_clip = new JPanel();
		panel_clip.setLayout(null);
		panel_clip.setBorder(new LineBorder(Color.DARK_GRAY));
		panel_clip.setBounds(548, 346, 212, 66);
		contentPane.add(panel_clip);

		JLabel label_titleClipping = new JLabel("\uD074\uB9AC\uD551");
		label_titleClipping.setHorizontalAlignment(SwingConstants.CENTER);
		label_titleClipping.setBounds(0, 0, 210, 30);
		panel_clip.add(label_titleClipping);

		// Å¬¸®ÇÎ
		JRadioButton radio_clipClipping = new JRadioButton("\uD074\uB9AC\uD551");
		radio_clipClipping.addActionListener(e -> {
			selectedFunc = CLIP_CLIPPING;
			Board.clickedPos.clear();
		});
		radio_clipClipping.setBounds(10, 40, 62, 20);
		panel_clip.add(radio_clipClipping);

		text_clipClipP1 = new JTextField();
		text_clipClipP1.setFont(new Font("±¼¸²", Font.BOLD, 9));
		text_clipClipP1.setEditable(false);
		text_clipClipP1.setColumns(10);
		text_clipClipP1.setBounds(70, 40, 30, 20);
		panel_clip.add(text_clipClipP1);

		text_clipClipP2 = new JTextField();
		text_clipClipP2.setFont(new Font("±¼¸²", Font.BOLD, 9));
		text_clipClipP2.setEditable(false);
		text_clipClipP2.setColumns(10);
		text_clipClipP2.setBounds(105, 40, 30, 20);
		panel_clip.add(text_clipClipP2);

		btn_clipClipBack = new JButton("Back");
		btn_clipClipBack.addActionListener(e->{
			if(Board.isClipping) {
				Board.isClipping = false;
				label_checkIsClipped.setText("Off");
			}
			else {
				Board.isClipping = true;
				label_checkIsClipped.setText("On");
			}
			repaint();
		});
		btn_clipClipBack.setBounds(140, 40, 70, 20);
		panel_clip.add(btn_clipClipBack);

		// ±× ¿Ü
		// ÃÊ±âÈ­
		btn_clear = new JButton("Clear");
		btn_clear.addActionListener(e -> {
			Board.polygonLyst.clear();
			repaint();
		});
		btn_clear.setFont(new Font("±¼¸²", Font.PLAIN, 12));
		btn_clear.setBounds(548, 500, 100, 35);
		contentPane.add(btn_clear);

		// »èÁ¦
		btn_delete = new JButton("Delete");
		btn_delete.addActionListener(e -> {
			if (Board.polygonLyst.size() > 0) {
				Board.polygonLyst.pop();
				repaint();
			}
		});
		btn_delete.setFont(new Font("±¼¸²", Font.PLAIN, 12));
		btn_delete.setBounds(674, 500, 100, 35);
		contentPane.add(btn_delete);

		radioGroup = new ButtonGroup();
		radioGroup.add(radio_drawPoint);
		radioGroup.add(radio_drawLine);
		radioGroup.add(radio_drawCircle);
		radioGroup.add(radio_drawTri);
		radioGroup.add(radio_drawQuad);
		radioGroup.add(radio_transMove);
		radioGroup.add(radio_transSize);
		radioGroup.add(radio_transRotate);

		text_transMoveP2 = new JTextField();
		text_transMoveP2.setFont(new Font("±¼¸²", Font.BOLD, 9));
		text_transMoveP2.setEditable(false);
		text_transMoveP2.setColumns(10);
		text_transMoveP2.setBounds(105, 40, 30, 20);
		panel_trans.add(text_transMoveP2);
		radioGroup.add(radio_clipClipping);
		
		label_checkIsClipped = new JLabel("Off");
		label_checkIsClipped.setFont(new Font("±¼¸²", Font.BOLD, 12));
		label_checkIsClipped.setForeground(Color.BLACK);
		label_checkIsClipped.setHorizontalAlignment(SwingConstants.CENTER);
		label_checkIsClipped.setBounds(10, 23, 30, 20);
		panel_clip.add(label_checkIsClipped);

	}
}
