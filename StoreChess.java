package com.antony.wuziqi0908;

import java.awt.Color;
import java.awt.Graphics;

public class StoreChess {
	private int x, y,colorNum;
	private int width;
	private Color c;
	
	
    public int getX(){
    	return x;
    }
    
    public int getY(){
    	return y;
    }
    
    
    public Color getColor(){
    	return c;
    }
    
	StoreChess(int x, int y, int w, Color c) {
		this.x = x;
		this.y = y;
//		System.out.println("x="+ x);
//		System.out.println("y="+ y);
		this.c = c;
		width = w;
	}

	void draw(Graphics g,Color nC) {
		g.setColor(nC);
//		for (int i = 0; i < 15; i++) {
//			double y1 = 100 + (nTWidth - 150) / 14 * i;
//			if (Math.abs(y - y1) < (nTWidth - 150) / 28) {
//				y = y1;
//				break; 
//			}
//		}
//		for (double i = 0; i < 15; i++) {
//			double x1 = 75 + (nTWidth - 150) / 14 * i;
//			if (Math.abs(x - x1) < (nTWidth - 150) / 28) {
//				x = x1;
//				break;
//			}
//		}

//		System.out.println("nTWidth="+nTWidth);
//		System.out.println("FTW="+FTW);
//		System.out.println("proportion = "+proportion);
//		System.out.println("x - d="+(x - d));
//		System.out.println("y - d="+(y - d));
		
//		System.out.println("draw:"+((xx*r)+75 - d) + "," + ((yy*r)+100 - d));
//		System.out.println("r:"+r+", xx:"+xx+", d:"+d);
		g.fillOval(x,y,width,width);
	}
}
