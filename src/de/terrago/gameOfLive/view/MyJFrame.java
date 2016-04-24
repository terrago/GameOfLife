package de.terrago.gameOfLive.view;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

import de.terrago.gameOfLive.service.GameOfLifeService;

public class MyJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JButton bEast;
	private JButton bNorth;
	private JButton bSouth;
	private JButton bWest;
	private JCheckBox checkBoxInfinte;
	private JComboBox <String>comboBox;
	private int count = 0;
	private MyDrawPanel drawPanel;
	private GameOfLifeService gameOfLifeService;
	private JPanel jScrollPane;
	private JSlider jSliderSize;
	private JSlider jSliderSpeed;
	private JTextField jTextFieldHeight;
	private JTextField jTextFieldWidth;
	private JPanel panelButtons;
	private JSplitPane splitPaneH;
	private JSplitPane splitPaneV;
	private Timer timer;

	public void createPanelButtons() {
		panelButtons = new JPanel();
		panelButtons.setLayout(new BorderLayout());
		bNorth = new JButton("Start");
		panelButtons.add(bNorth, BorderLayout.NORTH);
		bSouth = new JButton("Stop");
		panelButtons.add(bSouth, BorderLayout.SOUTH);
		bEast = new JButton("Übernehmen");
		panelButtons.add(bEast, BorderLayout.EAST);
		bWest = new JButton("Step");
		panelButtons.add(bWest, BorderLayout.WEST);
		JPanel panelCenter = new JPanel(); 
		String[] options = { "r-Pentomino", "double-u", "blinker", "lwss", "Option15" };
		comboBox = new JComboBox<String>(options);
		checkBoxInfinte= new JCheckBox();
		checkBoxInfinte.setText("infinte");
		jSliderSpeed = new JSlider(1, 5, 1);
		jSliderSpeed.setMajorTickSpacing(1);
		jSliderSpeed.setMinorTickSpacing(1);
		jSliderSpeed.createStandardLabels(1);
		jSliderSpeed.setPaintTicks(true);
		jSliderSpeed.setPaintLabels(true);
		jSliderSpeed.setBorder(new TitledBorder("speed factor (0.1 seconds)"));
		JPanel jPanelTextfields = new JPanel();
		jPanelTextfields.setLayout(new BorderLayout());
		jTextFieldHeight = new JTextField(5);
		jTextFieldHeight.setText("100");
		jTextFieldWidth = new JTextField(5);
		jTextFieldWidth.setText("100");
		jPanelTextfields.add(jTextFieldHeight, BorderLayout.NORTH);
		jPanelTextfields.add(jTextFieldWidth, BorderLayout.SOUTH);
		panelCenter.setLayout(new BorderLayout());
		panelCenter.add(comboBox,BorderLayout.NORTH);
		panelCenter.add(jPanelTextfields, BorderLayout.EAST);
		panelCenter.add(checkBoxInfinte,BorderLayout.WEST);
		panelCenter.add(jSliderSpeed, BorderLayout.SOUTH);
		panelButtons.add(panelCenter, BorderLayout.CENTER);
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

	public JCheckBox getCheckBoxInfinte() {
		return checkBoxInfinte;
	}

	public JComboBox<String> getComboBox() {
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

	public JPanel getjScrollPane() {
		return jScrollPane;
	}

	public JSlider getjSliderSize() {
		return jSliderSize;
	}

	public JSlider getjSliderSpeed() {
		return jSliderSpeed;
	}

	public JTextField getjTextFieldHeight() {
		return jTextFieldHeight;
	}

	public JTextField getjTextFieldWidth() {
		return jTextFieldWidth;
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
		createPanelButtons();
		drawPanel = new MyDrawPanel(gameOfLifeService.getArena().getWidth(), gameOfLifeService.getArena().getHeight());
		splitPaneV = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		topPanel.add(splitPaneV, BorderLayout.CENTER);	
		jSliderSize = new JSlider(1, 5, 1);
		jSliderSize.setMajorTickSpacing(1);
		jSliderSize.setMinorTickSpacing(1);
		jSliderSize.createStandardLabels(1);
		jSliderSize.setPaintTicks(true);
		jSliderSize.setPaintLabels(true);
		jSliderSize.setBorder(new TitledBorder("size factor"));
		topPanel.add(jSliderSize, BorderLayout.SOUTH);
		splitPaneH = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		splitPaneH.setLeftComponent(panelButtons);
		splitPaneV.setTopComponent(splitPaneH);
		jScrollPane = new JPanel();
		jScrollPane.setLayout(new GridBagLayout());
		jScrollPane.add(drawPanel);
		splitPaneV.setBottomComponent(new JScrollPane(jScrollPane));
		comboBox.addActionListener(new MyActionListener(gameOfLifeService, this));
		bNorth.addActionListener(new MyActionListener(gameOfLifeService, this));
		bSouth.addActionListener(new MyActionListener(gameOfLifeService, this));
		bEast.addActionListener(new MyActionListener(gameOfLifeService, this));
		bWest.addActionListener(new MyActionListener(gameOfLifeService, this));
	    jSliderSize.addChangeListener(new MyActionListener(gameOfLifeService, this));
	    jSliderSpeed.addChangeListener(new MyActionListener(gameOfLifeService, this));
		timer = new Timer(100, new MyActionListener(gameOfLifeService, this));
		this.validate();
		this.pack();
		this.setVisible(true);
		this.repaint();
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}
}
