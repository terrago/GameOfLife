package de.terrago.gameOfLive.service;

import java.awt.event.ActionListener;

import javax.swing.Timer;

import de.terrago.gameOfLive.model.Arena;
import de.terrago.gameOfLive.model.Point;
import de.terrago.gameOfLive.view.MyJFrame;
import de.terrago.gameOfLive.view.TimerListener;

public class GameOfLifeService {

	int delay = 100; // milliseconds
	private MyJFrame myJFrame;
	//private Point[][] points;
	private Arena arena;
	private Timer timer;

	public GameOfLifeService(Arena arena, MyJFrame myJFrame) {
		this.arena = arena;
		this.myJFrame = myJFrame;
		ActionListener timerListener = new TimerListener(this);
		timer = new Timer(delay, timerListener);
	}

	public Arena getNextGeneration(Arena arena) {

		// initialize empty return array of same size
		//Point[][] ret = new Point[points.length][points[0].length];
		Arena ret = new Arena(arena.getWidth(), arena.getHeight());
		
		for (int i = 0; i < arena.getWidth(); i++)
			for (int j = 0; j < arena.getHeight(); j++) {
				int numberOfNeighbors = 0;
				Point toBeChecked = arena.getPoint(i, j);
				Point newPoint = new Point(i, j);
				// Create array neighbors
				Point[] neighbors = new Point[8];
				// add upper row
				neighbors[0] =  arena.getPoint(toBeChecked.getX() - 1, toBeChecked.getY() - 1);
				neighbors[1] =  arena.getPoint(toBeChecked.getX(), toBeChecked.getY() - 1); //points[toBeChecked.getX()][toBeChecked.getY() - 1];
				neighbors[2] = arena.getPoint(toBeChecked.getX() + 1,toBeChecked.getY() - 1);
				// add middle row
				neighbors[3] = arena.getPoint(toBeChecked.getX() - 1,toBeChecked.getY());
				neighbors[4] = arena.getPoint(toBeChecked.getX() + 1,toBeChecked.getY());
				// add lower row
				neighbors[5] = arena.getPoint(toBeChecked.getX() - 1,toBeChecked.getY() + 1);
				neighbors[6] = arena.getPoint(toBeChecked.getX(),toBeChecked.getY() + 1);
				neighbors[7] = arena.getPoint(toBeChecked.getX() + 1,toBeChecked.getY() + 1);

				for (int k = 0; k < neighbors.length; k++)
					if (neighbors[k] != null && neighbors[k].isAlife())
						numberOfNeighbors++;
				if (toBeChecked.isAlife() && numberOfNeighbors < 4 && numberOfNeighbors > 1)
					newPoint.setAlife(true);
				if (!toBeChecked.isAlife() && numberOfNeighbors == 3)
					newPoint.setAlife(true);
				
				//ret[newPoint.getX()][newPoint.getY()] = newPoint;
				ret.setPoint(newPoint.getX(), newPoint.getY(), newPoint.isAlife());
			}
		return ret;
	}

	public Arena getArena() {
		return arena;
	}

	public Point[][] getPoints() {
		return arena.getPoints();
	}

//	public void setPoints(Point[][] points) {
//		this.points = points;
//		myJFrame.getDrawPanel().getPoints().clear();
//		for (int i = 0; i < myJFrame.getHeight(); i++)
//			for (int j = 0; j < myJFrame.getWidth(); j++) {
//				myJFrame.getDrawPanel().getPoints().add(points[i][j]);
//			}
//		myJFrame.getDrawPanel().repaint();
//		myJFrame.getbSouth().setText("Schritt:" + myJFrame.getCount());
//		myJFrame.setCount(myJFrame.getCount()+1);
//	}
	
	public void setArena(Arena arena){
		this.arena = arena;
		myJFrame.getDrawPanel().getPoints().clear();
		for (int i = 0; i < arena.getHeight(); i++)
			for (int j = 0; j < arena.getWidth(); j++) {
				myJFrame.getDrawPanel().getPoints().add(arena.getPoint(i, j));
			}
		myJFrame.getDrawPanel().repaint();
		myJFrame.getbSouth().setText("Schritt:" + myJFrame.getCount());
		myJFrame.setCount(myJFrame.getCount()+1);
	}
	

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}
}
