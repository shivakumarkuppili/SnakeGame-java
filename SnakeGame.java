package com.studyopedia;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Random.*;
import javax.swing.*;


public class SnakeGame extends JPanel implements ActionListener,KeyListener{
	private class Tile{
		int x;
		int y;
		Tile(int x,int y){
			this.x=x;
			this.y=y;
		}
		
	}
	Tile snakehead;
	ArrayList<Tile> snakebody;
	
	Tile food;
	Random random;
	Timer gameloop;
	int velocityx;
	int velocityy;
	boolean gameover=false;
        int tilesize=25;
		int boardheight;
		int boardwidth;
		SnakeGame(int bw,int bh){
			this.boardwidth=bw;
			this.boardheight=bh;
			setPreferredSize(new Dimension(this. boardwidth,this.boardheight));
			setBackground(Color.black);
			addKeyListener(this);
			setFocusable(true);
			
			
			snakehead = new Tile(5,5);
			snakebody = new ArrayList<Tile>();
			
			food =new Tile(10,10);
			random = new Random();
			placefood();
			
			velocityx=0;
			velocityy=0;
			
			gameloop = new Timer(100,this);
			gameloop.start();
		}
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			draw(g);
		}
		public void draw(Graphics g) {
//			for(int i=0;i<(boardwidth/tilesize) ;i++) {
//				g.drawLine(i*tilesize, 0, i *tilesize, boardheight);
//				g.drawLine(0, i *tilesize, boardwidth, i *tilesize);
//			}
			
			
			//snake head
			g.setColor(Color.red);
			g.fill3DRect(snakehead.x*tilesize,snakehead.y*tilesize,tilesize, tilesize, true);
			//food
			g.setColor(Color.magenta);
			g.fill3DRect(food.x*tilesize,food.y*tilesize,tilesize, tilesize, true);
			
			//snakebody
			for(int i=0;i<snakebody.size();i++) {
				Tile snakepart =snakebody.get(i);
				g.setColor(Color.yellow);
				g.fill3DRect(snakepart.x*tilesize, snakepart.y*tilesize, tilesize, tilesize,true);
				
				
				
			}
             //score
			
			g.setFont(new Font("arial",Font.PLAIN,16));
			if (gameover) {
				g.setColor(Color.red);
				g.drawString("game over: "+ String.valueOf(snakebody.size()),tilesize-16,tilesize);
			}
			else {
				g.setColor(Color.yellow);
				g.drawString("score:" + String.valueOf(snakebody.size()),tilesize-16,tilesize);
			}
			
		}
		
		public void placefood() {
			food.x=random.nextInt(boardwidth/tilesize);
			food.y=random.nextInt(boardwidth/tilesize);
		}
		public void move() {
			
			//eat food
			if(collision(snakehead,food)) {
				snakebody.add(new Tile(food.x,food.y));
				placefood();
			}
			
			//snake body
			for (int i=snakebody.size()-1;i>=0;i--) {
				Tile snakepart =snakebody.get(i);
			if(i==0) {
				snakepart.x=snakehead.x;
				snakepart.y=snakehead.y;
			}
			else {
				Tile prevsnakepart = snakebody.get(i-1);
				snakepart.x=prevsnakepart.x;
				snakepart.y=prevsnakepart.y;
				
			}
			}
			
			
			
			
			//snake head

			snakehead.x+=velocityx;
			snakehead.y+=velocityy;
			
			//gameover condition
			for(int i=0;i<snakebody.size();i++) {
				Tile snakepart =snakebody.get(i);
				if(collision(snakehead,snakepart)) {
					gameover =true;
				}
			}
			
			if (snakehead.x * tilesize < 0) {
			    snakehead.x = boardwidth / tilesize - 1; // Wrap from left to right
			   
			} else if (snakehead.x * tilesize > boardwidth) {
			    snakehead.x = 0; // Wrap from right to left
			}

			if (snakehead.y * tilesize < 0) {
			    snakehead.y = boardheight / tilesize - 1; // Wrap from top to bottom
			} else if (snakehead.y * tilesize > boardheight) {
			    snakehead.y = 0; // Wrap from bottom to top
			}
		}

		
		public boolean collision(Tile tile1,Tile tile2) {
			return tile1.x==tile2.x && tile1.y ==tile2.y;
		}
		
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			move();
			repaint();
			if(gameover) {
				gameloop.stop();
			}
			
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			if(e.getKeyCode()==KeyEvent.VK_UP && velocityy != 1) {
				velocityx=0;
				velocityy=-1;
			}
			else if (e.getKeyCode()==KeyEvent.VK_DOWN && velocityy !=-1) {
				velocityx=0;
				velocityy=1;
			}
			else if (e.getKeyCode()==KeyEvent.VK_LEFT && velocityx !=1) {
				velocityx=-1;
				velocityy=0;
			}
			else if (e.getKeyCode()==KeyEvent.VK_RIGHT && velocityx !=-1) {
				velocityx=1;
				velocityy=0;
			}
			
		}
		
		//no need
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		

	

	
}
