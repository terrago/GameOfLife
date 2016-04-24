package de.terrago.gameOfLive.model;

public class Point {
	private boolean alife = false;
	private int x;
	private int y;
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public boolean isAlife() {
		return alife;
	}
	public void setAlife(boolean alife) {
		this.alife = alife;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
}
