package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Snake {
	//STATIC CONSTANTS 
	//X-Axis movement acceleration values
	protected static final int LEFT = -1;
	protected static final int RIGHT = 1;
	//Y-Axis movement acceleration values
	protected static final int UP = -1;
	protected static final int DOWN = 1;
	//SNAKE VARIABLES
	private int x, y, tailLength;
	//Accelation values for snake head.
	private int acc_x, acc_y;
	private Color tailColor, headColor;
	private ArrayList<Point> tail;
	private int speed;
	
	//CONSTRUCTOR
	public Snake(int x, int y) {
		this.x = x;
		this.y = y;
		tailColor = Color.green;
		headColor = Color.cyan;
		tail = new ArrayList<Point>();
		tailLength = 5;
		speed = 50;
	}
	//GETTERS SETTERS
	public void setAccX(int acc_x) {
		this.acc_x = acc_x;
	}
	
	public void setAccY(int acc_y) {
		this.acc_y = acc_y;
	}

	// SHOW FUNCTION
	public void show(Graphics g, int width, int height, int tc) {
		g.setColor(tailColor);
		for(Point p : tail) {
			//System.out.println("Cx: " + c.x + " Cy: "+ c.y);
			g.fillRect(p.x*tc, p.y*tc, width, height);
		}
		g.setColor(headColor);
		g.fillRect(tail.get(0).x*tc, tail.get(0).y*tc, width, height);
	}
	
	// CLASS FUNCTIONS
	public int getSleepTime() {
		/*
		if(tailLength >= 5 && tailLength <= 8) {
			return 150;
		}
		else if(tailLength > 8 && tailLength <= 14) {
			return 90;
		}
		else {
			return 65;
		}
		*/
		
		return 200 - speed;
	}
	
	
	/**
	 * Check snake tail if snake is bitting its own tail and check for the tail length. 
	 */
	private void checkTail(ArrayList<Border> borders) {
		//Check if snake eat itself first. If so, shorten the tail length.
		for(Point p : tail) {
			if(p.x == x && p.y == y) {
				tailLength = 5;
				speed = 50;
				break;
			}
		}
		//Change the effect
		for(Border b : borders) {
			if(b.hits(x, y)) {
				tailLength = 5;
				speed = 50;
				break;
			}
		}
		while(tail.size() > tailLength) {
			//Remove from last index while tail trail is the same as tail length.
			tail.remove(tail.size()-1);
		}
	}
	/**
	 * Update snake's position and tail length.
	 * @param a An apple to check if snake has eaten that apple
	 * @return True if snake has eaten the apple, false if not.
	 */
	public boolean update(Apple a, ArrayList<Border> borders) {
		boolean isAppleEaten = false;
		x += acc_x;
		y += acc_y;
		//Boundary check. Snake will wrap up in case of boundary collision.
		if(x < 0) {
			x = Game.tileCount-1;
		}
		else if(x >= Game.tileCount) {
			x = 0;
		}
		if(y < 0) {
			y = Game.tileCount-1;
		}
		else if(y >= Game.tileCount) {
			y = 0;
		}
		//Check if snake has eaten the apple or not.
		if(x == a.getX() && y == a.getY()) {
			isAppleEaten = true;
			tailLength++;
			speed = tailLength > 15 ? speed : speed+8;
		}
		//Check tail
		checkTail(borders);
		//Add current position to tail
		tail.add(0, new Point(x, y));
		//When ever frame snake moves to the given direction, we push the head position to 
		//first index of tail array. In every iteration tail loses its last point that is longer than the
		//tail length. Doing this instead of keeping track of every point's direction is using less memory
		//and overall performs better. Altough, we are not in  80's anymore even an Arduino Nano can run this
		//without any problems.
		return isAppleEaten;
	}
}
