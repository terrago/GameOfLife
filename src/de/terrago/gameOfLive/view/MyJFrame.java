package de.terrago.gameOfLive.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
	private int count = 0;

	private DrawPanel drawPanel;
	private GameOfLifeService gameOfLifeService;
	private JPanel panel1;
	private JSplitPane splitPaneH;
	private JSplitPane splitPaneV;
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
		
		//panel1.add(new JButton("West"), BorderLayout.WEST);
		String[] options = { "Option1", "Option2", "Option3", "Option4", "Option15" };
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

	public DrawPanel getDrawPanel() {
		return drawPanel;
	}

	public GameOfLifeService getGameOfLifeService() {
		return gameOfLifeService;
	}

	public void setbSouth(JButton bSouth) {
		this.bSouth = bSouth;
	}

	public void setCount(int count) {
		this.count = count;
	}
	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public void setDrawpanel(DrawPanel drawpanel) {
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
		drawPanel = new DrawPanel(gameOfLifeService.getArena().getWidth(), gameOfLifeService.getArena().getHeight());
		// Create a splitter pane
		splitPaneV = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		topPanel.add(splitPaneV, BorderLayout.CENTER);
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
		bEast.addActionListener(new MyActionListener(gameOfLifeService,this));
		bWest.addActionListener(new MyActionListener(gameOfLifeService, this));
		timer = new Timer(gameOfLifeService.getDelay(), new MyActionListener(gameOfLifeService, this));
	}
}
