package snake;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

public class Border {
	/*
	 * Border direction variables 
	 */
	protected static final int DIR_LEFT = 1;
	protected static final int DIR_RIGHT = 2;
	protected static final int DIR_UP = 3;
	protected static final int DIR_DOWN = 4;
	
	private ArrayList<Point> border;
	public Border(int x, int y, int length, int direction) {
		border = new ArrayList<Point>();
		int x_acc = direction == DIR_LEFT ? -1 : direction == DIR_RIGHT ? 1 : 0;
		int y_acc = direction == DIR_UP ? -1 : direction == DIR_DOWN ? 1 : 0;
	
		for (int i = 0; i < length; i++) {
			border.add(new Point(x,y));
			x += x_acc;
			y += y_acc;
		}	
	}
	
	public void show(Graphics g, int width, int height, int tc) {
		g.setColor(Color.LIGHT_GRAY);
		for(Point p : border) {
			g.fillRect(p.x*tc, p.y*tc, width, height);	
		}
	}
	
	public boolean hits(int x, int y) {
		for(Point p : border) {
			if(p.x == x && p.y == y) {
				return true;
			}
		}
		return false;
	}
	
}
