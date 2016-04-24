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
		Arena arena = new Arena(150, 150, false);
		MyJFrame myJFrame = new MyJFrame();
		GameOfLifeService gameOfLifeService = new GameOfLifeService(arena, myJFrame);
		myJFrame.setGameOfLifeService(gameOfLifeService);
	}
}
