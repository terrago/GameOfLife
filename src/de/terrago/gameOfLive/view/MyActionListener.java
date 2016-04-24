package de.terrago.gameOfLive.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.terrago.gameOfLive.model.Arena;
import de.terrago.gameOfLive.service.ArenaModifierService;
import de.terrago.gameOfLive.service.GameOfLifeService;

public class MyActionListener implements ActionListener, ChangeListener {

	private ArenaModifierService arenaModifierService;
	private GameOfLifeService gameOfLifeService;
	private MyJFrame myJFrame;
	private int startingPointX;
	private int startingPointY;

	public MyActionListener(GameOfLifeService gameOfLifeService, MyJFrame myJFrame) {
		this.gameOfLifeService = gameOfLifeService;
		this.myJFrame = myJFrame;
		this.startingPointX = gameOfLifeService.getArena().getWidth() / 2;
		this.startingPointY = gameOfLifeService.getArena().getHeight() / 2;
		arenaModifierService = new ArenaModifierService();
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
			Arena arena = new Arena((int) Integer.parseInt(myJFrame.getjTextFieldWidth().getText()),
					(int) Integer.parseInt(myJFrame.getjTextFieldHeight().getText()),
					myJFrame.getCheckBoxInfinte().isSelected());
			this.startingPointX = arena.getWidth() / 2;
			this.startingPointY = arena.getHeight() / 2;

			String selectedValue = myJFrame.getComboBox().getSelectedItem().toString();
			switch (selectedValue) {
			case "r-Pentomino":
				arena = arenaModifierService.getRPentomino(arena, startingPointX, startingPointY);
				break;
			case "double-u":
				arena = arenaModifierService.getDoubleU(arena, startingPointX, startingPointY);
				break;
			case "blinker":
				arena = arenaModifierService.getBlinker(arena, startingPointX, startingPointY);
				break;
			case "lwss":
				arena = arenaModifierService.getLwss(arena, startingPointX, startingPointY);
				break;
			default:
				break;
			}
			gameOfLifeService.setCount(0);
			myJFrame.getDrawPanel().setSizefactor(myJFrame.getjSliderSize().getValue());
			myJFrame.getDrawPanel()
					.setPreferredSize(new Dimension(arena.getWidth() * myJFrame.getjSliderSize().getValue(),
							arena.getHeight() * myJFrame.getjSliderSize().getValue()));
			myJFrame.getDrawPanel().setSize(new Dimension(arena.getWidth() * myJFrame.getjSliderSize().getValue(),
					arena.getHeight() * myJFrame.getjSliderSize().getValue()));
			myJFrame.getDrawPanel()
					.setMaximumSize(new Dimension(arena.getWidth() * myJFrame.getjSliderSize().getValue(),
							arena.getHeight() * myJFrame.getjSliderSize().getValue()));
			gameOfLifeService.setArena(arena);
			myJFrame.setArena(gameOfLifeService.getArena(), gameOfLifeService.getCount());
			myJFrame.getDrawPanel().update(myJFrame.getDrawPanel().getGraphics());
			myJFrame.getjScrollPane().update(myJFrame.getjScrollPane().getGraphics());
			myJFrame.getDrawPanel().repaint();
			myJFrame.paintAll(myJFrame.getGraphics());
		}
		if (ae.getSource() == myJFrame.getbWest()) {
			gameOfLifeService.setArena(gameOfLifeService.getNextGeneration(gameOfLifeService.getArena()));
			myJFrame.setArena(gameOfLifeService.getArena(), gameOfLifeService.getCount());
			
		}
		if (ae.getSource() == myJFrame.getTimer()) {
			gameOfLifeService.setArena(gameOfLifeService.getNextGeneration(gameOfLifeService.getArena()));
			myJFrame.setArena(gameOfLifeService.getArena(), gameOfLifeService.getCount());
		}
	}

	@Override
	public void stateChanged(ChangeEvent ce) {
		if (ce.getSource() == myJFrame.getjSliderSize()) {
			myJFrame.getDrawPanel().setSizefactor(myJFrame.getjSliderSize().getValue());
			myJFrame.getDrawPanel()
					.setPreferredSize(new Dimension(
							gameOfLifeService.getArena().getWidth() * myJFrame.getjSliderSize().getValue(),
							gameOfLifeService.getArena().getHeight() * myJFrame.getjSliderSize().getValue()));
			myJFrame.getDrawPanel()
					.setSize(new Dimension(
							gameOfLifeService.getArena().getWidth() * myJFrame.getjSliderSize().getValue(),
							gameOfLifeService.getArena().getHeight() * myJFrame.getjSliderSize().getValue()));
			myJFrame.getDrawPanel()
					.setMaximumSize(new Dimension(
							gameOfLifeService.getArena().getWidth() * myJFrame.getjSliderSize().getValue(),
							gameOfLifeService.getArena().getHeight() * myJFrame.getjSliderSize().getValue()));
			myJFrame.getDrawPanel().update(myJFrame.getDrawPanel().getGraphics());
			myJFrame.getjScrollPane().update(myJFrame.getjScrollPane().getGraphics());
			myJFrame.getDrawPanel().repaint();
			myJFrame.paintAll(myJFrame.getGraphics());
		}
		if (ce.getSource() == myJFrame.getjSliderSpeed()) {
			myJFrame.getTimer().setDelay(myJFrame.getjSliderSpeed().getValue() * 100);
		}
	}
}
