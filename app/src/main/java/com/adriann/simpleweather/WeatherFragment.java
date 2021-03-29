package com.adriann.simpleweather;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.net.URL;
import java.util.zip.Inflater;


public class WeatherFragment extends Fragment {

    TextView textView;
    String TAG = "OurDATA";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.weather_fragment, container, false);

        textView = view.findViewById(R.id.text_weather);
        textView.setText("Weather");

        URL url = NetworkUtil.buildURLForWeather();

        new FetchWeatherData().execute(url);


        return view;
    }


    class FetchWeatherData extends AsyncTask<URL,Void,String>
    {

        @Override
        protected String doInBackground(URL... urls) {

            URL weatherURL = urls[0];
            String weatherResult = null;

            try
            {
                weatherResult = NetworkUtil.getResponse(weatherURL);
            }

            catch (IOException e)
            {
                e.printStackTrace();
            }

                Log.i(TAG, "doInBackground" + weatherResult);
            return weatherResult;
        }
    }

}
