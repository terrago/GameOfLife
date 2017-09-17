package de.terrago.gol.model;

import java.io.Serializable;

public class Point implements Serializable {
	public static class Key implements Serializable {

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
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (alife != other.alife)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (alife ? 1231 : 1237);
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}
}
