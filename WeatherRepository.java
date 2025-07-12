package com.example.weatherapp.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.weatherapp.model.WeatherResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherRepository {
    private final WeatherApiService apiService;

    public WeatherRepository() {
        apiService = ApiClient.getClient().create(WeatherApiService.class);
    }

    public LiveData<WeatherResponse> getWeather(String city, String apiKey) {
        MutableLiveData<WeatherResponse> data = new MutableLiveData<>();
        apiService.getWeather(city, apiKey, "metric").enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> resp) {
                data.postValue(resp.body());
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                data.postValue(null);
            }
        });
        return data;
    }
}
