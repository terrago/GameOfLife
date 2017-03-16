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

	public Arena getArena() {
		return arena;
	}

	public GameOfLifeService(){
	 rule = new Rule("23/3");
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

	public Arena cloneArena(Arena arena){
		Arena ret = new Arena(arena.getWidth(), arena.getHeight());
		ret.setTorusWorld(arena.isTorusWorld());
		for (Point oldArenaPoint : arena.getPoints()) {
			ret.setPoint(oldArenaPoint.getX(),oldArenaPoint.getY(), oldArenaPoint.isAlife());
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
				boolean isAlife = false;
				int numberOfNeighbors = getNumberofNeighbors(arena, toBeChecked);
				isAlife = rule.getIsALife(toBeChecked.isAlife(), numberOfNeighbors);
				ret.setPoint(toBeChecked.getX(), toBeChecked.getY(), isAlife);
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
