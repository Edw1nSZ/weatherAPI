package org.adaschool.Weather.service;

import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherReport;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherReportService {

    private static final String API_KEY = "8dcb128d6cfe74b8e3ac56d366eb9ac9";
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";

    private final RestTemplate restTemplate;

    public WeatherReportService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherReport getWeatherReport(double latitude, double longitude) {
        String url = API_URL + "?lat=" + latitude + "&lon=" + longitude + "&appid=" + API_KEY;
        WeatherApiResponse response = restTemplate.getForObject(url, WeatherApiResponse.class);

        WeatherReport report = new WeatherReport();
        if (response != null) {
            WeatherApiResponse.Main main = response.getMain();
            if (main != null) {
                report.setTemperature(main.getTemperature());
                report.setHumidity(main.getHumidity());
            }
        }

        return report;
    }
}
