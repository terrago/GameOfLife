package de.terrago.gameOfLive;

import javax.swing.UIManager;

import de.terrago.gameOfLive.model.Arena;
import de.terrago.gameOfLive.service.GameOfLifeService;
import de.terrago.gameOfLive.view.MyJFrame;

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
