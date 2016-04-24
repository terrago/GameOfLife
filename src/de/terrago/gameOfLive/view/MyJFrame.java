package de.terrago.gameOfLive.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.Timer;

import de.terrago.gameOfLive.service.GameOfLifeService;

public class MyJFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JButton bEast;
	private JButton bNorth;
	private JButton bSouth;
	private JButton bWest;
	private JComboBox comboBox;
	private JSlider jSlider;
	private JPanel panel1;
	private JSplitPane splitPaneH;
	private JSplitPane splitPaneV;

	private int count = 0;
	private MyDrawPanel drawPanel;

	private GameOfLifeService gameOfLifeService;

	private Timer timer;

	public MyJFrame() {

	}

	public void createPanel1() {
		panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		// Add buttons
		bNorth = new JButton("Start");
		panel1.add(bNorth, BorderLayout.NORTH);

		bSouth = new JButton("Stop");
		panel1.add(bSouth, BorderLayout.SOUTH);

		bEast = new JButton("Übernehmen");
		panel1.add(bEast, BorderLayout.EAST);

		bWest = new JButton("Step");
		panel1.add(bWest, BorderLayout.WEST);

		// panel1.add(new JButton("West"), BorderLayout.WEST);
		String[] options = { "r-Pentomino", "double-u", "Option3", "Option4", "Option15" };
		comboBox = new JComboBox(options);
		panel1.add(comboBox, BorderLayout.CENTER);



	}

	public JButton getbEast() {
		return bEast;
	}

	public JButton getbNorth() {
		return bNorth;
	}

	public JButton getbSouth() {
		return bSouth;
	}

	public JButton getbWest() {
		return bWest;
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public int getCount() {
		return count;
	}

	public MyDrawPanel getDrawPanel() {
		return drawPanel;
	}

	public GameOfLifeService getGameOfLifeService() {
		return gameOfLifeService;
	}

	public JSlider getjSlider() {
		return jSlider;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setbSouth(JButton bSouth) {
		this.bSouth = bSouth;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setDrawpanel(MyDrawPanel drawpanel) {
		this.drawPanel = drawpanel;
	}

	public void setGameOfLifeService(GameOfLifeService gameOfLifeService) {
		this.gameOfLifeService = gameOfLifeService;
		setTitle("Game of Life");
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		getContentPane().add(topPanel);
		// Create the panels
		createPanel1();
		drawPanel = new MyDrawPanel(gameOfLifeService.getArena().getWidth(), gameOfLifeService.getArena().getHeight());
		// Create a splitter pane
		splitPaneV = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		topPanel.add(splitPaneV, BorderLayout.CENTER);	
		jSlider = new JSlider(1, 3, 2);
		// Die Abstände zwischen den
		// Teilmarkierungen werden festgelegt
		jSlider.setMajorTickSpacing(1);
		jSlider.setMinorTickSpacing(1);
		// Standardmarkierungen werden erzeugt
		jSlider.createStandardLabels(1);
		// Zeichnen der Markierungen wird aktiviert
		jSlider.setPaintTicks(true);
		// Zeichnen der Labels wird aktiviert
		jSlider.setPaintLabels(true);
		topPanel.add(jSlider, BorderLayout.SOUTH);
		
		splitPaneH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPaneH.setLeftComponent(panel1);
		splitPaneV.setTopComponent(splitPaneH);
		splitPaneV.setBottomComponent(drawPanel);
		splitPaneV.validate();
		this.pack();
		this.setVisible(true);
		comboBox.addActionListener(new MyActionListener(gameOfLifeService, this));
		bNorth.addActionListener(new MyActionListener(gameOfLifeService, this));
		bSouth.addActionListener(new MyActionListener(gameOfLifeService, this));
		bEast.addActionListener(new MyActionListener(gameOfLifeService, this));
		bWest.addActionListener(new MyActionListener(gameOfLifeService, this));
		//jSlider.addActionListener(new MyActionListener(gameOfLifeService, this);
		timer = new Timer(gameOfLifeService.getDelay(), new MyActionListener(gameOfLifeService, this));
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}
}
