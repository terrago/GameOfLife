package de.terrago.gameOfLive.view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.terrago.gameOfLive.service.GameOfLifeService;

public class TimerListener implements ActionListener{

	private GameOfLifeService gameOfLifeService;
	public TimerListener(GameOfLifeService gameOfLifeService) {
		this.gameOfLifeService = gameOfLifeService;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		//gameOfLifeService.setPoints(gameOfLifeService.getNextGeneration(gameOfLifeService.getPoints()));
		gameOfLifeService.setArena(gameOfLifeService.getNextGeneration(gameOfLifeService.getArena()));
	}
	
}