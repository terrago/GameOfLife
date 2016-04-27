package de.terrago.gameOfLive.model;

import java.io.Serializable;

public class Point implements Serializable{

	private static final long serialVersionUID = 1L;
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
