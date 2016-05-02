package de.terrago.gameOfLive.view;

import java.awt.BorderLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;

import de.terrago.gameOfLive.service.enums.ArenaModifierEnum;

public class NewFileDialog extends JPanel{

	private JComboBox comboBox;
	private JTextField jTextField1;
	private JTextField jTextField2;
	private JTextField jTextFieldRule;
	private MyJFrame myJFrame;

	public NewFileDialog(MyJFrame myJFrame){
		this.setMyJFrame(myJFrame);
		this.setLayout(new BorderLayout());
		JSplitPane splitPaneVTextbox1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		jTextField1 = new JTextField(5);
		jTextField1.setText(Integer.toString(myJFrame.getjScrollPane().getWidth()-20));
		JLabel jLabel1 = new JLabel("width:");
		splitPaneVTextbox1.setLeftComponent(jLabel1);
		splitPaneVTextbox1.setRightComponent(jTextField1);
		
		JSplitPane splitPaneVTextbox2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		jTextField2 = new JTextField(5);
		jTextField2.setText(Integer.toString(myJFrame.getjScrollPane().getHeight()-20));
		JLabel jLabel2 = new JLabel("height:");
		splitPaneVTextbox2.setLeftComponent(jLabel2);
		splitPaneVTextbox2.setRightComponent(jTextField2);
		
		
		JSplitPane splitPaneVTextbox3 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		jTextFieldRule = new JTextField(5);
		jTextFieldRule.setText("23/3");
		JLabel jLabel3 = new JLabel("rule:");
		splitPaneVTextbox3.setLeftComponent(jLabel3);
		splitPaneVTextbox3.setRightComponent(jTextFieldRule);
		
		JSplitPane splitPaneTextFields1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPaneTextFields1.setTopComponent(splitPaneVTextbox1);
		splitPaneTextFields1.setBottomComponent(splitPaneVTextbox2);
		
		JSplitPane splitPaneV = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPaneV.setTopComponent(splitPaneTextFields1);
		
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel(ArenaModifierEnum.values()));
		
		JSplitPane splitPaneTextFields2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPaneTextFields2.setTopComponent(splitPaneVTextbox3);
		splitPaneTextFields2.setBottomComponent(comboBox);
		
		splitPaneV.setTopComponent(splitPaneTextFields1);
		splitPaneV.setBottomComponent(splitPaneTextFields2);
		this.add(splitPaneV);
		this.validate();
		this.setVisible(true);
		this.repaint();
	}

	public JTextField getjTextFieldRule() {
		return jTextFieldRule;
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

	public MyJFrame getMyJFrame() {
		return myJFrame;
	}

	public void setMyJFrame(MyJFrame myJFrame) {
		this.myJFrame = myJFrame;
	}
}
