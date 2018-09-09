package com.antony.wuziqi0908;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class DrawListener extends MouseAdapter implements ActionListener {
	private boolean canPlay = false, isPlayer = false;
	private int x, y, xx, yy;
	private Graphics g;
	private JFrame j;
	private int tWidth;
	public StoreChess[][] SC;
	public StoreChess[] SCF;
	private Robot r;
	// private int count = 0;
	private boolean isChess = true; // 判断点击的地方有没有已经下了的棋子
	private boolean isBlack = true; // 判断下一棋子是不是黑棋
	private String BT = null;
	private String oBT = null;
	private int count = 0;

	DrawListener(StoreChess[][] s, StoreChess[] sf, Graphics g, JFrame j) {
		this.g = g;
		this.j = j;
		SC = s;
		SCF = sf;
		r = new Robot(SC);
	}

	public int getTWidth() {
		int width = j.getWidth();
		int length = j.getHeight();
		if (length > width)
			tWidth = width;
		else
			tWidth = length;
		return tWidth;
	}

	public void mouseClicked(MouseEvent e) {
		r.clearValue();
		x = e.getX();
		y = e.getY();
		switch (BT) {
		case "玩家先下":
			oBT = BT;
			if (isPlayer) {
				playChess(x, y);
				isPlayer = false;
			}
			if (!isPlayer) {
				if (isBlack)
					r.AI(2);
				else
					r.AI(1);
				robPlayChess(r.getX(), r.getY());
				isPlayer = true;
			}
			break;

		case "机器先下":
			oBT = BT;
			if (isPlayer) {
				playChess(x, y);
				isPlayer = false;
			}
			if (!isPlayer) {
				if (isBlack)
					r.AI(2);
				else
					r.AI(1);
				robPlayChess(r.getX(), r.getY());
				isPlayer = true;
			}
			break;
		case "双人对战":
			oBT = BT;
			playChess(x, y);
			break;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		BT = e.getActionCommand();
		if (BT.equals("玩家先下")) {
			for (int i = 0; i < count - 1; i++) {
				SCF[i] = null;
			}
			count = 0;
			for (int i = 0; i < SC.length; i++) {
				for (int j = 0; j < SC[i].length; j++) {
					if (SC[i][j] != null) {
						SC[i][j] = null;
					}
				}
			}
			j.repaint();
			canPlay = true;
			isChess = false;
			if (count == 0)
				isPlayer = true;
		} else if (BT.equals("机器先下")) {
			for (int i = 0; i < count - 1; i++) {
				SCF[i] = null;
			}
			count = 0;
			for (int i = 0; i < SC.length; i++) {
				for (int j = 0; j < SC[i].length; j++) {
					if (SC[i][j] != null) {
						SC[i][j] = null;
					}
				}
			}
			j.repaint();
			canPlay = true;
			isChess = false;
			if (count == 0) {
				int[] rp = r.firstPlay();
				playChess(rp[0], rp[1]);
				isPlayer = true;
			}

		} else if (BT.equals("双人对战")) {
			for (int i = 0; i < count - 1; i++) {
				SCF[i] = null;
			}
			count = 0;
			for (int i = 0; i < SC.length; i++) {
				for (int j = 0; j < SC[i].length; j++) {
					if (SC[i][j] != null) {
						SC[i][j] = null;
					}
				}
			}
			j.repaint();
			canPlay = true;
			isChess = false;
		} else if (BT.equals("悔棋") && canPlay) {
			for (int i = 0; i < SC.length; i++) {
				for (int k = 0; k < SC[i].length; k++) {
					if (SC[i][k] != null) {
						if (SC[i][k].equals(SCF[count - 1])) {
							SC[i][k] = null;
						}
					}
				}
			}
			if (count > 0) {
				if (SCF[count - 1].getColor().equals(Color.BLACK)) {
					isBlack = true;
				} else {
					isBlack = false;
				}
				SCF[count - 1] = null;
				count--;
			}
			if(isPlayer==true){
				for (int i = 0; i < SC.length; i++) {
					for (int k = 0; k < SC[i].length; k++) {
						if (SC[i][k] != null) {
							if (SC[i][k].equals(SCF[count - 1])) {
								SC[i][k] = null;
							}
						}
					}
				}
				if (count > 0) {
					if (SCF[count - 1].getColor().equals(Color.BLACK)) {
						isBlack = true;
					} else {
						isBlack = false;
					}
					SCF[count - 1] = null;
					count--;
				}
			}
			BT = oBT;
			canPlay = true;
			isPlayer = true;
			// if (count <= 1) {
			// count = 0;
			// } else {
			// count = count - 2;
			// }
			j.repaint();
		}
	}

	public void playChess(int x, int y) {
		int d = (getTWidth() - 150) / 28;
		for (int i = 0; i < 15; i++) {
			int y1 = 100 + 2 * d * i;
			if (Math.abs(y - y1) < (getTWidth() - 150) / 28) {
				y = y1;
				yy = (y - 100) / (2 * d);
				break;
			}
		}
		for (int i = 0; i < 15; i++) {
			int x1 = 75 + 2 * d * i;
			if (Math.abs(x - x1) < (getTWidth() - 150) / 28) {
				x = x1;
				xx = (x - 75) / (2 * d);
				break;
			}
		}
		// g.setColor(BLACK);

		for (int i = 0; i < SC.length; i++) {
			for (int j = 0; j < SC[i].length; j++) {
				if (SC[i][j] != null) {
					if (x - d == SC[i][j].getX() && y - d == SC[i][j].getY()) {
						isChess = true;
						break;
					}
				}
			}
		}
		if ((!isChess) && canPlay) {
			if (isBlack) {
				g.setColor(Color.BLACK);
				g.fillOval(x - d, y - d, 2 * d, 2 * d);
				StoreChess chess = new StoreChess(x - d, y - d, 2 * d, g.getColor());

				// chess.draw(g);
				SC[xx][yy] = chess;
				SCF[count++] = chess;
				checkWinner();
				isBlack = false;
			} else {
				g.setColor(Color.WHITE);
				g.fillOval(x - d, y - d, 2 * d, 2 * d);
				StoreChess chess = new StoreChess(x - d, y - d, 2 * d, g.getColor());

				// chess.draw(g);
				SC[xx][yy] = chess;
				SCF[count++] = chess;
				checkWinner();
				isBlack = true;
			}
		}
		isChess = false;
	}

	public void robPlayChess(int x, int y) {
		int d = (j.getWidth() - 150) / 14;
		if ((!isChess) && canPlay) {
			if (isBlack) {
				g.setColor(Color.BLACK);
				g.fillOval(75 + x * d - d / 2, 100 + y * d - d / 2, d, d);
				StoreChess chess = new StoreChess(75 + x * d - d / 2, 100 + y * d - d / 2, d, g.getColor());

				SC[x][y] = chess;
				SCF[count++] = chess;
				checkWinner();
				isBlack = false;
			} else {
				g.setColor(Color.WHITE);
				g.fillOval(75 + x * d - d / 2, 100 + y * d - d / 2, d, d);
				StoreChess chess = new StoreChess(75 + x * d - d / 2, 100 + y * d - d / 2, d, g.getColor());

				SC[x][y] = chess;
				SCF[count++] = chess;
				checkWinner();
				isBlack = true;
			}
		}
		isChess = false;
	}

	public void checkWinner() {
		int d = (getTWidth() - 150) / 28;
		for (int i = 0; i < SC.length; i++) {
			for (int j = 0; j < SC[i].length - 4; j++) {
				if (SC[i][j] != null && SC[i][j + 1] != null && SC[i][j + 2] != null && SC[i][j + 3] != null
						&& SC[i][j + 4] != null) {
					if (SC[i][j].getColor().equals(SC[i][j + 1].getColor())
							&& SC[i][j].getColor().equals(SC[i][j + 2].getColor())
							&& SC[i][j].getColor().equals(SC[i][j + 3].getColor())
							&& SC[i][j].getColor().equals(SC[i][j + 4].getColor())) {
						SC[i][j].draw(g, Color.RED);
						SC[i][j + 1].draw(g, Color.RED);
						SC[i][j + 2].draw(g, Color.RED);
						SC[i][j + 3].draw(g, Color.RED);
						SC[i][j + 4].draw(g, Color.RED);
						isChess = true;
						canPlay = false;
					}
				}
			}
		}
		for (int i = 0; i < SC.length - 4; i++) {
			for (int j = 0; j < SC[i].length; j++) {
				if (SC[i][j] != null && SC[i + 1][j] != null && SC[i + 2][j] != null && SC[i + 3][j] != null
						&& SC[i + 4][j] != null) {
					if (SC[i][j].getColor().equals(SC[i + 1][j].getColor())
							&& SC[i][j].getColor().equals(SC[i + 2][j].getColor())
							&& SC[i][j].getColor().equals(SC[i + 3][j].getColor())
							&& SC[i][j].getColor().equals(SC[i + 4][j].getColor())) {
						SC[i][j].draw(g, Color.RED);
						SC[i + 1][j].draw(g, Color.RED);
						SC[i + 2][j].draw(g, Color.RED);
						SC[i + 3][j].draw(g, Color.RED);
						SC[i + 4][j].draw(g, Color.RED);
						isChess = true;
						canPlay = false;
					}
				}
			}
		}
		for (int i = 0; i < SC.length - 4; i++) {
			for (int j = 0; j < SC[i].length - 4; j++) {
				if (SC[i][j] != null && SC[i + 1][j + 1] != null && SC[i + 2][j + 2] != null && SC[i + 3][j + 3] != null
						&& SC[i + 4][j + 4] != null) {
					if (SC[i][j].getColor().equals(SC[i + 1][j + 1].getColor())
							&& SC[i][j].getColor().equals(SC[i + 2][j + 2].getColor())
							&& SC[i][j].getColor().equals(SC[i + 3][j + 3].getColor())
							&& SC[i][j].getColor().equals(SC[i + 4][j + 4].getColor())) {
						SC[i][j].draw(g, Color.RED);
						SC[i + 1][j + 1].draw(g, Color.RED);
						SC[i + 2][j + 2].draw(g, Color.RED);
						SC[i + 3][j + 3].draw(g, Color.RED);
						SC[i + 4][j + 4].draw(g, Color.RED);
						isChess = true;
						canPlay = false;
					}
				}
			}
		}
		for (int i = 4; i < SC.length - 4; i++) {
			for (int j = 0; j < SC[i].length; j++) {
				if (SC[i][j] != null && SC[i + 1][j - 1] != null && SC[i + 2][j - 2] != null && SC[i + 3][j - 3] != null
						&& SC[i + 4][j - 4] != null) {
					if (SC[i][j].getColor().equals(SC[i + 1][j - 1].getColor())
							&& SC[i][j].getColor().equals(SC[i + 2][j - 2].getColor())
							&& SC[i][j].getColor().equals(SC[i + 3][j - 3].getColor())
							&& SC[i][j].getColor().equals(SC[i + 4][j - 4].getColor())) {
						SC[i][j].draw(g, Color.RED);
						SC[i + 1][j - 1].draw(g, Color.RED);
						SC[i + 2][j - 2].draw(g, Color.RED);
						SC[i + 3][j - 3].draw(g, Color.RED);
						SC[i + 4][j - 4].draw(g, Color.RED);
						isChess = true;
						canPlay = false;
					}
				}
			}
		}
	}

}
