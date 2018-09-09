package com.antony.wuziqi0908;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ChessBoard {

	public static void main(String[] args) {
		ReFrame ChessBoard = new ReFrame();
		ChessBoard.setTitle("五子棋");
		ChessBoard.setSize(910, 910);
		ChessBoard.setDefaultCloseOperation(3);
		FlowLayout flow = new FlowLayout();
		BorderLayout boder=new BorderLayout();
		ChessBoard.setLayout(boder);
		JPanel p=new JPanel();
		p.setLayout(flow);
		Dimension d1 = new Dimension(60, 300);
		JLabel jsl_pla = new JLabel();
		jsl_pla.setPreferredSize(d1);
		JButton jsb_Whi = new JButton("玩家先下");
		JButton jsb_Bla = new JButton("机器先下");
		JButton jsb_Rob = new JButton("双人对战");
		JButton jsb_Ret = new JButton("悔棋");
		JButton jsb_Exi = new JButton("退出");
		Dimension d2 = new Dimension(60, 910);
		p.setPreferredSize(d2);
		p.add(jsl_pla);
		p.add(jsb_Whi);
		p.add(jsb_Bla);
		p.add(jsb_Rob);
		p.add(jsb_Ret);
		p.add(jsb_Exi);
		ChessBoard.getContentPane().add("East",p);
//		ChessBoard.getContentPane().add("East",jsb_Per);
//		ChessBoard.getContentPane().add("East",jsb_Rob);
//		ChessBoard.getContentPane().add("East",jsb_Ret);
//		ChessBoard.getContentPane().add("East",jsb_Exi);
		Color boardColor = new Color(225, 176, 63);
		ChessBoard.getContentPane().setBackground(boardColor);
		p.setBackground(boardColor);
		ChessBoard.setLocationRelativeTo(null);
	    ChessBoard.setResizable(false);
		ChessBoard.setVisible(true);
		Graphics g = ChessBoard.getGraphics();
		DrawListener dl = new DrawListener(ChessBoard.SC,ChessBoard.SCF, g, ChessBoard);
		jsb_Whi.addActionListener(dl);
		jsb_Bla.addActionListener(dl);
		jsb_Rob.addActionListener(dl);
		jsb_Ret.addActionListener(dl);
		jsb_Exi.addActionListener(dl);
		ChessBoard.addMouseListener(dl);
	}
}
