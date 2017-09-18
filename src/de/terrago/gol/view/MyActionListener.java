package de.terrago.gol.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import de.terrago.gol.model.Arena;
import de.terrago.gol.model.Point;
import de.terrago.gol.model.Rule;
import de.terrago.gol.service.ArenaService;
import de.terrago.gol.service.GameOfLifeService;
import de.terrago.utils.Tools;

public class MyActionListener implements ActionListener, ChangeListener, MouseListener {

	private GameOfLifeService gameOfLifeService;
	private MyJFrame myJFrame;

	public MyActionListener(GameOfLifeService gameOfLifeService, MyJFrame myJFrame) {
		this.gameOfLifeService = gameOfLifeService;
		this.myJFrame = myJFrame;
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (myJFrame != null) {
			if (ae.getSource() == myJFrame.getbNorth()) {
				if (!myJFrame.getTimer().isRunning()) {
					myJFrame.setStartArena((Arena) Tools.deepCopy(gameOfLifeService.getArena()));
					myJFrame.getTimer().start();
					myJFrame.getbNorth().setText("stop");
				} else {
					myJFrame.getTimer().stop();
					myJFrame.getbNorth().setText("start");
				}
			}

			if (ae.getSource() == myJFrame.getMenuItemNew()) {
				NewFileDialog newFileDialog = new NewFileDialog(myJFrame);
				int i = JOptionPane.showConfirmDialog(myJFrame, newFileDialog, "New Arena", JOptionPane.PLAIN_MESSAGE);
				if (i == 0) {
					int width = Integer.parseInt(newFileDialog.getjTextField1().getText());
					int height = Integer.parseInt(newFileDialog.getjTextField2().getText());
					newArena(width, height);
					Rule rule = new Rule(newFileDialog.getjTextFieldRule().getText());
					gameOfLifeService.setRule(rule);
					myJFrame.setPath("new File *");
					myJFrame.setTitle("Game of Life " + gameOfLifeService.getRule() +" " +myJFrame.getPath());
				}
			}
			if (ae.getSource() == myJFrame.getMenuItemBackToLastStart()) {
				if (myJFrame.getStartArena() != null) {
					gameOfLifeService.setArena(myJFrame.getStartArena());
					gameOfLifeService.setCountGeneration(0);
					myJFrame.setArena(gameOfLifeService.getArena(), gameOfLifeService.getCountGeneration());
				}
			}
			if (ae.getSource() == myJFrame.getMenuItemImport()) {
				importCellFile();
			}
			if (ae.getSource() == myJFrame.getMenuItemExport()) {
				exportCellFile();
			}

			if (ae.getSource() == myJFrame.getbWest()) {
				gameOfLifeService.getArena().setTorusWorld(myJFrame.getCheckBoxTorus().isSelected());
				gameOfLifeService.setArena(gameOfLifeService.getNextGeneration(gameOfLifeService.getArena()));
				myJFrame.setArena(gameOfLifeService.getArena(), gameOfLifeService.getCountGeneration());
			}
			if (ae.getSource() == myJFrame.getTimer()) {
				gameOfLifeService.getArena().setTorusWorld(myJFrame.getCheckBoxTorus().isSelected());
				gameOfLifeService.setArena(gameOfLifeService.getNextGeneration(gameOfLifeService.getArena()));
				myJFrame.setArena(gameOfLifeService.getArena(), gameOfLifeService.getCountGeneration());
			}
			if (ae.getSource() == myJFrame.getMenuItemResizeToFullscreen()) {
				resizeArenaToViewport();
			}

			if (ae.getSource() == myJFrame.getMenuItemResizeToMinimum()) {
				gameOfLifeService.setArena(ArenaService.getMinimalArena(gameOfLifeService.getArena()));
				myJFrame.setArena(gameOfLifeService.getArena(), 0);
				myJFrame.resizeDrawpanel(gameOfLifeService.getArena());
			}
		}

	}

	private void resizeArenaToViewport() {
		Arena arena = new Arena(
				(myJFrame.getjTrueScrollPane().getWidth() - 20) / myJFrame.getDrawPanel().getSizefactor(),
				(myJFrame.getjTrueScrollPane().getHeight() - 20) / myJFrame.getDrawPanel().getSizefactor());
		int startingPointX;
		int startingPointY;
		startingPointX = (arena.getWidth() - gameOfLifeService.getArena().getWidth()) / 2;
		startingPointY = (arena.getHeight() - gameOfLifeService.getArena().getHeight()) / 2;
		for (Point point : gameOfLifeService.getArena().getPoints()) {
			ArenaService.setPoint(arena, point.getX() + startingPointX, point.getY() + startingPointY, true);
		}
		arena.setTorusWorld(myJFrame.getCheckBoxTorus().isSelected());
		gameOfLifeService.setArena(arena);
		myJFrame.setArena(gameOfLifeService.getArena(), gameOfLifeService.getCountGeneration());
		myJFrame.resizeDrawpanel(arena);
	}

	private void exportCellFile() {
		JFileChooser c = new JFileChooser();
		c.setCurrentDirectory(new java.io.File(myJFrame.getPath()));
		c.setSelectedFile(new java.io.File(myJFrame.getPath()));
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CELL-Files", "cells");
		c.setFileFilter(filter);
		BufferedWriter bw = null;
		FileWriter fw = null;
		int rVal = c.showSaveDialog(myJFrame);
		if (rVal == JFileChooser.APPROVE_OPTION) {
			try {
				String filename = c.getSelectedFile().getPath();
				if (!filename.endsWith(".cells")) {
					filename = filename.concat(".cells");
				}
				fw = new FileWriter(filename);
				bw = new BufferedWriter(fw);
				for (int y = 0; y <= gameOfLifeService.getArena().getHeight() - 1; y++) {
					for (int x = 0; x <= gameOfLifeService.getArena().getWidth() - 1; x++) {
						Point point = ArenaService.getPoint(gameOfLifeService.getArena(), x, y);
						if (point.isAlife()) {
							bw.write("O");
						} else {
							bw.write(".");
						}
					}
					bw.newLine();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (bw != null)
						bw.close();
					if (fw != null)
						fw.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	private void importCellFile() {
		JFileChooser c = new JFileChooser();
		c.setCurrentDirectory(new java.io.File("./resources/cellFiles"));
		Action details = c.getActionMap().get("viewTypeDetails");
		details.actionPerformed(null);
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CELL-Files", "cells");
		c.setFileFilter(filter);
		BufferedReader in = null;
		if (c.showOpenDialog(myJFrame) == JFileChooser.APPROVE_OPTION) {
			try {
				myJFrame.setPath(c.getSelectedFile().getPath());
				myJFrame.setTitle("Game of Life " + gameOfLifeService.getRule() +" " +myJFrame.getPath());
				in = new BufferedReader(new FileReader(c.getSelectedFile().getPath()));
				String line = null;
				int lineCounter = 0;
				Set<Point> points = new HashSet<Point>();
				while ((line = in.readLine()) != null) {
					if (!line.startsWith("!")) {
						char[] cline = line.toCharArray();
						int charCounter = 0;
						for (char ch : cline) {
							if (ch == 'O')
								points.add(new Point(charCounter, lineCounter));
							charCounter++;
						}
						lineCounter++;
					}
				}
				in.close();
				int maxX = 0;
				for (Point point : points) {
					if (point.getX() > maxX)
						maxX = point.getX();
				}
				Arena arena = new Arena(maxX + 1, lineCounter);
				for (Point point : points) {
					ArenaService.setPoint(arena, point.getX(), point.getY(), true);
				}
				Arena minimumArena = ArenaService.getMinimalArena(arena);
				int sizeFactor = 0;
				for (int i = 1; i < myJFrame.getjSliderSize().getMaximum()+1; i++) {
					int possibleWidth = (myJFrame.getjTrueScrollPane().getWidth() - 20) / i;
					int possiblehight = (myJFrame.getjTrueScrollPane().getHeight() - 20) / i;
					if (possiblehight>=minimumArena.getHeight()
							&&  possibleWidth >=minimumArena.getWidth())
						sizeFactor = i;
				}
				if (sizeFactor>0) {
					myJFrame.getjSliderSize().setValue(sizeFactor);
				}else {
					myJFrame.getjSliderSize().setValue(1);
				}

				gameOfLifeService.setArena(arena);
				gameOfLifeService.setCountGeneration(0);
				myJFrame.setArena(gameOfLifeService.getArena(), gameOfLifeService.getCountGeneration());
				myJFrame.resizeDrawpanel(arena);
				if (myJFrame.getMenuItemResizeByOpen().isSelected()&& sizeFactor>0) {
					resizeArenaToViewport();
				}
			} catch (IOException ioException) {
				ioException.printStackTrace();
			} finally {
				try {
					if (in != null)
						in.close();
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	}

	private void newArena(int width, int height) {
		myJFrame.getTimer().stop();
		Arena arena = new Arena(width, height);
		arena.setTorusWorld(myJFrame.getCheckBoxTorus().isSelected());
		gameOfLifeService.setCountGeneration(0);
		gameOfLifeService.setArena(arena);
		myJFrame.setArena(gameOfLifeService.getArena(), gameOfLifeService.getCountGeneration());
		myJFrame.resizeDrawpanel(arena);
		myJFrame.getDrawPanel().update(myJFrame.getDrawPanel().getGraphics());
		myJFrame.getjScrollPane().update(myJFrame.getjScrollPane().getGraphics());
		myJFrame.getDrawPanel().repaint();
		myJFrame.paintAll(myJFrame.getGraphics());
	}

	@Override
	public void stateChanged(ChangeEvent ce) {
		if (ce.getSource() == myJFrame.getjSliderSize()) {
			myJFrame.resizeDrawpanel(gameOfLifeService.getArena());
			myJFrame.paintAll(myJFrame.getGraphics());
		}
		if (ce.getSource() == myJFrame.getjSliderSpeed()) {
			myJFrame.getTimer().setDelay(myJFrame.getjSliderSpeed().getValue() * 10);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// is needed by implementation
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// is needed by implementation
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// is needed by implementation
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// is needed by implementation
	}

	@Override
	public void mouseReleased(MouseEvent mouseEvent) {
		if (mouseEvent.getSource() == myJFrame.getDrawPanel()) {
			int x = mouseEvent.getX() / myJFrame.getDrawPanel().getSizefactor();
			int y = mouseEvent.getY() / myJFrame.getDrawPanel().getSizefactor();
			if (ArenaService.getPoint(gameOfLifeService.getArena(), x, y).isAlife()) {
				ArenaService.setPoint(gameOfLifeService.getArena(), x, y, false);
			} else {
				ArenaService.setPoint(gameOfLifeService.getArena(), x, y, true);
			}
			myJFrame.getDrawPanel().setPoints(gameOfLifeService.getPoints());
			myJFrame.getDrawPanel().repaint();
		}
	}
}
