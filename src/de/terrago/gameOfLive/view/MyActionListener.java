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
			myJFrame.getTimer().start();
		}
		if (ae.getSource() == myJFrame.getbSouth()) {
			myJFrame.getTimer().stop();
		}
		if (ae.getSource() == myJFrame.getComboBox()) {

		}
		if (ae.getSource() == myJFrame.getbEast()) {
			myJFrame.getTimer().stop();
			gameOfLifeService.setArena(gameOfLifeService.getArena());
		}
		if (ae.getSource() == myJFrame.getbWest()) {
			gameOfLifeService.setArena(gameOfLifeService.getNextGeneration(gameOfLifeService.getArena()));
		}
		if (ae.getSource() == myJFrame.getTimer()) {
			gameOfLifeService.setArena(gameOfLifeService.getNextGeneration(gameOfLifeService.getArena()));
		}
	}
}
