package de.terrago.gol.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Arena implements Serializable {

	private static final long serialVersionUID = 1L;
	private int height;
	private Map<Point.Key, Point> mapPoints;
	private boolean torusWorld;
	private int width;

	public Arena(int width, int height) {
		this.width = width;
		this.height = height;
		mapPoints = new HashMap<>();
	}

	public int getHeight() {
		return height;
	}

	public Map<Point.Key, Point> getMapPoints() {
		return mapPoints;
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

	public void setMapPoints(Map<Point.Key, Point> mapPoints) {
		this.mapPoints = mapPoints;
	}

	public void setTorusWorld(boolean infinteWorld) {
		this.torusWorld = infinteWorld;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}
