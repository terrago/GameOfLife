package de.terrago.gameOfLive;
import javax.swing.UIManager;

import de.terrago.gameOfLive.model.Arena;
import de.terrago.gameOfLive.model.Point;
import de.terrago.gameOfLive.service.GameOfLifeService;
import de.terrago.gameOfLive.view.MyJFrame;

public class GameOfLife {

	private static Point[][] points;

	public static void main(String args[]) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (Exception evt) {
		}
		// Initialize Points
		// Blinker
		// points[100][100].setAlife(true);
		// points[100][101].setAlife(true);
		// points[100][102].setAlife(true);

		Arena arena = new Arena(150, 150);

		MyJFrame myJFrame = new MyJFrame();

		GameOfLifeService gameOfLifeService = new GameOfLifeService(arena, myJFrame);
		myJFrame.setGameOfLifeService(gameOfLifeService);
		myJFrame.getDrawPanel().repaint();
	}

}
