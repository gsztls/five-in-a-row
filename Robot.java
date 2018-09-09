package com.antony.wuziqi0908;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Random;

public class Robot {
	private StoreChess[][] Chess;
	private int[][] chess = new int[15][15];
	private int[][] chessValue = new int[15][15];
	private int x,y;
	
	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}
	
	Robot(StoreChess[][] chess) {
		Chess = chess;
	}
	
	public void setChess(){
		for(int i=0;i<Chess.length;i++){
			for(int j = 0;j<Chess[i].length;j++){
				chess[i][j]=0;
				if(Chess[i][j]!=null){
					if(Chess[i][j].getColor()==Color.BLACK){
						chess[i][j]=1;
					}
					else if(Chess[i][j].getColor()==Color.WHITE){
						chess[i][j]=2;
					}
				}
			}
		}
	}

	private HashMap<String, Integer> hm = new HashMap<String, Integer>();

	public void setValue() {
		hm.put("1", 20);
		hm.put("11", 200);
		hm.put("111", 2000);
		hm.put("1111", 3000);
		hm.put("12", 10);
		hm.put("112", 100);
		hm.put("1112", 1000);
		hm.put("11112", 2000);
	}

	public int[] firstPlay(){
		int[] point = new int[2];
		Random rand = new Random();
		int x = 405+rand.nextInt(100);
		int y = 405+rand.nextInt(100);
		point[0]=x;
		point[1]=y;
		return point;
	}
	
	public void clearValue(){
		for(int i=0;i<chessValue.length;i++){
			for(int j=0;j<chessValue[i].length;j++){
				chessValue[i][j]=0;
			}
		}
	}
	
	public void AI(int plaColor) {
		setChess();
		setValue();
		for (int i = 0; i < chess.length; i++) {
			for (int j = 0; j < chess[i].length; j++) {
				if (chess[i][j] == 0) {
					String code = "";
					int color = 0;
					// ����
					for (int k = i + 1; k < chess.length; k++) {
						if (chess[k][j] == 0) {
							break;
						} else {
							if (color == plaColor) { // �ұߵ�һ������
								color = chess[k][j]; // ������ɫ
								code += 1; // �������
							} else if (chess[k][j] == plaColor) { // �ұߵڶ�����..ͬ��ɫ����
								code += 1; // �������
							} else { // �ұ߲�ͬ��ɫ
								code += 2; // �������
								break;
							}
						}
					}
					// ����codeȡ��hm��Ӧ��Ȩֵ
					Integer value = hm.get(code);
					if (value != null) {
						chessValue[i][j] += value;// Ȩֵ�ۼ�
					}

					// ����
					for (int k = i - 1; k >= 0; k--) {
						if (chess[k][j] == 0) {
							break;
						} else {
							if (color == plaColor) { 
								color = chess[k][j]; // ������ɫ
								code += 1; // �������
							} else if (chess[k][j] == plaColor) {
								code += 1; // �������
							} else { 
								code += 2; // �������
								break;
							}
						}
					}
					// ����codeȡ��hm��Ӧ��Ȩֵ
					value = hm.get(code);
					if (value != null) {
						chessValue[i][j] += value; // Ȩֵ�ۼ�
					}
					
					// ����
					for (int k = j + 1; k < chess[i].length; k++) {
						if (chess[i][k] == 0) {
							break;
						} else {
							if (color == plaColor) { 
								color = chess[i][k]; // ������ɫ
								code += 1; // �������
							} else if (chess[i][k] == plaColor) { 
								code += 1; // �������
							} else {
								code += 2; // �������
								break;
							}
						}
					}
					// ����codeȡ��hm��Ӧ��Ȩֵ
					value = hm.get(code);
					if (value != null) {
						chessValue[i][j] += value; // Ȩֵ�ۼ�
					}
					
					// ����
					for (int k = j - 1; k > 0; k--) {
						if (chess[i][k] == 0) {
							break;
						} else {
							if (color == plaColor) {
								color = chess[i][k]; // ������ɫ
								code += 1; // �������
							} else if (chess[i][k] == plaColor) { 
								code += 1; // �������
							} else { 
								code += 2; // �������
								break;
							}
						}
					}
					// ����codeȡ��hm��Ӧ��Ȩֵ
					value = hm.get(code);
					if (value != null) {
						chessValue[i][j] += value; // Ȩֵ�ۼ�
					}
					// ��б����
					for (int k = 1; i-k >0&&j-k>0 ; k++) {
						if (chess[i-k][j-k] == 0) {
							break;
						} else {
							if (color == plaColor) { 
								color = chess[i-k][j-k]; // ������ɫ
								code += 1; // �������
							} else if (chess[i-k][j-k] == plaColor) { 
								code += 1; // �������
							} else {
								code += 2; // �������
								break;
							}
						}
					}
					// ����codeȡ��hm��Ӧ��Ȩֵ
					value = hm.get(code);
					if (value != null) {
						chessValue[i][j] += value; // Ȩֵ�ۼ�
					}
					// ��б����
					for (int k = 1; i+k<chess.length&&j+k<chess[i].length ; k++) {
						if (chess[i+k][j+k] == 0) {
							break;
						} else {
							if (color == plaColor) {
								color = chess[i+k][j+k]; // ������ɫ
								code += 1; // �������
							} else if (chess[i+k][j+k] == plaColor) { 
								code += 1; // �������
							} else { 
								code += 2; // �������
								break;
							}
						}
					}
					// ����codeȡ��hm��Ӧ��Ȩֵ
					value = hm.get(code);
					if (value != null) {
						chessValue[i][j] += value; // Ȩֵ�ۼ�
					}
					// ��б����
					for (int k = 1; j-k>0&&i+k<chess[i].length ; k++) {
						if (chess[i+k][j-k] == 0) {
							break;
						} else {
							if (color == plaColor) {
								color = chess[i+k][j-k]; // ������ɫ
								code += 1; // �������
							} else if (chess[i+k][j-k] == plaColor) {
								code += 1; // �������
							} else { 
								code += 2; // �������
								break;
							}
						}
					}
					// ����codeȡ��hm��Ӧ��Ȩֵ
					value = hm.get(code);
					if (value != null) {
						chessValue[i][j] += value; // Ȩֵ�ۼ�
					}
					// ��б����
					for (int k = 1; j+k<chess.length&&i-k>0; k++) {
						if (chess[i-k][j+k] == 0) {
							break;
						} else {
							if (color == plaColor) {
								color = chess[i-k][j+k]; // ������ɫ
								code += 1; // �������
							} else if (chess[i-k][j+k] == plaColor) {
								code += 1; // �������
							} else {
								code += 2; // �������
								break;
							}
						}
					}
					// ����codeȡ��hm��Ӧ��Ȩֵ
					value = hm.get(code);
					if (value != null) {
						chessValue[i][j] += value; // Ȩֵ�ۼ�
					}
				}
				else{
					chessValue[i][j] = 0;
				}
			}
			
		}
		int max=0;
		for(int i = 0; i<chessValue.length-1;i++){
			for(int j=0; j<chessValue[i].length-1; j++){
				if(max<chessValue[i][j]){
					max = chessValue[i][j];
					x=i;
					y=j;
				}
			}
		}
		//chess[x][y].draw(g, plaColor);
		for(int i = 0 ; i<chessValue.length;i++){
			for(int j = 0; j<chessValue[i].length;j++){
				System.out.print("  "+chessValue[i][j]);
			}
			System.out.println(' ');
		}
	}
	

}
