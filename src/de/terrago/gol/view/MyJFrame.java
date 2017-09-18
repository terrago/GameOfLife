package de.terrago.gol.view;

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
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

import de.terrago.gol.model.Arena;
import de.terrago.gol.service.GameOfLifeService;

public class MyJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton bNorth,bWest;
	private JCheckBox checkBoxTorus;

	private JComboBox<String> comboBox;

	private MyDrawPanel drawPanel;
	private GameOfLifeService gameOfLifeService;
	private JPanel jPanelTextfields;
	private JPanel jScrollPane,panelButtons;
	private JSlider jSliderSize,jSliderSpeed;
	private JTextField jTextfield;
	private JScrollPane jTrueScrollPane;
	private JMenuBar menuBar;
	private JMenu menuFile,menuEdit,menuPreferences;
	private JMenuItem menuItemNew,menuItemBackToLastStart,menuItemImport,menuItemExport,menuItemResizeToFullscreen,menuItemResizeToMinimum;
	private JCheckBoxMenuItem menuItemResizeByOpen;
	private String path;

	private JSplitPane splitPaneH,splitPaneV;

	private Arena startArena;
	private Timer timer;

	public MyJFrame(GameOfLifeService gameOfLifeService) {
		this.gameOfLifeService = gameOfLifeService;
		this.path = "./resources/cellFiles";
		setTitle("Game of Life");
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new BorderLayout());
		getContentPane().add(topPanel);
		createPanelButtons();
		gameOfLifeService.setArena(new Arena(75, 75)); 
		setTitle("Game of Life "+gameOfLifeService.getRule().toString());
		drawPanel = new MyDrawPanel(gameOfLifeService.getArena().getWidth(), gameOfLifeService.getArena().getHeight());
		splitPaneV = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		topPanel.add(splitPaneV, BorderLayout.CENTER);
		jSliderSize = new JSlider(1, 10, 1);
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
		jTrueScrollPane = new JScrollPane(jScrollPane);
		splitPaneV.setBottomComponent(jTrueScrollPane);
		bNorth.addActionListener(new MyActionListener(gameOfLifeService, this));
		bWest.addActionListener(new MyActionListener(gameOfLifeService, this));
		jSliderSize.addChangeListener(new MyActionListener(gameOfLifeService, this));
		jSliderSpeed.addChangeListener(new MyActionListener(gameOfLifeService, this));
		timer = new Timer(10, new MyActionListener(gameOfLifeService, this));
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
		menuPreferences = new JMenu("Preferences");
		menuBar.add(menuFile);
		menuBar.add(menuEdit);
		menuBar.add(menuPreferences);
		menuItemNew = new JMenuItem("New");
		menuItemNew.addActionListener(new MyActionListener(gameOfLifeService, this));
		menuFile.add(menuItemNew);
		menuItemImport = new JMenuItem("Open");
		menuItemImport.addActionListener(new MyActionListener(gameOfLifeService, this) );
		menuFile.add(menuItemImport);
		menuItemExport = new JMenuItem("Save");
		menuItemExport.addActionListener(new MyActionListener(gameOfLifeService, this) );
		menuFile.add(menuItemExport);
		menuItemBackToLastStart = new JMenuItem("Back to last Start");
		menuItemBackToLastStart.addActionListener(new MyActionListener(gameOfLifeService, this));
		menuEdit.add(menuItemBackToLastStart);
		menuItemResizeToFullscreen = new JMenuItem("Enlarge arena to viewport");
		menuItemResizeToFullscreen.addActionListener(new MyActionListener(gameOfLifeService, this));
		menuEdit.add(menuItemResizeToFullscreen);
		menuItemResizeToMinimum = new JMenuItem("Resize arena to minimum");
		menuItemResizeToMinimum.addActionListener(new MyActionListener(gameOfLifeService, this));
		menuEdit.add(menuItemResizeToMinimum);
		menuItemResizeByOpen = new JCheckBoxMenuItem("Enlarge arena to viewport by opening a file");
		menuItemResizeByOpen.addActionListener(new MyActionListener(gameOfLifeService, this));
		menuItemResizeByOpen.setSelected(true);
		menuPreferences.add(menuItemResizeByOpen);
		
		this.setJMenuBar(menuBar);
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
		checkBoxTorus.setText("arena is a torus");
		checkBoxTorus.setSelected(true);
		jSliderSpeed = new JSlider(1, 10, 5);
		jSliderSpeed.setMajorTickSpacing(1);
		jSliderSpeed.setMinorTickSpacing(1);
		jSliderSpeed.createStandardLabels(1);
		jSliderSpeed.setPaintTicks(true);
		jSliderSpeed.setPaintLabels(true);
		jSliderSpeed.setBorder(new TitledBorder("slow motion(speed factor * 0.05 seconds)"));
		jPanelTextfields = new JPanel();
		jPanelTextfields.setLayout(new BorderLayout());
		jTextfield = new JTextField("Points: ");

		jPanelTextfields.add(jTextfield );
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



	public JPanel getjPanelTextfields() {
		return jPanelTextfields;
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
	public JTextField getjTextfield() {
		return jTextfield;
	}

	public JScrollPane getjTrueScrollPane() {
		return jTrueScrollPane;
	}

	public JMenuItem getMenuItemBackToLastStart() {
		return menuItemBackToLastStart;
	}

	public JMenuItem getMenuItemExport() {
		return menuItemExport;
	}

	public JMenuItem getMenuItemImport() {
		return menuItemImport;
	}


	public JMenuItem getMenuItemNew() {
		return menuItemNew;
	}

	public JCheckBoxMenuItem getMenuItemResizeByOpen() {
		return menuItemResizeByOpen;
	}

	public JMenuItem getMenuItemResizeToFullscreen() {
		return menuItemResizeToFullscreen;
	}

	public JMenuItem getMenuItemResizeToMinimum() {
		return menuItemResizeToMinimum;
	}

	public JMenu getMenuPreferences() {
		return menuPreferences;
	}

	public String getPath() {
		return path;
	}

	public Arena getStartArena() {
		return startArena;
	}

	public Timer getTimer() {
		return timer;
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

	public void setArena(Arena arena, int count) {
		this.getDrawPanel().getPoints().clear();
		if (count%10==0){
			this.getjTextfield().setText(" Points: "+arena.getPoints().size());
		}
		this.getjScrollPane().setBackground(Color.GRAY);
		this.getDrawPanel().setBackground(Color.BLACK);
		this.getDrawPanel().setPoints(arena.getPoints());
		this.getbWest().setText("step: " + count);
		this.getDrawPanel().repaint();
	}

	public void setDrawpanel(MyDrawPanel drawpanel) {
		this.drawPanel = drawpanel;
	}

	public void setMenuPreferences(JMenu menuPreferences) {
		this.menuPreferences = menuPreferences;
	}
	
	public void setPath(String path) {
		this.path = path;
	}

	public void setStartArena(Arena startArena) {
		this.startArena = startArena;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}
}
