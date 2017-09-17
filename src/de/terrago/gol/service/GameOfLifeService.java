package de.terrago.gol.service;

import java.util.HashSet;
import java.util.Set;

import de.terrago.gol.model.Arena;
import de.terrago.gol.model.Point;
import de.terrago.gol.model.Rule;

public class GameOfLifeService {
	private Arena arena;
	private Rule rule;
	private int countGeneration = 0;

	public GameOfLifeService() {
		rule = new Rule("23/3");

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
		neighbors[0] = ArenaService.getPoint(arena, toBeChecked.getX() - 1, toBeChecked.getY() - 1);
		neighbors[1] = ArenaService.getPoint(arena, toBeChecked.getX(), toBeChecked.getY() - 1);
		neighbors[2] = ArenaService.getPoint(arena, toBeChecked.getX() + 1, toBeChecked.getY() - 1);
		// add middle row
		neighbors[3] = ArenaService.getPoint(arena, toBeChecked.getX() - 1, toBeChecked.getY());
		neighbors[4] = ArenaService.getPoint(arena, toBeChecked.getX() + 1, toBeChecked.getY());
		// add lower row
		neighbors[5] = ArenaService.getPoint(arena, toBeChecked.getX() - 1, toBeChecked.getY() + 1);
		neighbors[6] = ArenaService.getPoint(arena, toBeChecked.getX(), toBeChecked.getY() + 1);
		neighbors[7] = ArenaService.getPoint(arena, toBeChecked.getX() + 1, toBeChecked.getY() +1);
		return neighbors;
	}

	public Arena cloneArena(Arena arena) {
		Arena ret = new Arena(arena.getWidth(), arena.getHeight());
		ret.setTorusWorld(arena.isTorusWorld());
		for (Point oldArenaPoint : arena.getPoints()) {
			ArenaService.setPoint(ret, oldArenaPoint.getX(), oldArenaPoint.getY(), oldArenaPoint.isAlife());
		}
		return ret;

	}

	public Arena getNextGeneration(Arena arena) {
		Arena ret = new Arena(arena.getWidth(), arena.getHeight());
		ret.setTorusWorld(arena.isTorusWorld());
		for (Point oldArenaPoint : arena.getPoints()) {
			Set<Point> allPossiblePoints = new HashSet<>();
			allPossiblePoints.add(oldArenaPoint);
			for (Point addPoint : getNeighbors(arena, oldArenaPoint)) {
				allPossiblePoints.add(addPoint);
			}
			for (Point toBeChecked : allPossiblePoints) {
				int numberOfNeighbors = getNumberofNeighbors(arena, toBeChecked);
				boolean isAlife = rule.getIsALife(toBeChecked.isAlife(), numberOfNeighbors);
				ArenaService.setPoint(ret, toBeChecked.getX(), toBeChecked.getY(), isAlife);
			}
		}
		this.countGeneration++;
		return ret;
	}

	public Rule getRule() {
		return rule;
	}

	public void setRule(Rule rule) {
		this.rule = rule;
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
