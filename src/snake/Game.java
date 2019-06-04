package snake;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Game extends Canvas{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4145096979527517819L;
	
	//WINDOW PROPERTIES
	private final static int window = 600;
	protected final static int tileCount = 40;
	private final int gridSize = window/tileCount;
	
	//GAME VARIABLES
	private Random rnd;
	private Snake snake;
	private Apple apple;
	private ArrayList<Border> borders;
	//CONTROL VARIABLES
	private int lastMoveX, lastMoveY;
	public Game() {
		rnd = new Random();
		snake = new Snake(tileCount/2, tileCount/2);
		apple = new Apple(rnd.nextInt(tileCount), rnd.nextInt(tileCount));
		lastMoveX = lastMoveY = 0;
		borders = new ArrayList<Border>();
		borders.add(new Border(10, 0, 10, Border.DIR_DOWN));
		addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyCode() == KeyEvent.VK_UP && lastMoveY != Snake.DOWN) {
					snake.setAccY(Snake.UP);
					lastMoveY = Snake.UP;
					lastMoveX = 0;
					snake.setAccX(0);
				}
				else if(arg0.getKeyCode() == KeyEvent.VK_DOWN && lastMoveY != Snake.UP) {
					snake.setAccY(Snake.DOWN);
					lastMoveY = Snake.DOWN;
					lastMoveX = 0;
					snake.setAccX(0);
				}
				else if(arg0.getKeyCode() == KeyEvent.VK_LEFT && lastMoveX != Snake.RIGHT) {
					snake.setAccX(Snake.LEFT);
					lastMoveX = Snake.LEFT;
					lastMoveY = 0;
					snake.setAccY(0);
				}
				else if(arg0.getKeyCode() == KeyEvent.VK_RIGHT && lastMoveX != Snake.LEFT) {
					snake.setAccX(Snake.RIGHT);
					lastMoveX = Snake.RIGHT;
					lastMoveY = 0;
					snake.setAccY(0);
				}
				repaint();
				
			}
		});
		//Timer thread to repaint canvas
		new Thread() {
			@Override
			public void run() {
				while(true) {
					try {
						int sleep_time = snake.getSleepTime();
						Thread.sleep(sleep_time);
						repaint();
					}
					catch(InterruptedException e) {}
				}
			}
		}.start();
	}
	
	@Override
	public void paint(Graphics g) {
		//Update snake
		if(snake.update(apple, borders)) {
			//To ease garbage collection.
			apple = null;
			apple = new Apple(rnd.nextInt(tileCount), rnd.nextInt(tileCount));
		}
		//Set window properties
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getSize().width, this.getSize().height);

		//Show borders
		for(Border b : borders) {
			b.show(g, gridSize-2, gridSize-2, gridSize);
		}
		//Show Snake
		snake.show(g, gridSize-2, gridSize-2, gridSize);
		//Show Apple
		apple.show(g, gridSize-2, gridSize-2, gridSize);

	}
	
	
	//Main Function
	public static void main(String[] args) {
		JFrame game = new JFrame();
		game.setTitle("Snake");
		game.setResizable(false);
		game.setSize(window+20, window+50);
	    game.setLocationRelativeTo(null);
	    game.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	    
	    game.add(new Game());
	    
	    game.setVisible(true);
	}
}
