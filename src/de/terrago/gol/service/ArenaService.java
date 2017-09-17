package de.terrago.gol.service;

import de.terrago.gol.model.Arena;
import de.terrago.gol.model.Point;
import de.terrago.gol.model.Point.Key;

public class ArenaService {

	private static Point getPointFromMap(Arena arena, int x, int y) {
		Point result = new Point(x, y);
		if (arena.getMapPoints().containsKey(new Point.Key(x, y))) {
			result = arena.getMapPoints().get(new Point.Key(x, y));
		}
		return result;
	}

	public static Point getPoint(Arena arena, int x, int y) {
		if (x < arena.getWidth() - 1 && x > -1 && y < arena.getHeight() - 1 && y > -1)
			return getPointFromMap(arena, x, y);
		else {
			if (arena.isTorusWorld()) {
				return getPointFromLimitedTorusWorld(arena, x, y);
			} else
				return getPointFromMap(arena, x, y);
		}
	}

	private static Point getPointFromLimitedTorusWorld(Arena arena, int x, int y) {
		int resultX = x;
		int resultY = y;
		if (x < 0)
			resultX = arena.getWidth() - 1;
		if (x > arena.getWidth() - 1)
			resultX = 0;
		if (y < 0)
			resultY = arena.getHeight() - 1;
		if (y > arena.getHeight() - 1)
			resultY = 0;
		return getPointFromMap(arena, resultX, resultY);
	}

	public static void setPoint(Arena arena, int x, int y, boolean alife) {
		Point point = getPointFromMap(arena, x, y);
		point.setAlife(alife);
		if (point.isAlife()) {
			arena.getMapPoints().put(new Key(x, y), point);
		} else {
			arena.getMapPoints().remove(new Key(x, y));
		}
	}

	public static Arena getMinimalArena(Arena arena) {
		Arena result = null;
		int minX = Integer.MAX_VALUE;
		int maxX = 0;
		int minY = Integer.MAX_VALUE;
		int maxY = 0;
		for (Point point : arena.getPoints()) {
			if (point.getX() >maxX)
				maxX = point.getX();
			if (point.getX() < minX)
				minX = point.getX();
			if (point.getY() > maxY)
				maxY = point.getY();
			if (point.getY() < minY)
				minY = point.getY();
		}
		result = new Arena ((maxX-minX)+1,(maxY-minY)+1);
		result.setTorusWorld(arena.isTorusWorld());
		for (Point point : arena.getPoints()) {
			Point newPoint = new Point(point.getX()-minX, point.getY()-minY);
			newPoint.setAlife(point.isAlife());
			result.getMapPoints().put(new Key(newPoint.getX(), newPoint.getY()), newPoint);
		}
		return result;
	}

}
