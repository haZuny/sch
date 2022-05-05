import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;

public class Board extends JPanel {

	// ������ ũ��
	static int winX;
	static int winY;
	static boolean gridFlag;

	// �ȼ� ����Ʈ
	ArrayList<Position> figureGroups;

	// �ȼ� ����
	int gap;

	// ������
	public Board(int windowSizeX, int windowSizeY, int gapSize, boolean gridFlag) {
		winX = windowSizeX + 1;
		winY = windowSizeY + 1;
		setPreferredSize(new Dimension(winX, winY));
		gap = gapSize;
		this.gridFlag = gridFlag;
	}

	// paint ���� ����
	public void paint(Graphics g) {
		
		// �ʱ�ȭ
		g.setColor(Color.white);
		g.fillRect(0, 0, winX, winY);
		g.setColor(Color.black);
		g.drawRect(0, 0, winX - 1, winY - 1);
		
		if (gridFlag) {
			// ���� �׸���
			for (int i = 1; i < winX / gap; i++) {
				// y��
				g.drawLine(i * gap, 0, i * gap, winY);

				for (int j = 1; j < winY / gap; j++)
					// x��
					g.drawLine(0, i * gap, winX, i * gap);
			}
		}
	}

}
