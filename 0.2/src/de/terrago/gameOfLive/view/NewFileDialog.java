package de.terrago.gameOfLive.view;

import java.awt.BorderLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import de.terrago.gameOfLive.service.GameOfLifeService;
import de.terrago.gameOfLive.service.enums.ArenaModifierEnum;

public class NewFileDialog extends JPanel{

	private JComboBox comboBox;
	private JTextField jTextField1;
	private JTextField jTextField2;

	public NewFileDialog(){
		this.setLayout(new BorderLayout());
		
		JSplitPane splitPaneVTextbox1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		jTextField1 = new JTextField(5);
		jTextField1.setText("150");
		JLabel jLabel1 = new JLabel("width:");
		splitPaneVTextbox1.setLeftComponent(jLabel1);
		splitPaneVTextbox1.setRightComponent(jTextField1);
		
		JSplitPane splitPaneVTextbox2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		jTextField2 = new JTextField(5);
		jTextField2.setText("150");

		JLabel jLabel2 = new JLabel("height:");
		splitPaneVTextbox2.setLeftComponent(jLabel2);
		splitPaneVTextbox2.setRightComponent(jTextField2);
		
		JSplitPane splitPaneH = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPaneH.setTopComponent(splitPaneVTextbox1);
		splitPaneH.setBottomComponent(splitPaneVTextbox2);
		
		JSplitPane splitPaneV = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPaneV.setTopComponent(splitPaneH);
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel(ArenaModifierEnum.values()));
		splitPaneV.setBottomComponent(comboBox);
		this.add(splitPaneV);
		this.validate();
		this.setVisible(true);
		this.repaint();
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public JTextField getjTextField1() {
		return jTextField1;
	}

	public JTextField getjTextField2() {
		return jTextField2;
	}
}
