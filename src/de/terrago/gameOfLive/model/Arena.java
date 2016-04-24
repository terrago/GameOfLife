package de.terrago.gameOfLive.model;

public class Arena {
	private int height;
	private Point[][] points;
	private int width;

	public Arena(int width, int height) {
		this.width = width;
		this.height = height;
		points = new Point[width][height];
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++) {
				points[i][j] = new Point(i, j);
			}
	}

	public int getHeight() {
		return height;
	}

	public Point getPoint(int x, int y) {
		if (x < width - 1 && x > -1 && y < height - 1 && y > -1)
			return points[x][y];
		else
			return new Point(x, y);
	}

	public Point[][] getPoints() {
		return points;
	}

	public int getWidth() {
		return width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setPoint(int x, int y, boolean alife) {
		points[x][y].setAlife(alife);
	}

	public void setPoints(Point[][] points) {
		this.points = points;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}
