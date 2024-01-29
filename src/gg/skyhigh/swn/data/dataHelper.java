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
		
		String type;
		
		JsonArray features;
	};
	
	//Create functions that can deconstruct the api data so each part of the zone or alert can be individually addressed
	
};