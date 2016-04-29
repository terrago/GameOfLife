package de.terrago.gameOfLive.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

import de.terrago.gameOfLive.model.Arena;
import de.terrago.gameOfLive.service.ArenaModifierService;
import de.terrago.gameOfLive.service.GameOfLifeService;

public class MyJFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private JButton bNorth,bWest;
	private JCheckBox checkBoxTorus;
	private JComboBox<String> comboBox;
	private MyDrawPanel drawPanel;
	private GameOfLifeService gameOfLifeService;
	private JPanel jScrollPane,panelButtons;
	private JSlider jSliderSize,jSliderSpeed;
	private JMenu menuFile,menuEdit;
	private JMenuBar menuBar;
	private JMenuItem menuItemOpen,menuItemSave,menuItemNew,menuItemBackToLastStart
	,menuItemImport;
	private JSplitPane splitPaneH,splitPaneV;
	private Timer timer;
	private Arena startArena;

	public MyJFrame(GameOfLifeService gameOfLifeService) {
		this.gameOfLifeService = gameOfLifeService;
		setTitle("Game of Life");
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		getContentPane().add(topPanel);
		createPanelButtons();
		gameOfLifeService.setArena(new ArenaModifierService().getRPentomino(new Arena(150, 150), 75, 75)); 
		
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
		drawPanel.addMouseListener(new MyActionListener(gameOfLifeService, this));
		jScrollPane.add(drawPanel);
		splitPaneV.setBottomComponent(new JScrollPane(jScrollPane));
		bNorth.addActionListener(new MyActionListener(gameOfLifeService, this));
		bWest.addActionListener(new MyActionListener(gameOfLifeService, this));
		jSliderSize.addChangeListener(new MyActionListener(gameOfLifeService, this));
		jSliderSpeed.addChangeListener(new MyActionListener(gameOfLifeService, this));
		timer = new Timer(50, new MyActionListener(gameOfLifeService, this));
		createMenubar();
		this.setArena(gameOfLifeService.getArena(), gameOfLifeService.getCountGeneration());

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		this.validate();
		this.pack();
		this.setVisible(true);
		this.repaint();
	}
	
	public void createMenubar(){
		menuBar = new JMenuBar();
		menuFile = new JMenu("File");
		menuEdit = new JMenu("Edit");
		menuBar.add(menuFile);
		menuBar.add(menuEdit);
		menuItemNew = new JMenuItem("New");
		menuItemNew.addActionListener(new MyActionListener(gameOfLifeService, this));
		menuFile.add(menuItemNew);
		menuItemOpen = new JMenuItem("Open");
		menuItemOpen.addActionListener(new MyActionListener(gameOfLifeService, this));
		menuFile.add(menuItemOpen);
		menuItemSave = new JMenuItem("Save As");
		menuItemSave.addActionListener(new MyActionListener(gameOfLifeService, this));
		menuFile.add(menuItemSave);
		menuItemImport = new JMenuItem("Import .cell Files");
		menuItemImport.addActionListener(new MyActionListener(gameOfLifeService, this) );
		menuFile.add(menuItemImport);

		menuItemBackToLastStart = new JMenuItem("Back to last Start");
		menuItemBackToLastStart.addActionListener(new MyActionListener(gameOfLifeService, this));
		menuEdit.add(menuItemBackToLastStart);
		this.setJMenuBar(menuBar);
	}

	public JMenuItem getMenuItemImport() {
		return menuItemImport;
	}

	public void setStartArena(Arena startArena) {
		this.startArena = startArena;
	}

	public Arena getStartArena() {
		return startArena;
	}

	public JMenuItem getMenuItemBackToLastStart() {
		return menuItemBackToLastStart;
	}

	public void createPanelButtons() {
		panelButtons = new JPanel();
		panelButtons.setLayout(new BorderLayout());
		bNorth = new JButton("start");
		panelButtons.add(bNorth, BorderLayout.NORTH);
		bWest = new JButton("step");
		panelButtons.add(bWest, BorderLayout.WEST);
		JPanel panelCenter = new JPanel();

		checkBoxTorus = new JCheckBox();
		checkBoxTorus.setText("make arena a torus");
		jSliderSpeed = new JSlider(1, 10, 1);
		jSliderSpeed.setMajorTickSpacing(1);
		jSliderSpeed.setMinorTickSpacing(1);
		jSliderSpeed.createStandardLabels(1);
		jSliderSpeed.setPaintTicks(true);
		jSliderSpeed.setPaintLabels(true);
		jSliderSpeed.setBorder(new TitledBorder("speed factor * 0.05 seconds"));
		JPanel jPanelTextfields = new JPanel();
		jPanelTextfields.setLayout(new BorderLayout());
		panelCenter.setLayout(new BorderLayout());
		panelCenter.add(jPanelTextfields, BorderLayout.EAST);
		panelCenter.add(checkBoxTorus, BorderLayout.WEST);
		panelCenter.add(jSliderSpeed, BorderLayout.SOUTH);
		panelButtons.add(panelCenter, BorderLayout.CENTER);
	}


	public JButton getbNorth() {
		return bNorth;
	}

	public JButton getbWest() {
		return bWest;
	}

	public JCheckBox getCheckBoxTorus() {
		return checkBoxTorus;
	}

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

	public JMenuItem getMenuItemNew() {
		return menuItemNew;
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
		this.getDrawPanel().setBackground(Color.BLACK);
		this.getDrawPanel().setPoints(arena.getPoints());
		this.getbWest().setText("step: " + count);
		this.getDrawPanel().repaint();
	}
	
	public void resizeDrawpanel(Arena arena) {
		this.getDrawPanel().setSizefactor(this.getjSliderSize().getValue());
		this.getDrawPanel()
				.setPreferredSize(new Dimension(arena.getWidth() * this.getjSliderSize().getValue(),
						arena.getHeight() * this.getjSliderSize().getValue()));
		this.getDrawPanel().setSize(new Dimension(arena.getWidth() * this.getjSliderSize().getValue(),
				arena.getHeight() * this.getjSliderSize().getValue()));
		this.getDrawPanel()
				.setMaximumSize(new Dimension(arena.getWidth() * this.getjSliderSize().getValue(),
						arena.getHeight() * this.getjSliderSize().getValue()));
		this.getDrawPanel().update(this.getDrawPanel().getGraphics());
		this.getjScrollPane().update(this.getjScrollPane().getGraphics());
		this.getDrawPanel().repaint();
		this.paintAll(this.getGraphics());
	}

	public void setDrawpanel(MyDrawPanel drawpanel) {
		this.drawPanel = drawpanel;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}
}
