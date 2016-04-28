package de.terrago.gameOfLive.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import de.terrago.gameOfLive.model.Arena;

public class ImportFileDialog extends JPanel{

	private static final long serialVersionUID = 1L;
	private JTextField jTextField1;
	private JTextField jTextField2;
	private MyDrawPanel myDrawPanel;
	private Arena arena;
	private JPanel jScrollPane;
	
	public ImportFileDialog(){
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
		int sizeFactor = 50/arena.getHeight();
		this.getMyDrawPanel().setSizefactor(sizeFactor);
		this.getMyDrawPanel().setPreferredSize(new Dimension(arena.getWidth()*this.getMyDrawPanel().getSizefactor()
				, arena.getHeight()*this.getMyDrawPanel().getSizefactor()));
		this.getMyDrawPanel().setSize(new Dimension(arena.getWidth()*this.getMyDrawPanel().getSizefactor()
				, arena.getHeight()*this.getMyDrawPanel().getSizefactor()));
		
		this.getMyDrawPanel().getPoints().clear();
		this.getjScrollPane().setBackground(Color.GRAY);
		this.getMyDrawPanel().setBackground(Color.BLACK);
		this.getMyDrawPanel().setPoints(arena.getPoints());
		this.getMyDrawPanel().update(this.getMyDrawPanel().getGraphics());
		this.getjScrollPane().update(this.getjScrollPane().getGraphics());
		this.getMyDrawPanel().repaint();

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
