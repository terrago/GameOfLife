package de.terrago.gol;

import javax.swing.UIManager;

import de.terrago.gol.service.GameOfLifeService;
import de.terrago.gol.view.MyJFrame;

public class GameOfLife {

	public static void main(String args[]) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception evt) {
		}
		GameOfLifeService gameOfLifeService = new GameOfLifeService();
		new MyJFrame(gameOfLifeService);
	}
}
