package com.antony.wuziqi0908;

import java.awt.Graphics;

import javax.swing.JFrame;

public class ReFrame extends JFrame {
	public StoreChess[][] SC = new StoreChess[15][15];
	public StoreChess[] SCF = new StoreChess[255];
	public void paint(Graphics g) {
		super.paint(g);
		drawBoard(g);
		drawChess(g);
		System.out.println("Ë¢ÐÂÆåÅÌ£¡");
	}

	public void drawBoard(Graphics g) {
		int length = this.getHeight();
		int width = this.getWidth();
		int tWidth = 0;
		if (length > width)
			tWidth = width;
		else
			tWidth = length;
		for (int i = 0; i < 15; i++) {
			int x1 = 75 + (tWidth - 150) / 14 * 0;
			int y1 = 100 + (tWidth - 150) / 14 * i;
			int x2 = 75 + (tWidth - 150) / 14 * 14;
			int y2 = y1;
			g.drawLine(x1, y1, x2, y2);
			// System.out.println("x1:"+x1+",y1:"+y1+",x2:"+x2+",y2:"+y2+",(tWidth
			// - 150) / 14="+((tWidth - 150) / 14));
		}
		for (int i = 0; i < 15; i++) {
			int x1 = 75 + (tWidth - 150) / 14 * i;
			int x2 = x1;
			int y1 = 100 + (tWidth - 150) / 14 * 0;
			int y2 = 100 + (tWidth - 150) / 14 * 14;
			g.drawLine(x1, y1, x2, y2);
		}
	}
	// public int getTWidth(){
	// int length = this.getHeight();
	// int width = this.getWidth();
	// int tWidth = 0;
	// if (length > width)
	// tWidth = width;
	// else
	// tWidth = length;
	// return tWidth;
	// }

	public void drawChess(Graphics g) {
		for (int i = 0; i < SC.length; i++) {
			for (int j = 0; j < SC[i].length; j++) {
				if (SC[i][j]!= null) {
					SC[i][j].draw(g,SC[i][j].getColor());
				}
			}
		}
	}
}
