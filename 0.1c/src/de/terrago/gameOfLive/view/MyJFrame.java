package de.terrago.gameOfLive.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

import de.terrago.gameOfLive.model.Arena;
import de.terrago.gameOfLive.service.GameOfLifeService;

public class MyJFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JButton bEast;
	private JButton bNorth;
	//private JButton bSouth;
	private JButton bWest;
	JCheckBoxMenuItem cbMenuItem;
	private JCheckBox checkBoxInfinte;

	private JComboBox<String> comboBox;
	private MyDrawPanel drawPanel;
	private GameOfLifeService gameOfLifeService;
	private JPanel jScrollPane;
	private JSlider jSliderSize;
	private JSlider jSliderSpeed;
	private JTextField jTextFieldHeight;
	private JTextField jTextFieldWidth;
	JMenu menu;
	JMenuBar menuBar;
	JMenuItem menuItemOpen,menuItemSave;
	
	private JPanel panelButtons;
	JRadioButtonMenuItem rbMenuItem;
	private JSplitPane splitPaneH;
	private JSplitPane splitPaneV;
	private Timer timer;

	public MyJFrame(GameOfLifeService gameOfLifeService) {
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
		//bSouth.addActionListener(new MyActionListener(gameOfLifeService, this));
		bEast.addActionListener(new MyActionListener(gameOfLifeService, this));
		bWest.addActionListener(new MyActionListener(gameOfLifeService, this));
		jSliderSize.addChangeListener(new MyActionListener(gameOfLifeService, this));
		jSliderSpeed.addChangeListener(new MyActionListener(gameOfLifeService, this));
		timer = new Timer(100, new MyActionListener(gameOfLifeService, this));
		createMenubar();

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		this.validate();
		this.pack();
		this.setVisible(true);
		this.repaint();
	}
	
	public void createMenubar(){
		//Create the menu bar.
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_A);
		menu.getAccessibleContext().setAccessibleDescription("File menu");
		menuBar.add(menu);
		menuItemOpen = new JMenuItem("Open");
		menuItemOpen.addActionListener(new MyActionListener(gameOfLifeService, this));
		menu.add(menuItemOpen);
		menuItemSave = new JMenuItem("Save As");
		menuItemSave.addActionListener(new MyActionListener(gameOfLifeService, this));
		menu.add(menuItemSave);
		this.setJMenuBar(menuBar);
	}

	public void createPanelButtons() {
		panelButtons = new JPanel();
		panelButtons.setLayout(new BorderLayout());
		bNorth = new JButton("start");
		panelButtons.add(bNorth, BorderLayout.NORTH);
		//bSouth = new JButton("stop");
		//panelButtons.add(bSouth, BorderLayout.SOUTH);
		bEast = new JButton("take over");
		panelButtons.add(bEast, BorderLayout.EAST);
		bWest = new JButton("step");
		panelButtons.add(bWest, BorderLayout.WEST);
		JPanel panelCenter = new JPanel();
		String[] options = { "r-Pentomino", "double-u", "blinker", "lwss", "Option15" };
		comboBox = new JComboBox<String>(options);
		checkBoxInfinte = new JCheckBox();
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
		panelCenter.add(comboBox, BorderLayout.NORTH);
		panelCenter.add(jPanelTextfields, BorderLayout.EAST);
		panelCenter.add(checkBoxInfinte, BorderLayout.WEST);
		panelCenter.add(jSliderSpeed, BorderLayout.SOUTH);
		panelButtons.add(panelCenter, BorderLayout.CENTER);
	}

	public JButton getbEast() {
		return bEast;
	}

	public JButton getbNorth() {
		return bNorth;
	}

	public JButton getbWest() {
		return bWest;
	}

	public JCheckBox getCheckBoxInfinte() {
		return checkBoxInfinte;
	}

//	public JButton getbSouth() {
//		return bSouth;
//	}

	public JComboBox<String> getComboBox() {
		return comboBox;
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

	public JMenuItem getMenuItemOpen() {
		return menuItemOpen;
	}

	public JMenuItem getMenuItemSave() {
		return menuItemSave;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setArena(Arena arena, int count) {

		this.getDrawPanel().getPoints().clear();
		this.getjScrollPane().setBackground(Color.GRAY);
		this.getDrawPanel().setBackground(Color.WHITE);
		for (int i = 0; i < arena.getHeight(); i++)
			for (int j = 0; j < arena.getWidth(); j++) {
				this.getDrawPanel().getPoints().add(arena.getPoint(j, i));
			}
		this.getDrawPanel().repaint();
		this.getbWest().setText("step: " + count);
		this.getDrawPanel().repaint();
	}

//	public void setbSouth(JButton bSouth) {
//		this.bSouth = bSouth;
//	}

	public void setDrawpanel(MyDrawPanel drawpanel) {
		this.drawPanel = drawpanel;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}
}