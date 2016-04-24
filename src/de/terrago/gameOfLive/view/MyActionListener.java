package de.terrago.gameOfLive.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.terrago.gameOfLive.model.Arena;
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
			Arena arena = new Arena(300, 300);
			String selectedValue =   myJFrame.getComboBox().getSelectedItem().toString();
			switch (selectedValue){
			case"r-Pentomino":
				arena.setPoint(99, 101, true);
				arena.setPoint(100, 100, true);
				arena.setPoint(100, 101, true);
				arena.setPoint(100, 102, true);
				arena.setPoint(101, 100, true);
			break;
			case "double-u":
				arena.setPoint(100, 100, true);
				arena.setPoint(101, 100, true);
				arena.setPoint(102, 100, true);
				arena.setPoint(100, 101, true);				
				arena.setPoint(102, 101, true);
				arena.setPoint(100, 102, true);				
				arena.setPoint(102, 102, true);
				
				arena.setPoint(100, 104, true);				
				arena.setPoint(102, 104, true);
				arena.setPoint(100, 105, true);				
				arena.setPoint(102, 105, true);
				arena.setPoint(100, 106, true);
				arena.setPoint(101, 106, true);
				arena.setPoint(102, 106, true);
				break;
			default:
				arena.setPoint(99, 101, true);
				arena.setPoint(100, 100, true);
				arena.setPoint(100, 101, true);
				arena.setPoint(100, 102, true);
				arena.setPoint(101, 100, true);	
			break;
			}
			myJFrame.setCount(0);
			gameOfLifeService.setArena(arena);
		}
		if (ae.getSource() == myJFrame.getbWest()) {
			gameOfLifeService.setArena(gameOfLifeService.getNextGeneration(gameOfLifeService.getArena()));
		}
		if (ae.getSource() == myJFrame.getTimer()) {
			gameOfLifeService.setArena(gameOfLifeService.getNextGeneration(gameOfLifeService.getArena()));
		}
	}
}
