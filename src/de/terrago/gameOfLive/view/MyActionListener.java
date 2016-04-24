package de.terrago.gameOfLive.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import de.terrago.gameOfLive.model.Arena;
import de.terrago.gameOfLive.service.GameOfLifeService;

public class MyActionListener implements ActionListener {

	private GameOfLifeService gameOfLifeService;
	private MyJFrame myJFrame;
	private int startingPointX;
	private int startingPointY;

	public MyActionListener(GameOfLifeService gameOfLifeService, MyJFrame myJFrame) {
		this.gameOfLifeService = gameOfLifeService;
		this.myJFrame = myJFrame;
		this.startingPointX = gameOfLifeService.getArena().getWidth() / 2;
		this.startingPointY = gameOfLifeService.getArena().getHeight() / 2;
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
//			Arena arena = new Arena(myJFrame.getDrawPanel().getWidth() / myJFrame.getjSlider().getValue(),
//					myJFrame.getDrawPanel().getHeight() / myJFrame.getjSlider().getValue());
			
			Arena arena = new Arena(150,150);

			
			this.startingPointX = arena.getWidth() / 2;
			this.startingPointY = arena.getHeight() / 2;

			String selectedValue = myJFrame.getComboBox().getSelectedItem().toString();
			switch (selectedValue) {
			case "r-Pentomino":
				arena.setPoint(startingPointX - 1, startingPointY + 1, true);
				arena.setPoint(startingPointX + 0, startingPointY + 0, true);
				arena.setPoint(startingPointX + 0, startingPointY + 1, true);
				arena.setPoint(startingPointX + 0, startingPointY + 2, true);
				arena.setPoint(startingPointX + 1, startingPointY + 0, true);
				break;
			case "double-u":
				arena.setPoint(startingPointX + 0, startingPointY + 0, true);
				arena.setPoint(startingPointX + 1, startingPointY + 0, true);
				arena.setPoint(startingPointX + 2, startingPointY + 0, true);
				arena.setPoint(startingPointX + 0, startingPointY + 1, true);
				arena.setPoint(startingPointX + 2, startingPointY + 1, true);
				arena.setPoint(startingPointX + 0, startingPointY + 2, true);
				arena.setPoint(startingPointX + 2, startingPointY + 2, true);
				arena.setPoint(startingPointX + 0, startingPointY + 4, true);
				arena.setPoint(startingPointX + 2, startingPointY + 4, true);
				arena.setPoint(startingPointX + 0, startingPointY + 5, true);
				arena.setPoint(startingPointX + 2, startingPointY + 5, true);
				arena.setPoint(startingPointX + 0, startingPointY + 6, true);
				arena.setPoint(startingPointX + 1, startingPointY + 6, true);
				arena.setPoint(startingPointX + 2, startingPointY + 6, true);
				break;
			default:
				arena.setPoint(startingPointX - 1, startingPointY + 1, true);
				arena.setPoint(startingPointX + 0, startingPointY + 0, true);
				arena.setPoint(startingPointX + 0, startingPointY + 1, true);
				arena.setPoint(startingPointX + 0, startingPointY + 2, true);
				arena.setPoint(startingPointX + 1, startingPointY + 0, true);
				break;
			}
			myJFrame.setCount(0);
			myJFrame.getDrawPanel().setSizefactor(myJFrame.getjSlider().getValue());
			myJFrame.getDrawPanel().setPreferredSize(new Dimension(arena.getWidth()*myJFrame.getjSlider().getValue(),arena.getHeight()*myJFrame.getjSlider().getValue()));
			myJFrame.getDrawPanel().setSize(new Dimension(arena.getWidth()*myJFrame.getjSlider().getValue(),arena.getHeight()*myJFrame.getjSlider().getValue()));
			myJFrame.getDrawPanel().setMaximumSize(new Dimension(arena.getWidth()*myJFrame.getjSlider().getValue(),arena.getHeight()*myJFrame.getjSlider().getValue()));
			myJFrame.getDrawPanel().update(myJFrame.getDrawPanel().getGraphics());
			myJFrame.getjScrollPane().update(myJFrame.getjScrollPane().getGraphics());
			myJFrame.getDrawPanel().repaint();
			myJFrame.paintAll(myJFrame.getGraphics());
			
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
