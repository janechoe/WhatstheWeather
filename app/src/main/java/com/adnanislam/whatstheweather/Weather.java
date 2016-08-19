package com.adnanislam.whatstheweather;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

/**
 * Created by Adnan on 8/13/2016.
 */
public class Weather {
    private String url = "http://api.openweathermap.org/data/2.5/weather?q=";
    private String apiKey = "&appid=d5b0a22dbd0b4b2e972b4f2095f7326e";

    private String apiContent;
    private String weatherContent;
    private String name;



    private boolean error;

    public Weather(){
    }

    public void downloadWeather(String city){
        DownloadTask task = new DownloadTask();
        try {
            error = false;
            apiContent = task.execute(url + city + apiKey).get();

            JSONObject jsonObject = new JSONObject(apiContent);

            String weatherInfo = jsonObject.getString("weather");
            name = jsonObject.getString("name");

            JSONArray arr = new JSONArray(weatherInfo);

            for (int i = 0; i< arr.length(); i++){
                JSONObject jsonPart = arr.getJSONObject(i);

                weatherContent = jsonPart.getString("main");
                weatherContent += ": " + jsonPart.getString("description");
            }


        } catch (Exception e) {
            error = true;
            e.printStackTrace();
        }
    }

    public String getApiContent() {
        return apiContent;
    }

    public String getWeatherContent() {
        return weatherContent;
    }

    public String getName() {
        return name;
    }

    public boolean isError() {
        return error;
    }

}
