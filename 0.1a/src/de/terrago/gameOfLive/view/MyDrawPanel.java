package de.terrago.gameOfLive.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

import de.terrago.gameOfLive.model.Point;

public class MyDrawPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private Set<Point> points = new HashSet<Point>();

	public MyDrawPanel(int width, int height) {
		super.setPreferredSize(new Dimension(width, height));
		super.setLayout(new BorderLayout());
	}
	public Set<Point> getPoints() {
		return points;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.black);
		for (Point point : points) {
			if (point.isAlife())
				g.drawRect((int) point.getX(), (int) point.getY(), 1, 1);
		}
	}
	public void setPoints(Set<Point> points) {
		this.points = points;
	}
}
