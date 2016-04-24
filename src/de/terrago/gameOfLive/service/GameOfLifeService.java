package de.terrago.gameOfLive.service;

import de.terrago.gameOfLive.model.Arena;
import de.terrago.gameOfLive.model.Point;

public class GameOfLifeService {
	private Arena arena;
	private int countGeneration = 0;

	public GameOfLifeService() {
		arena = new Arena(150, 150);
	}

	public Arena getArena() {
		return arena;
	}
	
	public int getCountGeneration() {
		return countGeneration;
	}

	private Point[] getNeighbors(Arena arena, Point toBeChecked) {
		Point[] neighbors = new Point[8];
		// add upper row
		neighbors[0] = arena.getPoint(toBeChecked.getX() - 1, toBeChecked.getY() - 1);
		neighbors[1] = arena.getPoint(toBeChecked.getX(), toBeChecked.getY() - 1);
		neighbors[2] = arena.getPoint(toBeChecked.getX() + 1, toBeChecked.getY() - 1);
		// add middle row
		neighbors[3] = arena.getPoint(toBeChecked.getX() - 1, toBeChecked.getY());
		neighbors[4] = arena.getPoint(toBeChecked.getX() + 1, toBeChecked.getY());
		// add lower row
		neighbors[5] = arena.getPoint(toBeChecked.getX() - 1, toBeChecked.getY() + 1);
		neighbors[6] = arena.getPoint(toBeChecked.getX(), toBeChecked.getY() + 1);
		neighbors[7] = arena.getPoint(toBeChecked.getX() + 1, toBeChecked.getY() + 1);
		return neighbors;
	}

	public Arena getNextGeneration(Arena arena) {
		Arena ret = new Arena(arena.getWidth(), arena.getHeight());
		ret.setInfinteWorld(arena.isInfinteWorld());
		for (int i = 0; i < arena.getWidth(); i++)
			for (int j = 0; j < arena.getHeight(); j++) {
				Point toBeChecked = arena.getPoint(i, j);
				boolean isAlife = false;
				int numberOfNeighbors = getNumberofNeighbors(arena, toBeChecked);
				if (toBeChecked.isAlife() && numberOfNeighbors < 4 && numberOfNeighbors > 1)
					isAlife = true;
				if (!toBeChecked.isAlife() && numberOfNeighbors == 3)
					isAlife = true;
				ret.setPoint(i, j, isAlife);
			}
		this.countGeneration++;
		return ret;
	}

	private int getNumberofNeighbors(Arena arena, Point toBeChecked) {
		int numberOfNeighbors = 0;
		Point[] neighbors = getNeighbors(arena, toBeChecked);
		for (int k = 0; k < neighbors.length; k++)
			if (neighbors[k] != null && neighbors[k].isAlife())
				numberOfNeighbors++;
		return numberOfNeighbors;
	}

	public Point[][] getPoints() {
		return arena.getPoints();
	}

	public void setArena(Arena arena) {
		this.arena = arena;
	}
	public void setCountGeneration(int count) {
		this.countGeneration = count;
	}
}
