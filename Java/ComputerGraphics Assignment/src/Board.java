import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.*;
import javax.swing.border.*;

public class Board extends JPanel implements MouseListener, MouseMotionListener {

	static Stack<Polygon> polygonLyst; // 도형 리스트
	static ArrayList<Position> clickedPos; // 마우스 클릭된 포스

	int winX;
	int winY;
	int gap;
	boolean gridFlag;

	Position mousePos;

	// 생성자
	public Board(int windowSizeX, int windowSizeY, int gapSize, boolean gridFlag) {
		polygonLyst = new Stack<>();
		clickedPos = new ArrayList<>();
		winX = windowSizeX + 1;
		winY = windowSizeY + 1;
		setPreferredSize(new Dimension(winX, winY));
		gap = gapSize;
		this.gridFlag = gridFlag;
		mousePos = new Position(0, 0);
	}

	// paint 동작 정의
	public void paint(Graphics g) {

		// 초기화
		g.setColor(Color.white);
		g.fillRect(0, 0, winX, winY);
		g.setColor(Color.black);
		g.drawRect(0, 0, winX - 1, winY - 1);

		// 격자 그리기
		if (gridFlag) {
			g.setColor(Color.gray);
			for (int i = 1; i < winX / gap; i++) {
				// y축
				g.drawLine(i * gap, 0, i * gap, winY);

				for (int j = 1; j < winY / gap; j++)
					// x축
					g.drawLine(0, i * gap, winX, i * gap);
			}
			g.setColor(Color.black);
		}

		// 마우스 올라가있는 영역 색칠
		if (mousePos != null) {
			g.setColor(Color.RED);
			g.fillRect(mousePos.x * gap, mousePos.y * gap, gap, gap);
			g.setColor(Color.black);
		}

		// 도형 그리기
		for (Polygon poly : polygonLyst) {
			for (Position pos : poly.fixelList) {
				g.fillRect(pos.x * gap, pos.y * gap, gap, gap);
			}
		}
	}

	// 마우스 동작
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mousePos.x = e.getX() / gap;
		mousePos.y = e.getY() / gap;
		repaint();

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Position p = new Position(mousePos.x, mousePos.y);
		// 점 그리기
		if (Program.selectedFunc == Program.DRAW_POINT) {
			if (clickedPos.size() == 0) {
				Program.text_drawPointP1.setText("");
				clickedPos.add(p);

				Program.text_drawPointP1.setText(p.x + "," + p.y);
				polygonLyst.push(new Point2(clickedPos.get(0)));
				clickedPos.clear();
				repaint();
			}
		}

		// 선 그리기
		if (Program.selectedFunc == Program.DRAW_LINE) {
			if (clickedPos.size() == 0) {
				Program.text_drawLineP1.setText("");
				Program.text_drawLineP2.setText("");
				clickedPos.add(p);
				Program.text_drawLineP1.setText(p.x + "," + p.y);
			} else {
				clickedPos.add(p);
				Program.text_drawLineP2.setText(p.x + "," + p.y);
				polygonLyst.push(new Line(clickedPos.get(0), clickedPos.get(1)));
				clickedPos.clear();
				repaint();
			}
		}

		// 원 그리기
		if (Program.selectedFunc == Program.DRAW_CIRCLE) {
			if (clickedPos.size() == 0) {
				Program.text_drawCircleP1.setText("");
				clickedPos.add(p);
				Program.text_drawCircleP1.setText(p.x + "," + p.y);

				try {
					polygonLyst.push(
							new Circle(clickedPos.get(0), Integer.parseInt(Program.text_drawCircleRadi.getText())));
					clickedPos.clear();
					repaint();
				} catch (NumberFormatException ee) {
					clickedPos.clear();
				}
			}
		}

		// 삼각형 그리기
		if (Program.selectedFunc == Program.DRAW_TRI) {
			if (clickedPos.size() == 0) {
				Program.text_drawTriP1.setText("");
				Program.text_drawTriP2.setText("");
				Program.text_drawTriP3.setText("");
				clickedPos.add(p);
				Program.text_drawTriP1.setText(p.x + "," + p.y);
			} else if (clickedPos.size() == 1) {
				clickedPos.add(p);
				Program.text_drawTriP2.setText(p.x + "," + p.y);
			} else {
				clickedPos.add(p);
				Program.text_drawTriP3.setText(p.x + "," + p.y);
				polygonLyst.push(new Triangle(clickedPos.get(0), clickedPos.get(1), clickedPos.get(2)));
				clickedPos.clear();
				repaint();
			}
		}

		// 사각형 그리기
		if (Program.selectedFunc == Program.DRAW_QUAD) {
			if (clickedPos.size() == 0) {
				Program.text_drawQuadP1.setText("");
				Program.text_drawQuadP2.setText("");
				Program.text_drawQuadP3.setText("");
				Program.text_drawQuadP4.setText("");
				clickedPos.add(p);
				Program.text_drawQuadP1.setText(p.x + "," + p.y);
			} else if (clickedPos.size() == 1) {
				clickedPos.add(p);
				Program.text_drawQuadP2.setText(p.x + "," + p.y);

			} else if (clickedPos.size() == 2) {
				clickedPos.add(p);
				Program.text_drawQuadP3.setText(p.x + "," + p.y);
			} else {
				clickedPos.add(p);
				Program.text_drawQuadP4.setText(p.x + "," + p.y);
				polygonLyst.push(new Quad(clickedPos.get(0), clickedPos.get(1), clickedPos.get(2), clickedPos.get(3)));
				clickedPos.clear();
				repaint();
			}
		}

		// 이동
		if (Program.selectedFunc == Program.TRANS_MOVE) {
			if (clickedPos.size() == 0) {
				Program.text_transMoveP1.setText("");
				Program.text_transMoveP2.setText("");
				clickedPos.add(p);
				Program.text_transMoveP1.setText(p.x + "," + p.y);
			} else {
				clickedPos.add(p);
				Program.text_transMoveP2.setText(p.x + "," + p.y);
				polygonLyst.peek().transMove(clickedPos.get(0), clickedPos.get(1));
				clickedPos.clear();
				repaint();
			}
		}

		// 크기 변환
		if (Program.selectedFunc == Program.TRANS_SCALE) {
			if (clickedPos.size() == 0) {
				Program.text_transScaleFP1.setText("");
				clickedPos.add(p);
				Program.text_transScaleFP1.setText(p.x + "," + p.y);
				polygonLyst.peek().transScale(Program.text_transScaleSizeX.getText(), Program.text_transScaleSizeY.getText(), clickedPos.get(0));
				clickedPos.clear();
				repaint();
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		mousePos = new Position(0, 0);
		mousePos.x = e.getX() / gap;
		mousePos.y = e.getY() / gap;
		repaint();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		mousePos = null;
		repaint();
	}
}
