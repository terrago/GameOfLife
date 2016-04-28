package de.terrago.gameOfLive.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Arena implements Serializable {

	private static final long serialVersionUID = 1L;
	private int height;
	private boolean infinteWorld;
	private Set<Point> points;

	private int width;

	public Arena(int width, int height) {
		this.width = width;
		this.height = height;
		points = new HashSet<Point>();

	}

	public int getHeight() {
		return height;
	}

	public Point getPoint(int x, int y) {
		if (x < width - 1 && x > -1 && y < height - 1 && y > -1)
			return _getPoint(x, y);
		else {
			if (infinteWorld) {
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

	private Point _getPoint(int x, int y) {
		Point result = new Point(x, y);
		for (Point point : points) {
			if (point.getX() == x && point.getY() == y) {
				result = point;
			}
		}
		return result;
	}

	public int getWidth() {
		return width;
	}

	public boolean isInfinteWorld() {
		return infinteWorld;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setInfinteWorld(boolean infinteWorld) {
		this.infinteWorld = infinteWorld;
	}

	public void setPoint(int x, int y, boolean alife) {
		Point point = _getPoint(x, y);
		point.setAlife(alife);
		if (point.isAlife()) {
			points.add(point);
		}
	}

	public Set<Point> getPoints() {
		return points;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}
