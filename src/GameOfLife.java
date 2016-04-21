import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.swing.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.UIManager;

public class GameOfLife extends JFrame {

	private static final long serialVersionUID = 1L;
	private int count = 0;
	private int height = 200;
	private int width = 200;
	private JButton bSouth;
	private JPanel panel1;
	private DrawPanel panel3;
	private Point[][] points;
	private JSplitPane splitPaneH;
	private JSplitPane splitPaneV;

	private Timer timer;
	public GameOfLife() {
		setTitle("Game of Life");
		setBackground(Color.gray);
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		getContentPane().add(topPanel);
		// Create the panels
		createPanel3();
		createPanel1();
		
		// Create a splitter pane
		splitPaneV = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		topPanel.add(splitPaneV, BorderLayout.CENTER);
		splitPaneH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPaneH.setLeftComponent(panel1);
		splitPaneV.setLeftComponent(splitPaneH);
		splitPaneV.setRightComponent(panel3);
		// Initialize Points
		points = new Point[width][height];
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++) {
				points[i][j] = new Point(i, j);
			}

		// Blinker
		// points[100][100].setAlife(true);
		// points[100][101].setAlife(true);
		// points[100][102].setAlife(true);

		points[100][100].setAlife(true);
		points[100][101].setAlife(true);
		points[101][99].setAlife(true);
		points[101][100].setAlife(true);
		points[102][100].setAlife(true);
	}
	public  Point[][] getNextGeneration(Point[][] points) {

		// initialize empty return array of same size
		Point[][] ret = new Point[points.length][points[0].length];
		for (int i = 0; i < points.length; i++)
			for (int j = 0; j < points[0].length; j++) {
				ret[i][j] = new Point(i, j);
			}

		for (int i = 0; i < points.length; i++)
			for (int j = 0; j < points[0].length; j++) {
				int numberOfNeighbors = 0;
				Point toBeChecked = points[i][j];
				Point newPoint = new Point(i, j);
				// Create array neighbors
				Point[] neighbors = new Point[8];
				// add upper row
				if (toBeChecked.getY() > 1) {
					if (toBeChecked.getX() > 1)
						neighbors[0] = points[toBeChecked.getX() - 1][toBeChecked.getY() - 1];
					neighbors[1] = points[toBeChecked.getX()][toBeChecked.getY() - 1];
					if (toBeChecked.getX() < points[0].length - 1)
						neighbors[2] = points[toBeChecked.getX() + 1][toBeChecked.getY() - 1];
				}
				// add middle row
				if (toBeChecked.getX() > 1)
					neighbors[3] = points[toBeChecked.getX() - 1][toBeChecked.getY()];
				if (toBeChecked.getX() < points[0].length - 1)
					neighbors[4] = points[toBeChecked.getX() + 1][toBeChecked.getY()];
				// add lower row
				if (toBeChecked.getY() < points.length - 1) {
					if (toBeChecked.getX() > 1)
						neighbors[5] = points[toBeChecked.getX() - 1][toBeChecked.getY() + 1];
					neighbors[6] = points[toBeChecked.getX()][toBeChecked.getY() + 1];
					if (toBeChecked.getX() < points[0].length - 1)
						neighbors[7] = points[toBeChecked.getX() + 1][toBeChecked.getY() + 1];
				}
				for (int k = 0; k < neighbors.length; k++)
					if (neighbors[k] != null && neighbors[k].isAlife())
						numberOfNeighbors++;
				if (toBeChecked.isAlife() && numberOfNeighbors < 4 && numberOfNeighbors > 1)
					newPoint.setAlife(true);
				if (!toBeChecked.isAlife() && numberOfNeighbors == 3)
					newPoint.setAlife(true);
				ret[newPoint.getX()][newPoint.getY()] = newPoint;
			}
		return ret;
	}
	public static void main(String args[]) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception evt) {
		}
		// Create an instance of the test application
		GameOfLife mainFrame = new GameOfLife();
		mainFrame.pack();
		mainFrame.setVisible(true);
	}
	public void createPanel1() {
		panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		// Add buttons
		JButton bNorth = new JButton("Start");
		panel1.add(bNorth, BorderLayout.NORTH);
		bNorth.addActionListener(new BNorthClickListener(panel3));

		bSouth = new JButton(" ");
		panel1.add(bSouth, BorderLayout.SOUTH);
		bSouth.addActionListener(new BSouthClickListener());
		
		
		// panel1.add(new JButton("South"), BorderLayout.SOUTH);
		// panel1.add(new JButton("East"), BorderLayout.EAST);
		// panel1.add(new JButton("West"), BorderLayout.WEST);
		// panel1.add(new JButton("Center"), BorderLayout.CENTER);

	}

	public DrawPanel createPanel3() {
		panel3 = new DrawPanel(width, height);
		panel3.setLayout(new BorderLayout());
		panel3.setPreferredSize(new Dimension(width, height));
		return panel3;
	}
	public class BSouthClickListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			timer.stop();	
		}
		
	}
	
	
	public class BNorthClickListener implements ActionListener {
		DrawPanel drawPanel;
		Random random = new Random();

		public BNorthClickListener(DrawPanel drawPanel) {
			this.drawPanel = drawPanel;
		}

		public void actionPerformed(ActionEvent ae) {
			int delay = 100; // milliseconds
			ActionListener taskPerformer = new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					points = getNextGeneration(points);
					drawPanel.getPoints().clear();
					for (int i = 0; i < height; i++)
						for (int j = 0; j < width; j++) {
							drawPanel.getPoints().add(points[i][j]);
						}
					drawPanel.repaint();
					bSouth.setText("Schritt:" + count++);
				}
			};
			timer = new Timer(delay, taskPerformer);
			timer.start();
		}
	}
	
	public class DrawPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		private Set<Point> points = new HashSet<Point>();

		public DrawPanel(int width, int height) {
			super.setPreferredSize(new Dimension(width, height));
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

}
