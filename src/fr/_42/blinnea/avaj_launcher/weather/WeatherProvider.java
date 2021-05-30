package fr._42.blinnea.avaj_launcher.weather;

public class WeatherProvider {
    private static WeatherProvider weatherProvider = null;
    private static final String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

    private WeatherProvider() {
        weatherProvider = new WeatherProvider();

    }

    public WeatherProvider getProvider() {
        if (weatherProvider == null) {
            weatherProvider = new WeatherProvider();
        }
        return weatherProvider;
    }

    public String getCurrentWeather(Coordinates coordinates) {
        // TODO: implement body
        return "None";
    }
}
