package com.adnanislam.whatstheweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText userCity;
    TextView weather;

    Weather myWeather;

    public void submit(View view){
        weather.setVisibility(View.INVISIBLE);

        if (userCity.getText().toString().length() == 0){
            Toast.makeText(getApplicationContext(), "Please enter a valid city name", Toast.LENGTH_SHORT).show();
        }else{
            myWeather.downloadWeather(userCity.getText().toString().replaceAll("\\s", ""));

            if (myWeather.isError()){
                Toast.makeText(getApplicationContext(), "Please enter a valid city name", Toast.LENGTH_SHORT).show();
            }else {
                weather.setText(myWeather.getName() + "\n" + myWeather.getWeatherContent());
                weather.setVisibility(View.VISIBLE);
            }

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userCity = (EditText) findViewById(R.id.userCity);
        weather = (TextView) findViewById(R.id.weather);

        myWeather = new Weather();
    }
}
