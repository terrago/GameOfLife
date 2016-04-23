package de.terrago.gameOfLive.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.terrago.gameOfLive.service.GameOfLifeService;

public class MyActionListener implements ActionListener {

	private GameOfLifeService gameOfLifeService;
	private MyJFrame myJFrame;

	public MyActionListener(GameOfLifeService gameOfLifeService, MyJFrame myJFrame) {
		this.gameOfLifeService = gameOfLifeService;
		this.myJFrame = myJFrame;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {

		if (ae.getSource() == myJFrame.getbNorth()) {
			gameOfLifeService.getTimer().start();
		}
		if (ae.getSource() == myJFrame.getbSouth()) {
			gameOfLifeService.getTimer().stop();
		}
		if (ae.getSource() == myJFrame.getComboBox()) {

		}
	}
}
