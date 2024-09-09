package com.studyopedia;
import javax.swing.*;

public class App {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		int boardwidth=600;
		int boardheight=boardwidth;
		
		JFrame frame=new JFrame("snake");
		frame.setVisible(true);
		frame.setSize(boardwidth,boardheight);
		 
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SnakeGame sg = new SnakeGame(boardwidth,boardheight);
		frame.add(sg);
		frame.pack();
		sg.requestFocus();
		

	}

}
