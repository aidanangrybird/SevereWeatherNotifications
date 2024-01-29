package gg.skyhigh.swn.data;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.JsonArray;

import javax.swing.JFormattedTextField;

public class dataHelper {
	public static final ExecutorService EXECUTOR = Executors.newFixedThreadPool(2, new ThreadFactoryBuilder().setNameFormat("swn-skyhigh-gg").setDaemon(true).build());
	private static final String API_URL = "https://api.weather.gov";
	public location featureList;
	
	public URLConnection getAPIData(String url) throws MalformedURLException, IOException {
		String fullURL = API_URL + url;
		URLConnection apiConnection = new URL(fullURL).openConnection();
		apiConnection.setRequestProperty("User-Agent", "swn.skyhigh.gg, aidanpaoletti@ou.edu");
		return apiConnection;
	};
	
	
	public JsonArray getZonesFromCoords(String latitude, String longitude) throws MalformedURLException, IOException {
        try (InputStreamReader apiWeatherGov = new InputStreamReader(getAPIData("/zones?point=" + longitude + "%2C" + latitude).getInputStream());){
        	featureList = (location) new Gson().fromJson((Reader)apiWeatherGov, location.class);
        	JsonArray zoneList = featureList.features;
        	return zoneList;
        }
	};
	
	private class location {
		
		JsonArray features;
		
	};
	
	//Create functions that can deconstruct the api data so each part of the zone or alert can be individually addressed
	//This will be better than having to do the pain of what I had to do in coordinateSelectionHelper
	//Also another part that will need to be done is decontructing the product text in a way where
	//we can pull out coordinates and probabilities
	
	
	//API documentation: https://www.weather.gov/documentation/services-web-api
	//Some important NWS API Product Type Code:
	//WWP: Severe Thunderstorm / Tornado Watch Probabilities
	//PWO: Public Severe Weather Outlook
	//HWO: Hazardous Weather Outlook
	//PTS: Probabilistic Outlook Points (More notes on this below)
	//This is the coordinates and probabilities for each of the convective outlooks.
	//The wmoCollectiveID within each of them is how we would get which day that product is for
	//WUUS01 is day 1 outlook, WUUS02 is day 2 outlook, WUUS03 is day 3 outlook, WUUS48 is days 4-8 outlook
	//SWO: Severe Storm Outlook Narrative (AC)
	//This is the text portion of the convective outlooks
	//SEL: Severe Local Storm Watch and Watch Cancellation Msg
	//The ones below will be handled with the active alerts section of the api:
	//TOR: Tornado Warning
	//SVR: Sever Thunderstorm Warning
	//SVS: Special Weather Statement
	
};