package gg.skyhigh.swn.data;

import gg.skyhigh.swn.data.dataHelper;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;


import com.google.gson.JsonArray;

public class coordinateSelectionHelper {
	
	//public long LONGITUDE;
	//public long LATITUDE;


	public void init(JPanel panel) throws ParseException  {
		//NumberFormatter format = new NumberFormatter();
		//DefaultFormatterFactory coordinateFormat = new DefaultFormatterFactory(format);
		Pattern pattern = Pattern.compile("^(?:([^\\D]*)(-?)(\\d{0,3})\\.(\\d+))$", Pattern.CASE_INSENSITIVE);
		//MaskFormatter coordinates = new MaskFormatter("***************");
		//coordinates.setValidCharacters("1234567890-.");
	    JLabel lbl = new JLabel("Coordinates:");
	    lbl.setVisible(true);
	    panel.add(lbl);
	    JLabel errorLabel = new JLabel("Not valid coordinates!");
	    errorLabel.setVisible(false);
	    panel.add(errorLabel);

	    JLabel countyNameLabel = new JLabel("");
	    countyNameLabel.setVisible(false);
	    panel.add(countyNameLabel);

	    JLabel stateNameLabel = new JLabel("");
	    stateNameLabel.setVisible(false);
	    panel.add(stateNameLabel);

	    JLabel cwaLabel = new JLabel("");
	    cwaLabel.setVisible(false);
	    panel.add(cwaLabel);

	    JLabel radarLabel = new JLabel("");
	    radarLabel.setVisible(false);
	    panel.add(radarLabel);
	    
	    JFormattedTextField longitude = new JFormattedTextField();
	    JFormattedTextField latitude = new JFormattedTextField();
	    panel.add(longitude);
	    panel.add(latitude);
	    longitude.setText("35.2226");
	    latitude.setText("-97.4395");
	    longitude.setVisible(true);
	    latitude.setVisible(true);
	    longitude.setLocation(500,100);
	    latitude.setLocation(500,100);
	    longitude.setSize(80, 40);
	    latitude.setSize(80, 40);
	    
	    JButton goButton = new JButton("Go");
	    panel.add(goButton);
	    goButton.setVisible(true);
	    
	    goButton.addActionListener(action -> {
	    	String currentLongitude = String.valueOf(longitude.getText());
	    	String currentLatitude = String.valueOf(latitude.getText());
			Matcher longitudeMatcher = pattern.matcher(currentLongitude);
			Matcher latitudeMatcher = pattern.matcher(currentLatitude);
			boolean longitudeMatch = longitudeMatcher.find();
			boolean latitudeMatch = latitudeMatcher.find();
			System.out.println(longitudeMatch);
			System.out.println(latitudeMatch);
			
			if (longitudeMatch == true && latitudeMatch == true) {
			    errorLabel.setVisible(false);
		    	System.out.println(currentLongitude);
		    	System.out.println(currentLatitude);
		    	dataHelper latLongHelper = new dataHelper();
		    	try {
					JsonArray zones = latLongHelper.getZonesFromCoords(currentLatitude, currentLongitude);
					countyNameLabel.setText("Those coordinates are in " + zones.get(2).getAsJsonObject().get("properties").getAsJsonObject().get("name").getAsString() + " county");
					stateNameLabel.setText("Those coordinates are in the state of " + zones.get(2).getAsJsonObject().get("properties").getAsJsonObject().get("state").getAsString());
					cwaLabel.setText("Those coordinates are in the forecast area of " + zones.get(2).getAsJsonObject().get("properties").getAsJsonObject().get("cwa") + " NWS office");
					radarLabel.setText("Those coordinates are under the K" + zones.get(2).getAsJsonObject().get("properties").getAsJsonObject().get("radarStation").getAsString() + " radar site");
				    countyNameLabel.setVisible(true);
				    stateNameLabel.setVisible(true);
				    radarLabel.setVisible(true);
				    cwaLabel.setVisible(true);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException f) {
					// TODO Auto-generated catch block
					f.printStackTrace();
				}
			}
	    	
		});
		
	}
}