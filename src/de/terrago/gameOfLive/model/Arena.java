package de.terrago.gameOfLive.model;

public class Arena {
	private int height;
	private Point[][] points;
	private int width;
	private boolean infinteWorld;

	public boolean isInfinteWorld() {
		return infinteWorld;
	}

	public void setInfinteWorld(boolean infinteWorld) {
		this.infinteWorld = infinteWorld;
	}

	public Arena(int width, int height, boolean infinteWorld) {
		this.infinteWorld = infinteWorld;
		this.width = width;
		this.height = height;
		points = new Point[width][height];
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++) {
				points[i][j] = new Point(i, j);
			}
	}

	public int getHeight() {
		return height;
	}

	public Point getPoint(int x, int y) {
		if (x < width - 1 && x > -1 && y < height - 1 && y > -1)
			return points[x][y];
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
				return points[x][y];

			} else
				return new Point(x, y);
		}
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
