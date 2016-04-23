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

		MyJFrame myJFrame = new MyJFrame(300, 300);

		points = new Point[myJFrame.getWidth()][myJFrame.getHeight()];
		for (int i = 0; i < myJFrame.getHeight(); i++)
			for (int j = 0; j < myJFrame.getWidth(); j++) {
				points[i][j] = new Point(i, j);
			}

		// Initialize Points
		// Blinker
		// points[100][100].setAlife(true);
		// points[100][101].setAlife(true);
		// points[100][102].setAlife(true);

		
		Arena arena = new Arena(300,300);
		arena.setPoint(99, 101,true);
		arena.setPoint(100, 100,true);
		arena.setPoint(100, 101, true);
		arena.setPoint(100, 102, true);
		arena.setPoint(101, 100, true);

		GameOfLifeService gameOfLifeService = new GameOfLifeService(arena, myJFrame);
		myJFrame.setGameOfLifeService(gameOfLifeService);
		for (int i = 0; i < myJFrame.getHeight(); i++)
			for (int j = 0; j < myJFrame.getWidth(); j++) {
				myJFrame.getDrawPanel().getPoints().add(arena.getPoint(i, j));//points[i][j]
			}
		myJFrame.getDrawPanel().repaint();
	}

}
