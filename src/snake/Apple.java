package snake;

import java.awt.Color;
import java.awt.Graphics;

public class Apple {
	private int x, y;
	private Color color;
	public Apple(int x, int y) {
		this.x = x;
		this.y = y;
		color = Color.red;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public Color getColor() {
		return color;
	}
	
	public void show(Graphics g, int width, int height, int tc) {
		g.setColor(color);
		g.fillRect(x*tc, y*tc, width, height);	
	}
	
	
}
