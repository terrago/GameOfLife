package de.terrago.gameOfLive.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import de.terrago.gameOfLive.model.Arena;
import de.terrago.gameOfLive.service.ArenaModifierService;
import de.terrago.gameOfLive.service.GameOfLifeService;
import de.terrago.gameOfLive.service.enums.ArenaModifierEnum;

public class MyActionListener implements ActionListener, ChangeListener,MouseListener {

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
			if (!myJFrame.getTimer().isRunning()) {
				myJFrame.getTimer().start();
				myJFrame.getbNorth().setText("stop");
			} else {
				myJFrame.getTimer().stop();
				myJFrame.getbNorth().setText("start");
			}
		}
		if (ae.getSource() == myJFrame.getMenuItemOpen()) {
			JFileChooser c = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("GOL-Files", "gol");
			c.setFileFilter(filter);
			int rVal = c.showOpenDialog(myJFrame);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				try {
					FileInputStream streamIn = new FileInputStream(c.getSelectedFile().getPath());
					ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
					@SuppressWarnings("unchecked")
					List<Arena> arenas = (List<Arena>) objectinputstream.readObject();
					Arena arena = arenas.iterator().next();
					gameOfLifeService.setArena(arena);
					myJFrame.setArena(gameOfLifeService.getArena(), 0);
					objectinputstream.close();
				} catch (Exception E) {
				}
			}
		}
		if (ae.getSource() == myJFrame.getMenuItemSave()) {
			JFileChooser c = new JFileChooser();
			FileNameExtensionFilter filter = new FileNameExtensionFilter("GOL-Files", "gol");
			c.setFileFilter(filter);
			int rVal = c.showSaveDialog(myJFrame);
			if (rVal == JFileChooser.APPROVE_OPTION) {
				List<Arena> arenas = new ArrayList<Arena>();
				arenas.add(gameOfLifeService.getArena());
				try {
					FileOutputStream fout = new FileOutputStream(c.getSelectedFile().getPath());
					ObjectOutputStream oos = new ObjectOutputStream(fout);
					oos.writeObject(arenas);
					fout.close();
					oos.close();
				} catch (Exception E) {
				}
			}
		}
		if (ae.getSource() == myJFrame.getMenuItemNew()){
			NewFileDialog newFileDialog = new NewFileDialog();
			int i =JOptionPane.showConfirmDialog(null,newFileDialog,"New Arena",JOptionPane.PLAIN_MESSAGE);
			if (i == 0){
				int width = Integer.parseInt(newFileDialog.getjTextField1().getText());
				int height = Integer.parseInt(newFileDialog.getjTextField2().getText());
				ArenaModifierEnum arenaModifierEnum = (ArenaModifierEnum)newFileDialog.getComboBox().getSelectedItem();
				newArena(width, height, arenaModifierEnum);
			}
		}

		if (ae.getSource() == myJFrame.getbWest()) {
			gameOfLifeService.getArena().setInfinteWorld(myJFrame.getCheckBoxInfinte().isSelected());
			gameOfLifeService.setArena(gameOfLifeService.getNextGeneration(gameOfLifeService.getArena()));
			myJFrame.setArena(gameOfLifeService.getArena(), gameOfLifeService.getCountGeneration());
		}
		if (ae.getSource() == myJFrame.getTimer()) {
			gameOfLifeService.getArena().setInfinteWorld(myJFrame.getCheckBoxInfinte().isSelected());
			gameOfLifeService.setArena(gameOfLifeService.getNextGeneration(gameOfLifeService.getArena()));
			myJFrame.setArena(gameOfLifeService.getArena(), gameOfLifeService.getCountGeneration());
		}

	}

	private void newArena(int width, int height,ArenaModifierEnum arenaModifierEnum){
		myJFrame.getTimer().stop();
		Arena arena = new Arena(width,height);
		arena.setInfinteWorld(myJFrame.getCheckBoxInfinte().isSelected());
		this.startingPointX = arena.getWidth() / 2;
		this.startingPointY = arena.getHeight() / 2;

		switch (arenaModifierEnum) {
		case RPENTOMINO:
			arena = arenaModifierService.getRPentomino(arena, startingPointX, startingPointY);
			break;
		case DOUBLEU:
			arena = arenaModifierService.getDoubleU(arena, startingPointX, startingPointY);
			break;
		case BLINKER:
			arena = arenaModifierService.getBlinker(arena, startingPointX, startingPointY);
			break;
		case LWSS:
			arena = arenaModifierService.getLwss(arena, startingPointX, startingPointY);
			break;
		default:
			break;
		}
		gameOfLifeService.setCountGeneration(0);
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
		myJFrame.setArena(gameOfLifeService.getArena(), gameOfLifeService.getCountGeneration());
		myJFrame.getDrawPanel().update(myJFrame.getDrawPanel().getGraphics());
		myJFrame.getjScrollPane().update(myJFrame.getjScrollPane().getGraphics());
		myJFrame.getDrawPanel().repaint();
		myJFrame.paintAll(myJFrame.getGraphics());
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
			myJFrame.getTimer().setDelay(myJFrame.getjSliderSpeed().getValue() * 50);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent mouseEvent) {
		if (mouseEvent.getSource() == myJFrame.getDrawPanel()){
			int x = mouseEvent.getX()/myJFrame.getDrawPanel().getSizefactor();
			int y = mouseEvent.getY()/myJFrame.getDrawPanel().getSizefactor();
			if (gameOfLifeService.getArena().getPoint(x, y).isAlife()){
				gameOfLifeService.getArena().setPoint(x, y, false);
			}else{
				gameOfLifeService.getArena().setPoint(x, y, true);
			}
			myJFrame.getDrawPanel().repaint();
		}
		
	}
}
