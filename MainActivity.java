package com.example.weatherapp.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import com.example.weatherapp.R;
import com.example.weatherapp.data.WeatherRepository;
import com.example.weatherapp.model.WeatherResponse;

public class MainActivity extends AppCompatActivity {
    private WeatherRepository repo = new WeatherRepository();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText cityInput = findViewById(R.id.cityInput);
        Button fetchBtn = findViewById(R.id.fetchButton);
        TextView resultTv = findViewById(R.id.resultTextView);

        fetchBtn.setOnClickListener(v -> {
            String city = cityInput.getText().toString();
            repo.getWeather(city, "YOUR_API_KEY").observe(this, new Observer<WeatherResponse>() {
                @Override
                public void onChanged(WeatherResponse resp) {
                    if (resp != null) {
                        String res = "Temp: " + resp.main.temp + "°C\n" +
                                     "Humidity: " + resp.main.humidity + "%\n" +
                                     "Condition: " + resp.weather.get(0).main;
                        resultTv.setText(res);
                    } else {
                        resultTv.setText("Failed to fetch data");
                    }
                }
            });
        });
    }
}
