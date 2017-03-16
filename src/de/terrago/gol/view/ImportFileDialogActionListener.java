package de.terrago.gol.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import de.terrago.gol.model.Arena;
import de.terrago.gol.model.Point;

public class ImportFileDialogActionListener implements ActionListener {

	private ImportFileDialog importFileDialog;

	public ImportFileDialogActionListener(ImportFileDialog importFileDialog) {
		this.importFileDialog = importFileDialog;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser c = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("CELL-Files", "cells");
		c.setFileFilter(filter);
		int rVal = c.showOpenDialog(importFileDialog);
		if (rVal == JFileChooser.APPROVE_OPTION) {
			try {
				BufferedReader in = null;
				try {
					in = new BufferedReader(new FileReader(c.getSelectedFile().getPath()));
					String line = null;
					int lineCounter = 0;
					Set<Point> points = new HashSet<Point>();
					while ((line = in.readLine()) != null) {
						if(!line.startsWith("!")){
							char[] cline = line.toCharArray();
							int charCounter = 0;
							for (char ch:cline){
								if (ch == 'O')
									points.add(new Point(charCounter,lineCounter));
								charCounter++;
							}
							System.out.println(line);
							lineCounter++;
						}
					}
					
					in.close();
					int maxX = 0;
					for (Point point:points){
						if (point.getX()>maxX)maxX = point.getX();
					}
					Arena arena = new Arena(maxX+1, lineCounter+1);
					for (Point point:points){
						arena.setPoint(point.getX(), point.getY(), true);
					}
					importFileDialog.setArena(arena,0);
					
					
				} catch (IOException ioException) {
					in.close();
					ioException.printStackTrace();
				}

			} catch (Exception E) {
			}
		}

	}

}
