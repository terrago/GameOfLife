package de.terrago.gameOfLive.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import de.terrago.gameOfLive.model.Arena;
import de.terrago.gameOfLive.service.GameOfLifeService;

public class ImportFileDialog extends JPanel{

	private static final long serialVersionUID = 1L;
	private JTextField jTextField1;
	private JTextField jTextField2;
	private MyDrawPanel myDrawPanel;
	private Arena arena;
	private JPanel jScrollPane;
	private GameOfLifeService gameOfLifeService;
	private MyJFrame myJFrame;
	
	public ImportFileDialog(GameOfLifeService gameOfLifeService, MyJFrame myJFrame){
		this.myJFrame = myJFrame;
		this.gameOfLifeService = gameOfLifeService;
		this.setLayout(new BorderLayout());
		
		JSplitPane splitPaneVTextbox1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		jTextField1 = new JTextField(5);
		jTextField1.setText("0");
		JLabel jLabel1 = new JLabel("x-coordinate:");
		splitPaneVTextbox1.setLeftComponent(jLabel1);
		splitPaneVTextbox1.setRightComponent(jTextField1);
		
		JSplitPane splitPaneVTextbox2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		jTextField2 = new JTextField(5);
		jTextField2.setText("0");

		JLabel jLabel2 = new JLabel("y-coordinate:");
		splitPaneVTextbox2.setLeftComponent(jLabel2);
		splitPaneVTextbox2.setRightComponent(jTextField2);
		
		JSplitPane splitPaneH = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPaneH.setTopComponent(splitPaneVTextbox1);
		splitPaneH.setBottomComponent(splitPaneVTextbox2);
		
		JSplitPane splitPaneV = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPaneV.setTopComponent(splitPaneH);
		
		jScrollPane = new JPanel();
		jScrollPane.setLayout(new GridBagLayout());
		
		arena = new Arena(50,50);
		myDrawPanel = new MyDrawPanel(50, 50);
		myDrawPanel.getPoints().clear();
		myDrawPanel.setBackground(Color.BLACK);
		myDrawPanel.setPoints(arena.getPoints());
		myDrawPanel.repaint();
		jScrollPane.add(myDrawPanel);
		
		splitPaneV.setBottomComponent(jScrollPane);
		JSplitPane splitPaneVi = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPaneVi.setTopComponent(splitPaneV);
		JButton openFileButton = new JButton("open .cell File");
		openFileButton.addActionListener(new ImportFileDialogActionListener(this));
		splitPaneVi.setBottomComponent(openFileButton);
		
		this.add(splitPaneVi);
		this.validate();
		this.setVisible(true);
		this.repaint();
	}


	public JPanel getjScrollPane() {
		return jScrollPane;
	}


	public Arena getArena() {
		return arena;
	}


	public void setArena(Arena arena, int count) {
		this.arena = arena;
		
		if (gameOfLifeService.getArena().getWidth() < arena.getWidth() ||
				gameOfLifeService.getArena().getHeight() < arena.getHeight()){
			Arena newArena = new Arena(arena.getWidth(),arena.getHeight());
			gameOfLifeService.setArena(newArena);
			myJFrame.resizeDrawpanel(newArena);
		}
		
		
		int sizeFactor = 50/arena.getHeight();
		
		this.getMyDrawPanel().getPoints().clear();
		this.getjScrollPane().setBackground(Color.GRAY);
		this.getMyDrawPanel().setBackground(Color.BLACK);
		this.getMyDrawPanel().setPoints(arena.getPoints());
		this.getMyDrawPanel().update(this.getMyDrawPanel().getGraphics());
		this.getMyDrawPanel().repaint();
		
		int positionXv = gameOfLifeService.getArena().getWidth()/2-arena.getWidth()/2; 
		int positionYv = gameOfLifeService.getArena().getHeight()/2-arena.getHeight()/2;
		this.jTextField1.setText(Integer.toString(positionXv));
		this.jTextField2.setText(Integer.toString(positionYv));

	}



	public MyDrawPanel getMyDrawPanel() {
		return myDrawPanel;
	}


	public JTextField getjTextField1() {
		return jTextField1;
	}

	public JTextField getjTextField2() {
		return jTextField2;
	}
}
