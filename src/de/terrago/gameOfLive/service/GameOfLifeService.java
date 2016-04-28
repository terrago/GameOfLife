package de.terrago.gameOfLive.service;

import java.util.HashSet;
import java.util.Set;

import de.terrago.gameOfLive.model.Arena;
import de.terrago.gameOfLive.model.Point;

public class GameOfLifeService {
	private Arena arena;
	private int countGeneration = 0;

	public GameOfLifeService() {
		
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
		for (Point oldArenaPoint : arena.getPoints()) {
			Set<Point> allPossiblePoints = new HashSet<>();
			allPossiblePoints.add(oldArenaPoint);
			for (Point addPoint : getNeighbors(arena, oldArenaPoint)) {
				allPossiblePoints.add(addPoint);
			}
			for (Point toBeChecked : allPossiblePoints) {
				boolean isAlife = false;
				int numberOfNeighbors = getNumberofNeighbors(arena, toBeChecked);
				if (toBeChecked.isAlife() && numberOfNeighbors < 4 && numberOfNeighbors > 1)
					isAlife = true;
				if (!toBeChecked.isAlife() && numberOfNeighbors == 3)
					isAlife = true;
				ret.setPoint(toBeChecked.getX(), toBeChecked.getY(), isAlife);
			}
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

	public Set<Point> getPoints() {
		return arena.getPoints();
	}

	public void setArena(Arena arena) {
		this.arena = arena;
	}

	public void setCountGeneration(int count) {
		this.countGeneration = count;
	}
}
