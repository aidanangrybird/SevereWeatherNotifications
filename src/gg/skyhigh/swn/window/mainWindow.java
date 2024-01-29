package gg.skyhigh.swn.window;

import java.text.ParseException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import gg.skyhigh.swn.data.coordinateSelectionHelper;

public class mainWindow {
	//This will be how everything gets rendered
	JFrame window = null;
	
	public void createWindow() throws ParseException {
		JFrame window = new JFrame("Severe Weather Notifications (Copyright (C) 2024 Sky High Creators)");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.pack();
		window.setVisible(true);
		window.setSize(800,500);
		window.setLocation(300, 100);
		
		JPanel panel = new JPanel();
	    window.add(panel);
	    coordinateSelectionHelper coordinateSelection = new coordinateSelectionHelper();
	    coordinateSelection.init(panel);
	    countySelectionHelper countySelection = new countySelectionHelper();
	    countySelection.init(panel);
	}
	
	//Improve the layout of this so that we can display the data we want to
	
};