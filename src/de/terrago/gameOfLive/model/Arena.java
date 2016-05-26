package de.terrago.gameOfLive.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Arena implements Serializable {

	public class Key implements Serializable {

		private static final long serialVersionUID = 1L;
		private final int x;
		private final int y;

		public Key(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (!(o instanceof Key))
				return false;
			Key key = (Key) o;
			return x == key.x && y == key.y;
		}

		@Override
		public int hashCode() {
			int result = x;
			result = 31 * result + y;
			return result;
		}

	}

	private static final long serialVersionUID = 1L;
	private int height;
	Map<Key, Point> mapPoints;
	private boolean torusWorld;
	private int width;

	public Arena(int width, int height) {
		this.width = width;
		this.height = height;
		mapPoints = new HashMap<Key, Point>();
	}

	private Point _getPoint(int x, int y) {
		Point result = new Point(x, y);
		if (mapPoints.containsKey(new Key(x, y))) {
			result = mapPoints.get(new Key(x, y));
		}
		return result;
	}

	public int getHeight() {
		return height;
	}

	public Point getPoint(int x, int y) {
		if (x < width - 1 && x > -1 && y < height - 1 && y > -1)
			return _getPoint(x, y);
		else {
			if (torusWorld) {
				if (x < 0)
					x = x + (width - 1);
				if (x >= width - 1)
					x = x - (width - 1);
				if (y < 0)
					y = y + (height - 1);
				if (y >= height - 1)
					y = y - (height - 1);
				return _getPoint(x, y);

			} else
				return _getPoint(x, y);
		}
	}

	public Set<Point> getPoints() {
		Set<Point> points = new HashSet<>();
		for (Point point : mapPoints.values())
			points.add(point);

		return points;
	}

	public int getWidth() {
		return width;
	}

	public boolean isTorusWorld() {
		return torusWorld;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setPoint(int x, int y, boolean alife) {
		Point point = _getPoint(x, y);
		point.setAlife(alife);
		if (point.isAlife()) {
			mapPoints.put(new Key(x, y), point);
		} else {
			mapPoints.remove(new Key(x, y));
		}
	}

	public void setTorusWorld(boolean infinteWorld) {
		this.torusWorld = infinteWorld;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}
