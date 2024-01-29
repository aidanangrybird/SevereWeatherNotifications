package gg.skyhigh.swn;
import gg.skyhigh.swn.window.mainWindow;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;


public class swnMain implements Runnable {
	
	public static void info() {
		System.out.println("Release Version 1.0");
		System.out.println("Copyright (C) 2024 Sky High Creators. All rights reserved");
	}
	
	public void run() {
		//info();
	}
	
	public static void main(String[] args) throws JsonIOException, JsonSyntaxException, FileNotFoundException, IOException, MalformedURLException, ParseException {
		swnMain main = new swnMain();
		mainWindow window = new mainWindow();
	    main.run();
	    window.createWindow();
	}
	
	//Generally: add a way to add and store locations that you want to receive alerts for
	//Maybe in future, hook into the windows/mac notification things so you actually get a desktop notification for alerts
}
