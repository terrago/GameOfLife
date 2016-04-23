package de.terrago.gameOfLive.view;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import de.terrago.gameOfLive.service.GameOfLifeService;

public class MyJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton bNorth;
	public JButton getbNorth() {
		return bNorth;
	}


	public void setbSouth(JButton bSouth) {
		this.bSouth = bSouth;
	}

	private JButton bSouth;
	private int count = 0;
	private DrawPanel drawPanel;
	private GameOfLifeService gameOfLifeService;
	private int height;
	private JPanel panel1;
	private JSplitPane splitPaneH;
	private JSplitPane splitPaneV;
	private int width;
	private JComboBox comboBox;
	
	public MyJFrame(int width, int height) {
		this.width = width;
		this.height = height;
		setTitle("Game of Life");
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		getContentPane().add(topPanel);
		// Create the panels
		createPanel1();
		drawPanel = new DrawPanel(width, height);
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
		this.repaint();
	}

	public void createPanel1() {
		panel1 = new JPanel();
		panel1.setLayout(new BorderLayout());
		// Add buttons
		bNorth = new JButton("Start");
		panel1.add(bNorth, BorderLayout.NORTH);


		bSouth = new JButton("Stop");
		panel1.add(bSouth, BorderLayout.SOUTH);


		panel1.add(new JButton("East"), BorderLayout.EAST);
		panel1.add(new JButton("West"), BorderLayout.WEST);
        String[] options = { "Option1", "Option2", "Option3", "Option4", "Option15" };
        comboBox = new JComboBox(options);

        
		panel1.add(comboBox, BorderLayout.CENTER);

	}

	public JComboBox getComboBox() {
		return comboBox;
	}


	public JButton getbSouth() {
		return bSouth;
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

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setDrawpanel(DrawPanel drawpanel) {
		this.drawPanel = drawpanel;
	}

	public void setGameOfLifeService(GameOfLifeService gameOfLifeService) {
		this.gameOfLifeService = gameOfLifeService;
        comboBox.addActionListener(new MyActionListener(gameOfLifeService,this));
		bNorth.addActionListener(new MyActionListener(gameOfLifeService,this));
		bSouth.addActionListener(new MyActionListener(gameOfLifeService,this));
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public String toString() {
		return "MyJFrame [height=" + height + ", width=" + width + ", bSouth=" + bSouth + ", panel1=" + panel1
				+ ", drawPanel=" + drawPanel + ", splitPaneH=" + splitPaneH + ", splitPaneV=" + splitPaneV + "]";
	}
}
